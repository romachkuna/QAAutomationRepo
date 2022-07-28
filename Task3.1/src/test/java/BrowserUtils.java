
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class BrowserUtils {
    static JavascriptExecutor js = (JavascriptExecutor) DriverUtils.instance;


    public static void scrollDown() {
        js.executeScript("window.scrollBy(0,350)", "");
    }

    public static void clickOnElement(int index, String path) {
        String script = "arguments[\"" + index + "\"].click();";
        js.executeScript(script,
                DriverUtils.instance.findElement(By.xpath(path)));
    }
}
