package pl.xcodesoftware.exchangerate.messageesponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
class Message {

    private final static String response = "pong";

    public static String getResponse() {
        return response;
    }

}