import org.json.simple.parser.ParseException;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;


public class TestCases {
    @BeforeMethod
    public void setup() {
        DriverUtils.setupChromeDriver();
        DriverUtils.expandWindow();
    }

//    @AfterMethod
//    public void quit() {
//        DriverUtils.closeDriver();
//    }

    @Test
    public void case1() throws IOException, ParseException {
        // navigate to main page
        Banner mp_banner = new Banner(By.xpath(PropertiesHandler.mainPageUniqueElement()), "banner on main page");
        MainPage mp = new MainPage(mp_banner, "name");
        DriverUtils.instance.get(mp.url);
        Assert.assertTrue("Unique Element isn't displayed", mp_banner.isElementDisplayed());

        //Click on Alerts, Frame & Windows button,In a menu click Alerts button.
        Actions afw = new Actions(DriverUtils.instance);
        afw.moveToElement(DriverUtils.instance.findElement(By.xpath(PropertiesHandler.alerts()))).click().perform();


        BrowserUtils.scrollDown();
        BrowserUtils.clickOnElement(0, PropertiesHandler.alerts());


        Banner alerts_banner = new Banner(By.xpath(PropertiesHandler.alertsFormUE()), "this is alerts form");
        Assert.assertTrue("Alerts Form isn't Displayed", alerts_banner.isElementDisplayed());

        // click on Alerts button

        Button alert_button = new Button(By.xpath(PropertiesHandler.alertsbutton()), "Alerts Button");
        alert_button.click();


        Assert.assertEquals("You clicked a button", DriverUtils.instance.switchTo().alert().getText());
        //close alert
        DriverUtils.instance.switchTo().alert().accept();

        //Click on On button click, confirm box will appear button
        Button confirm_button = new Button(By.xpath(PropertiesHandler.confirmbutton()), "This is confirm button");
        confirm_button.click();
        Assert.assertEquals("Do you confirm action?", DriverUtils.instance.switchTo().alert().getText());

        //close confirm alert
        DriverUtils.instance.switchTo().alert().accept();
        // check the result
        Banner text_banner = new Banner(By.xpath(PropertiesHandler.confirmresult()), "Result");
        Assert.assertTrue("result isn't displayed", text_banner.isElementDisplayed());
        Assert.assertEquals("You selected Ok", text_banner.getText());

        //Click on On button click, prompt box will appear button
        Button prompt = new Button(By.xpath(PropertiesHandler.promptbutton()), "prompt button");
        prompt.click();
        Alert prompt_input = DriverUtils.instance.switchTo().alert();

        String send_to_prompt = MyUtils.generateString();
        prompt_input.sendKeys(send_to_prompt);
        DriverUtils.instance.switchTo().alert().accept();

        Assert.assertEquals("You entered " + send_to_prompt, DriverUtils.instance.findElement(By.xpath(PropertiesHandler.promptresult())).getText());


    }

    @Test
    public void case2() throws IOException, ParseException {
        Banner mp_banner = new Banner(By.xpath(PropertiesHandler.mainPageUniqueElement()), "banner on main page");
        MainPage mp = new MainPage(mp_banner, "name");
        DriverUtils.instance.get(mp.url);
        Assert.assertTrue("Unique Element isn't displayed", mp_banner.isElementDisplayed());

        // Click on Alerts, Frame & Windows button
        //In a menu click Nested Frames button
        Actions afw = new Actions(DriverUtils.instance);
        afw.moveToElement(DriverUtils.instance.findElement(By.xpath(PropertiesHandler.alerts()))).click().perform();


        BrowserUtils.scrollDown();
        BrowserUtils.clickOnElement(0, PropertiesHandler.nestedframes());


        DriverUtils.instance.switchTo().frame("frame1");
        String parent_fr = DriverUtils.instance.findElement(By.xpath("//body")).getText();

        Assert.assertEquals("Parent frame", parent_fr);

        DriverUtils.instance.switchTo().frame(0);
        String child_fr = DriverUtils.instance.findElement(By.xpath("//body")).getText();

        Assert.assertEquals("Child Iframe", child_fr);

        //exit both child and parent frames


        DriverUtils.instance.switchTo().defaultContent();

        //Click on Frames
        Button frames_btn = new Button(By.xpath(PropertiesHandler.frames()), "frames button");
        frames_btn.click();

        DriverUtils.instance.switchTo().frame("frame1");
        String big_frame = DriverUtils.instance.findElement(By.xpath("//body")).getText();
        DriverUtils.instance.switchTo().defaultContent();

        DriverUtils.instance.switchTo().frame("frame2");
        String small_fr = DriverUtils.instance.findElement(By.xpath("//body")).getText();
        DriverUtils.instance.switchTo().defaultContent();
        Assert.assertEquals(big_frame, small_fr);

    }

