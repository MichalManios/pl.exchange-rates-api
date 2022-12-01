package pl.xcodesoftware.exchangerate.numbersort.validationpolice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.xcodesoftware.exchangerate.common.exception.IncorrectDataException$UnprecessableEntity;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ValidateNumbersTest {

    @Test
    @DisplayName("Should pass verification when numbers is not null")
    void should_pass_verification_when_numbers_is_not_null() {
        var validateNumbers = mock(ValidateNumbers.class);

        validateNumbers.validate(List.of(9, -2, 3, 2, -5, 11));

        verify(validateNumbers, times(1)).validate(List.of(9, -2, 3, 2, -5, 11));
    }

    @Test
    @DisplayName("Should pass verification when numbers is empty")
    void should_pass_verification_when_numbers_is_empty() {
        var validateNumbers = mock(ValidateNumbers.class);

        validateNumbers.validate(EMPTY_LIST);

        verify(validateNumbers, times(1)).validate(EMPTY_LIST);
    }

    @Test
    @DisplayName("Should throw exception if numbers is null")
    void should_throw_exception_if_numbers_is_null() {
        var validateNumbers = mock(ValidateNumbers.class);

        doThrow(new IncorrectDataException$UnprecessableEntity("Numbers to sort is null. Cannot be sorted."))
                .when(validateNumbers).validate(null);
    }

}