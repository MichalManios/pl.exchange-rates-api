package pl.xcodesoftware.exchangerate.numbersort.validationpolice;

import org.springframework.stereotype.Service;
import pl.xcodesoftware.exchangerate.common.exception.IncorrectDataException$UnprecessableEntity;

import java.util.List;
import java.util.Objects;

@Service
class ValidateNumbers {

    public void validate(List<Integer> numbers) {
        isNumbersNull(numbers);
    }

    private void isNumbersNull(List<Integer> numbers) {
        if (Objects.isNull(numbers)) {
            throw new IncorrectDataException$UnprecessableEntity("Numbers to sort is null. Cannot be sorted.");
        }
    }
}