    @Test
    public void case3() throws IOException, ParseException {
        Banner mp_banner = new Banner(By.xpath(PropertiesHandler.mainPageUniqueElement()), "banner on main page");
        MainPage mp = new MainPage(mp_banner, "name");
        DriverUtils.instance.get(mp.url);
        Assert.assertTrue("Unique Element isn't displayed", mp_banner.isElementDisplayed());

        Actions afw = new Actions(DriverUtils.instance);
        afw.moveToElement(DriverUtils.instance.findElement(By.xpath(PropertiesHandler.elementsButton()))).click().perform();


        BrowserUtils.scrollDown();
        BrowserUtils.clickOnElement(0, PropertiesHandler.webtables());


        Banner webtables_ue = new Banner(By.xpath(PropertiesHandler.webtablesUE()), "WebTable Banner");

        Assert.assertEquals("Web Tables", webtables_ue.getText());
        // click add

        Button add_button = new Button(By.xpath(PropertiesHandler.addButton()), "add-button");
        add_button.click();
        Banner registration = new Banner(By.xpath(PropertiesHandler.registration_form()), "registration banner");
        WebDriverWait wait = new WebDriverWait(DriverUtils.instance, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(DriverUtils.instance.findElement(By.xpath(PropertiesHandler.registration_form()))));

        Assert.assertTrue("Element not displayed", registration.isElementDisplayed());

        // enter data
        RegistrationForm rf = new RegistrationForm(By.xpath(PropertiesHandler.registration_form()), "Registration form");
        rf.fill();
        rf.submitForm();

        Assert.assertTrue("Not displayed/not displayed correctly", rf.formsuccsess());

        // delete item

        rf.deleteItem(PropertiesHandler.delete_item());

        Assert.assertFalse(rf.formsuccsess());


    }

    @Test
    public void case4() throws IOException, ParseException {
        Banner mp_banner = new Banner(By.xpath(PropertiesHandler.mainPageUniqueElement()), "banner on main page");
        MainPage mp = new MainPage(mp_banner, "name");
        DriverUtils.instance.get(mp.url);
        Assert.assertTrue("Unique Element isn't displayed", mp_banner.isElementDisplayed());

        // Click on Alerts, Frame & Windows button
        //In the menu click a Browser Windows button
        Actions afw = new Actions(DriverUtils.instance);
        afw.moveToElement(DriverUtils.instance.findElement(By.xpath(PropertiesHandler.elementsButton()))).click().perform();

        BrowserUtils.scrollDown();
        BrowserUtils.clickOnElement(0, PropertiesHandler.browser());


        Button newtab_btn = new Button(By.xpath(PropertiesHandler.newtabbtn()), "new tab btn");
        DriverUtils.instance.findElement(newtab_btn.locator).click();
        DriverUtils.instance.switchTo().window(DriverUtils.getTab(1));
        Banner new_tab = new Banner(By.xpath(PropertiesHandler.newtabUE()), "new tab h1");

        Assert.assertTrue(new_tab.isElementDisplayed());

        DriverUtils.instance.close(); // exit out of the tab

        DriverUtils.instance.switchTo().window(DriverUtils.getTab(0)); // switch back to main tab

        Assert.assertTrue(newtab_btn.isElementDisplayed()); // if this button is present we are back to the page


        // In the menu on the left click Elements → Links button

        BrowserUtils.scrollDown();
        Button links = new Button(By.xpath(PropertiesHandler.links()), "links button");
        links.click();
        Banner links_unique = new Banner(By.xpath(PropertiesHandler.linksue()), "link page unique element");
        Assert.assertTrue(links_unique.isElementDisplayed());

        // click home
        Button homebtn_links = new Button(By.xpath(PropertiesHandler.homelink()), "home button on link page");
        homebtn_links.click();

        DriverUtils.instance.switchTo().window(DriverUtils.getTab(1));

        Assert.assertTrue("Unique Element isn't displayed", mp_banner.isElementDisplayed()); // assert main page is displayed

        DriverUtils.instance.switchTo().window(DriverUtils.getTab(0));

        Assert.assertTrue(links_unique.isElementDisplayed());


    }

    @Test
    public void case5() throws IOException, ParseException, InterruptedException {
        // 5.1
        Banner mp_banner = new Banner(By.xpath(PropertiesHandler.mainPageUniqueElement()), "banner on main page");
        MainPage mp = new MainPage(mp_banner, "name");
        DriverUtils.instance.get(mp.url);
        Assert.assertTrue("Unique Element isn't displayed", mp_banner.isElementDisplayed());

        // 5.2
        BrowserUtils.scrollDown();
        BrowserUtils.clickOnElement(0, PropertiesHandler.widgets());
        BrowserUtils.scrollDown();
        BrowserUtils.clickOnElement(0, PropertiesHandler.slider());

        Banner slider_ue = new Banner(By.xpath(PropertiesHandler.slider_ue()), "Slider page unique element");

        Assert.assertTrue("Unique element isn't displayed ", slider_ue.isElementDisplayed());

        // 5.3

        Banner Slider = new Banner(By.xpath(PropertiesHandler.sliderInput()), "Slider");
        int randomInput = MyUtils.generateRandomValue(0, 100);
        MyUtils.moveSliderToPosition(Slider.locator, randomInput);
        int expected = Integer.parseInt(MyUtils.getAttribute());
        Assert.assertTrue("values dont match", MyUtils.validateSlider(randomInput, expected));


        //5.4
        Banner progressBar = new Banner(By.xpath(PropertiesHandler.progressBar()), "Progress Bar");
        BrowserUtils.scrollDown();
        progressBar.click();

        Button start_stop = new Button(By.xpath(PropertiesHandler.startStopBtn()), "Start-Stop Button");
        start_stop.click();
        String myAge = "19";

        Assert.assertTrue("Progess bar isn't correct", MyUtils.validateProgressBar(myAge, start_stop));


    }

}

