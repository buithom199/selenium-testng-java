package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_25_HandleWindown_Tab {
    WebDriver driver;


    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D://ECLIPSE//lib0//chromedriver_win32//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    public void TC_01_Only_Two_Window_Or_Tab() {
        driver.get("https://kyna.vn/");
        //Before click
        String parentID = driver.getWindowHandle();
        System.out.println("ID of page A =" + parentID);
        //Click to Facebook link at Footer
        driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
        //=>Mình nhìn thấy nó chuyển qua trang facebook rồi nhưng driver vẫn còn ở trang kyna

        //After clik
        Set<String> allIDs = driver.getWindowHandles();
        //Switch to Ffacebook pae (tab)
        for (String id : allIDs) {
            System.out.println("All IDs =" + id);
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
            }
        }

        //Verify Ulr is corrected Facebook page
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

        //Switch to Kyna page (parent)
        for (String id : allIDs) {
            System.out.println("All IDs =" + id);
            if (id.equals(parentID)) {
                driver.switchTo().window(id);
            }
        }

        //Verify URL at kyna.vn
        Assert.assertTrue(driver.getCurrentUrl().contains("kyna.vn"));
    }
@Test
    public void TC_02_Greater_Than_Two_Window_Or_Tab() {
        driver.get("https://kyna.vn/");
        String kynaID = driver.getWindowHandle();

        //Click to Facebook at footer
//        driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
//        //Switch to Facebook
//        switchToWindowByTitle("Đăng nhập Facebook");
//        sleepInSecond(3);
//        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.facebook.com/login/?next=https%3A%2F%2Fwww.facebook.com%2Fkyna.vn"));
//        //switch to kyna
//        switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
//        sleepInSecond(3);
//        Assert.assertTrue(driver.getCurrentUrl().contains("https://kyna.vn/"));
//        //Click youtube
//        driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();
//        switchToWindowByTitle("accounts.google.com");
//        sleepInSecond(3);
//        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.youtube.com/channel/UCsYlsiaTIKYxcapupuyOirA"));
//        //Switch to Kyna (parentID)
//        switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
//        Assert.assertTrue(driver.getCurrentUrl().contains("kyna.vn"));
        //Click to Primus
        driver.findElement(By.xpath("//div[@id = 'k-footer']//a[text()='PRIMUS']")).click();
        //switch to Primus
        switchToWindowByTitle("PRIMUS Homepage");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www1.primus.vn/?utm_source=Kyna&utm_campaign=Acquisition_ConversionRate_9092020_MKT&utm_medium=CVS_KynaUser_Des.Mob_Direct__VN_&utm_term=&utm_content=footer_EN"));

        //Close all tabs without kyna
    closeAllTabWithoutParent(kynaID);

    }

    public void switchToWindowByID(String windowID) {
        //Lấy hết tất cả các ID của window/ tab đang có
        Set<String> allIDs = driver.getWindowHandles();
        //Dùng vòng lặp duyệt qua từng giá trị
        for (String id : allIDs) {
            //Mỗi lần duyệt qua 1 giá trị thì kiểm tra điều kiện
            //Nếu như nó không bằng vs giá trị đang so sánh
            if (!id.equals(windowID)) {
                //Switch vào cái ID đó
                driver.switchTo().window(id);
                //Thoát khỏi vòng lặp
                break;

            }
        }
    }
    public void switchToWindowByTitle(String expectedWindowTittle){
        Set<String> allIDs = driver.getWindowHandles();
        for (String id :allIDs){
            driver.switchTo().window(id);
            String actualWindowTitle = driver.getTitle();
            if (actualWindowTitle.equals(expectedWindowTittle)){
                break;
            }
        }

    }
    public void closeAllTabWithoutParent(String windowID){
        Set<String> allIDs = driver.getWindowHandles();
        for(String id : allIDs){
            if (!id.equals(windowID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
    }
    public void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}