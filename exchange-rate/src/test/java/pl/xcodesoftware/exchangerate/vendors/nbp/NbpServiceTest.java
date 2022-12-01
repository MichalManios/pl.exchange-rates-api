package pl.xcodesoftware.exchangerate.vendors.nbp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.xcodesoftware.exchangerate.common.exception.NbpDataException$NotFound;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.CurrencyShortcut;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.ExchangeRate;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.NbpExchangeRate;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.Rate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NbpServiceTest {

    @Mock
    private NbpClient nbpClient;

    private NbpService service;

    @BeforeEach
    void setUp() {
        service = new NbpService(nbpClient);
    }

    @Test
    @DisplayName("Should return NbpExchangeRate")
    void should_return_nbp_exchange_rate() {
        var currencyShortcut = new CurrencyShortcut("USD");

        var rate = new Rate();
        rate.setEffectiveDate(LocalDate.of(2022,11,30));
        rate.setMid(BigDecimal.valueOf(4.5066));
        rate.setNo("231/A/NBP/2022");

        var resultFromNBP = new NbpExchangeRate();
        resultFromNBP.setCode("USD");
        resultFromNBP.setCurrency("dolar amerykański");
        resultFromNBP.setTable("A");
        resultFromNBP.setRates(List.of(rate));

        var expected = new ExchangeRate(BigDecimal.valueOf(4.5066));

        when(nbpClient.getNbpExchangeRate(currencyShortcut)).thenReturn(resultFromNBP);

        assertThat(service.getExchangeRate(currencyShortcut))
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Should throw exception if not found")
    void should_throw_exception_if_not_found() {
        var currencyShortcut = mock(CurrencyShortcut.class);
        var nbpExchangeRate = mock(NbpExchangeRate.class);

        when(nbpClient.getNbpExchangeRate(currencyShortcut)).thenReturn(nbpExchangeRate);
        when(nbpExchangeRate.getRates()).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> service.getExchangeRate(currencyShortcut))
                .isInstanceOf(NbpDataException$NotFound.class)
                .hasMessage("Not found data.");
    }

}