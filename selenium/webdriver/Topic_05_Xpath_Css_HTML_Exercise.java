package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class Topic_05_Xpath_Css_HTML_Exercise {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    @Test
    public void TC_01_Login_Empty_Email_And_Passwords() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class ='footer']//a[text() ='My Account']")).click();

        driver.findElement(By.id("email")).sendKeys();
        driver.findElement(By.name("login[password]")).sendKeys(" ");
        driver.findElement(By.xpath("//button[@title = 'Login']")).click();

        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field");
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"advice-required-entry-pass");
    }
    @Test
    public void TC_02_Login_Invalid_Email(){
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class ='footer']//a[text()='My Account']")).click();

        driver.findElement(By.id("email")).sendKeys("123@345.678");
        driver.findElement(By.name("login[password]")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@title = 'Login']")).click();

        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }
    @Test
    public void TC_03_Login_Invalid_Password(){
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class ='footer']//a[text()='My Account']")).click();

        driver.findElement(By.id("email")).sendKeys("buithom199@gmail.com");
        driver.findElement(By.name("login[password]")).sendKeys("123");
        driver.findElement(By.xpath("//button[@title = 'Login']")).click();

        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

    }
    @Test
    public void TC_04_Login_Incorrect_Password(){
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class ='footer']//a[text()='My Account']")).click();

        driver.findElement(By.id("email")).sendKeys("buithom199@gmail.com");
        driver.findElement(By.name("login[password]")).sendKeys("123456123");
        driver.findElement(By.xpath("//button[@title = 'Login']")).click();

        Assert.assertEquals(driver.findElement(By.id("//li[@class ='error-msg']//span")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }
    //Direct child(đi một node)
    //Xpath: //div/input[@id = 'email']
    //CSS: div>input[id = 'email']

    //Sub child (nhiều node)
    //Xpth: //ul//input[@id ='email']
    //Css: ul input[id = 'email']

    //Xpath vs ID: //input[@id ='email']
    //CSS vs ID: TH1:  input[id = 'email']
    //           TH2: input#email
    //           TH3: #email

    //Xpath vs Class: //div[@class = 'footer']
    //Css vs Class: TH1: div[class = 'footer']
    //              TH2: div.footer
    //              TH3: .footer

    //Trường hợp có nhiều giá trị
    //Css: .name-firstname or .name-firstname.field

    //Xpath vs multiple attribute: //input[@id ='newsletter' and @name='email']
    //Css vs multiple attribute: TH1: input[id = 'newsletter'][name = 'email]
    //                           TH2:  input[id = 'newsletter'][name = 'email][title ='sign up for newsletter']

    //Xpath vs Index: TH1: //ol[@id = 'selectable']/li[6]
    //                TH2: //ol[@id = 'selectable']/li[position() = 6]
    //Css vs Index: li:nth-child(6)

    //Xpath vs First index: TH1: //ol[@id = 'selectable']/li[1]
    //                      TH2: //ol[@id = 'selectable']/li[position() = 1]
    //Css vs First index: li:first-child

    //Xpath vs Last index: //ol[@id = 'selectable']/li[last()]
    //Css vs Last index: li:last-child

    //Xpath vs contains://li[contains(@class,'ui-selected']
    //Css vs contains: li[class* = 'ui-selected]
    // **Note: Chỉ add được với attribute, không add được với text

    //Xpath vs start with: //li[starts-with(@class,'ui-state')]
    //Css vs start with: li[class^ = 'ui-state']

    //Xpath vs end with: Không support
    //Css vs end with: li[class$ = 'ui-selected']

    //Vì sao Css chỉ có following-sibling mà không có preceding-sibling hoặc parent
    //=>Css không có cơ chế đi ngược node lên trên
    //Xpath vs following-sibling: //label[@for = 'firstname']/following-sibling::div/input
    //Css vs following-sibling: label[for = 'firstname']+div>input

    //Xpath vs following-sibling(multiple-node): //div[@class = 'field name-firstname']/following-sibling::div
    //Css vs following-sibling(multiple-node): div.name-firstname~div

    //Xpath vs Or: .//input[@id = 'password' or @name = 'password']
    //Css vs Or: TH1: #password,.validate-password
    //           TH2: #password,.validate-password,input[name = 'password']
    //           TH3: #password,.validate-password,input[name = 'confirmation']

    //Chrome - element Tab (String/Xpath/Css)
    //Chrome - Console Tab: Verify lại giá trị: Xpath: $x("xpath"), Css: $$("css"), JQuery: $("css")
    //Xpath: $x("//input[@id ='email'")
    //Css: $$("input[id = 'email']")

    //Tóm tắt:

    // Tag name & Attribute & Value ->Check attribute value = unique
    //  //tagname[@attribute = 'value']
    //  1 - //input[@id='email']
    //  2 - //input[@title='Email Address']
    //  3 - //input[@name = 'login[username]'
    //Priority:
    // 1-Unique +Meaning
    // 2-Attribute: ID/Class/Name
    // 3-Attribute value meaning - related to field name
    // 4-NOT (ID/Class/name) =>Any attribute + Unique + Related to field name
    // 5-Link -Don't use with attribute = 'href (server)
    //Parent node (Lấy từ đời cha trở xuống)
    //  - Khi không thể định danh được element bằng các thẻ và attribute của nó
    //   //parent-tagname[@attribte = 'value']//child-tagname[@attribute = 'value']
    //   1-//div[@class = 'footer']//a[@title='My Account']
    //   2-//div[@class = 'footer-container']//a[@title = 'My Account']
    // Parent node (Lấy từ đời cha trở lên)
    //   //li[@class = 'success-msg']//span
    // Lấy tuyệt đối (=)
    //   Lấy text()=: Chứa giá trị tuyệt đối trong chuỗi
    //    //div[@class='footer-container']//a[(text()='My Account')] =>chạy nhanh hơn vì nó quét phạm vi hẹp
    //   Lấy @attribute()=: Chứa giá trị tuyệt đối trong attribute
    //    //div[@class = 'footer-container']//a[@title='My Account']
    // Lấy tương đối(,)
    // contains():text()||attribute(value) =>chạy chậm hơn vì nó quét phạm vi rộng lớn hơn
    // //div[@class='footer-container']//a[contains(text(),'My Account')]
    // //div[@class='footer-container']//a[contains(@title,'My Account')]
    // //div[@class='footer']//a[contains(@href,'account')]
    // start-with:text()||@attribute
    // 1-//div[@class ='footer-container']//a[starts-with(text(),'My Account')]
    // 2-//div[@class='flipBannerWrap']//iframe[starts-with(@id,'viz_iframe')]
    // starts-with:text()||@attribute
    // //input[starts-with(@data-spm-anchor-id,'a2o4n.login_signup')]
    // Khi automation test ở level là Functional UI (giả lập hành vi của End User thì thao tác với text rất nhiều
    // Có 5 loại:
    // 1. text()='....'
    // 2. contains[text(),'.....']
    // 3. contains[..,'....']
    // 4. contains[string(),'.....']
    // 5. concat()
    //Nested text (text lồng nhau đều nằm trong 1 thẻ: h3[@id='nested']
    //text()=
    //   text nằm ở trên chính node (tagname) đó-không nằm trong child node-hoặc dạng nested text
    //   lấy text tuyệt đối =>không có khoảng trắng/xuống dòng/tab ở đầu hoặc cuối chuỗi
    //   //div[@id='advice-required-entry-email' and text() = 'This is a required field']
    //contains(text(),''):
    //    text nó nằm ở trên chính node đó- hoặc dạng nested text nhưng text phải nằm ở đầu tiên
    //    text này có khoẳng trắng/xuống dòng/tab đầu dòng hoặc cuối text vẫn làm việc được
    //    nếu text nằm trực tiếp trong child node thì không lấy được
    //    Không dùng contains(text()='') mà dùng contains(text(),'')
    //    //h3[contains(text(),'Michael Jackson')]
    //contains(..,'')
    //    text nó nằm ở trên chính node đó-hoặc nằm trong child node ở bất kỳ vị trí nào-hoặc nested text ở bất kỳ vị trí nào
    //    text này có khoảng trắng/xuống dòng/tab ở đầu dòng hoặc cuối chuỗi vẫn làm việc được
    //    dùng contains(.,' ') không dùng contains,='')
    //    //h3[contains(.,'Michael Jackson']
    //contains(string(),''):
    //    text nó nằm ở trên node đó hoặc nằm trong child node ở bất kỳ vị trí nào hoac85 nested text cũng đươc
    //    text này có khoảng trắng/xuống dòng/tab nằm ở đầu hoặc cuối vẫn làm việc được
    //    dùng contains(string(),'') không dùng contains(string()='')
    //string()method ->verify text in node tree
    //  string(//fieldset[1])
    //concat()method
    //   //span[text()=concat("Hello"John",What's happened?")]
    //and (kết quả trả về tuyệt đối-cả 2 đều đúng)
    //  //tag[@attribute and @attribute/text()]
    //  //*[@id='email' and @type='email']
    //or (kết quả trả về tương đối 1 trong 2 đúng
    //  //tag[@attribute and @attribute/text()]
    //  //*[@id ='email' or @type = 'email']
    //last() and position()
    //  //tag[@attribute = 'value'][position()=' ']
    //  //tag[@attribute = 'value'][last()]
    //Parent (cha): //a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']/child::button
    //preceding-sibling (anh của node hiện tại): //span[contains(text(),'Shipping')]/preceding-sibling::span
    //                                          //input[@name='currency_payement']/preceding-sibling::b
    //following-sibling (em của node hiện tại): //td[contains(text(),'CustomerID']/following-sibling::td














}