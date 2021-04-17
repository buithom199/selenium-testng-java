package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Element_Part_II {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    public void TC_01_Displayed() throws Exception {
        driver.get("https://automationfc.github.io/basic-form/");
        boolean emailTextboxStatus =driver.findElement(By.cssSelector("#mail")).isDisplayed();
        if(emailTextboxStatus ==true){
            driver.findElement(By.cssSelector("#mail")).sendKeys("Automation Testing");
            Thread.sleep(5000);
            System.out.print("Email text box is displayed");
        }
        else {
            System.out.print("Email text box is not displayed");
        }
        boolean educationTextAresStatus =driver.findElement(By.cssSelector("#edu")).isDisplayed();
        if(educationTextAresStatus ==true){
            driver.findElement(By.cssSelector("#edu")).sendKeys("Automation Testing");
            System.out.print("Education Text Area is displayed");
        }
        else {
            System.out.print("Education Text Area is not displayed");
        }
        boolean ageUnderEighteenRadioStatus =driver.findElement(By.cssSelector("#under_18")).isDisplayed();
        if(ageUnderEighteenRadioStatus ==true){
            driver.findElement(By.cssSelector("#under_18")).click();
            System.out.print("Eighteen under 18 is displayed");
        }
        else {
            System.out.print("Eighteen under 18 is not displayed");
        }
        boolean nameUserFiveTextStatus =driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed();
        if(nameUserFiveTextStatus ==true){
            driver.findElement(By.cssSelector("#under_18")).click();
            System.out.print("Name User 5 is displayed");
        }
        else {
            System.out.print("Name User 5 is not displayed");
        }

    }

    public void TC_02_Enable() {
        driver.get("https://automationfc.github.io/basic-form/");
        boolean emailTextboxStatus =driver.findElement(By.cssSelector("#mail")).isEnabled();
        if(emailTextboxStatus ==true){
            System.out.print("Email text box is enabled");
        }
        else {
            System.out.print("Email text box is not enabled");
        }
        boolean SliderOneStatus =driver.findElement(By.cssSelector("#slider-1")).isEnabled();
        if(SliderOneStatus ==true){
            System.out.print("Slider 1 is enabled");
        }
        else {
            System.out.print("Slider 1 is not enabled");
        }
        boolean SliderTwoStatus =driver.findElement(By.cssSelector("#slider-2")).isEnabled();
        if(SliderOneStatus ==true){
            System.out.print("Slider 2 is enabled");
        }
        else {
            System.out.print("Slider 2 is not enabled");
        }

    }
    @Test
    public void TC_03_Selected(){
        driver.get("https://automationfc.github.io/basic-form/");
        //Click to age Under 18/Language Java
        driver.findElement(By.id("under_18")).click();
        driver.findElement(By.id("java")).click();

        //Verify element are selected
        Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.id("java")).isSelected());

        //Click to age Under 18/Language Java
        driver.findElement(By.id("under_18")).click();
        driver.findElement(By.id("java")).click();

        //Verify element is selected
        Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
        //Verify element is not selected
        Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
        System.out.print("Under 18 is selected");
        System.out.print("Java is not selected");

    }

}
