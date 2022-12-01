package pl.xcodesoftware.exchangerate.vendors.nbp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.CurrencyShortcut;
import pl.xcodesoftware.exchangerate.vendors.nbp.dto.ExchangeRate;

@RestController
@RequestMapping(value = "/currencies/get-current-currency-value-command", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
class NbpController {

    private final NbpService service;

    @PostMapping
    public ResponseEntity<ExchangeRate> add(@RequestBody CurrencyShortcut currencyShortcut) {
        return new ResponseEntity<>(service.getExchangeRate(currencyShortcut), HttpStatus.OK);
    }

}
