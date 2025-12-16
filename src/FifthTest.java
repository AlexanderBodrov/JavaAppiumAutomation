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

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class FifthTest {
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
    public void fifthTest() throws InterruptedException {
        click(
                By.xpath("//*[contains(@text,'Search Wikipedia')]")
        );

        typeText(
                By.id("org.wikipedia:id/search_src_text"),
                "UFO"
        );

        String line_1 = "UFO conspiracy theories";
        longTap(
                By.xpath(String.format("//*[contains(@text,'%s')]", line_1)),
                3
        );

        click(
                By.xpath("//*[contains(@text,'Add to reading list')]")
        );

        click(
                By.id("org.wikipedia:id/onboarding_button")
        );

        String linkName = "Test list";
        typeText(
                By.xpath("//*[contains(@text,'My reading list')]"),
                linkName
        );

        click(
                By.id("android:id/button1")
        );

        String line_2 = "UFO (British TV series)";
        longTap(
                By.xpath(String.format("//*[contains(@text,'%s')]", line_2)),
                3
        );

        click(
                By.xpath("//*[contains(@text,'Add to reading list')]")
        );

        click(
                By.xpath(String.format("//*[contains(@text, '%s')]", linkName))
        );

        click(
                By.className("android.widget.ImageButton")
        );

         click(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']")
        );

        click(
                By.xpath(String.format("//*[contains(@text, '%s')]", linkName))
        );

        assertListHasMoreElements(
                By.id("org.wikipedia:id/page_list_item_title"),
                2,
                "less 2 elements on the screen"
        );

        swipeLeft (
                By.xpath(String.format("//*[contains(@text, '%s')]", line_1))
        );

        assertListHasMoreElements(
                By.id("org.wikipedia:id/page_list_item_container"),
                1,
                "less 1 elements on the screen"
        );

        click(
                By.id("org.wikipedia:id/page_list_item_title")
        );

        assertTitleText(
                By.xpath(String.format("//*[contains(@text,'%s')]", line_2))
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

    private void longTap(By by, int time) {
        WebElement element = waitForElementPresent(by);
        element.findElement(by);
        TouchAction action = new TouchAction(driver);
        action.longPress(element, time).perform();
    }

    private void assertListHasMoreElements(By by, int elements, String errorText) {
        List<WebElement> foundElements = driver.findElements(by);
        int actualCount = foundElements.size();
        Assert.assertTrue(errorText, actualCount >= elements);
    }

    private void swipeLeft(By by) {
        WebElement element = waitForElementPresent(by);
        element.findElement(by);
        int width = element.getSize().width;
        int height = element.getRect().getY();
        TouchAction action = new TouchAction(driver);
        action.press((int) (0.9 * width), height).waitAction(2000).moveTo((int) (0.1 * width), height).release().perform();
    }

    private void assertTitleText(By by) {
        WebElement element = waitForElementPresent(by);
        element.findElement(by);
    }
}
