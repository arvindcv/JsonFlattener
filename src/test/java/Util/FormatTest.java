package Util;

import Flattener.Errors;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;

public class FormatTest extends TestCase {
    JsonNode node;
    private static ObjectMapper mapper = getDefaultMapper();
    FileInputStream fip ;
    String json;
    @Before
    public void prepareTest() throws IOException {
        fip = new FileInputStream("src/test/resources/Input/Json1.txt");
        json = IOUtils.toString(fip,"UTF-8");
        node = mapper.readTree(json);
    }
    private static ObjectMapper getDefaultMapper(){
        ObjectMapper defaultMapper = new ObjectMapper();
        return defaultMapper;
    }

    @Test
    public void testFormatErrorValidValue() {
        String returnValue = "{\n" +
                "\"error\":\"JSON Arrays are not supported\"\n" +
                "}";
        assertEquals(returnValue,Format.formatError(Errors.ARRAYS_NOT_SUPPORTED));

    }
    @Test
    public void testFormatNullValue() {
        assertEquals(null,Format.formatError(null));
    }
    @Test
    public void testFormatEmptyValue() {
        assertEquals(null,Format.formatError(""));
    }
    @Test
    public void testFormatJsonFieldsValidValue() throws IOException {
        prepareTest();
        String retVal = "\"key\":{\"a\":1,\"b\":true,\"c\":{\"d\":3,\"e\":\"test\"}},\n";
        assertEquals(retVal,Format.formatJsonFields("key",node));
    }
    @Test
    public void testFormatJsonFieldsEmptyValidValue() throws IOException {
        prepareTest();
        String retVal = "\"\":{\"a\":1,\"b\":true,\"c\":{\"d\":3,\"e\":\"test\"}},\n";
        assertEquals(retVal,Format.formatJsonFields("",node));
    }
    @Test
    public void testFormatJsonFieldsEmptyNullValue() {
        assertEquals(null,Format.formatJsonFields("",null));
    }
    @Test
    public void testFormatJsonFieldsNullNullValue() {
        assertEquals(null,Format.formatJsonFields(null,null));
    }
    @Test
    public void testRemoveTrailingCharInValidPattern() {
        assertEquals("text,",Format.removeTrailingChar("text,",","));
    }
    @Test
    public void testRemoveTrailingCharValidPattern() {
        assertEquals("{text\n}",Format.removeTrailingChar("{text,\n",","));
    }
    @Test
    public void testRemoveTrailingCharNullNull() {
        assertEquals(null,Format.removeTrailingChar(null,null));
    }
    @Test
    public void testRemoveTrailingCharNullValid() {
        assertEquals(null,Format.removeTrailingChar(null,","));
    }
    @Test
    public void testRemoveTrailingCharValidNull() {
        assertEquals("{test,\n",Format.removeTrailingChar("{test,\n",null));
    }
    @Test
    public void testRemoveTrailingCharValidEmpty() {
        assertEquals("{test,\n",Format.removeTrailingChar("{test,\n",""));
    }
    @Test
    public void testRemoveTrailingCharEmptyEmpty() {
        assertEquals(null,Format.removeTrailingChar("",""));
    }
    @Test
    public void testValidateFlattendedJsonValid() {
        String json = "\"\":{\"a\":1,\"b\":true,\"c\":{\"d\":3,\"e\":\"test\"}},\n";
        assertEquals(json,Format.validateFlattendedJson(json));
    }
    @Test
    public void testValidateFlattendedJsonNull() {
        String json = "\"\":{\"a\":1,\"b\":true,\"c\":{\"d\":3,\"e\":\"test\"}},\n";
        assertEquals(null,Format.validateFlattendedJson(null));
    }
    @Test
    public void testValidateFlattendedJsonEmpty() {
        String json = "\"\":{\"a\":1,\"b\":true,\"c\":{\"d\":3,\"e\":\"test\"}},\n";
        assertEquals(null,Format.validateFlattendedJson(""));
    }
    @Test
    public void testValidateFlattendedJsonValidPattern() {
        String json = "\"\":{\"a\":1,\"b\":true,\"c\":{\"d\":3,\"e\":\"test\"}},\n"+Errors.ARRAYS_NOT_SUPPORTED;
        assertEquals(Format.formatError(Errors.ARRAYS_NOT_SUPPORTED),Format.validateFlattendedJson(json));
    }
    @Test
    public void testFormatKeyValid() {
        assertEquals("key.value",Format.formatKey("key","value"));
    }
    @Test
    public void testFormatKeyNullNull() {
        assertEquals(null,Format.formatKey(null,null));
    }
    @Test
    public void testFormatKeyNullValid() {
        assertEquals(null,Format.formatKey(null,"value"));
    }
    @Test
    public void testFormatKeyValidNull() {
        assertEquals(null,Format.formatKey("key",null));
    }
    @Test
    public void testFormatKeyEmptyEmpty() {
        assertEquals("",Format.formatKey("",""));
    }
}