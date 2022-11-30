package pl.xcodesoftware.exchangerate.numbersort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersSorted;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersToSort;
import pl.xcodesoftware.exchangerate.numbersort.dto.OrderType;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
        var numberToSort = new NumbersToSort(Arrays.asList(9, 3, 0, -5, 8, 13, 2), OrderType.ASC);
        var expectedSortedNumbers = new NumbersSorted(Arrays.asList(-5, 0, 2, 3, 8, 9, 13));

        when(service.getSortedNumbers(numberToSort)).thenReturn(expectedSortedNumbers);

        mockMvc.perform(post("/numbers/sort-command")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(toJsonString(numberToSort)))
                .andExpect(status().isOk())
                .andExpect(content().string(toJsonString(expectedSortedNumbers)));

        verify(service).getSortedNumbers(numberToSort);
    }

}