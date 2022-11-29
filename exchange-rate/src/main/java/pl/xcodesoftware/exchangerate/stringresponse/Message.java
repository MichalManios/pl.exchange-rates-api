package pl.xcodesoftware.exchangerate.stringresponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Message {

    private final static String response = "pong";

    public static String getResponse() {
        return response;
    }

}