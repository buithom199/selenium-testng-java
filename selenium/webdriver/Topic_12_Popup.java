package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_12_Popup {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D://ECLIPSE//lib0//chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void TC01_Popup_Fixed() {
        driver.get("https://tiki.vn/");
        action.moveToElement(driver.findElement(By.xpath("//img[@class='profile-icon']"))).perform();

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSecond(2);
        //Verify popup is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']/div")).isDisplayed());
        sleepInSecond(2);
        //click to Close icon
        driver.findElement(By.xpath("//span[@class='tikicon icon-circle-close']")).click();

        //Popup không có trong DOM
    //Hàm find element chỉ tìm được element nếu như nó có trong DOM
    //Nếu không có trong DOM thì sẽ bị fail
    //"Command duration or timeout 30.04 seconds" hiển thị là do bị ảnh hưởng bởi implicitlyWait(30)
    //1-Driver sẽ đi tìm lement vs 1xpath nào đó
    //Nếu như tìm thấy thì qua vế tiếp theo (trong 1 dòng)
    //Nếu như không tìm thấy ->đánh fail cái testcase này + exception: Nosuchelement
    //Nếu như tìm thấy thì qua vế tiếp theo (trong 1 dòng)->isDisplayed()

    }

    public void TC2_Popup_In_DOM() {
        driver.get("https://bni.vn/");
        //Verify popup is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
        sleepInSecond(3);

        //Close popup
        driver.findElement(By.xpath("//img[@class ='sgpb-popup-close-button-1']")).click();
        sleepInSecond(3);

        //Verify popup is not displayed
        Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());

    }
    @Test
    public void TC2_Popup_In_DOM_Condition() {
        System.out.println("step 1");
        driver.get("https://testproject.io/");
        sleepInSecond(5);
        //1-Nếu như cái popup xuất hiện ->Close đi để làm qua step 3
        //2-Nếu như cái popup không xuất hiện ->qua step thứ 3
        if (driver.findElement(By.xpath("//div[@class ='mailch-wrap']")).isDisplayed()) {
            System.out.println("step 2");
            driver.findElement(By.xpath("//div[@class ='close-mailch']")).click();
            sleepInSecond(2);
        }
        System.out.println("Step 3");
        driver.findElement(By.xpath("//section[@id='search-2']")).sendKeys("Robot Framework");
        sleepInSecond(2);

        System.out.println("Step 4");
        driver.findElement(By.xpath("//section[@id='search-2']")).click();
    }
    public void TC3_Popup_Not_In_DOM() {
        driver.get("https://tiki.vn/");
        action.moveToElement(driver.findElement(By.xpath("//img[@class='profile-icon']"))).perform();

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSecond(2);
        //Verify popup is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']/div")).isDisplayed());
        sleepInSecond(2);
        //click to Close icon
        driver.findElement(By.xpath("//span[@class='tikicon icon-circle-close']")).click();

        //verify popup is not displayed (Not in DOM)
        Assert.assertEquals(driver.findElements(By.xpath("//div[@role='dialog']/div")).size(),0);

    }

    public void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
