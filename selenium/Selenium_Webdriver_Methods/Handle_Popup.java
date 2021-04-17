package Selenium_Webdriver_Methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Handle_Popup {
    WebDriver driver;
    public static void manin(String[] args) throws InterruptedException {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "D://ECLIPSE//lib0//chromedriver_win32//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //Step 1) Launch the site: Launch the browser and open the site "http://demo.guru99.com/popup.php
        //Step 2) Click on link "Click Here": When the user clicks on the Click Here link, new child window opens
        //Step 3) New Child Window opens: A new window opens, ask the user to enter email id submit the page
        //Step 4) Enter your email ID and submit
        //Step 5) Display the Access Credentials on submiting the page:
        // When you execute the code, you will see the child window is open in new tab
        //1. Close the child window on which credential are displayed
        //Switch to the parent window


        //Launching the site
        driver.get("http://demo.guru99.com.popup.php");
        driver.findElement(By.xpath("//[contains(@href,'popup.php")).click();
        String MainWindow = driver.getWindowHandle();
        //To handle all new opened window
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while(i1.hasNext()){
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)){
                //Switching to Child window
                driver.switchTo().window(ChildWindow);
                driver.findElement(By.name("emailid")).sendKeys("gaurav.3n@gmail.com");
                driver.findElement(By.name("btnLogin")).click();
                //Closing the Child Windown
                driver.close();

            }
        }
        //Swithching to Parent window i.e Main Window
        driver.switchTo().window(MainWindow);
    }
}