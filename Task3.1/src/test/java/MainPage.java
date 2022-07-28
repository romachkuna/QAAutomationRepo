import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MainPage extends BasePage{


    String url = PropertiesHandler.mainPageUrl();

    public MainPage(BaseElement uniqueElement, String name) throws IOException, ParseException {
        super(uniqueElement, name);
    }
}
