package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Topic_30_WebDriver_Wait_Part_II {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver","D://ECLIPSE//lib0//chromedriver_win32//chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

    }
    @Test
    public void TC_01_Find_Element(){
        // 1- Tìm được 1 element
        // Nếu như tìm thấy ngay thì không cần chờ hết timeout
        // Nếu như chưa tìm thấy thì tiếp tục chờ - trong thời gian chờ mỗi nửa s sẽ tìm lại 1 lần
        // Nếu như tìm thấy vẫn đang còn trong thời gian chờ thì không cần chờ hết timeout
        // Chuyển qua step tiếp theo
        System.out.println("Start -" + getDateTimeNow());
        driver.findElement(By.id("email"));
        System.out.println("End-"+getDateTimeNow());

        // 2- Tìm thấy nhiều hơn element
        // Nó sẽ thao tác với element đầu tiên (không quan tâm element này ẩn hay hiện)
        System.out.println("Start -" + getDateTimeNow());
        driver.findElement(By.xpath("//input[@id ='email' or @id = 'pass']")).sendKeys("dam@gmail.com");
        System.out.println("End-"+getDateTimeNow());


        // 3- Không thấy element nào
        // Chờ hết timeout rồi mà vẫn không tìm thấy element
        // Đánh testcase này fail ngay lập tức tại đúng step này
        // Throw ra 1 exception: No such element
        System.out.println("Start -" + getDateTimeNow());
        try{
            driver.findElement(By.xpath("//label"));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("End-"+getDateTimeNow());
        }



    }

    public void TC_02_Find_Elements() {
    }
    public void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }
}
