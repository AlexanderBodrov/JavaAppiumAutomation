import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


import static org.junit.Assert.assertEquals;

public class SeventhTest {
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
    public void rotateBack() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void tearDown() {
        driver.quit();
    }

    @Test
    public void sevenTest() throws InterruptedException {
        rotateToLandscape();

        assertIsLandscape();
    }


    private void rotateToLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    private void assertIsLandscape() {
        String orientation = driver.getOrientation().toString();
        String expectedOrientation = "LANDSCAPE";
        assertEquals(driver.getOrientation().toString(), expectedOrientation, orientation);
    }
}
