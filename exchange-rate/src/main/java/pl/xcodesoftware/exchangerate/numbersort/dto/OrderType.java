package pl.xcodesoftware.exchangerate.numbersort.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import pl.xcodesoftware.exchangerate.common.exception.IncorrectDataException$UnprecessableEntity;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum OrderType {

    ASC("ASC"),
    DESC("DESC");

    private static final Map<String, OrderType> FORMAT_MAP = Stream.of(OrderType.values())
            .collect(Collectors.toMap(s -> s.formatted, Function.identity()));

    private final String formatted;

    OrderType(String formatted) { this.formatted = formatted; }

    @JsonCreator
    public static OrderType fromString(String string) {
        return Optional.ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IncorrectDataException$UnprecessableEntity(String.format("The word %1$s is incorrect.", string)));
    }

}
