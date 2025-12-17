import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SixthTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("newCommandTimeout", "3600");
        capabilities.setCapability("deviceName", "Main");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/aleksandr/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void sixthTest() throws InterruptedException {
        click(
                By.xpath("//*[contains(@text,'Search Wikipedia')]")
        );

        typeText(
                By.id("org.wikipedia:id/search_src_text"),
                "UFO"
        );

        String line_1 = "UFO conspiracy theories";
        click(
                By.xpath(String.format("//*[contains(@text,'%s')]", line_1))
        );

        assertElementPresent(
                By.xpath("//title"),
                "Element not presented"
        );
    }


    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by) {
        return waitForElementPresent(by, "something went wrong", 5);
    }

    private void click(By by) {
        WebElement element = waitForElementPresent(by);
        element.findElement(by);
        element.click();
    }

    private void typeText(By by, String text) {
        WebElement element = waitForElementPresent(by);
        element.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    private void assertElementPresent(By by, String errorText) {
        try {
            waitForElementPresent(by);
        } catch (Exception e) {
            throw new AssertionError(errorText);
        }
    }
}
