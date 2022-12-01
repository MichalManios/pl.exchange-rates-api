package pl.xcodesoftware.exchangerate.numbersort.validationpolice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.xcodesoftware.exchangerate.common.exception.IncorrectDataException$UnprocessableEntity;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NumbersPoliceStrategyTest {

    private NumbersPoliceStrategy numberPoliceStrategy;

    @Mock
    private ValidateNumbers validateNumbers;

    @BeforeEach
    void setUp() {
        numberPoliceStrategy = new NumbersPoliceStrategy(validateNumbers);
    }

    @Test
    @DisplayName("Should pass verification when numbers is not null")
    void should_pass_verification_when_numbers_is_not_null() {
        numberPoliceStrategy.validateOnSort(List.of(9, -2, 3, 2, -5, 11));

        verify(validateNumbers, times(1)).validate(List.of(9, -2, 3, 2, -5, 11));
    }

    @Test
    @DisplayName("Should pass verification when numbers is empty")
    void should_pass_verification_when_numbers_is_empty() {
        numberPoliceStrategy.validateOnSort(EMPTY_LIST);

        verify(validateNumbers, times(1)).validate(EMPTY_LIST);
    }

    @Test
    @DisplayName("Should throw exception if numbers is null")
    void should_throw_exception_if_numbers_is_null() {
        numberPoliceStrategy.validateOnSort(isNull());

        doThrow(new IncorrectDataException$UnprocessableEntity("Numbers to sort is null. Cannot be sorted."))
                .when(validateNumbers).validate(isNull());

        assertThatThrownBy(() -> numberPoliceStrategy.validateOnSort(isNull()))
                .hasMessage("Numbers to sort is null. Cannot be sorted.")
                .isExactlyInstanceOf(IncorrectDataException$UnprocessableEntity.class);
    }

}