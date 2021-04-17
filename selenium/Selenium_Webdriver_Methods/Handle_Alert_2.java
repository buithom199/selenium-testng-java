package Selenium_Webdriver_Methods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Handle_Alert_2 {
    WebDriver driver;
    public void DemoWebAlert(){
    }
    @BeforeTest
    public void setUp(){
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "D://ECLIPSE//lib0//chromedriver_win32//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    //1- Launch the web browser and open the webpage
    //2- Click on the "Try it" button
    //3- Accept the alert
    //4- Click on the "Try it" button again
    //5- Reject the alert
    @Test
    public void testWebAlert() throws InterruptedException {
        //Clicking on try it button
        driver.findElement(By.xpath("//button[contains(text(),'Try it')]")).click();
        //accepting javascript alert
        Alert alert = driver.switchTo().alert();
        alert.accept();
        //Clicking on try it button
        driver.findElement(By.xpath("//button[contains(text(),'Try it')]")).click();
        Thread.sleep(5);
        //accepting javascript alert
        driver.switchTo().alert().dismiss();
        //clicking on try it button
        driver.findElement(By.xpath("//button[contains(text(),'Try it')]")).click();
        Thread.sleep(5);
        //accepting javascript alert
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
    }
    //1- Launch the web browser and open the application -"gmail.com"
    //2- Enter valid username and password
    //3- Click on the sign in button
    //4- Click on the compose button
    //5- Click on the attach icon
    //6- Select the files to be uploaded
    @Test
    public void testWindowAlert() throws InterruptedException, AWTException {
        //enter a valid email address
        driver.findElement(By.id("Email")).sendKeys("TestSelenium1607@gmail.com");
        //enter a valid password
        driver.findElement(By.id("Password")).sendKeys("TestSelenium");
        //click on sign in button
        driver.findElement(By.id("signIn")).click();
        Thread.sleep(3);
        //click on compose button
        driver.findElement(By.xpath("//div[@class ='z0']//dive[contains(text),'COMPOSE']")).click();
        //click on attach file icon
        driver.findElement(By.xpath("//div[contains@command,'Files']//dive[contains(@class,'aaA')]")).click();
        //creating instance of Robot class (A java based utility)
        Robot rb = new Robot();
        //pressing keys with the help of keyPress and keyRelease events
        rb.keyPress(KeyEvent.VK_D);
        

    }

}
