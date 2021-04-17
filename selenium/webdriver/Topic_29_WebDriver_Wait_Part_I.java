package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_29_WebDriver_Wait_Part_I {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver,30);
    }
@Test
    public void TC_01_Visible() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        //Chờ cho Submit button được visible trong vòng 15s
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));


    //Cố tình wait cho error message visible ->Failed
    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='An email address required.']")));
}
    public void TC_02_Invisible() {

    }
    public void TC_03_Presence() {

    }
    public void TC_04_Staleness() {

    }
    public void afterClass(){

    }
    public void sleepInSecond(long timeout){
        try{
            Thread.sleep(timeout*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}