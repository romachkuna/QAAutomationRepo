package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DriverUtils {

    public static WebDriver instance =null;

    public static void setupDriver(){
        if(instance==null) {
            WebDriverManager.chromedriver().setup(); //use the WebDriverManager to setup the driver
            ChromeOptions options = new ChromeOptions(); //chrome options to start the browser in incognito mode
            options.addArguments("--incognito");
            instance = new ChromeDriver(options);
        }
    }
    public static void closeDriver(){
        instance.close();
    }
    public static void getMainPage() throws IOException, ParseException {
        instance.get(myUtils.getMainPageURL());
    }

}
