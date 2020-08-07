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

public class SampleTesting extends BaseTest {
    WebDriver driver;

    @AfterMethod
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void validateGoogleHomePage() {
        driver = lanchDriver("chrome");
        launchUrl("https://google.com");
        Assert.assertEquals(driver.getTitle(),"Google");
    }

    @Test
    public void validateGoogleSearchBox() {
        driver = lanchDriver("chrome");
        launchUrl("https://google.com");
        Assert.assertEquals(driver.findElement(By.xpath("//*[@name='q']")).isDisplayed(),true);
    }

    @Test
    public void validateGoogleSearchButton() {
        driver = lanchDriver("chrome");
        launchUrl("https://google.com");
        WebElement searchButton = getElement("(//*[@name='btnK'])[2]" , "Search Button");
        Assert.assertEquals(searchButton.isDisplayed(),true);
    }

    @Test
    public void validateGoogleSearchButtonText() {
        driver = lanchDriver("chrome");
        launchUrl("https://google.com");
        WebElement searchButton = getElement("(//*[@name='btnK'])[2]" , "Search Button");
        String searchButtonText = searchButton.getAttribute("value");
        Assert.assertEquals(searchButtonText,"Google Searc");
    }

    @Test
    public void validateGoogleFeelingLucky() {
        driver = lanchDriver("chrome");
        launchUrl("https://google.com");
        WebElement searchButton = getElement("(//*[@name='btnI'])[2]" , "Feeling Lucky Button");
        Assert.assertEquals(searchButton.isDisplayed(),true);
    }

    @Test
    public void validateGoogleFeelingLuckyText() {
        driver = lanchDriver("chrome");
        launchUrl("https://google.com");
        WebElement feelingLuckyButton = getElement("(//*[@name='btnI'])[2]" , "Feeling Lucky Button");
        String feelingLuckyText = feelingLuckyButton.getAttribute("value");
        Assert.assertEquals(feelingLuckyText,"I'm Feeling Lucky");
    }

    @Test
    public void validateGoogleSignInButton() {
        driver = lanchDriver("chrome");
        launchUrl("https://google.com");
        WebElement googleSignInButton = getElement("(//div[@class='gb_0f']//a)[2]" , "Google SignIn Button");
        Assert.assertEquals(googleSignInButton.isDisplayed(),true);
    }

    @Test
    public void validateGoogleSignInButtonText() {
        driver = lanchDriver("chrome");
        launchUrl("https://google.com");
        WebElement googleSignInButton = getElement("(//div[@class='gb_0f']//a)[2]" , "Google SignIn Button");
        String signButtonText = googleSignInButton.getText();
        Assert.assertEquals(signButtonText,"Sign in");
    }

    @Test
    public void validateGoogleSignInButtonNavigation() {
        driver = lanchDriver("chrome");
        launchUrl("https://google.com");
        WebElement googleSignInButton = getElement("(//div[@class='gb_0f']//a)[2]" , "Google SignIn Button");
        googleSignInButton.click();
        Assert.assertEquals(driver.getTitle(),"Sign in - Google Accounts");
    }

}
