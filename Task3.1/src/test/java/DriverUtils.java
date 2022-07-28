import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;


public class DriverUtils {

    public static WebDriver instance = null;

    public static void setupChromeDriver() {
        if (instance == null) {
            WebDriverManager.chromedriver().setup(); //use the WebDriverManager to setup the driver
            ChromeOptions options = new ChromeOptions(); //chrome options to start the browser in incognito mode
            options.addArguments("--incognito");
            instance = new ChromeDriver(options);
        }
    }

    public static void setupFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--incognito");
        instance = new FirefoxDriver(options);
    }

    public static void expandWindow() {
        instance.manage().window().maximize();
    }

    public static void closeDriver() {
        instance.quit();
    }
    public static String getTab(int index) {
        List<String> instances = new ArrayList<String>(instance.getWindowHandles());
        return instances.get(index);
    }

}
