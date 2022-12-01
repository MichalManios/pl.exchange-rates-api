package pl.xcodesoftware.exchangerate.messageesponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MessageTest {

    @Test
    @DisplayName("Should return string equal pong")
    void should_return_string_equal_pong() {
        assertThat(Message.getResponse()).isEqualTo("pong");
    }
}