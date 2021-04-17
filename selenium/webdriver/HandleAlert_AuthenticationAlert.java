package webdriver;

import com.sun.org.apache.bcel.internal.ExceptionConstants;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HandleAlert_AuthenticationAlert {
    WebDriver driver;
    WebDriverWait explicitWait;
    Alert alert;


    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void TC_01_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text() ='Click for JS Alert']")).click();
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        //alert = driver.switchTo().alert();
        // 4 hành vi của alert: acceept, cancel, input value, Get title
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        sleepInSecond(5);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked an alert successfully");


    }

    public void TC_02_Confirm_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text() ='Click for JS Confirm']")).click();
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        sleepInSecond(5);
        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id = 'result']")).getText(),"You clicked: Cancel");

    }

    public void TC_03_Prompt_Alert(){
        String alertTtext = "Automation Test 2021";
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text() ='Click for JS Prompt']")).click();
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        sleepInSecond(5);
        alert.sendKeys(alertTtext);
        sleepInSecond(5);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id ='result']")).getText(),"You entered: "+alertTtext);


    }
    @Test
    public void TC_04_Authentication_Alert(){
        //Selenium By Pass
        //http://username:password@the-internet.herokuapp.com/basic_auth

        //Trường hợp 1: Truyền thẳng username và password
        //driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        //Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        //Trường hợp 2:
        //Pass contain special chars: admin/P@ssw0!d ->Use URL Encode
        //driver.get("http://admin:P%40ssw0%21d@the-internet.herokuapp.com/basic_auth");
    }
    @Test
    public void TC_05_Authentication_Alert(){
        driver.get("http://the-internet.herokuapp.com/");
        String url = driver.findElement(By.xpath("//a[text() ='Basic Auth']")).getAttribute(("href"));
        driver.get(getCredentialToUrl(url,"admin","admin"));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }
    public String getCredentialToUrl(String ulr, String username, String password){
        String[] newUrl = ulr.split("//");
        String url = newUrl[0] + "//" + username + ":" + password + "@" + newUrl[1];
        return ulr;
    }
    public void sleepInSecond(long timeout){
        try{
            Thread.sleep(timeout*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    
}
