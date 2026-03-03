package ge.credo.testautomation.utils;

import ge.credo.testautomation.data.enums.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static ge.credo.testautomation.data.Constants.*;

@Listeners({ScreenshotListener.class})
public class BaseSetup {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    private static final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();
    private static final List<String> BROWSER_ARGS = List.of(
            CHROME_MAXIMIZED, CHROME_DISABLE_NOTIFICATIONS, CHROME_REMOTE_ALLOW_ORIGINS
    );
    @Getter
    private static final RandomStringUtils randomStringUtils = RandomStringUtils.insecure();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriverWait getWait() {
        return wait.get();
    }

    public static SoftAssert getSoftAssert() {
        return softAssert.get();
    }

    @Parameters("browser")
    @BeforeClass
    public void setUp(@Optional(DEFAULT_BROWSER) String browserName) {
        var browser = Browser.fromString(browserName);
        var webDriver = createDriver(browser);

        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        driver.set(webDriver);
        wait.set(new WebDriverWait(webDriver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT)));
        softAssert.set(new SoftAssert());
    }

    private WebDriver createDriver(Browser browser) {
        return switch (browser) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                var options = new ChromeOptions();
                options.addArguments(BROWSER_ARGS);
                yield new ChromeDriver(options);
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                var options = new EdgeOptions();
                options.addArguments(BROWSER_ARGS);
                yield new EdgeDriver(options);
            }
        };
    }

    @AfterClass
    public void tearDown() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
            wait.remove();
        }
        softAssert.remove();
    }
}
