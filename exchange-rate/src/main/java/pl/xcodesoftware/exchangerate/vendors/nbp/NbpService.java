package pl.xcodesoftware.exchangerate.vendors.nbp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xcodesoftware.exchangerate.common.exception.NbpDataException$NotFound;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.CurrencyShortcut;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.ExchangeRate;

@Service
@AllArgsConstructor
class NbpService {

    private final NbpClient nbpClient;

    public ExchangeRate getExchangeRate(CurrencyShortcut currencyShortcut) {
        return nbpClient.getNbpExchangeRate(currencyShortcut).getRates()
                .stream()
                .findFirst()
                .map(rate -> new ExchangeRate(rate.getMid()))
                .orElseThrow(() -> new NbpDataException$NotFound("Not found data."));
    }

}
