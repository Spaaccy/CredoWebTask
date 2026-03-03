package ge.credo.testautomation.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class ScreenshotListener implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test STARTED: [{}]", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test PASSED: [{}]", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test SKIPPED: [{}]", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test FAILED: [{}]", result.getName());
        takeScreenshot(result);
    }

    private void takeScreenshot(ITestResult result) {
        WebDriver driver = BaseSetup.getDriver();
        if (driver == null) {
            log.warn("Driver is null, skipping screenshot for: [{}]", result.getName());
            return;
        }
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on Failure", "image/png",
                    new ByteArrayInputStream(screenshot), "png");

            Throwable t = result.getThrowable();
            if (t != null) {
                Allure.addAttachment("Failure Message", "text/plain",
                        new ByteArrayInputStream(t.getMessage().getBytes(StandardCharsets.UTF_8)), "txt");
            }

            log.info("Screenshot attached for: [{}]", result.getName());
        } catch (Exception e) {
            log.error("Failed to capture screenshot for [{}]: {}", result.getName(), e.getMessage());
        }
    }
}