package Selenium_Webdriver_Methods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class Handle_Alert {
    WebDriver driver;


    public static void manin(String[] args) throws InterruptedException {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver","D://ECLIPSE//lib0//chromedriver_win32//chromedriver.exe" );
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //Alert Message handling
        driver.get("http://demo.guru.com/test/delete_customer.php");
        driver.findElement(By.name("cusid")).sendKeys("53920");
        driver.findElement(By.name("submit")).submit();
        //Switching to Alert
        Alert alert = driver.switchTo().alert();
        //Capturing alert message
        String alertMessage = driver.switchTo().alert().getText();
        //Displaying alert message
        System.out.println(alertMessage);
        Thread.sleep(5000);
        //Accepting alert
        alert.accept();

    }

    //Step 1) Launch the web browser and open the site
    //Step 2) Enter Any Customer id
    //Step 3) After entering the customer ID, Click on the "Submit" button
    //Step 4) Reject/accept the alert

}
