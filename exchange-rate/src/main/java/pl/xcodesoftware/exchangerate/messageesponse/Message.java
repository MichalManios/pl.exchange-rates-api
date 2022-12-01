package pl.xcodesoftware.exchangerate.messageesponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Message {

    private final static String response = "pong";

    public static String getResponse() {
        return response;
    }

}