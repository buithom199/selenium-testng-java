package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_API {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }
    @Test
    public void TC_01_URL() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class ='footer']//a[text() ='My Account']")).click();
        //Kiểm tra được các URL của page mới mở đó có đúng hay không?
      Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
      //
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        //
       Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");


//
//        //Lấy ra title của page hiện tại
//        driver.getTitle();
//
//        //Lấy ra source code của page hiện tại
//        driver.getPageSource();
//
//        //Láy ra ID của tab/window nó đang đứng(active)
//        driver.getWindowHandle();
//
//        //Lấy ra tất cả ID của tất cả các tab/window
//        driver.getWindowHandles();
//
//        //chờ cho page được load trong vòng..
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//
//        //Chờ cho 1 đoạn script của JavascriptExecutor thực thi trong vòng...
//        driver.manage().timeouts().setScriptTimeout(30,TimeUnit.SECONDS);
//
//        //Tiếp trang trước đó
//        driver.navigate().forward();
//
//        //Tải lại trang (F5/Reload)
//        driver.navigate().refresh();
//
//        //Thao tác với alert
//        driver.switchTo().alert();
//
//        //thao tác với frame/iframe
//        driver.switchTo().frame();
    }
    @Test
    public void TC_02_Title(){
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class ='footer']//a[text() ='My Account']")).click();
        //Kiểm tra được các URL của page mới mở đó có đúng hay không?
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
        //
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        //
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }
    @Test
    public void TC_03_Navigate(){
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class ='footer']//a[text() ='My Account']")).click();

        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
        driver.navigate().forward();
        //
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");


    }
    @Test
    public void TC_04_Page_Source(){
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class ='footer']//a[text() ='My Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }
}
