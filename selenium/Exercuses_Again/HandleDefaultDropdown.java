package Exercuses_Again;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class HandleDefaultDropdown {
    WebDriver driver;
    Select select;
    String firstName, lastName, emailAddress, companyName, password;
    String date, month, year;



    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        firstName= "John";
        lastName ="Wick";
        companyName="Hollywood ";
        password ="123456 ";

        date ="30";
        month = "September";
        year= "1986";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    public void TC_01_Register() {
        // 1- Mở trang register
        driver.findElement(By.cssSelector(".ico-register")).click();

        //2-  Điền thông tin vào các required
        checkToCheckboxOrRadio(By.cssSelector("#gender-male"));
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        //Khởi tạo biến select để thao tác vs dropdown Date
        select = new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthDay']")));
        //sử dụng text:
        select.selectByVisibleText(date);
        //Trả về text đã chọn thành công
        Assert.assertEquals(select.getFirstSelectedOption().getText(),date);
        Assert.assertEquals(select.getOptions().size(),32);

        //Khởi tạo biến select để thao tác vs dropdown Month
        select = new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthMonth']")));
        select.selectByVisibleText(month);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
        Assert.assertEquals(select.getOptions().size(),13);

        //Khởi tạo biến select để thao tác vs dropdown Year
        select = new Select(driver.findElement(By.cssSelector("select[name ='DateOfBirthYear']")));
        select.selectByVisibleText(year);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
        Assert.assertEquals(select.getOptions().size(),112);

        emailAddress= "johnwick" + getRandomNumber() +"@hotmail.com" ;

        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Company")).sendKeys(companyName);

        checkToCheckboxOrRadio(By.id("Newsletter"));
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        //3- Đăng ký
        driver.findElement(By.id("register-button")).click();

        //4- kiểm tra xuất hiện message đăng ký thành công
        Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(),"Your registration completed");

        //5- Vào trong my account
        driver.findElement(By.cssSelector(".ico-account")).click();

        //6- Kiểm tra đúng với thông tin đăng ký
        Assert.assertTrue(driver.findElement(By.cssSelector("#gender-male")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("#Newsletter")).isSelected());
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),emailAddress);
        Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),companyName);


        select = new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthDay']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),date);

        select = new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthMonth']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),month);

        select = new Select(driver.findElement(By.cssSelector("select[name ='DateOfBirthYear']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),year);

    }

    public void checkToCheckboxOrRadio(By by) {
        WebElement element = driver.findElement(by);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckbox(By by) {
        WebElement element = driver.findElement(by);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    public int getRandomNumber(){
        Random rand = new Random();
        return rand.nextInt(99999);
    }
}