package pl.xcodesoftware.exchangerate.numbersort.validationpolice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xcodesoftware.exchangerate.common.exception.IncorrectDataException$UnprecessableEntity;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersToSort;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ValidateNumbers {

    public void validate(NumbersToSort numbersToSort) {
        isNumbersNull(numbersToSort);
    }

    private void isNumbersNull(NumbersToSort numbersToSort) {
        if (Objects.isNull(numbersToSort.getNumbers())) {
            throw new IncorrectDataException$UnprecessableEntity("Numbers to sort is null. Cannot be sorted.");
        }
    }
}
