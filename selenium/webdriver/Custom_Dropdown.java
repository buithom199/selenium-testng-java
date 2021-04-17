package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    //Các hành vi của dropdown
    //1. Click vào dropdown
    //2. Chờ cho các item được hiển thị
    //3. Tìm các item cần chọn
    //4. Bấm vào
    //5. Kiểm tra xem chọn đúng chưa
    //6. Nếu item mình cần nằm trong tầm nhìn thấy ->có thể click được
    //7. Nếu item mình cần không nằm trong tầm nhìn thấy ->dùng hàm để viết
    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TC01_JQuery(){
        //1. Cần quan tâm đến locator cho cha
        //2. Quan tâm đến locator con đại diện cho chung chung=>bắt buộc phải lấy đến thẻ có text
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");




    }
//    Hành vi của 1 dropdown
//    - Click vào 1 dropdown
//    - Chờ cho các iteam được hiển thị ra
//    - Tìm cái item cần chọn ->Lấy ra text item mà mình mong muốn->So sánh với cái mình đang mong đợi ->Bằng nhau=> Mình phải duyệt qua hết từng thằng item
//        + Item mình cần nó nằm trong tầm nhìn thấy của user->click luôn
//        + Item không nằm trong tầm nhìn thấy (viewport) ->scroll xuống ->click
//    - Bấm vào - Kiểm tra xem chọn đúng chưa

    public void selectItemInCustomerDropdown(String parentXpath, String allitemXpath, String expectedText){
        //Click vào dropdown
        driver.findElement(By.xpath(parentXpath)).click();

        //Chờ cho các item được hiển thị trước khi chọn
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allitemXpath)));
// Muốn duyệt qua từng thằng item thì phải dùng vòng lặp
        //Lấy hết tất cả các item con đưa vào 1 list để duyệt qua
        List<WebElement> allitem = driver.findElements(By.xpath(allitemXpath));
        //Dùng vòng lặp duyệt qua từng item
        for(WebElement item:allitem){
            //Duyệt qua từng cái và getText ra
            //Nếu như text get ra bằng với text mong muốn thì dừng lại và click vào item đó luôn
            //Thoát khỏi vòng lặp
            if(item.getText().equals(expectedText)){
                item.click();
                break;
            }
        }
    }
//    Nội dung cần nắm của framework
//            - Hành vi của chức năng này
//            - Thư viện của Selenium vận dụng hàm nào trong case nào
//            - Ngôn ngữ java để xử lý code
//            - Biết viết thành 1 hàm
}
