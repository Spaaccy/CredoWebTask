package ge.credo.testautomation.steps;

import ge.credo.testautomation.data.enums.SubmitButtonState;
import ge.credo.testautomation.pages.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static ge.credo.testautomation.data.Constants.INPUT_TYPE_TEXT;
import static ge.credo.testautomation.utils.BaseSetup.getSoftAssert;
import static ge.credo.testautomation.utils.BaseSetup.getWait;
import static ge.credo.testautomation.utils.Helpers.*;

public class LoginPageSteps extends LoginPage {

    public LoginPageSteps(WebDriver driver) {
        super(driver);
    }

    @Step("Check login header text is: {expectedText}")
    public LoginPageSteps checkLoginHeaderText(String expectedText) {
        assertTextPresentSoft(getLoginHeader(), expectedText);
        return this;
    }

    @Step("Check hero header text is: {expectedText}")
    public LoginPageSteps checkHeroHeaderText(String expectedText) {
        getSoftAssert().assertEquals(getHeroHeader().getText().trim(), expectedText);
        return this;
    }

    @Step("Check forgot password text is: {expectedText}")
    public LoginPageSteps checkForgotPassText(String expectedText) {
        getSoftAssert().assertEquals(getForgotPasswordButton().getText().trim(), expectedText);
        return this;
    }

    @Step("Check submit button text is: {expectedText}")
    public LoginPageSteps checkSubmitButtonText(String expectedText) {
        getSoftAssert().assertEquals(getSubmitButton().getText().trim(), expectedText);
        return this;
    }

    @Step("Check register button text is: {expectedText}")
    public LoginPageSteps checkRegisterButtonText(String expectedText) {
        getSoftAssert().assertEquals(getRegisterButton().getText().trim(), expectedText);
        return this;
    }

    @Step("Enter username: {text}")
    public LoginPageSteps enterUserName(String text) {
        getUserNameInput().sendKeys(text);
        return this;
    }

    @Step("Clear username input")
    public LoginPageSteps clearUserName() {
        clearInput(getUserNameInput());
        return this;
    }

    @Step("Enter password: {text}")
    public LoginPageSteps enterUserPass(String text) {
        getUserPassInput().sendKeys(text);
        return this;
    }

    @Step("Clear password input")
    public LoginPageSteps clearUserPass() {
        clearInput(getUserPassInput());
        return this;
    }

    @Step("Check error bar is visible")
    public LoginPageSteps checkErrorBarVisible() {
        assertVisible(getErrorNotificationBar());
        return this;
    }

    @Step("Check error bar is not visible")
    public LoginPageSteps checkErrorBarNotVisible() {
        assertNotVisible(getErrorNotificationBar());
        return this;
    }

    @Step("Check error bar text is: {expectedText}")
    public LoginPageSteps checkErrorBarText(String expectedText) {
        getSoftAssert().assertEquals(getErrorNotificationBar().getText().trim(), expectedText);
        return this;
    }

    @Step("Check submit button state: {state}")
    public LoginPageSteps checkSubmitButtonState(SubmitButtonState state) {
        getWait().until(ExpectedConditions.visibilityOf(getSubmitButton()));
        getSoftAssert().assertEquals(getSubmitButton().isEnabled(), state.isEnabled());
        return this;
    }

    @Step("Click submit button")
    public LoginPageSteps clickSubmitUser() {
        getSubmitButton().click();
        return this;
    }

    @Step("Toggle password visibility")
    public LoginPageSteps togglePasswordVisibility() {
        getPasswordVisibilityToggle().click();
        return this;
    }

    @Step("Check password input is visible (unmasked)")
    public LoginPageSteps checkUserPassVisible() {
        getSoftAssert().assertEquals(getUserPassInput().getAttribute("type"), INPUT_TYPE_TEXT);
        return this;
    }

    @Step("Click save user checkbox")
    public LoginPageSteps clickSaveUserButton() {
        getSaveUserCheckBox().click();
        return this;
    }

    @Step("Click close error notification button")
    public LoginPageSteps clickCloseNotificationErrorButton() {
        getCloseErrorNotificationButton().click();
        return this;
    }
}
