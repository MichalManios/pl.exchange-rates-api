package pl.xcodesoftware.exchangerate.numbersort.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NumbersToSort extends NumbersSorted{

    @NotNull
    private OrderType order;
}
