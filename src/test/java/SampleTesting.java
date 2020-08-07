import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SampleTesting {
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

    @AfterMethod
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void validateGoogleHomePage() {
        driver = lanchDriver("chrome");
        driver.get("https://google.com");
        Assert.assertEquals(driver.getTitle(),"Google");
    }

    @Test
    public void validateGoogleSearchBox() {
        driver = lanchDriver("chrome");
        driver.get("https://google.com");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@name='q']")).isDisplayed(),true);
    }

    @Test
    public void validateGoogleSearchButton() {
        driver = lanchDriver("chrome");
        driver.get("https://google.com");
        Assert.assertEquals(driver.findElement(By.xpath("(//*[@name='btnK'])[2]")).isDisplayed(),true);
    }

    @Test
    public void validateGoogleSearchButtonText() {
        driver = lanchDriver("chrome");
        driver.get("https://google.com");
        String seachButtonText = driver.findElement(By.xpath("(//*[@name='btnK'])[2]")).getAttribute("value");
        Assert.assertEquals(seachButtonText,"Google Search");
    }

    @Test
    public void validateGoogleFeelingLucky() {
        driver = lanchDriver("chrome");
        driver.get("https://google.com");
        Assert.assertEquals(driver.findElement(By.xpath("(//*[@name='btnI'])[2]")).isDisplayed(),true);
    }

    @Test
    public void validateGoogleFeelingLuckyText() {
        driver = lanchDriver("chrome");
        driver.get("https://google.com");
        String feelingLuckyText = driver.findElement(By.xpath("(//*[@name='btnI'])[2]")).getAttribute("value");
        Assert.assertEquals(feelingLuckyText,"I'm Feeling Lucky");
    }

    @Test
    public void validateGoogleSignInButton() {
        driver = lanchDriver("chrome");
        driver.get("https://google.com");
        Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='gb_0f']//a)[2]")).isDisplayed(),true);
    }

    @Test
    public void validateGoogleSignInButtonText() {
        driver = lanchDriver("chrome");
        driver.get("https://google.com");
        String signButtonText = driver.findElement(By.xpath("(//div[@class='gb_0f']//a)[2]")).getText();
        Assert.assertEquals(signButtonText,"Sign in");
    }

    @Test
    public void validateGoogleSignInButtonNavigation() {
        driver = lanchDriver("chrome");
        driver.get("https://google.com");
        WebElement signInButton = driver.findElement(By.xpath("(//div[@class='gb_0f']//a)[2]"));
        signInButton.click();
        Assert.assertEquals(driver.getTitle(),"Sign in - Google Accounts");
    }

}
