import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static WebDriver driver;

    @Step
    public WebDriver lanchDriver(String browser) {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setPlatform(Platform.WINDOWS);
            desiredCapabilities.setBrowserName(browser);
            desiredCapabilities.setVersion("");

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(desiredCapabilities);

            driver = new RemoteWebDriver(new URL("http://192.168.18.27:9595/wd/hub"),chromeOptions);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        } catch (MalformedURLException e) {
            System.out.println("In catch Block");
        }

        /*System.setProperty("webdriver.chrome.driver","/Users/prdivaka/Selenium Driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/
        return driver;
    }

    @Step
    public void launchUrl(String url){
        try {
            driver.get(url);
            Allure.addAttachment("Url is launched successfully", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        } catch (WebDriverException e) {
            Allure.addAttachment("Url is not launched successfully", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }

    @Step
    public WebElement getElement(String xpath, String elementName){
        WebElement webElement = null;
        try {
            webElement = driver.findElement(By.xpath(xpath));
            //Allure.addAttachment("Webelement -> " + elementName + "is present", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        } catch (NoSuchElementException e) {
            Allure.addAttachment("Webelement -> " + elementName + "is absent", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        return webElement;
    }


}
