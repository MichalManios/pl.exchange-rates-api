package pl.xcodesoftware.exchangerate.numbersort.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.xcodesoftware.exchangerate.common.util.CustomEnumDeserializer;

@RequiredArgsConstructor
@Getter
@JsonDeserialize(using = CustomEnumDeserializer.class)
public enum OrderType {
    ASC,
    DESC
}
