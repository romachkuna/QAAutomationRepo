import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Arrays;

public class RegistrationForm extends BaseElement {
    WebDriver instance = DriverUtils.instance;
    String[] inputs = TestDataHandler.getRegistrationInputs();

    public RegistrationForm(By locator, String name) throws IOException, ParseException {
        super(locator, name);
    }

    public void fill() throws IOException, ParseException {
        instance.findElement(By.xpath(PropertiesHandler.name())).sendKeys(inputs[0]);
        instance.findElement(By.xpath(PropertiesHandler.surname())).sendKeys(inputs[1]);
        instance.findElement(By.xpath(PropertiesHandler.email())).sendKeys(inputs[2]);
        instance.findElement(By.xpath(PropertiesHandler.age())).sendKeys(inputs[3]);
        instance.findElement(By.xpath(PropertiesHandler.salary())).sendKeys(inputs[4]);
        instance.findElement(By.xpath(PropertiesHandler.department())).sendKeys(inputs[5]);

    }

    public void submitForm() throws IOException, ParseException {
        instance.findElement(By.xpath(PropertiesHandler.submitForm())).click();

    }

    public boolean formsuccsess() {
        Boolean[] result = new Boolean[6];

        for (int i = 0; i < inputs.length; i++) {
            try {
                String new_path = PropertiesHandler.form_elements().replace("replace", inputs[i]);
                result[i] = DriverUtils.instance.findElement(By.xpath(new_path)).isDisplayed();
            } catch (NoSuchElementException e) {
                return false;
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return Arrays.stream(result).allMatch(s -> s.equals(result[0]));
    }

    public void deleteItem(String path) {
        String new_path = path.replace("replace", inputs[0]);
        DriverUtils.instance.findElement(By.xpath(new_path)).click();

    }
}
