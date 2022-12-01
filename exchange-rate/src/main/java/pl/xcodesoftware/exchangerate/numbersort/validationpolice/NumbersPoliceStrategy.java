package pl.xcodesoftware.exchangerate.numbersort.validationpolice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NumbersPoliceStrategy {

    private final ValidateNumbers validateNumbers;

    public void validateOnSort(List<Integer> numbers) {
        validateNumbers.validate(numbers);
    }

}
