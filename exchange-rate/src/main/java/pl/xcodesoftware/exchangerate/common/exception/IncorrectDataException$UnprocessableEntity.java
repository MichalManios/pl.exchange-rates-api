package pl.xcodesoftware.exchangerate.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class IncorrectDataException$UnprocessableEntity extends RuntimeException {

    public IncorrectDataException$UnprocessableEntity(String message) {
        super(message);
    }

}
