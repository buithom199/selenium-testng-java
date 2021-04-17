package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sun.font.TrueTypeFont;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_Element_Part_I {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/multiple-fields/");
    }

    @Test
    public void TC_01_WebElement_Command() {
        //1-Thao tác trực tiếp lên element + không cần khai báo biến
        //Chỉ dùng 1 lần duy nhất không cần khai báo biến
        //driver.findElement(By.xpath("//input[@id='email']")).sendKeys("selenium_19@gmail.com");

        //2-Khai báo biến rồi mới thao tác (action: click/sendkey/getText/select...)
        //*Chú ý: 1. Nếu muốn thao tác với 1 element: WebElement
        //        2. Nếu muốn thao tác với nhiều element (2 trở lên):List <WebElement>
//Dùng nhiều lần thì nên khai báo biến (đỡ viết lại code)

//        List<WebElement> checkboxes=driver.findElements(By.xpath("//input[@type='checkbox']"));
//        System.out.print("Tổng số checkbox tại page này = "+ checkboxes.size());
//        for(WebElement checkbox: checkboxes){
//            checkbox.click();
        //}
        //driver.findElements(By.xpath("//input[@id='email']"));
        //Xóa dữ liệu trong 1 textbox/textarea/dropdown (editable)
       WebElement element = driver.findElement(By.xpath(""));
        //Xóa dữ liệu trong 1 textbox/textarea/dropdown (editable)
         element.clear();
//Nhập dữ liệu vào 1 textbox/textarea/dropdoen (editable)
        element.sendKeys("Anh Phạm");
        //Click vào buttoncheckbox/radio button/link/dropdown/image..
        element.click();
        element.getAttribute("placeholder");
        //Search entire store here...

        //Lấy ra style của 1 element (font/size/color/backgroung/->GUI
        element.getCssValue("background");
        element.getLocation();
        element.getRect();
        element.getSize();
        //Lấy ra text của 1 element bất kỳ (label/span/header/div...) text ko nằm trong atribute
        element.getText();
        //Chia thành 3 nhóm chính:
        //action: click,senkey
        //lấy dữ liệu: getCssValue, getTagname,getText,getAttribute...

        //Chụp hình lỗi đưa vào report
        //element.getScreenshotAs(target);

        String emailTextboxTagname = element.getTagName();
        Assert.assertEquals(emailTextboxTagname,"input");
        //input/div/span
        //Đầu ra của hàm này ->tên thẻ ->đầu vào của 1 element khax1 (tagname trong xpath)
        driver.findElement(By.xpath("//" + emailTextboxTagname + "[@id='email']"));

//Kiểm tra tính đúng đắn: đúng/true sai:false
//Kiểm tra 2 giá trị bằng nhau:equal

        //Kiểm tra mong muốn element
        Assert.assertTrue(element.isDisplayed());;
        //kiểm tra không mong muốn hiển thị
        Assert.assertFalse(element.isDisplayed());
        //Kiểm tra cho element có thể tương tác được
        Assert.assertTrue(element.isEnabled());
        //Kiểm tra 1 element đã và đang hiển thị thành công (checkbox/radio)
        Assert.assertTrue(element.isSelected());

        //Enter vào trong 1 form (chỉ dùng được với form)
        element.submit();





    }

}
