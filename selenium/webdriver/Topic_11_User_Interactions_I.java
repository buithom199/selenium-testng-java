package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_11_User_Interactions_I {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D://ECLIPSE//lib0//chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }

    public void TC01_Hover_Mouse() {
        driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
        action.moveToElement(driver.findElement(By.xpath("//a[text() = 'ThemeRoller']"))).perform();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//div[class='ui-tooltip-content']")).getText(), "ThemeRoller: jQuery UI's theme builder application");


    }

    public void TC02_Hover_Mouse() {
        driver.get("https://hn.telio.vn");
        action.moveToElement(driver.findElement(By.xpath("//main[@id ='maincontent']//a[span='Đồ uống']"))).perform();
        sleepInSecond(3);
        action.moveToElement(driver.findElement(By.xpath("//main[@id='maincontent']//a[text()='Cà phê']"))).perform();
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//main[@id='maincontent']//a[text()='Cà phê']")).isDisplayed());

        driver.findElement(By.xpath("//main[@id='maincontent']//a[text()='Cà phê']")).click();
        sleepInSecond(5);


    }

    public void TC03_Click_And_Hold() {
        driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
        List<WebElement> numberSelect = (List<WebElement>) driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        Assert.assertEquals(numberSelect.size(), 12);

        action.clickAndHold(numberSelect.get(0)).moveToElement(numberSelect.get(3)).release().perform();
        sleepInSecond(3);

        List<WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(numberSelected.size(), 4);

        for (WebElement number : numberSelected) {
            System.out.println(number.getText());
        }


    }

    @Test
    public void TC04_Click_And_Hold_Random() {
        driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

        List<WebElement> numberSelect = (List<WebElement>) driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        Assert.assertEquals(numberSelect.size(), 12);

        //Nhấn phím Ctrl xuống
        action.keyDown(Keys.CONTROL).perform();

        //Click các số 2, 7, 9, 10
        action.click(numberSelect.get(1)).click(numberSelect.get(6)).click(numberSelect.get(8)).click(numberSelect.get(9)).perform();

        //Nhả phím ctrl
        action.keyUp(Keys.CONTROL).perform();

        List<WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(numberSelected.size(), 4);

        for (WebElement number : numberSelected) {
            System.out.println(number.getText());


        }
    }

    @Test
    public void TC05_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        action.doubleClick(driver.findElement(By.xpath("//div[@class='container']/button[text() ='Double click me']"))).perform();
        sleepInSecond(4);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class ='container']/p[@id='demo']")).getText(),"Hello Automation Guys!");
    }




        public void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}