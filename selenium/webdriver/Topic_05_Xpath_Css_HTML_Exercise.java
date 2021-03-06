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
    //Direct child(??i m???t node)
    //Xpath: //div/input[@id = 'email']
    //CSS: div>input[id = 'email']

    //Sub child (nhi???u node)
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

    //Tr?????ng h???p c?? nhi???u gi?? tr???
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
    // **Note: Ch??? add ???????c v???i attribute, kh??ng add ???????c v???i text

    //Xpath vs start with: //li[starts-with(@class,'ui-state')]
    //Css vs start with: li[class^ = 'ui-state']

    //Xpath vs end with: Kh??ng support
    //Css vs end with: li[class$ = 'ui-selected']

    //V?? sao Css ch??? c?? following-sibling m?? kh??ng c?? preceding-sibling ho???c parent
    //=>Css kh??ng c?? c?? ch??? ??i ng?????c node l??n tr??n
    //Xpath vs following-sibling: //label[@for = 'firstname']/following-sibling::div/input
    //Css vs following-sibling: label[for = 'firstname']+div>input

    //Xpath vs following-sibling(multiple-node): //div[@class = 'field name-firstname']/following-sibling::div
    //Css vs following-sibling(multiple-node): div.name-firstname~div

    //Xpath vs Or: .//input[@id = 'password' or @name = 'password']
    //Css vs Or: TH1: #password,.validate-password
    //           TH2: #password,.validate-password,input[name = 'password']
    //           TH3: #password,.validate-password,input[name = 'confirmation']

    //Chrome - element Tab (String/Xpath/Css)
    //Chrome - Console Tab: Verify l???i gi?? tr???: Xpath: $x("xpath"), Css: $$("css"), JQuery: $("css")
    //Xpath: $x("//input[@id ='email'")
    //Css: $$("input[id = 'email']")

    //T??m t???t:

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
    //Parent node (L???y t??? ?????i cha tr??? xu???ng)
    //  - Khi kh??ng th??? ?????nh danh ???????c element b???ng c??c th??? v?? attribute c???a n??
    //   //parent-tagname[@attribte = 'value']//child-tagname[@attribute = 'value']
    //   1-//div[@class = 'footer']//a[@title='My Account']
    //   2-//div[@class = 'footer-container']//a[@title = 'My Account']
    // Parent node (L???y t??? ?????i cha tr??? l??n)
    //   //li[@class = 'success-msg']//span
    // L???y tuy???t ?????i (=)
    //   L???y text()=: Ch???a gi?? tr??? tuy???t ?????i trong chu???i
    //    //div[@class='footer-container']//a[(text()='My Account')] =>ch???y nhanh h??n v?? n?? qu??t ph???m vi h???p
    //   L???y @attribute()=: Ch???a gi?? tr??? tuy???t ?????i trong attribute
    //    //div[@class = 'footer-container']//a[@title='My Account']
    // L???y t????ng ?????i(,)
    // contains():text()||attribute(value) =>ch???y ch???m h??n v?? n?? qu??t ph???m vi r???ng l???n h??n
    // //div[@class='footer-container']//a[contains(text(),'My Account')]
    // //div[@class='footer-container']//a[contains(@title,'My Account')]
    // //div[@class='footer']//a[contains(@href,'account')]
    // start-with:text()||@attribute
    // 1-//div[@class ='footer-container']//a[starts-with(text(),'My Account')]
    // 2-//div[@class='flipBannerWrap']//iframe[starts-with(@id,'viz_iframe')]
    // starts-with:text()||@attribute
    // //input[starts-with(@data-spm-anchor-id,'a2o4n.login_signup')]
    // Khi automation test ??? level l?? Functional UI (gi??? l???p h??nh vi c???a End User th?? thao t??c v???i text r???t nhi???u
    // C?? 5 lo???i:
    // 1. text()='....'
    // 2. contains[text(),'.....']
    // 3. contains[..,'....']
    // 4. contains[string(),'.....']
    // 5. concat()
    //Nested text (text l???ng nhau ?????u n???m trong 1 th???: h3[@id='nested']
    //text()=
    //   text n???m ??? tr??n ch??nh node (tagname) ????-kh??ng n???m trong child node-ho???c d???ng nested text
    //   l???y text tuy???t ?????i =>kh??ng c?? kho???ng tr???ng/xu???ng d??ng/tab ??? ?????u ho???c cu???i chu???i
    //   //div[@id='advice-required-entry-email' and text() = 'This is a required field']
    //contains(text(),''):
    //    text n?? n???m ??? tr??n ch??nh node ????- ho???c d???ng nested text nh??ng text ph???i n???m ??? ?????u ti??n
    //    text n??y c?? kho???ng tr???ng/xu???ng d??ng/tab ?????u d??ng ho???c cu???i text v???n l??m vi???c ???????c
    //    n???u text n???m tr???c ti???p trong child node th?? kh??ng l???y ???????c
    //    Kh??ng d??ng contains(text()='') m?? d??ng contains(text(),'')
    //    //h3[contains(text(),'Michael Jackson')]
    //contains(..,'')
    //    text n?? n???m ??? tr??n ch??nh node ????-ho???c n???m trong child node ??? b???t k??? v??? tr?? n??o-ho???c nested text ??? b???t k??? v??? tr?? n??o
    //    text n??y c?? kho???ng tr???ng/xu???ng d??ng/tab ??? ?????u d??ng ho???c cu???i chu???i v???n l??m vi???c ???????c
    //    d??ng contains(.,' ') kh??ng d??ng contains,='')
    //    //h3[contains(.,'Michael Jackson']
    //contains(string(),''):
    //    text n?? n???m ??? tr??n node ???? ho???c n???m trong child node ??? b???t k??? v??? tr?? n??o hoac85 nested text c??ng ??????c
    //    text n??y c?? kho???ng tr???ng/xu???ng d??ng/tab n???m ??? ?????u ho???c cu???i v???n l??m vi???c ???????c
    //    d??ng contains(string(),'') kh??ng d??ng contains(string()='')
    //string()method ->verify text in node tree
    //  string(//fieldset[1])
    //concat()method
    //   //span[text()=concat("Hello"John",What's happened?")]
    //and (k???t qu??? tr??? v??? tuy???t ?????i-c??? 2 ?????u ????ng)
    //  //tag[@attribute and @attribute/text()]
    //  //*[@id='email' and @type='email']
    //or (k???t qu??? tr??? v??? t????ng ?????i 1 trong 2 ????ng
    //  //tag[@attribute and @attribute/text()]
    //  //*[@id ='email' or @type = 'email']
    //last() and position()
    //  //tag[@attribute = 'value'][position()=' ']
    //  //tag[@attribute = 'value'][last()]
    //Parent (cha): //a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']/child::button
    //preceding-sibling (anh c???a node hi???n t???i): //span[contains(text(),'Shipping')]/preceding-sibling::span
    //                                          //input[@name='currency_payement']/preceding-sibling::b
    //following-sibling (em c???a node hi???n t???i): //td[contains(text(),'CustomerID']/following-sibling::td














}