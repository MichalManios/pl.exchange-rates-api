package pl.xcodesoftware.exchangerate.numbersort.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class NumbersToSort extends NumbersSorted{

    @NotNull
    private OrderType order;

    public NumbersToSort(List<Integer> numbers, @NotNull OrderType order) {
        super(numbers);
        this.order = order;
    }

}
