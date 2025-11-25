import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class FirstTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("newCommandTimeout", "3600");
        capabilities.setCapability("deviceName", "Main");
        capabilities.setCapability("platformVersion", "14");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/aleksandr/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void checkFirstTest()
    {
        System.out.println("First test run");
    }
}
