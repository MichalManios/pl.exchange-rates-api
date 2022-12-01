package pl.xcodesoftware.exchangerate.numbersort.validationpolice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.xcodesoftware.exchangerate.common.exception.IncorrectDataException$UnprecessableEntity;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NumbersPoliceStrategyTest {

    @Mock
    private ValidateNumbers validateNumbers;

    @Test
    @DisplayName("Should pass verification when numbers is not null")
    void should_pass_verification_when_numbers_is_not_null() {
        var numberPoliceStrategy = mock(NumbersPoliceStrategy.class);

        numberPoliceStrategy.validateOnSort(List.of(9, -2, 3, 2, -5, 11));

        verify(numberPoliceStrategy, times(1)).validateOnSort(List.of(9, -2, 3, 2, -5, 11));
    }

    @Test
    @DisplayName("Should pass verification when numbers is empty")
    void should_pass_verification_when_numbers_is_empty() {
        var numberPoliceStrategy = mock(NumbersPoliceStrategy.class);

        numberPoliceStrategy.validateOnSort(EMPTY_LIST);

        verify(numberPoliceStrategy, times(1)).validateOnSort(EMPTY_LIST);
    }

    //od tego momentu
    @Test
    @DisplayName("Should throw exception if numbers is null")
    void should_throw_exception_if_numbers_is_null() {
        var numberPoliceStrategy = mock(NumbersPoliceStrategy.class);

        doThrow(new IncorrectDataException$UnprecessableEntity("Numbers to sort is null. Cannot be sorted."))
                .when(validateNumbers).validate(null);

        doThrow(new IncorrectDataException$UnprecessableEntity("Numbers to sort is null. Cannot be sorted."))
                .when(numberPoliceStrategy).validateOnSort(null);
    }

}