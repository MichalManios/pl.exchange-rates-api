package pl.xcodesoftware.exchangerate.numbersort;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersSorted;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersToSort;
import pl.xcodesoftware.exchangerate.numbersort.dto.OrderType;
import pl.xcodesoftware.exchangerate.numbersort.validationpolice.NumbersPoliceStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static pl.xcodesoftware.exchangerate.numbersort.dto.OrderType.ASC;

@Service
@AllArgsConstructor
class NumberSortService {

    private final NumbersPoliceStrategy numbersPoliceStrategy;

    public NumbersSorted getSortedNumbers(NumbersToSort numbersToSort) {
        numbersPoliceStrategy.validateOnSort(numbersToSort.getNumbers());
        return new NumbersSorted(sortNumbers(numbersToSort.getNumbers(), numbersToSort.getOrder()));
    }

    private List<Integer> sortNumbers(List<Integer> numbers, OrderType order) {
        return numbers.stream().sorted(compareByOrder(order)).collect(Collectors.toList());
    }

    private Comparator<Integer> compareByOrder(OrderType orderType) {
        return orderType.equals(ASC) ? Comparator.naturalOrder() : Comparator.reverseOrder();
    }

}
