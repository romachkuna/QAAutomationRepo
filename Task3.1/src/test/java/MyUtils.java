import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class MyUtils {

    public static String generateString() {
        return "uuid = " + UUID.randomUUID();
    }

    public static String[] toStringArray(JSONArray array) {
        if (array == null)
            return null;

        String[] arr = new String[array.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array.toString();
        }
        return arr;
    }

    public static void moveSliderToPosition(By by, int percentage) throws InterruptedException {
        WebElement slider = DriverUtils.instance.findElement(by);
        Actions builder = new Actions(DriverUtils.instance);
        builder.moveToElement(slider, (int) (-slider.getRect().width / 2), 0) // move to the leftmost position of the
                // element
                .moveByOffset((int) (percentage * slider.getRect().width / 100), 0) // move the cursor by an offset
                // proportionate to the value passed
                .click().build().perform();

    }

    public static int generateRandomValue(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static boolean validateSlider(int input, int actual) {
        int abs = Math.abs(input - actual);
        return abs < 3;
    }

    public static String getAttribute() throws IOException, ParseException {
        return DriverUtils.instance.findElement(By.xpath(PropertiesHandler.sliderValue())).getAttribute("value");
    }

    public static boolean validateProgressBar(String target, Button btn) throws IOException, ParseException {
        WebElement progress_bar = DriverUtils.instance.findElement(By.xpath(PropertiesHandler.progressValue()));
        while (true) {
            if (Objects.equals(progress_bar.getText(), target + "%")) {
                btn.click();
                break;
            }
        }
        int expected = Integer.parseInt(target);
        int actual = Integer.parseInt(progress_bar.getText().replaceAll("[^a-zA-Z0-9]", ""));
        return Math.abs(expected - actual) < 3;

    }
}
