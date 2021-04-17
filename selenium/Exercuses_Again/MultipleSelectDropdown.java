package Exercuses_Again;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MultipleSelectDropdown {
    WebDriver driver;
    Select select;
    String firstName, lastName, emailAddress, companyName, password;
    String date, month, year;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        firstName = "John";
        lastName = "Wick";
        companyName = "Hollywood ";
        password = "123456 ";

        date = "30";
        month = "September";
        year = "1986";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }


    @Test
    public void TC_01_MultipleSelector() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        select = new Select(driver.findElement(By.xpath("//selct[@id='job2']")));

        ArrayList<String> allItemText = new ArrayList<String>();
        allItemText.add("Automation");
        allItemText.add("Mobile");
        allItemText.add("Perfomance");
        allItemText.add("Functional UI");

        //Chọn 4 item
        for(String item: allItemText){
            select.selectByVisibleText(item);
        }

        //Kiểm tra dropdown có hỗ trợ multiple select
        Assert.assertTrue(select.isMultiple());

        //Kiểm tra đã chọn đúng 4 item thành công
        List<WebElement> allSelectedIttems = select.getAllSelectedOptions();
        ArrayList<String> allSelectedText = new ArrayList<String>();

        for(WebElement item : allSelectedIttems){
            allSelectedText.add(item.getText());
        }
        Assert.assertEquals(allSelectedText.size(),4);
        Assert.assertEquals(allSelectedText,allItemText);
    }
}