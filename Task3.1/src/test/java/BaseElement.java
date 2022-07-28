import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BaseElement {

    By locator;
    private String name;


    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public boolean isElementDisplayed() {
        return DriverUtils.instance.findElement(locator).isDisplayed();
    }

    public void click() {
        DriverUtils.instance.findElement(locator).click();
    }

    public String getText() {
        return DriverUtils.instance.findElement(locator).getText();
    }

    public WebElement getElement() {
        return DriverUtils.instance.findElement(locator);
    }
}
