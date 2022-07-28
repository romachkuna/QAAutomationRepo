package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MarketPage {

    public static void fill (WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"advancedSearchBox\"]")).sendKeys("golden");

        driver.findElement(By.xpath("//div[@id=\"app_option_0_selected\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"app_option_570\"]")).click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait2.until(ExpectedConditions.elementToBeClickable(By.name("category_570_Hero[]")));
        driver.findElement(By.name("category_570_Hero[]")).click();

        WebElement hero = driver.findElement(By.name("category_570_Hero[]"));
        Select select_hero = new Select(hero);
        select_hero.selectByVisibleText("Lifestealer");
        driver.findElement(By.xpath("//*[@id=\"tag_570_Rarity_Rarity_Immortal\"]")).click();

    }
    public static void delete_tags (WebDriver driver){
        List<WebElement> todelete =
                driver.findElements(By.xpath("//div[@class='market_search_results_header']/div[1]/a")).
                        stream().filter(a -> a.getText().equals("\"golden\"")).toList();
        todelete.forEach(WebElement::click);
        driver.findElement(By.xpath("//*[@id=\"BG_bottom\"]/div[1]/div/a[1]")).click();
    }
}
