package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Xpath_Css_HTML_II {
    WebDriver driver ;
    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
       // driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

    }

    public void TC_01_ID() throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys("automationtest@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");

        Thread.sleep(3000);

    }

    public void TC_02_Class() throws InterruptedException {
        //refresh page
        driver.navigate().refresh();
        //testcase is not run
        //get all class is not action because it was not find all array (chuỗi)in dom, sereparated (bị tách ra)
        driver.findElement(By.className("validate-password")).sendKeys("123456");
        //Verify testcase is Displayed
        driver.findElement(By.className("account-login")).isDisplayed();
        Thread.sleep(3000);

        //There are 2 types data base
        //1. Primitive data base (nguyên thủy)
        //2. Reference data base (tham chiếu)

    }

    public void TC_03_Name() throws InterruptedException {
        driver.findElement(By.name("login[username]")).sendKeys("1234893");
        Thread.sleep(3000);

    }
//    @Test
//    public void TC_04_Tagname() {
//        //find, count. how many link in tagname
//       // System.out.println("Tổng số link trên trang hiện tai =" + driver.findElement(By.tagName("a")).getSize());

   // }
//    @Test
//    public void TC_05_LinkText() throws InterruptedException {
//        //Text của link tuyệt đối (lấy toàn bộ chuỗi)
//            driver.findElement(By.linkText("Forgot Your Password?")).click();
//            Thread.sleep(3000);
//
//    }
//    @Test
//    public void TC_06_Partial_Linktext() {
//        //Text của link tương đối (lấy toàn một phần của chuỗi)
//    }
    public void TC_07_Css_Selector() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        driver.findElement(By.cssSelector("input[id ='id_email']")).sendKeys("automationfc@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[id ='id_email']")).clear();


        driver.findElement(By.cssSelector("input[placeholder ='Your email address']")).sendKeys("testing@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[placeholder ='Your email address']")).clear();

        driver.findElement(By.cssSelector("input[name ='email']")).sendKeys("buithom199@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[name ='email']")).clear();
    }
    @Test
    public void TC_08_Xpath() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        //Lấy theo quan hệ cha con
        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id-email']")).sendKeys("testing1@gmai.com");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id-email']")).clear();


        driver.findElement(By.xpath("//form[@id='login-form']//input[@name='email']")).sendKeys("testing2@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//form[@id='login-form']//input[@name='email']")).clear();

        //Lấy theo quan hệ anh em
        driver.findElement(By.xpath("//label[@for='id_email']/following-sibling::input")).sendKeys("testing3@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[@for='id_email']/following-sibling::input")).clear();

//Làm thực tế thì nên dùng css hoặc xpath dùng wed nhanh nhất
    }

        @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
