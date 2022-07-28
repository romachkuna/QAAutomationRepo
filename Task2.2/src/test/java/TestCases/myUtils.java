package TestCases;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class myUtils {



    public static String getonlyString(String input) {
        return input.replaceAll("[^0-9]", "");
    }

    public static JSONObject setupconfig() throws IOException, ParseException {
        FileReader reader = new FileReader("src/test/java/TestCases/config_data.json");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(reader);
        return (JSONObject) obj;
    }
    public static String uniqueMainXPath() throws IOException, ParseException {
        return (String) setupconfig().get("unique_main_xpath");
    }
    public static String getMainPageURL() throws IOException, ParseException {
        return (String) setupconfig().get("mainpage_url");
    }

    public static JSONObject getArray(int id) throws IOException, ParseException {
        FileReader reader = new FileReader("src/test/java/TestCases/test_data.json");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(reader);
        JSONObject jsonobj = (JSONObject) obj;
        JSONArray tags = (JSONArray) jsonobj.get("tag_array");
        return (JSONObject) tags.get(id);
    }
    public static String [] tag_arrays() throws IOException, ParseException {
        String [] result = new String[4];
        result[0] = (String) getArray(0).get("Dota2");
        result[1] = (String) getArray(0).get("Lifestealer");
        result[2] = (String) getArray(0).get("Immortal");
        result[3] = (String) getArray(0).get("golden");
        return  result;
    }

    public static String [] del_arrays() throws IOException, ParseException {
        String [] result = new String[2];
        result[0] = (String) getArray(1).get("Lifestealer");
        result[1] = (String) getArray(1).get("Immortal");
        return result;
    }


}
