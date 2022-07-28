import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class TestDataHandler {
    public static JSONObject getArray(String jsonarray) throws IOException, ParseException {
        FileReader reader = new FileReader("src/test/java/TestData.json");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(reader);
        JSONObject jsonobj = (JSONObject) obj;
        JSONArray tags = (JSONArray) jsonobj.get(jsonarray);
        return (JSONObject) tags.get(0);
    }
    public static String[] getRegistrationInputs() throws IOException, ParseException {
        String[] result = new String[6];
        result[0] = (String) getArray("RegistrationForm").get("Field_1");
        result[1] = (String) getArray("RegistrationForm").get("Field_2");
        result[2] = (String) getArray("RegistrationForm").get("Field_3");
        result[3] = (String) getArray("RegistrationForm").get("Field_4");
        result[4] = (String) getArray("RegistrationForm").get("Field_5");
        result[5] = (String) getArray("RegistrationForm").get("Field_6");
        return result;
    }
}
