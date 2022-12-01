package pl.xcodesoftware.exchangerate.vendors.nbp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.xcodesoftware.exchangerate.common.exception.NbpDataException$NotFound;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.CurrencyShortcut;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.ExchangeRate;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.xcodesoftware.exchangerate.TestUtil.APPLICATION_JSON_UTF8;
import static pl.xcodesoftware.exchangerate.TestUtil.toJsonString;

@ExtendWith(MockitoExtension.class)
class NbpControllerTest {

    @Mock
    private NbpService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        var controller = new NbpController(service);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Should return ExchangeRate and status ok")
    void should_return_exchange_rate_and_status_ok() throws Exception {
        var currency = new CurrencyShortcut("USD");
        var exchangeRate = new ExchangeRate(BigDecimal.valueOf(4.5432));

        when(service.getExchangeRate(currency)).thenReturn(exchangeRate);

        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(toJsonString(currency)))
                .andExpect(status().isOk())
                .andExpect(content().json(toJsonString(exchangeRate)));

        verify(service).getExchangeRate(currency);
    }

    @Test
    @DisplayName("Should throw exception if CurrencyShortcut currency is bad currency code and return status not found")
    void should_throw_exception_if_currency_shortcut_currency_is_bad_currency_code_and_return_status_not_found() throws Exception {
        var currency = new CurrencyShortcut("USDE");

        doThrow(new NbpDataException$NotFound("Not found data.")).when(service).getExchangeRate(currency);

        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(toJsonString(currency)))
                .andExpect(status().isNotFound());

        verify(service).getExchangeRate(currency);
    }

    @Test
    @DisplayName("Should throw exception if CurrencyShortcut currency is empty string and return status not found")
    void should_throw_exception_if_currency_is_empty_string_and_return_status_not_found() throws Exception {
        var currency = new CurrencyShortcut("");

        mockMvc.perform(post("/numbers/sort-command")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(toJsonString(currency)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should throw exception if CurrencyShortcut is null and return status bad request")
    void should_throw_exception_if_currency_shortcut_is_null_and_return_status_bad_request() throws Exception {
        mockMvc.perform(post("/numbers/sort-command")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(toJsonString(null)))
                .andExpect(status().isNotFound());
    }

}