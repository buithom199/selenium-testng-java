package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Button_Radio_Checkbox {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    private By by;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        //Ép kiểu tường minh
        //Từ Interface này qua InterFace kia
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void TC01_Button(){
        driver.get("https://www.fahasa.com/?attempt=1");
        By loginButton = By.cssSelector(".fhs-btn-login");
        driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
        //Verify login button is disabled
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());
        //Input to email/Password textbox
        driver.findElement(By.cssSelector("#login_username")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.cssSelector("#login_password")).sendKeys("automation");
        sleepInSecond(2);

        //Verify login button is disabled
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        driver.navigate().refresh();
        driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
        removeDisabledAttributeByIS(loginButton);
        sleepInSecond(2);

        driver.findElement(loginButton).click();
        sleepInSecond(2);

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");

    }
    @Test
    public void TC_02_Default_Radio_Checkbox(){
        driver.get("https://automationfc.github.io/multiple-fields/");
        //Select
        driver.findElement(By.xpath("//input[@value = 'Gallstones']")).click();
        //label[contains(text(),' Gallstones ')]/preceding-sibling::input
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value = 'Gallstones']")).isSelected());

        //De-select
        driver.findElement(By.xpath("//input[@value = 'Gallstones']")).click();
        Assert.assertFalse(driver.findElement(By.xpath("//input[@value = 'Gallstones']")).isSelected());

        //Select radio
        driver.findElement(By.xpath("//input[@value = 'I have a loose diet']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value = 'I have a loose diet']")).isSelected());

        //De-select (ko work)
        driver.findElement(By.xpath("//input[@value = 'I have a loose diet']")).click();
        sleepInSecond(1);
        Assert.assertFalse(driver.findElement(By.xpath("//input[@value ='I have a strict diet']")).isSelected());

//        //click all check box
//        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
//        for (WebElement checkbox : checkboxes){
//            checkbox.click();
//            sleepInSecond(1);
//        }
//
//        //Verify all checkboxes are selected
//        for(WebElement checkbox: checkboxes){
//            Assert.assertTrue(checkbox.isSelected());
//        }
    }
    public void TC_03_Custom_Radio_checkbox(){
        driver.get("https://material.angular.io/components/radio/examples");
//        //Click to radio
//        driver.findElement(By.xpath("//input[@value = 'spring']")).click();
//        //verify
//        Assert.assertTrue(driver.findElement(By.xpath("//input[@value = 'Spring']")).isSelected());
//
//        //Thẻ span để click được nhungu lại không verify được
//        driver.findElement(By.xpath("input[@value = 'Spring']/preceding::span[@class = 'mat-radio-outer-circle']")).click();
//        sleepInSecond(5);
//
//        //verify
//        Assert.assertTrue(driver.findElement(By.xpath("input[@value = 'Spring']/preceding::span[@class = 'mat-radio-outer-circle']")).isSelected());
//
//        //Kết hợp cả 2 (khi làm dự án: 1 element mà mình phải design tới 2 loại locator khác nhau-số lượng code tăng lên/bảo trì mất thời gian
//        driver.findElement(By.xpath("input[@value = 'Spring']/preceding::span[@class = 'mat-radio-outer-circle']")).click();
//        sleepInSecond(5);


        By springRadio = By.xpath("//input[@value = 'spring']");
        
        //Dùng hàm clcik của JS (vừa click/verify luôn với thẻ input
        clickByJS(springRadio);
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(springRadio).isSelected());
    }

    private void clickByJS(By springRadio) {
        WebElement element = driver.findElement(by);
        jsExecutor.executeScript("arguments[0].click();",element);
    }

    public void sleepInSecond(long timeout){
        try{
            Thread.sleep(timeout*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void removeDisabledAttributeByIS(By by) {
        WebElement element = driver.findElement(by);
        jsExecutor.executeScript("argumants[0].removeAttribute('disabled')",element);

    }
}
