package Exercuses_Again;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomDropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver,30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void TC01_JQuery(){
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']/div","13");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),"13");
        sleepInSecond(4);

        selectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']/div","1");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),"1");
        sleepInSecond(4);

        selectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']/div","19");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),"19");
        sleepInSecond(4);

        selectItemInCustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']/div","5");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),"5");
        sleepInSecond(4);

    }
    public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedText){
        //Click vào dropdown
        driver.findElement(By.xpath(parentXpath)).click();

        //Chờ cho các item được hiển thị ra trước khi chọn

        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

        //Lấy hết tất cả các item con đưa vào 1 list để duyệt
        List<WebElement> allItem = driver.findElements(By.cssSelector(allItemXpath));

        //Dùng vòng lặp qua từng item
        for(WebElement item:allItem){
            //Duyệt qua từng cái và getText ra
            //Nếu như text get ra bằng vs text mong muốn thì dừng lại và click vào item đó luôn
            if(item.getText().equals(expectedText)){

            }
        }
    }
    @Test
    public void TC_02_NoCommerce(){
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");

        selectItemInCustomDropdown("//select[@name ='DateOfBirthDay']","//select[@name ='DateOfBirthDay']/option","18");
        selectItemInCustomDropdown("//select[@name ='DateOfBirthMonth']","//select[@name ='DateOfBirthMonth']/option","July");
        selectItemInCustomDropdown("//select[@name ='DateOfBirthYear']","//select[@name ='DateOfBirthYear']/option","1940");

//        List<WebElement> countries = driver.findElements(By.cssSelector("//select[@name ='DateOfBirthDay']/option"));
//        for(WebElement country : countries){
//            if(country.getText().trim().equals("7"))
//                country.click();
//        }
        sleepInSecond(5);
    }
    public void sleepInSecond(long timeout){
        try{
            Thread.sleep(timeout*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}