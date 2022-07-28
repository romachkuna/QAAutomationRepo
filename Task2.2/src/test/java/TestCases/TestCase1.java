package TestCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TestCase1 {


    @BeforeMethod
    public void setup() {
        DriverUtils.setupDriver();
    }
    @Test
    public void Test1() throws IOException, ParseException {
        // 1.1
        DriverUtils.getMainPage(); //navigate to main page
        Assert.assertTrue(
                DriverUtils.instance.findElement(By.xpath(myUtils.uniqueMainXPath())).isDisplayed(), "Main Page isn't Displayed");//assert if main page is open
        //1.2
        DriverUtils.instance.findElement(By.xpath("//a[contains(text(),'ABOUT')]")).click();
        Assert.assertTrue(DriverUtils.instance.findElement(By.xpath("//div[@id='about_greeting']")).isDisplayed(), "About isn't Displayed");
        // 1.3 using same logic as in task1
        List<WebElement> element_online = DriverUtils.instance.findElements(By.xpath(" //div[@class=\"online_stat\"]"));
        int online = Integer.parseInt(myUtils.getonlyString(element_online.get(0).getText()));
        int playing = Integer.parseInt(myUtils.getonlyString(element_online.get(1).getText()));
        Assert.assertTrue(online > playing, "Assertion failed");
        //1.4
        DriverUtils.instance.findElement(By.xpath("//a[contains(text(),'STORE')]")).click();
        Assert.assertTrue(DriverUtils.instance.findElement(By.xpath("//div[@id='about_header']")).isDisplayed(), "About isn't Displayed");
    }

    @Test
    public void Test2() throws IOException, ParseException {
        // 2.1
        DriverUtils.getMainPage(); //navigate to main page
        Assert.assertTrue(
                DriverUtils.instance.findElement(By.xpath(myUtils.uniqueMainXPath())).isDisplayed(), "Main Page isn't Displayed");//assert if main page is open

        // 2.2
        WebElement el_community = DriverUtils.instance.findElement(By.xpath("//div[@class='supernav_container']/a[2]"));
        Actions action = new Actions(DriverUtils.instance);
        action.moveToElement(el_community).perform();


        WebDriverWait wait = new WebDriverWait(DriverUtils.instance, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(DriverUtils.instance.findElement(By.xpath("//div[@class='supernav_content'][2]/div/a[contains(text(),'Market')]"))));
        WebElement market = DriverUtils.instance.findElement(By.xpath("//div[@class='supernav_content'][2]/div/a[contains(text(),'Market')]"));
        action.moveToElement(market).click().perform();
        Assert.assertTrue(DriverUtils.instance.findElement(By.xpath("//span[@class='market_title_text']")).isDisplayed(), "Market isn't displayed");
        //2.3
        DriverUtils.instance.findElement(By.xpath("//div[@class='market_search_advanced_button']")).click();
        Assert.assertTrue(DriverUtils.instance.findElement(By.xpath("//*[@id=\"market_advanced_search\"]")).isDisplayed(), "Search Box isn't displayed");

        //2.4
        MarketPage.fill(DriverUtils.instance);

        //2.5
        int limit_items = 5;
        DriverUtils.instance.findElement(By.xpath("//div[@class='btn_medium btn_green_white_innerfade']")).click(); //click on search
        List<WebElement> elements =
                DriverUtils.instance.findElements(By.xpath("//div[@class='market_listing_item_name_block']/span[@class='market_listing_item_name']")).stream().limit(limit_items).toList();

        for (WebElement e : elements) {
            Assert.assertTrue(e.getText().contains("Golden")); // test top 5 elements if they contain "Golden"
        }

        int limit_search_items = 4;
        List<String> tags = DriverUtils.instance.findElements(By.xpath("//div[@class ='market_search_results_header']/div[1]/a"))
                .stream().map(WebElement::getText).toList().stream().limit(limit_search_items).toList();
        String[] tag_array = myUtils.tag_arrays();


        for (String s : tag_array) {
            Assert.assertTrue(tags.contains(s));  // check if the tags are all there
        }


        //2.6
        MarketPage.delete_tags(DriverUtils.instance);
        Assert.assertFalse(DriverUtils.instance.findElement(By.xpath("//*[@id=\"BG_bottom\"]/div[1]/div/a[1]")).isDisplayed(),"It's not displayed");



        //2.7
        // get the name of top item
        List<WebElement> xs =
                DriverUtils.instance.findElements(By.xpath("//div[@id='searchResultsRows']/a/div[1]/div[2]/span[1]")).stream().limit(1).toList();
        String topname = xs.get(0).getText();

        // click on the top item
        DriverUtils.instance.findElements(By.xpath("//div[@id='searchResultsRows']/a")).stream().limit(1).forEach(WebElement::click);

        String found = DriverUtils.instance.findElement(By.xpath("//h1[@id=\"largeiteminfo_item_name\"]")).getText();

        Assert.assertEquals(found, topname, "Not true"); // check the name if its matched

        // Website doesn't show other tags to check.



    }
    @AfterMethod
    public void finishTesting(){
        DriverUtils.closeDriver();

    }


}

