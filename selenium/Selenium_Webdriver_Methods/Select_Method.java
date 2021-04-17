package Selenium_Webdriver_Methods;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Select_Method {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver","D://ECLIPSE//lib0//chromedriver_win32//chromedriver.exe" );
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
    }
    @Test
    public void TC1_selectByVisibleText() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(10000);
        WebElement mySelectElement = driver.findElement(By.cssSelector("select[name ='multipleselect[]']"));
        Select dropdown = new Select(mySelectElement);
        dropdown.selectByValue("msagile");


//        Thread.sleep(2000);
//        dropdown.deselectByIndex(2);
//        dropdown.selectByVisibleText("Manual Testing");
//        Thread.sleep(2000);
//        dropdown.deselectByVisibleText("Manual Testing");
////        Thread.sleep(10000);
//        dropdown.selectByIndex(2);//Value is QTP
//        Thread.sleep(2000);
////        dropdown.deselectByIndex(2);
////        Thread.sleep(10000);
//        dropdown.selectByValue("ddmanual");
////        Thread.sleep(2000);
////        dropdown.deselectByValue("ddmanual");

    }



}