package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_24_Fame_iFame {
    WebDriver driver;
    Select select;
    @BeforeTest
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D://ECLIPSE//lib0//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void TC01_WordPress(){
        driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");

        //Switch vào Facebook iframe
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fb-page fb_iframe_widget']//iframe")));
        //Get like number
        String likeNumbers = driver.findElement(By.xpath("//a[text()='Automation FC']/parent::div/following-sibling::div")).getText();
        System.out.println(likeNumbers);

        //default Content (Back lại parent)
        driver.switchTo().defaultContent();

        //Switch vào Google Doc iframe
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com']")));

        //Verify Doc header is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='KHÓA HỌC SELENIUM AUTOMATION TESTING'")).isDisplayed());


    }
    @Test
    public void TC02_Kyna(){
        driver.get("https://kyna.vn/");
        //Switch vào Chat iframe
        driver.switchTo().frame("cs_chat_iframe");
        sleepInSecond(5);
        driver.findElement(By.xpath("//div[@class ='meshim_widget_components_chatButton_ButtonBar button_bar']")).click();
        sleepInSecond(5);

        //Input vào các field bắt buộc
        driver.findElement(By.cssSelector("input.input_name")).sendKeys("Johny");
        driver.findElement(By.cssSelector("input.input_phone")).sendKeys("09876669999");

        select = new Select(driver.findElement(By.id("serviceSelect")));
        select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");

        driver.findElement(By.cssSelector("textarea[name ='message']")).sendKeys("Java Course online");
        sleepInSecond(5);
        driver.findElement(By.xpath("//input[@class ='submit meshim_widget_widgets_ConnAwareSubmit ltr wide valid ng-scope desktop']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@class ='submit meshim_widget_widgets_ConnAwareSubmit ltr wide valid ng-scope desktop']")).isDisplayed());

//        driver.findElement(By.xpath("//input[@class ='input_phone mobile_error_icon meshim_widget_widgets_PhoneField ltr ng-scope ng-pristine ng-valid desktop']")).click();
//        Assert.assertTrue(driver.findElement(By.xpath("//input[@class ='input_phone mobile_error_icon meshim_widget_widgets_PhoneField ltr ng-scope ng-pristine ng-valid desktop']")).isDisplayed());
sleepInSecond(5);
driver.switchTo().defaultContent();
driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
sleepInSecond(1);
driver.findElement(By.className("search-button")).click();
sleepInSecond(1);
        List<WebElement> excelText = driver.findElements(By.cssSelector(".content>h4"));
        for (WebElement text: excelText){
            Assert.assertTrue(text.getText().contains("Excel"));
        }
    }

    public void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}