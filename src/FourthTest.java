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

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class FourthTest {
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
    public void fourthTest() {
        clickToSearch(
                By.xpath("//*[contains(@text,'Search Wikipedia')]")
        );

        startSearch(
                By.id("org.wikipedia:id/search_src_text"),
                "UFO"
        );

        assertListHasText(
                By.id("org.wikipedia:id/page_list_item_container"),
                "UFO"
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

    private void clickToSearch(By by) {
        WebElement element = waitForElementPresent(by);
        element.findElement(by);
        element.click();
    }

    private void startSearch(By by, String text) {
        WebElement element = waitForElementPresent(by);
        element.findElement(by);
        element.sendKeys(text);
    }

    private void assertListHasText(By by, String searchText) {
        List<WebElement> foundElements = driver.findElements(by);
        int actualCount = foundElements.size();
        for (int i = 1; i <= actualCount; i = i + 1) {
            WebElement element = waitForElementPresent(By.xpath(String.format("//android.widget.LinearLayout[%d]/android.widget.LinearLayout/android.widget.TextView", i)));
            Assert.assertTrue(String.format("Line %d has no '%s'", i, searchText), element.getText().contains(searchText));
        }
    }
}
