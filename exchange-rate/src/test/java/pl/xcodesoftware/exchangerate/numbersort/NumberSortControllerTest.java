package pl.xcodesoftware.exchangerate.numbersort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.xcodesoftware.exchangerate.common.exception.IncorrectDataException$UnprecessableEntity;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersSorted;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersToSort;
import pl.xcodesoftware.exchangerate.numbersort.dto.OrderType;

import java.util.Arrays;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.xcodesoftware.exchangerate.TestUtil.APPLICATION_JSON_UTF8;
import static pl.xcodesoftware.exchangerate.TestUtil.toJsonString;


@ExtendWith(MockitoExtension.class)
class NumberSortControllerTest {

    @Mock
    private NumberSortService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        var controller = new NumberSortController(service);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Should return NumbersSorted with numbers sorted in natural order and status ok")
    void should_return_numbers_sorted_with_numbers_sorted_in_natural_order_and_status_ok() throws Exception {
        var numbersToSort = new NumbersToSort(Arrays.asList(9, 3, 0, -5, 8, 13, 2), OrderType.ASC);
        var expectedSortedNumbers = new NumbersSorted(Arrays.asList(-5, 0, 2, 3, 8, 9, 13));

        when(service.getSortedNumbers(numbersToSort)).thenReturn(expectedSortedNumbers);

        mockMvc.perform(post("/numbers/sort-command")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(toJsonString(numbersToSort)))
                .andExpect(status().isOk())
                .andExpect(content().json(toJsonString(expectedSortedNumbers)));

        verify(service).getSortedNumbers(numbersToSort);
    }

    @Test
    @DisplayName("Should return NumbersSorted with numbers sorted in reverse order and status ok")
    void should_return_numbers_sorted_with_numbers_sorted_in_reverse_order_and_status_ok() throws Exception {
        var numbersToSort = new NumbersToSort(Arrays.asList(9, 3, 0, -5, 8, 13, 2), OrderType.ASC);
        var expectedSortedNumbers = new NumbersSorted(Arrays.asList(13, 9, 8, 3, 2, 0, -5));

        when(service.getSortedNumbers(numbersToSort)).thenReturn(expectedSortedNumbers);

        mockMvc.perform(post("/numbers/sort-command")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(toJsonString(numbersToSort)))
                .andExpect(status().isOk())
                .andExpect(content().json(toJsonString(expectedSortedNumbers)));

        verify(service).getSortedNumbers(numbersToSort);
    }

    @Test
    @DisplayName("Should return NumbersSorted with empty list of numbers and status ok")
    void should_return_numbers_sorted_with_empty_list_and_status_ok() throws Exception {
        var numbersToSort = new NumbersToSort(EMPTY_LIST, OrderType.ASC);
        var expectedSortedNumbers = new NumbersSorted(EMPTY_LIST);

        when(service.getSortedNumbers(numbersToSort)).thenReturn(expectedSortedNumbers);

        mockMvc.perform(post("/numbers/sort-command")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(toJsonString(numbersToSort)))
                .andExpect(status().isOk())
                .andExpect(content().json(toJsonString(expectedSortedNumbers)));

        verify(service).getSortedNumbers(numbersToSort);
    }

    @Test
    @DisplayName("Should throw exception if numbers in NumbersToSort is null and status UnprocessableEntity")
    void should_throw_exception_if_numbers_in_numbers_to_sort_is_null_and_return_unprecessable_entity() throws Exception {
        var numbersToSort = new NumbersToSort(null, OrderType.ASC);

        doThrow(new IncorrectDataException$UnprecessableEntity("Numbers to sort is null. Cannot be sorted."))
                .when(service).getSortedNumbers(numbersToSort);

        mockMvc.perform(post("/numbers/sort-command")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(toJsonString(numbersToSort)))
                .andExpect(status().isUnprocessableEntity());

        verify(service).getSortedNumbers(numbersToSort);
    }

}