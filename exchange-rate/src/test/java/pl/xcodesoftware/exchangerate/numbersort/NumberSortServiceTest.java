package pl.xcodesoftware.exchangerate.numbersort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.xcodesoftware.exchangerate.common.exception.IncorrectDataException$UnprocessableEntity;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersSorted;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersToSort;
import pl.xcodesoftware.exchangerate.numbersort.validationpolice.NumbersPoliceStrategy;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static pl.xcodesoftware.exchangerate.numbersort.dto.OrderType.ASC;
import static pl.xcodesoftware.exchangerate.numbersort.dto.OrderType.DESC;

@ExtendWith(MockitoExtension.class)
class NumberSortServiceTest {

    private NumberSortService service;

    @Mock
    private NumbersPoliceStrategy numbersPoliceStrategy;

    private List<Integer> listOfNumbersToSort;

    @BeforeEach
    void setUp() {
        service = new NumberSortService(numbersPoliceStrategy);

        listOfNumbersToSort = List.of(9, 3, 0, -5, 8, 13, 2);
    }

    @Test
    @DisplayName("Should return NumbersSorted with numbers sorted in natural order")
    void should_return_numbers_sorted_with_numbers_sorted_in_natural_order() {
        var numbersToSort = new NumbersToSort(listOfNumbersToSort, ASC);
        var expectedSortedNumbers = new NumbersSorted(List.of(-5, 0, 2, 3, 8, 9, 13));

        doNothing().when(numbersPoliceStrategy).validateOnSort(numbersToSort.getNumbers());

        assertThat(service.getSortedNumbers(numbersToSort))
                .usingRecursiveComparison()
                .isEqualTo(expectedSortedNumbers);
    }

    @Test
    @DisplayName("Should return NumberSorted with numbers sorted in reverse order")
    void should_return_numbers_sorted_with_numbers_sorted_in_reverse_order() {
        var numbersToSort = new NumbersToSort(listOfNumbersToSort, DESC);
        var expectedSortedNumbers = new NumbersSorted(List.of(13, 9, 8, 3, 2, 0, -5));

        doNothing().when(numbersPoliceStrategy).validateOnSort(numbersToSort.getNumbers());

        assertThat(service.getSortedNumbers(numbersToSort))
                .usingRecursiveComparison()
                .isEqualTo(expectedSortedNumbers);
    }

    @Test
    @DisplayName("Should return NumbersSorted with empty list of numbers")
    void should_return_numbers_sorted_with_empty_list_of_numbers() {
        var numbersToSort = new NumbersToSort(EMPTY_LIST, ASC);
        var expectedSortedNumbers = new NumbersSorted(EMPTY_LIST);

        doNothing().when(numbersPoliceStrategy).validateOnSort(numbersToSort.getNumbers());

        assertThat(service.getSortedNumbers(numbersToSort))
                .usingRecursiveComparison()
                .isEqualTo(expectedSortedNumbers);
    }

    @Test
    @DisplayName("Should throw exception if numbers in NumbersToSort is null")
    void should_throw_exception_if_numbers_in_numbers_to_sort_is_null() {
        var numbersToSort = new NumbersToSort(null, ASC);

        doThrow(new IncorrectDataException$UnprocessableEntity("Numbers to sort is null. Cannot be sorted."))
                .when(numbersPoliceStrategy).validateOnSort(numbersToSort.getNumbers());

        assertThatThrownBy(() -> service.getSortedNumbers(numbersToSort))
                .hasMessage("Numbers to sort is null. Cannot be sorted.")
                .isExactlyInstanceOf(IncorrectDataException$UnprocessableEntity.class);
    }

}