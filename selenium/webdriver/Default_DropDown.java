package webdriver;

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

public class Default_DropDown {
    WebDriver driver;
    //Thư viện dùng để xử lý dropdown là select
    Select select;
    //Không new trực tiếp trong before class
    String firstName,lastName,emailAddress,companyName,password;
    String date, month, year;

    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    Actions action;
//Custom thay vì dùng html default cho dropdown ->dev dùng các thư viện hỗ trợ
    //custom sẽ dùng các hàm để móc dữ liệu ra
    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();


        firstName = "John";
        lastName = "Wick";
        emailAddress= "johnwick" + getRandomNumber() +"@hotmail.com" ;
        companyName = "Hollywood";
        password = "123456 ";

        date = "30";
        month = "September ";
        year = "1999 ";


        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");

    }
    @Test(invocationCount = 10)
    public void TC_01_Register(){
        //1- Mở ra trang Register
        driver.findElement(By.cssSelector(".ico-register")).click();

        //2- Điền thông tin vào các field required
        checkToCheckboxOrRadio(By.cssSelector("#gender-male"));
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        //Khởi tạo biến select để thao tác với dropdown Date
        select= new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
        //Các hàm hay sử dụng: getFirstSelectedOption, isMultiple, selectByVisibleText
        //html dropdown có 3 cách dùng với thao tác
        //1. Sử dụng index, nó nhận tham số với integer số nguyên=> index chỉ phù hợp với các dropdown mà cố định luôn giá trị của các item
       //trường hợp mà không cố định index sẽ thay đổi
        //chọn index : chọn ngày 3
       // select.selectByIndex(3);
       // sleepInSecond(5);
        //2.Sử dụng value: chọn ngày 15
        //Nếu như dropdown không có value thì sao? =>khó
       // select.selectByValue("15");
       // sleepInSecond(5);
        //3.Sử dụng text: Chọn ngày 30
        //text->item hiển thị như thế nào thì mình chọn thế đó ->Tương ứng với hành vi của end user
        select.selectByVisibleText(date);
        sleepInSecond(5);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),date);
        Assert.assertEquals(select.getOptions().size(),date);
        Assert.assertEquals(select.getOptions().size(),32);
        //Muốn dropdown với thằng khác phải new select thằng mới
        //Khởi tạo biến select để thao tác với dropdown Month
        select= new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
        select.selectByVisibleText(month);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
        Assert.assertEquals(select.getOptions().size(),month);
        Assert.assertEquals(select.getOptions().size(),13);

        //Kiểm tra dropdown này có phải là multiple hay không?
        //Nếu có hỗ trợ dùng assertTrue, không có hỗ trợ dùng assertFalse
        //Kiểm tra dropdown này không hỗ trợ multiple
      //  Assert.assertFalse(select.isMultiple());
        //deselectall: bỏ hết 1 lần
        //getallselect: trả về list wed element
        //dropdown multiple: verify xem chọn đúng hay sai
       // select.getAllSelectedOptions();

        //Trả về text đã chọn thành công
        //select.getFirstSelectedOption().getText();


        //Khởi tạo biến select để thao tác với dropdown Year
        emailAddress = "john" + getRandomNumber() + "@hotmail.com";
        select = new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthYear']")));
        select.selectByVisibleText(year);
        Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
        Assert.assertEquals(select.getOptions().size(),year);
        Assert.assertEquals(select.getOptions().size(),112);

        //Lấy ra tất cả các item trong dropdown đó
        //List<WebElement> allitems = select.getOptions();
        //Kiểm tra xem số lượng item có bằng vs mong muốn hay không?
        //Assert.assertEquals(allitems.size(),13);

        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Company")).sendKeys(companyName);
        checkToCheckboxOrRadio(By.id("Newsletter"));
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        //3- Đăng ký
        driver.findElement(By.id("register-button")).click();

        //4- Kiểm tra xuất hiện message đăng ký thành công
        Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(),"Your registration completed");

        //5- Vào trang My Account
        driver.findElement(By.cssSelector(".ico-account")).click();

        //Sau khi click xong, màn hình chuyển qua 1 trang html khác rồi nên bắt buộc phải tìm element lại =>lỗi->không tìm thấy element
        //Refresh/load lại trang ->HTML update lại

        //6- Kiểm tra đúng với thông tin đã đăng ký
        Assert.assertTrue(driver.findElement(By.cssSelector("#gender-male")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("#Newsletter")).isSelected());
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),emailAddress);
        Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),companyName);



        select= new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),date);
        Assert.assertEquals(select.getOptions().size(),date);


        select= new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
        Assert.assertEquals(select.getOptions().size(),month);

        select = new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthYear']")));
        Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
        Assert.assertEquals(select.getOptions().size(),year);

        driver.findElement(By.cssSelector(".ico-logout")).click();


    }
//Kiểm tra checkbox or radio nếu chưa chọn thì click để chọn
    public void checkToCheckboxOrRadio (By by){
        WebElement element = driver.findElement(by);
        if(!element.isSelected()){
            element.click();
        }
    }
    //Kiểm tra bỏ chọn, nếu checkbox đã được chọn thì click để bỏ chọn
    public void uncheckToCheckbox(By by){
        WebElement element = driver.findElement(by);
        if(element.isSelected()){
            element.click();
        }
    }
    public void sleepInSecond(long second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public int getRandomNumber(){
        Random rand =  new Random();
        return rand.nextInt(99999);

    }
}
