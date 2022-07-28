import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class PropertiesHandler {
    public static JSONObject getArray(String jsonarray) throws IOException, ParseException {
        FileReader reader = new FileReader("src/test/java/Properties.json");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(reader);
        JSONObject jsonobj = (JSONObject) obj;
        JSONArray tags = (JSONArray) jsonobj.get(jsonarray);
        return (JSONObject) tags.get(0);
    }

    public static JSONObject fxz(String jsonarray) throws IOException, ParseException {
        FileReader reader = new FileReader("src/test/java/TestData.json");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(reader);
        JSONObject jsonobj = (JSONObject) obj;
        JSONArray tags = (JSONArray) jsonobj.get(jsonarray);
        return (JSONObject) tags.get(0);
    }

    public static String[] getRegistrationInputs() throws IOException, ParseException {
        String[] result = new String[6];
        result[0] = (String) fxz("RegistrationForm").get("Field_1");
        result[1] = (String) fxz("RegistrationForm").get("Field_2");
        result[2] = (String) fxz("RegistrationForm").get("Field_3");
        result[3] = (String) fxz("RegistrationForm").get("Field_4");
        result[4] = (String) fxz("RegistrationForm").get("Field_5");
        result[5] = (String) fxz("RegistrationForm").get("Field_6");
        return result;
    }

    public static String sliderValue() throws IOException, ParseException {
        return (String) getArray("widgets").get("slider-value");
    }

    public static String widgets() throws IOException, ParseException {
        return (String) getArray("mainpage").get("widgets");
    }

    public static String sliderInput() throws IOException, ParseException {
        return (String) getArray("widgets").get("slider-input");
    }

    public static String progressBar() throws IOException, ParseException {
        return (String) getArray("widgets").get("progressbar");
    }

    public static String startStopBtn() throws IOException, ParseException {
        return (String) getArray("widgets").get("start-stopbtn");
    }

    public static String progressValue() throws IOException, ParseException {
        return (String) getArray("widgets").get("progress-value");
    }

    public static String slider() throws IOException, ParseException {
        return (String) getArray("widgets").get("slider");
    }

    public static String slider_ue() throws IOException, ParseException {
        return (String) getArray("widgets").get("slider-ue");
    }

    public static String mainPageUrl() throws IOException, ParseException {
        return (String) getArray("mainpage").get("url");
    }

    public static String mainPageUniqueElement() throws IOException, ParseException {
        return (String) getArray("mainpage").get("unique_element");
    }

    public static String alerts() throws IOException, ParseException {
        return (String) getArray("mainpage").get("alerts");
    }

    public static String alertsFormUE() throws IOException, ParseException {
        return (String) getArray("AlertsForm").get("unique_element");

    }

    public static String alertsbutton() throws IOException, ParseException {
        return (String) getArray("AlertsForm").get("alert-button");


    }

    public static String confirmbutton() throws IOException, ParseException {
        return (String) getArray("AlertsForm").get("confirm-button");

    }

    public static String confirmresult() throws IOException, ParseException {
        return (String) getArray("AlertsForm").get("confirm-result");

    }

    public static String promptbutton() throws IOException, ParseException {
        return (String) getArray("AlertsForm").get("prompt-button");
    }

    public static String promptresult() throws IOException, ParseException {
        return (String) getArray("AlertsForm").get("prompt-result");
    }

    public static String frames() throws IOException, ParseException {
        return (String) getArray("AlertsFrameWindows").get("frames");
    }

    public static String nestedframes() throws IOException, ParseException {
        return (String) getArray("AlertsFrameWindows").get("nested-frames");
    }

    public static String elementsButton() throws IOException, ParseException {
        return (String) getArray("mainpage").get("elements-button");
    }

    public static String webtables() throws IOException, ParseException {
        return (String) getArray("elements").get("web-table");
    }

    public static String webtablesUE() throws IOException, ParseException {
        return (String) getArray("elements").get("unique-element");
    }

    public static String addButton() throws IOException, ParseException {
        return (String) getArray("elements").get("add-button");
    }

    public static String name() throws IOException, ParseException {
        return (String) getArray("elements").get("firstname");
    }

    public static String surname() throws IOException, ParseException {
        return (String) getArray("elements").get("lastname");
    }

    public static String email() throws IOException, ParseException {
        return (String) getArray("elements").get("email");
    }

    public static String age() throws IOException, ParseException {
        return (String) getArray("elements").get("age");
    }

    public static String salary() throws IOException, ParseException {
        return (String) getArray("elements").get("salary");

    }

    public static String registration_form() throws IOException, ParseException {
        return (String) getArray("elements").get("table-form");
    }

    public static String department() throws IOException, ParseException {
        return (String) getArray("elements").get("department");
    }

    public static String submitForm() throws IOException, ParseException {
        return (String) getArray("elements").get("submit");
    }

    public static String form_elements() throws IOException, ParseException {
        return (String) getArray("elements").get("form-elements");
    }

    public static String delete_item() throws IOException, ParseException {
        return (String) getArray("elements").get("delete-item");
    }

    public static String browser() throws IOException, ParseException {
        return (String) getArray("AlertsFrameWindows").get("browser");
    }

    public static String newtabUE() throws IOException, ParseException {
        return (String) getArray("new-tab").get("ue");
    }

    public static String newtabbtn() throws IOException, ParseException {
        return (String) getArray("browser").get("new-tab");
    }

    public static String elementsbtn() throws IOException, ParseException {
        return (String) getArray("elements").get("elements-btn");
    }

    public static String links() throws IOException, ParseException {
        return (String) getArray("elements").get("links");
    }

    public static String linksue() throws IOException, ParseException {
        return (String) getArray("links").get("ue");
    }

    public static String homelink() throws IOException, ParseException {
        return (String) getArray("links").get("home");
    }


}
