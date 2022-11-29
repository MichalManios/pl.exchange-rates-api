package pl.xcodesoftware.exchangerate.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NbpDataException$NotFound extends RuntimeException {

    public NbpDataException$NotFound(String message) {
        super(message);
    }

}
