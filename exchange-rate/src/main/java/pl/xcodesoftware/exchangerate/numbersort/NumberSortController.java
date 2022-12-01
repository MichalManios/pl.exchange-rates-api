package pl.xcodesoftware.exchangerate.numbersort;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersSorted;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersToSort;

@RestController
@RequestMapping(value = "/numbers/sort-command", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
class NumberSortController {

    private final NumberSortService service;

    @PostMapping
    public ResponseEntity<NumbersSorted> getSortedNumbers(@RequestBody NumbersToSort numbersToSort) {
        return new ResponseEntity<>(service.getSortedNumbers(numbersToSort), HttpStatus.OK);
    }

}
