package pl.xcodesoftware.exchangerate.stringresponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/status/ping", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    @GetMapping
    ResponseEntity<String> getString() {
        return new ResponseEntity<>(Message.getResponse(), HttpStatus.OK);
    }

}
