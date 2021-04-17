package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Topic_27_UploadFile_Senkey {
    WebDriver driver;

//    String projectPath =System.getProperty("user.dir");
//    String osName = System.getProperty("os.name");
//    String seleniumFileName = "selenium1.png";
//    String seleniumPath = "C:/Users/thom/Desktop/Hình điều kiện rewar 2/Selenium"+ seleniumFileName;

    @Test
    public void TC_01_Upload_One_File(){
        driver.get("http://blueimp.github.io/jQuery-File-Upload/");
        //Chú ý: Không có thao tác với Open File Dialog ->nên không cần click vào button cho nó bật lên
        WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
        driver.findElement(By.cssSelector("//span[@class ='btn btn-success fileinput-button']")).click();
        //Đường dẫn đến file
       // uploadFile.sendKeys(seleniumFileName);
        //Verify file loaded success
      //  Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text()='"+ seleniumFileName+" ']")).isDisplayed());

        //click start upload
      //  driver.findElement(By.cssSelector(".files .start")).click();
        //Verify file upload success
     //   Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seleniumFileName +"']")).isDisplayed());
    }

    public void TC_02_Upload_One_file_Chrome(){
//        System.setProperty("webdriver.chrome.driver", projectPath + "D://ECLIPSE//lib0//chromedriver_win32//chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver","D://ECLIPSE//lib0//chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://blueimp.github.io/jQuery-File-Upload/");
        //Chú ý: Không có thao tác với Open File Dialog ->nên không cần click vào button cho nó bật lên
        driver.findElement(By.cssSelector("//span[@class ='btn btn-success fileinput-button']")).click();
        //WebElement uploadFile = driver.findElement(By.xpath("//input[@type ='file']"));
        //Đường dẫn đến file
        //uploadFile.sendKeys(seleniumPath);
        //Verify file loaded success
//        Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text()='"+ seleniumFileName+" ']")).isDisplayed());
//
//        //click start upload
//        driver.findElement(By.cssSelector(".files .start")).click();
//        //Verify file upload success
//        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seleniumFileName +"']")).isDisplayed());

    }
    public void TC_03_Upload_Multiple_File(){

    }
    public String getFileSeparator(){
        return File.separator;
    }
}
