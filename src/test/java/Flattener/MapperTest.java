package Flattener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertEquals;

public class MapperTest {

    FileInputStream Json ;
    FileInputStream Json_Validate ;
    private static ObjectMapper mapper = getDefaultMapper();
    JsonNode node;
    Mapper map;

    @Before
    public void prepareTest() throws IOException {
        map = new Mapper();
    }

    private static ObjectMapper getDefaultMapper(){
        ObjectMapper defaultMapper = new ObjectMapper();
        return defaultMapper;
    }

    @Test
    public void readSimpleJson() throws IOException {
        Json = new FileInputStream("src/test/resources/Input/Json1.txt");
        Json_Validate = new FileInputStream("src/test/resources/Output/Json1_Validate.txt");
        String Json1_str= IOUtils.toString(Json,"UTF-8");
        String Json1_Validate_str = IOUtils.toString(Json_Validate,"UTF-8");
        System.out.println("TC1 Input:-"+Json1_str);
        System.out.println("TC1 Output:-"+map.read(Json1_str));
        assertEquals(Json1_Validate_str,map.read(Json1_str));

    }
    @Test
    public void readLongJson() throws IOException {
        Json = new FileInputStream("src/test/resources/Input/Json4.txt");
        Json_Validate = new FileInputStream("src/test/resources/Output/Json4_Validate.txt");
        String Json4_str= IOUtils.toString(Json,"UTF-8");
        String Json4_Validate_str = IOUtils.toString(Json_Validate,"UTF-8");
        System.out.println("TC2 Input:-"+Json4_str);
        System.out.println("TC2 Output:-"+map.read(Json4_str));
        assertEquals(Json4_Validate_str,map.read(Json4_str));
    }
    @Test
    public void readSimpleNestedJson() throws IOException {

        Json = new FileInputStream("src/test/resources/Input/Json2.txt");
        Json_Validate = new FileInputStream("src/test/resources/Output/Json2_Validate.txt");
        String Json2_str= IOUtils.toString(Json,"UTF-8");
        String Json2_Validate_str = IOUtils.toString(Json_Validate,"UTF-8");
        System.out.println("TC3 Input:-"+Json2_str);
        System.out.println("TC3 Output:-"+map.read(Json2_str));
        assertEquals(Json2_Validate_str,map.read(Json2_str));
    }
    @Test
    public void readNullJson() throws IOException {

        Json = new FileInputStream("src/test/resources/Output/JsonNull_Validate.txt");
        String JsonNull_str= IOUtils.toString(Json,"UTF-8");
        System.out.println("TC4 Input:-"+null);
        System.out.println("TC4 Output:-"+map.read(null));
        assertEquals(JsonNull_str,map.read(null));
    }
    @Test
    public void readInvalidJson() throws IOException {
        String invalidJson="{\"field\":\"value\",}";
        Json = new FileInputStream("src/test/resources/Output/JsonNull_Validate.txt");
        String JsonNull_Validate_str = IOUtils.toString(Json,"UTF-8");
        System.out.println("TC5 Input:-"+null);
        System.out.println("TC5 Output:-"+map.read(null));
        assertEquals(JsonNull_Validate_str,map.read(null));
    }
    @Test
    public void readMultiLevelNestedJson() throws IOException {
        Json = new FileInputStream("src/test/resources/Input/Json3.txt");
        Json_Validate = new FileInputStream("src/test/resources/Output/Json3_Validate.txt");
        String Json3_str =  IOUtils.toString(Json,"UTF-8");
        String Json3_Validate_str = IOUtils.toString(Json_Validate,"UTF-8");
        System.out.println("TC6 Input:-"+Json3_str);
        System.out.println("TC6 Output:-"+map.read(Json3_str));
        assertEquals(Json3_Validate_str,map.read(Json3_str));
    }
    @Test
    public void flattenSimpleField() throws IOException {
        Mapper map = new Mapper();
        String Json1_Field_str = "{\n\"a\":1\n}";
        String value = "\"a.a\":1,\n";
        node = mapper.readTree(Json1_Field_str);
        System.out.println("TC7 Input:-"+Json1_Field_str);
        System.out.println("TC7 Output:-"+map.read(Json1_Field_str));
        assertEquals(value,map.flatten(node.fields(),"a"));
    }
    @Test
    public void flattenNullKey() throws IOException {
        Mapper map = new Mapper();
        String Json1_Field_str = "{\n\"a\":1\n}";
        node = mapper.readTree(Json1_Field_str);
        System.out.println("TC8 Input:-"+Json1_Field_str);
        System.out.println("TC8 Output:-"+map.read(Json1_Field_str));
        assertEquals(Errors.INVALID_JSON,map.flatten(node.fields(),null));
    }
    @Test
    public void flattenEmptyKey() throws IOException {
        Mapper map = new Mapper();
        String Json1_Field_str = "{\n\"a\":1\n}";
        String Json1_Field_output = "\"a\":1,\n";
        node = mapper.readTree(Json1_Field_str);
        System.out.println("TC9 Input:-"+Json1_Field_str);
        System.out.println("TC9 Output:-"+map.flatten(node.fields(),""));
        assertEquals(Json1_Field_output,map.flatten(node.fields(),""));
    }

    @Test
    public void flattenNullNode() throws IOException {
        Mapper map = new Mapper();
        String Json1_Field_str = "{\n\"a\":1\n}";
        node = mapper.readTree(Json1_Field_str);
        System.out.println("TC10 Input:-"+Json1_Field_str);
        System.out.println("TC10 Output:-"+map.read(Json1_Field_str));
        assertEquals(Errors.INVALID_JSON,map.flatten(null,"a"));
    }
}