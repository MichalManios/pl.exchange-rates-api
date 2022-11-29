package pl.xcodesoftware.exchangerate.numbersort;

import org.springframework.stereotype.Service;
import pl.xcodesoftware.exchangerate.common.exception.IncorrectDataException$UnprecessableEntity;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersSorted;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersToSort;
import pl.xcodesoftware.exchangerate.numbersort.dto.OrderType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NumberSortService {

    public NumbersSorted getSortedNumbers(NumbersToSort numbersToSort) {
        validateData(numbersToSort);
        return new NumbersSorted(sortNumbers(numbersToSort.getNumbers(), numbersToSort.getOrder()));
    }

    private List<Integer> sortNumbers(List<Integer> numbers, OrderType order) {
        return switch(order) {
            case ASC -> getNumbersInNaturalOrder(numbers);
            case DESC -> getNumbersInReverseOrder(numbers);
        };
    }

    private List<Integer> getNumbersInNaturalOrder(List<Integer> numbers) {
        return numbers.stream().sorted().collect(Collectors.toList());
    }

    private List<Integer> getNumbersInReverseOrder(List<Integer> numbers) {
        return numbers.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
    }

    private void validateData(NumbersToSort numbersToSort) {
        isNumbersNull(numbersToSort);
    }

    private void isNumbersNull(NumbersToSort numbersToSort) {
        if (Objects.isNull(numbersToSort.getNumbers())) {
            throw new IncorrectDataException$UnprecessableEntity("Numbers to sort is null. Cannot be sorted.");
        }
    }

}
