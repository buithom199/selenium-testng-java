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
        //Click v??o dropdown
        driver.findElement(By.xpath(parentXpath)).click();

        //Ch??? cho c??c item ???????c hi???n th??? ra tr?????c khi ch???n

        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

        //L???y h???t t???t c??? c??c item con ????a v??o 1 list ????? duy???t
        List<WebElement> allItem = driver.findElements(By.cssSelector(allItemXpath));

        //D??ng v??ng l???p qua t???ng item
        for(WebElement item:allItem){
            //Duy???t qua t???ng c??i v?? getText ra
            //N???u nh?? text get ra b???ng vs text mong mu???n th?? d???ng l???i v?? click v??o item ???? lu??n
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