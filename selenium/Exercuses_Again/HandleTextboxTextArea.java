package Exercuses_Again;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HandleTextboxTextArea {
    WebDriver driver;
    String username ="mngr305718";
    String password = "bAnYhys";

    String loginPageUrl,userID, customerID;
    String name,dob,city,address,state,pin, phone,email;
    String editAddress, editCity, editState, editPin, editPhone,editEmail;

    By nameTextBoxBy = By.name("name");
    By genderRadioBoxBy = By.name("gender");
    By dobTextBoxBy = By.name("dob");
    By addrTextBoxBy = By.name("addr");
    By cityTextBoxBy = By.name("city");
    By stateTextBoxBy = By.name("state");
    By pinoTextBoxBy = By.name("pinno");
    By phoneTextBoxBy = By.name("telephoneno");
    By emailTextBoxBy = By.name("emailid");
    By passwordTextBoxBy = By.name("password");

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
       // driver.get("http://automationfc.github.io/basic-form/");
        driver.get("http://demo.guru99.com/v4");
        name ="Bui Thi Thom";
        dob="1999-09-19";
        city="Ho Chi Minh";
        address="23 nguyen dinh chinh";
        state="phu nhuan";
        pin ="123123";
        phone="0987654321";
        email="thombt@cnv.vn";

        editAddress= "5236 PO Address";
        editCity= "California";
        editState= "NewYork";
        editPin= "965896";
        editPhone= "0987654325";
        editEmail= "thombt123@cnv.vn";


    }
    @Test
    public void TC_01_Register(){
        loginPageUrl = driver.getCurrentUrl();

        driver.findElement(By.xpath("//a[text()='here']")).click();

        driver.findElement(By.name("emailid")).sendKeys("thombt@cnv.vn");

        driver.findElement(By.name("btnLogin")).click();

       //Get thông tin User/Pass và lưu vào biến
         userID = driver.findElement(By.xpath("//td[text() ='User ID :']/following-sibling::td")).getText();
        //td[text() = 'User ID:']/following-sibling::td
         password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();


    }
    @Test
    public void TC_02_Login(){
        driver.get(loginPageUrl);
        //Set giá trị từ biến vào form đăng nhập
        driver.findElement(By.name("uid")).sendKeys(userID);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("btnLogin")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());

    }
    @Test
    public void TC_03_NewCustomer()  {
        driver.findElement(By.xpath("//a[text() ='New Customer']")).click();

       //input
        driver.findElement(nameTextBoxBy).sendKeys(name);
        driver.findElement(dobTextBoxBy).sendKeys(dob);
        driver.findElement(addrTextBoxBy).sendKeys(address);
        driver.findElement(cityTextBoxBy).sendKeys(city);
        driver.findElement(stateTextBoxBy).sendKeys(state);
        driver.findElement(pinoTextBoxBy).sendKeys(pin);
        driver.findElement(emailTextBoxBy).sendKeys(email);
        driver.findElement(phoneTextBoxBy).sendKeys(phone);
        driver.findElement(passwordTextBoxBy).sendKeys(password);
        driver.findElement(By.name("sub")).click();

        //Kiểm tra lại sự hiển thị của message
        Assert.assertTrue(driver.findElement(By.xpath("//p[text() ='Customer Registered Successfully!!!']")).isDisplayed());

        //Server sẽ xử lý và phản hồi lại
        //Out put (Verify)
        //Tên
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer name']/following-sibling::td")).getText(),name);
        //Ngày sinh
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthday']/following-sibling::td")).getText(),dob);
        //Địa chỉ
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
        //City
        Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='City']/following-sibling::td")).getText(),city);
        //State
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
        //Pin
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
        //Mobile No
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No']/following-sibling::td")).getText(),phone);
        //Email
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);

       // userID = driver.findElement(By.xpath("//td[text() ='User ID :']/following-sibling::td")).getText();
       customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();




    }
    @Test
    public void TC_04_EditCustomer()  {
        driver.findElement(By.xpath("//a[text() = 'Edit Customer']")).click();
        driver.findElement(By.name("cusid")).sendKeys("10949");
        driver.findElement(By.name("AccSubmit")).click();

        //Kiểm tra không được phép edit (Verify 3 fields are disabled)
        Assert.assertFalse(isElementEnabled(nameTextBoxBy));
        Assert.assertFalse(isElementEnabled(genderRadioBoxBy));
        Assert.assertFalse(isElementEnabled(dobTextBoxBy));

        //Verify có đúng thông tin tạo ra từ thằng New hay ko?
        //Lưu ý sự khác nhau giữa 2 màn hình: text nằm ngoài thẻ, text ở đâu hàm ở đó
        //Text nằm bên ngoài dùng hàm getText
        //text nằm bên trong dùng hàm getAttribute
        Assert.assertEquals(driver.findElement(nameTextBoxBy).getAttribute("value"),name);
        Assert.assertEquals(driver.findElement(dobTextBoxBy).getAttribute("value"),dob);
        Assert.assertEquals(driver.findElement(addrTextBoxBy).getText(),address);
        Assert.assertEquals(driver.findElement(cityTextBoxBy).getAttribute("value"),city);
        Assert.assertEquals(driver.findElement(stateTextBoxBy).getAttribute("value"),state);
        Assert.assertEquals(driver.findElement(pinoTextBoxBy).getAttribute("value"),pin);
        Assert.assertEquals(driver.findElement(phoneTextBoxBy).getAttribute("value"),phone);
        Assert.assertEquals(driver.findElement(emailTextBoxBy).getAttribute("value"),email);

        //Edit Customer
        By addressTextAreaBy = By.name("name");
        driver.findElement(addressTextAreaBy).clear();
        driver.findElement(addrTextBoxBy).sendKeys(editAddress);
        driver.findElement(cityTextBoxBy).clear();
        driver.findElement(cityTextBoxBy).sendKeys(editCity);
        driver.findElement(stateTextBoxBy).clear();
        driver.findElement(stateTextBoxBy).sendKeys(editState);
        driver.findElement(pinoTextBoxBy).clear();
        driver.findElement(pinoTextBoxBy).sendKeys(editPin);
        driver.findElement(emailTextBoxBy).clear();
        driver.findElement(emailTextBoxBy).sendKeys(editEmail);
        driver.findElement(phoneTextBoxBy).clear();
        driver.findElement(phoneTextBoxBy).sendKeys(editPhone);
        driver.findElement(By.name("sub")).click();



        //Server sẽ xử lý và phản hồi lại
        //Out put (Verify)
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!']")).isDisplayed());
        //Tên
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer name']/following-sibling::td")).getText(),name);
        //Ngày sinh
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthday']/following-sibling::td")).getText(),dob);
        //Địa chỉ
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),editAddress);
        //City
        Assert.assertEquals(driver.findElement(By.xpath("//td[text() ='City']/following-sibling::td")).getText(),editCity);
        //State
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),editState);
        //Pin
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),editPin);
        //Mobile No
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No']/following-sibling::td")).getText(),editPhone);
        //Email
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),editEmail);

       // customerID = driver.findElement(By.xpath("//[text() = 'Customer ID']/following-sibling::td")).getText();
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),customerID);




    }
    //Kiểm tra không được phép edit
    public boolean isElementEnabled(By by){
        WebElement element = driver.findElement(by);
        return element.isEnabled();
    }
}
