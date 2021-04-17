package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_15_Javascrip_Executor {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;

    @BeforeTest
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "D://ECLIPSE//lib0//chromedriver_win32//chromedriver.exe");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, 15);
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().window();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }


    public void TC_01_Click_Hidden_Element() {
        driver.get("https://www.zingpoll.com/");

        WebElement vietnamLanguage = driver.findElement(By.xpath("//li[@class ='dropdown hidden-xs']//a[contains (string(), 'Tiếng Việt')]"));
        jsExecutor.executeScript("arguments[0].click();", vietnamLanguage);
        sleepInSecond(10);

    }

    public void TC_02(){
        navigateToUrlByJS(driver,"http://live.demoguru99.com/");
        String homepageDomain = (String) executeForBrowser(driver,"return document.domain;");
        Assert.assertEquals(homepageDomain, "live.demoguru99.com");

        String homepageUrl = (String) executeForBrowser(driver,"return document.URL");
        Assert.assertEquals(homepageUrl,"http://live.demoguru99.com/");

        highlightElement(driver,"//a[text() ='Mobile']");
        clickToElementByJS(driver,"//a[text() ='Mobile']");

        highlightElement(driver,"//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        clickToElementByJS(driver,"//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

  highlightElement(driver,"//li[@class='success-msg']");
  Assert.assertTrue(getInnerText(driver).contains("Samsung Galaxy was added to your shopping cart."));

  highlightElement(driver,"//a[text()='Customer Service']");
  clickToElementByJS(driver,"//a[text()='Customer Service']");

  highlightElement(driver,"//input[@id='newsletter']");
  scrollToElement(driver,"//input[@id='newsletter']");
  sendkeyToElementByJS(driver,"//input[@id='newsletter']",getEmailRandom());

  highlightElement(driver,"//button[@class ='button']");
  clickToElementByJS(driver,"//button[@class ='button']");

  highlightElement(driver,"//li[@class = 'success-msg']");
  Assert.assertTrue(getInnerText(driver).contains("Thank you for your subscription."));

  navigateToUrlByJS(driver,"http://demo.guru99.com/v4/");
  String demoGuruDomain = (String) executeForBrowser(driver,"return document.domain");
    Assert.assertEquals(demoGuruDomain,"demo.guru99.com");
    }
    @Test
    public void TC_03(){
        navigateToUrlByJS(driver,"https://login.ubuntu.com/");

        clickToElementByJS(driver,"//button[@id ='cookie-policy-button-accept']");

        sendkeyToElementBySelenium(driver,"//form[@id='login-form']/div[@class ='p-form-validation email-input']/input[@id='id_email']","qqq");
        clickToElementByJS(driver,"//button[@data-qa-id='login_button']");
        Assert.assertEquals(getHTML5ValidationMessage(driver,"//form[@id='login-form']/div[@class ='p-form-validation email-input']/input[@id='id_email']"),"Please include an '@' in the email address. 'qqq' is missing an '@'.");

        sendkeyToElementBySelenium(driver,"//form[@id='login-form']/div[@class ='p-form-validation email-input']/input[@id='id_email']","123");
        clickToElementByJS(driver,"//button[@data-qa-id='login_button']");
        Assert.assertEquals(getHTML5ValidationMessage(driver,"//form[@id='login-form']/div[@class ='p-form-validation email-input']/input[@id='id_email']"),"Please include an '@' in the email address. '123' is missing an '@'.");

        sendkeyToElementBySelenium(driver,"//form[@id='login-form']/div[@class ='p-form-validation email-input']/input[@id='id_email']","123@");
        clickToElementByJS(driver,"//button[@data-qa-id='login_button']");
        Assert.assertEquals(getHTML5ValidationMessage(driver,"//form[@id='login-form']/div[@class ='p-form-validation email-input']/input[@id='id_email']"),"Please enter a part following '@'. '123@' is incomplete.");

        sendkeyToElementBySelenium(driver,"//form[@id='login-form']/div[@class ='p-form-validation email-input']/input[@id='id_email']","123@...");
        clickToElementByJS(driver,"//button[@data-qa-id='login_button']");
        Assert.assertEquals(getHTML5ValidationMessage(driver,"//form[@id='login-form']/div[@class ='p-form-validation email-input']/input[@id='id_email']"),"'.' is used at a wrong position in '...'.");

        sendkeyToElementBySelenium(driver,"//form[@id='login-form']/div[@class ='p-form-validation email-input']/input[@id='id_email']","example@gmail.com");
        clickToElementByJS(driver,"//button[@data-qa-id='login_button']");
        Assert.assertEquals(getHTML5ValidationMessage(driver,"//form[@id='login-form']/div[@class ='p-form-validation password-input']/input[@id='id_password']"),"Please fill out this field.");
    }

    public void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);

    }

    public String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }
    public void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }
    public void sendkeyToElementBySelenium(WebDriver driver, String locator, String value){
        getElement(driver,locator).clear();
        getElement(driver,locator).sendKeys(value);
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, 15);
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getHTML5ValidationMessage(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }
    
    public WebElement getElement(WebDriver driver, String locator){
        WebElement element = driver.findElement(By.xpath(locator));
        return element;
    }
    //Để không bị trùng email chúng ta dùng hàm getEmailRandom để lọc theo thứ tự email
    public String getEmailRandom(){
        Random rand = new Random();
        return "thombt" + rand.nextInt() + "@cnv.vn";

    }
}
