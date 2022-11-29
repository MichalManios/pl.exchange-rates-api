package pl.xcodesoftware.exchangerate.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import pl.xcodesoftware.exchangerate.numbersort.dto.OrderType;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomEnumDeserializer extends JsonDeserializer<OrderType> {

    @Override
    public OrderType deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {

        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        String name = ((JsonNode) treeNode.get(0)).asText();
        if (name == null) {
            return null;
        }

        return OrderType.valueOf(name);
    }
}