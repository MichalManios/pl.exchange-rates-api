package pl.xcodesoftware.exchangerate.vendors.nbp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.xcodesoftware.exchangerate.common.exception.NbpDataException$BadRequest;
import pl.xcodesoftware.exchangerate.common.exception.NbpDataException$NotFound;
import pl.xcodesoftware.exchangerate.common.exception.NbpDataException$ServiceUnavailable;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.CurrencyShortcut;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.NbpExchangeRate;

@Service
@Slf4j
class NbpClient {

    private final String URI_NBP = "https://api.nbp.pl/api/exchangerates/rates/A/";

    private final String NOT_FOUND = "Not Found";

    private final String BAD_REQUEST = "Bad Request";

    private final String BAD_REQUEST_LIMIT = "Bad Request - Przekroczony limit";

    public NbpExchangeRate getNbpExchangeRate(CurrencyShortcut currencyShortcut) {
        try {

            RestTemplate restTemplate = new RestTemplate();

            return restTemplate.getForObject(
                    String.format("%1$s%2$s", URI_NBP, currencyShortcut.getCurrency()), NbpExchangeRate.class);

        } catch (HttpClientErrorException e) {

            log.error(e.getMessage());
            switch(e.getStatusText()) {
                case NOT_FOUND -> throw new NbpDataException$NotFound("Not found data.");
                case BAD_REQUEST -> throw new NbpDataException$BadRequest("Incorrectly worded query.");
                case BAD_REQUEST_LIMIT -> throw new NbpDataException$BadRequest("Query exceeding the limit of data returned.");
                default -> throw new NbpDataException$ServiceUnavailable("NBP database connection error.");
            }

        } catch (Exception e) {

            log.error(e.getMessage());
            throw new NbpDataException$ServiceUnavailable("NBP database connection error.");

        }
    }

}
