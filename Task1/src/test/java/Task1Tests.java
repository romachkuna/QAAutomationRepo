import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

import static org.testng.Assert.assertTrue;


public class Task1Tests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void NavigateToMainPage() {
        // steams main page title is Welcome to Steam, if this condition is met then test run successfully
        driver.navigate().to("https://store.steampowered.com/");
        String actual = driver.getTitle();
        String expected = "Welcome to Steam";
        Assert.assertEquals(actual, expected);
        driver.quit();
    }

    @Test
    public void ClickAbout() {
        // steams about page title is "Steam, The Ultimate Online Game Platform", if this condition is met then test run succesfully
        driver.get("https://store.steampowered.com/");
        driver.findElement(By.xpath("//a[contains(text(),'ABOUT')]")).click();
        String actual = driver.getTitle();
        String expected = "Steam, The Ultimate Online Game Platform";
        Assert.assertEquals(actual, expected);
        driver.quit();
    }

    @Test
    public void NumbersComparison() {
        // since using title() was giving me errors i stored the results in list and filtered for ingame counts.
        driver.get("https://store.steampowered.com/about/");
        List<WebElement> element_online = driver.findElements(By.xpath(" //div[@class=\"online_stat\"]"));
        int online = Integer.parseInt(element_online.get(0).getText().replaceAll("[^0-9]", ""));
        int playing = Integer.parseInt(element_online.get(1).getText().replaceAll("[^0-9]", ""));
        Assert.assertTrue(online > playing, "Assertion failed");
        driver.quit();
    }

    @Test
    public void ClickStore() {
        // Welcome to steam title means that STORE was clicked
        driver.get("https://store.steampowered.com/about/");
        driver.findElement(By.xpath("//a[contains(text(),'STORE')]")).click();
        String actual = driver.getTitle();
        String expected = "Welcome to Steam";
        Assert.assertEquals(actual, expected);
        driver.quit();
    }
}
