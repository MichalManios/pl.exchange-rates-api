package pl.xcodesoftware.exchangerate.numbersort.validationpolice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.xcodesoftware.exchangerate.numbersort.dto.NumbersToSort;

@Service
@RequiredArgsConstructor
public class NumbersPoliceStrategy {

    private final ValidateNumbers validateNumbers;

    public void validateOnSort(NumbersToSort numbersToSort) {
        validateNumbers.validate(numbersToSort);
    }
}
