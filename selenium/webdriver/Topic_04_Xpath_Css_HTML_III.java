package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Xpath_Css_HTML_III {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void TC_01_Xpath_Format(){
        // ID
        driver.findElement(By.xpath("//input[@id='small-searchterms']")).isDisplayed();
        //Class
        driver.findElement(By.xpath("//input[@class='search-box-text ui-autocomplete-input']")).isDisplayed();
        //Arial-label
        driver.findElement(By.xpath("//input[@aria-label='Search store']")).isDisplayed();
        //Xpath có thể kết hợp với các cách khác nhau như:
        //quan hệ cha con, quan hệ anh em
        //Index
        driver.findElement(By.xpath("//input[@class='search-box store-search-box']//input[1]")).isDisplayed();
        //Name
        driver.findElement(By.xpath("//input[@name='q']")).isDisplayed();
        //Place Holder
        driver.findElement(By.xpath("//input[@placeholder='Search store']")).isDisplayed();
        //quan hệ anh em
        driver.findElement(By.xpath("//label[@for='FirstName']/following-sibling::input")).isDisplayed();
        //xpath span (text): //span[contains(text(),'Sony Xperia')]
        //xpath thẻ from: //form[contains(@action,'updatePost')]
        //xpath thẻ a: //a[contains(@title, 'Advance')]
        //xpath thẻ a có chứa link: //a[contains(@href,'advanced')]
        //xpath thẻ td: //td[contains(text(),'Subtotal')]


        //**Attribute có @ không có mở ngoặc, đóng ngoặc =>Hàm là phải có đóng ngoặc, mở ngoặc
        //**Text có đóng ngoặc, mở ngoặc, không có @

        //Trường hợp attribute động thì nên dùng starts-with: //input[starts-with(@data-spm-anchor-id,'a2o4n')]

        //Trường hợp có 2 thằng giống nhau, có chung 1 href, 1 title và không xác định được: //a[@title="My Account"] có 2 matching node
        // thì chúng ta xét lên cha cùa nó: //div[@id ='header-account']//a[@title="My Account"] my account thứ nhất
        //my account thứ 2: //div[@class ='footer']//a[@title="My Account"]

        //Trường hợp thẻ không có attribute: //span thì cũng lấy theo parent node: //li[@class ='success-msg']//span

        //** Trường hợp lấy tuyệt đối gọi là phép "=" còn lấy tương đối gọi là "contain" hoặc là "start-with", "end-with"
        //text là hàm của Xpath : //span[text() ='Sony Xperia was added to your shopping cart.']=>với cách này nêu thiếu hoặc dư 1 ký tự, 1 khoảng trắng thì tìm cũng không ra
        //Khi có 2 giá trị giống nhau thì lấy lên thằng cha của nó //h2/a[text()='Sony Xperia']=> nằm 2 thẻ khác nhau

        //**Nếu trong code HTML có khoảng trắng ở đầu thì dùng tương đối, tương đối thì dùng dấu phẩy: //span[contains(text(),'Sony Xperia')]
        //hoặc: //form[contains(@action,'updatePost')]

        //attribute thì có @ không có mở ngoặc, đóng ngoặc:  //a[contains(@title,'Search')]
        //text thì có không có @ nhưng có mở ngoặc, đóng ngoặc: //td[contains(text(),'Subtotal')]
        //start-with dùng trong trường hợp text nó động: //input[start-with(@data-spm-anchor-id,'a2o4n.login_signup']

        //Khi các giá trị nằm ở các node khác nhau, không thẳng hàng thì dùng contains(.) hoặc contains(tring()). 2 thằng này không khác gì nhau
        //h5[contains(., '04:45PM')] hoặc //h5[contains(string(),'04:45PM']

        //Để verify hàm đó là gì thì dùng string bọc hàm đó lại:
        //string(//h5[@id='nested']

        //Trường hợp có nháy đôi dùng concat, text chứa dấu nháy đơn thì bọc trong dấu nháy đôi, text chứa dấu nháy đôi thì bọc trong dấu nháy đơn
        //span[text()=concat('Hello "John",What'', ""s happened?"")]

        //Tìm Xpath bằng cách kết hợp cả 2 điều kiện And và Or
        //input[@type='email'and @id='email']
        //a[contains(@href,'forgotpassword') or text()='Forgot Your Password?']

        //inside: Đề nằm trong 1 thẻ cha thì có thể dùng index lấy thứ tự element ra được
        //outside: Bọc các xpath lại (Nó không cùng 1 cha)
        //Lấy đồng cấp khác cha: (//a[@class =product-image']/img)[1]
        //lấy đồng cấp cùng cha: //a[@class = product-image']/img[1]

        //Làm thế nào để lấy được button Add to Card mà không cần dùng index(số)
        //=>Chỉ dùng tên sản phẩm làm cái khóa
        //  (//button[@title='Add to Card'][1] =>Nó thay đổi thì sẽ không đúng nữa
        //a[text() = 'Samsung Galaxy']/parent::h2/following-sibling::div[@class='ratings]/preceding-sibling::h2
        //a[text() = 'Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button
        //=>đổi tên sản phẩm và sử dụng lại đều được vì nó là Dynamic Text

        //lấy child
        //div[@class='product-info']/child::*

        //lấy cháu:
        //div[@class='product-info']/descendant::*

        // Dựa vào 1 thông tin cố định để lấy ra thông tin không cố định
        //->Xpath Axes
        // Thông tin cố định = thông tin không cố định =>có mối quan hệ với nhau với nhau hay không?
        //tr:dòng, td: cột
        ////td[text() = 'Customer ID']/following-sibling::td
        ////span[text() = 'Total']/preceding-sibling::span



































    }
}
