package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Topic_02_Xpath_Css_HTML_I {
    WebDriver driver ;
    @BeforeClass
            public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }
@Test
public void TC_01_ValidateCurrentUrl() throws InterruptedException {
        //Hiểu được HTML của 1 element
    //->mục đích của automation là giả lập lại hành động của user
    //Tại sao lại phải bắt element
    //Bắt xong rồi thì làm gì nữa/thao tác như thế nào nữa
    //Thao tác với ĐĂNG KÝ button
    driver.findElement(By.xpath("//form[@id='frmLogin']//button[text() = 'ĐĂNG KÝ']")).click();

    //driver: đại diện cho Selenium WebDriver - gôi thư viện ra để sử dụng
    //finElement: tìm element
    //By.id/ className/ Name / cssSelector / tagName / linkText / partialLinkText: loại locator gì
    //click(): click vào button
    Thread.sleep(5000);

    //Nhập vào textbox Họ Tên
    driver.findElement(By.cssSelector("//input[@id='txtFirstname']")).sendKeys("Automation FC");

    //Nhập vào textbox Password
    driver.findElement(By.id("txtPassword")).sendKeys("123456789");

}
    @AfterClass
    public void afterClass() {
        driver.quit();

    }
}

