package ge.credo.testautomation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static ge.credo.testautomation.data.Constants.*;
import static ge.credo.testautomation.utils.BaseSetup.*;

public class Helpers {

    public static void assertVisible(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static void assertNotVisible(WebElement element) {
        getWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public static void assertTextPresentSoft(WebElement element, String expectedText) {
        try {
            getWait().until(ExpectedConditions.textToBePresentInElement(element, expectedText));
        } catch (TimeoutException _) {
            getSoftAssert().fail("Expected '%s' but got '%s'".formatted(expectedText, element.getText().trim()));
        }
    }

    public static void clearInput(WebElement element) {
        if (getDriver() instanceof JavascriptExecutor js) {
            js.executeScript(JS_CLEAR_INPUT, element);
        }
    }

    public static String randomPassword() {
        return BaseSetup.getRandomStringUtils().nextAlphanumeric(PASSWORD_LENGTH);
    }

    public static String randomUserName() {
        return BaseSetup.getRandomStringUtils().nextAlphabetic(USERNAME_LENGTH);
    }

}
