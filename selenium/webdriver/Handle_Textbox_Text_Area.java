package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Handle_Textbox_Text_Area {
    WebDriver driver;
    String loginPageUrl,userID,password, customerID;
    String name, dob, address, city, state, pin, phone, email;
    String editAddress, editCity, editState, editPin, editPhone, ediEmail;

    By nameTextboxBY = By.name("name");
    By dobTextboxBY = By.name("dob");
    By addressTextboxBY = By.name("addr");
    By cityTextboxBY = By.name("city");
    By stateTextboxBY = By.name("state");
    By pinTextboxBY = By.name("pinno");
    By phoneTextboxBY = By.name("telephoneno");
    By emailTextboxBY = By.name("emailid");
    By passwordTextboxBY = By.name("password");
    By genderRadioBy = By.name("gender");

    //Khi mà driver và browser chưa được khởi tạo + app cũng chưa được mở lên ->tìm element ở đâu?
    //ko dùng kiểu này
    //WebElement emailTextbox = driver.findElement(By.name("name"));

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/v4");

        name ="John Kennedy ";
        dob = " 1960-01-01";
        address = "564 Suitable Address ";
        city = "New York" ;
        state = "Califonia ";
        pin = "999666 ";
        phone = " 0895892999";
        email = "automationfc.vn@gmail.com ";

        editAddress = "798 PO Address ";
        editCity= "Califonia ";
        editState= " New York";
        editPin= "657987 ";
        editPhone= "0895333777 ";
        ediEmail= "automationfc.com@gmail.com ";


    }

    @Test
    public void TC_01_Register() {
        loginPageUrl = driver.getCurrentUrl();
        driver.findElement(By.xpath("//a[text()='here'")).click();
        driver.findElement(By.name("email")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.name("btnLogin")).click();
        //Get thông tin User/Pass ra lưu vào biến
        userID = driver.findElement(By.xpath("//td[text()='User ID:']/flowing-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password:']//following-sibling::td")).getText();
    }

    @Test
    public void TC_02_Login() {
        driver.get(loginPageUrl);
        //set giá trị từ biến vào form đăng nhập
        driver.findElement(By.name("uid")).sendKeys(userID);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("btnLogin")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manager Id:mngr303746']")).isDisplayed());

    }
    @Test
    public void TC_03_New_Customer(){
        driver.findElement(By.xpath("//a[text() = 'New Customer']")).click();
        driver.findElement(By.xpath("//a[text() = 'New Customer']")).click();
        driver.findElement(nameTextboxBY).sendKeys(name);
        driver.findElement(dobTextboxBY).sendKeys(dob);
        driver.findElement(addressTextboxBY).sendKeys(address);
        driver.findElement(cityTextboxBY).sendKeys(city);
        driver.findElement(stateTextboxBY).sendKeys(state);
        driver.findElement(pinTextboxBY).sendKeys(pin);
        driver.findElement(phoneTextboxBY).sendKeys(phone);
        driver.findElement(emailTextboxBY).sendKeys(email);
        driver.findElement(passwordTextboxBY).sendKeys(password);

        driver.findElement(By.name("sub")).click();

        //Server process + Response (output)
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()= 'Customer Registered Successfully!!!]")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Customer Name']/following-sibling::td")).getText(),name);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Birthdate']/following-sibling::td")).getText(),dob);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Address']/following-sibling::td")).getText(),address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'State']/following-sibling::td")).getText(),state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Pin']/following-sibling::td")).getText(),pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Mobile No']/following-sibling::td")).getText(),phone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()= 'Email']/following-sibling::td")).getText(),email);

        customerID = driver.findElement(By.xpath("//td[text() = 'Customer ID]/following-sibling::td")).getText();



    }
    @Test
    public void TC_04_Edit_Customer(){
        driver.findElement(By.xpath("//a[text() = 'Edit Customer']")).click();
        driver.findElement(By.name("cusid")).sendKeys(customerID);
        driver.findElement(By.name("AccSubmit")).click();
        //Verify 3 fields are disabled
        Assert.assertFalse(isElementEnabled(nameTextboxBY));
        Assert.assertFalse(isElementEnabled(genderRadioBy));
        Assert.assertFalse(isElementEnabled(dobTextboxBY));

        Assert.assertEquals(driver.findElement(nameTextboxBY).getAttribute("value"),name);
        Assert.assertEquals(driver.findElement(dobTextboxBY).getAttribute("value"),dob);
        Assert.assertEquals(driver.findElement(addressTextboxBY).getText(),address);
        Assert.assertEquals(driver.findElement(cityTextboxBY).getAttribute("value"),city);
        Assert.assertEquals(driver.findElement(stateTextboxBY).getAttribute("value"),state);
        Assert.assertEquals(driver.findElement(pinTextboxBY).getAttribute("value"),pin);
        Assert.assertEquals(driver.findElement(phoneTextboxBY).getAttribute("value"),phone);
        Assert.assertEquals(driver.findElement(emailTextboxBY).getAttribute("value"),email);

        //Edit Customer
        driver.findElement(addressTextboxBY).clear();
        driver.findElement(addressTextboxBY).sendKeys(editAddress);
        driver.findElement(cityTextboxBY).clear();
        driver.findElement(cityTextboxBY).sendKeys(editCity);
        driver.findElement(stateTextboxBY).clear();
        driver.findElement(stateTextboxBY).sendKeys(editState);
        driver.findElement(pinTextboxBY).clear();
        driver.findElement(pinTextboxBY).sendKeys(editPin);
        driver.findElement(phoneTextboxBY).clear();
        driver.findElement(phoneTextboxBY).sendKeys(editPhone);
        driver.findElement(emailTextboxBY).clear();
        driver.findElement(emailTextboxBY).sendKeys(ediEmail);
        driver.findElement(By.name("sub")).click();

        //Server process + Response (output)
        Assert.assertTrue(driver.findElement(By.xpath("//p[text() = 'Customer details updated Successfully!!")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Birthdate']/following-sibling::td")).getText(),dob);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Address']/following-sibling::td")).getText(),editAddress);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'State']/following-sibling::td")).getText(),editState);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Pin']/following-sibling::td")).getText(),editPin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Mobile No]/following-sibling::td")).getText(),editPhone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='Email]/following-sibling::td")).getText(),ediEmail);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text() = 'Customer ID]/flowing-sibling::td")).getText(),customerID);




    }
    @AfterClass
    public  void afterClass(){
        driver.quit();
    }
    public boolean isElementEnabled(By by){
        WebElement element = driver.findElement(by);
        if(element.isEnabled()){
            return true;
        }else {
            return false;
        }
    }
}