package Flattener;

import Util.Format;
import java.util.Map;
import java.util.Iterator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Mapper {
    private static ObjectMapper mapper = getDefaultMapper();

    private static ObjectMapper getDefaultMapper() {
        ObjectMapper defaultMapper = new ObjectMapper();
        return defaultMapper;
    }

    public String read(String Json) throws JsonProcessingException {
        if (Json == null || Json.isEmpty()) {
            return Format.formatError(Errors.INVALID_JSON);
        }
        StringBuilder flattenedString = new StringBuilder();
        flattenedString.append(FormatterValues.BEGIN_BRACES + FormatterValues.NEW_LINE);
        JsonNode node = mapper.readTree(Json);
        flattenedString.append(flatten(node.fields(),""));
        flattenedString = new StringBuilder(Format.removeTrailingChar(flattenedString.toString(), FormatterValues.FIELD_TERMINATOR));
        return Format.validateFlattendedJson(flattenedString.toString());
    }

    public String flatten(Iterator<Map.Entry<String, JsonNode>> field, String parentKey){
        if (parentKey == null || field == null) {
            return Errors.INVALID_JSON;
        }
        StringBuilder flattenedString = new StringBuilder();
        while (field.hasNext()) {
            Map.Entry<String, JsonNode> jsonField = field.next();
            if (jsonField.getValue().getNodeType() == JsonNodeType.OBJECT) {
                flattenedString.append(flatten(jsonField.getValue().fields(), Format.formatKey(parentKey,jsonField.getKey())));
            }else if (jsonField.getValue().getNodeType().equals(JsonNodeType.ARRAY)) {
                return Format.formatError(Errors.ARRAYS_NOT_SUPPORTED);
            }else {
                flattenedString.append(Format.formatJsonFields(Format.formatKey(parentKey,jsonField.getKey()), jsonField.getValue()));
            }
        }
        return flattenedString.toString();
    }
}
