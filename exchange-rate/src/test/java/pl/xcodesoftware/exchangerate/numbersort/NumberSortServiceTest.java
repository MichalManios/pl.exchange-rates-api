package pl.xcodesoftware.exchangerate.numbersort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.xcodesoftware.exchangerate.common.exception.IncorrectDataException$UnprecessableEntity;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersSorted;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersToSort;
import pl.xcodesoftware.exchangerate.numbersort.dto.OrderType;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class NumberSortServiceTest {

    private NumberSortService service;

    @BeforeEach
    void setUp() {
        service = new NumberSortService();
    }

    @Test
    @DisplayName("Should return NumbersSorted with numbers sorted in natural order")
    void should_return_numbers_sorted_with_numbers_sorted_in_natural_order() {
        var numberToSort = new NumbersToSort(List.of(9, 3, 0, -5, 8, 13, 2), OrderType.ASC);
        var expectedSortedNumbers = new NumbersSorted(List.of(-5, 0, 2, 3, 8, 9, 13));

        assertThat(service.getSortedNumbers(numberToSort))
                .usingRecursiveComparison()
                .isEqualTo(expectedSortedNumbers);
    }

    @Test
    @DisplayName("Should return NumberSorted with numbers sorted in reverse order")
    void should_return_numbers_sorted_with_numbers_sorted_in_reverse_order() {
        var numberToSort = new NumbersToSort(List.of(9, 3, 0, -5, 8, 13, 2), OrderType.DESC);
        var expectedSortedNumbers = new NumbersSorted(List.of(13, 9, 8, 3, 2, 0, -5));

        assertThat(service.getSortedNumbers(numberToSort))
                .usingRecursiveComparison()
                .isEqualTo(expectedSortedNumbers);
    }

    @Test
    @DisplayName("Should return NumbersSorted with empty list of numbers")
    void should_return_numbers_sorted_with_empty_list_of_numbers() {
        var numberToSort = new NumbersToSort(EMPTY_LIST, OrderType.ASC);
        var expectedSortedNumbers = new NumbersSorted(EMPTY_LIST);

        assertThat(service.getSortedNumbers(numberToSort))
                .usingRecursiveComparison()
                .isEqualTo(expectedSortedNumbers);
    }

    @Test
    @DisplayName("Should throw exception if numbers in NumbersToSort is null")
    void throw_exception_if_numbers_in_numbers_to_sort_is_null() {
        var numberToSort = new NumbersToSort(null, OrderType.ASC);

        assertThatThrownBy(() -> service.getSortedNumbers(numberToSort))
                .hasMessage("Numbers to sort is null. Cannot be sorted.")
                .isExactlyInstanceOf(IncorrectDataException$UnprecessableEntity.class);
    }

}