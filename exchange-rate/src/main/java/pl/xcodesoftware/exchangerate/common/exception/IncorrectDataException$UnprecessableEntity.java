package pl.xcodesoftware.exchangerate.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class IncorrectDataException$UnprecessableEntity extends RuntimeException {

    public IncorrectDataException$UnprecessableEntity(String message) {
        super(message);
    }

}
