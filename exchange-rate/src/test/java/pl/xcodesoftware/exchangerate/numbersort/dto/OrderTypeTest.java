package pl.xcodesoftware.exchangerate.numbersort.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.xcodesoftware.exchangerate.common.exception.IncorrectDataException$UnprecessableEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class OrderTypeTest {

    @Test
    @DisplayName("Should return OrderType.ASC from string ASC")
    void should_return_order_type_asc_from_string_asc() {
        assertThat(OrderType.fromString("ASC")).isEqualTo(OrderType.ASC);
    }

    @Test
    @DisplayName("Should return OrderType.DESC from string DeSC")
    void should_return_order_type_desc_from_string_desc() {
        assertThat(OrderType.fromString("DESC")).isEqualTo(OrderType.DESC);
    }

    @Test
    @DisplayName("Should throw exception if string is not value of OrderType")
    void throw_exception_if_string_is_not_value_of_order_type() {
        assertThatThrownBy(() -> OrderType.fromString("asc"))
                .hasMessage("The word asc is incorrect.")
                .isExactlyInstanceOf(IncorrectDataException$UnprecessableEntity.class);
    }

}