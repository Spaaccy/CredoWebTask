package ge.credo.testautomation;

import ge.credo.testautomation.data.dataproviders.LoginDataProvider;
import ge.credo.testautomation.data.enums.Language;
import ge.credo.testautomation.data.enums.SubmitButtonState;
import ge.credo.testautomation.steps.LoginPageSteps;
import ge.credo.testautomation.steps.commonsteps.HeaderContainerSteps;
import ge.credo.testautomation.steps.modalsteps.LanguageSwitcherModalSteps;
import ge.credo.testautomation.utils.BaseSetup;
import ge.credo.testautomation.utils.RetryMaker;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static ge.credo.testautomation.data.Constants.UI.ACTIVE_HEADER_TEXT_GEO;
import static ge.credo.testautomation.data.Constants.Urls.LANDING_PAGE_URL;

@Epic("Credo Bank Web")
@Feature("Login Page")
public class CredoLoginTest extends BaseSetup {
    LoginPageSteps loginPageSteps;
    HeaderContainerSteps headerContainerSteps;
    LanguageSwitcherModalSteps languageSwitcherModalSteps;

    @BeforeClass
    public void initPageSteps() {
        headerContainerSteps = new HeaderContainerSteps(getDriver());
        loginPageSteps = new LoginPageSteps(getDriver());
        languageSwitcherModalSteps = new LanguageSwitcherModalSteps(getDriver());
    }

    @BeforeMethod
    public void navigateToLoginPage() {
        getDriver().get(LANDING_PAGE_URL);
        getDriver().manage().deleteAllCookies();
    }

    @Test(priority = 1)
    @Story("Landing Page")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that landing page headers are visible and the active header is correct")
    public void testLandingPageHeadersAreVisibleAndActive() {
        headerContainerSteps
                .checkThatLandingHeadersAreVisible();
        WebElement webElement = headerContainerSteps.getHeaderByText(ACTIVE_HEADER_TEXT_GEO);
        headerContainerSteps
                .checkThatHeaderIsActive(webElement);
        getSoftAssert().assertAll();
    }

    @Test(priority = 2)
    @Story("Save User & Password Visibility")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify save user button, password visibility toggle, and submit button is clickable when both fields are filled")
    public void testSaveUserAndPasswordVisibility() {
        loginPageSteps
                .enterUserName(getRandomStringUtils().nextAlphabetic(5))
                .clickSaveUserButton()
                .enterUserPass(getRandomStringUtils().nextAlphanumeric(10))
                .togglePasswordVisibility()
                .checkUserPassVisible();
        getSoftAssert().assertAll();
    }


    @Test(priority = 3)
    @Story("Submit Button State Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that submit button is not clickable when only username or only password is filled")
    public void testSubmitButtonNotClickableWithSingleField() {
        loginPageSteps
                .enterUserName(getRandomStringUtils().nextAlphabetic(5))
                .checkSubmitButtonState(SubmitButtonState.DISABLED)
                .clearUserName()
                .enterUserPass(getRandomStringUtils().nextAlphanumeric(10))
                .checkSubmitButtonState(SubmitButtonState.DISABLED);
        getSoftAssert().assertAll();
    }

    @Test(priority = 4, dataProvider = "testDataForLogin", dataProviderClass = LoginDataProvider.class, retryAnalyzer = RetryMaker.class)
    @Story("Login with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that login fails with invalid credentials and correct error message is displayed per language")
    public void testLoginWithInvalidCredentials(Language language, String userName, String userPass) {
        headerContainerSteps
                .clickOnLanguageSwitcher();
        languageSwitcherModalSteps
                .checkPopUpHeaderVisible()
                .clickLanguageByText(language.getLanguageName());
        loginPageSteps
                .checkLoginHeaderText(language.getLoginHeader())
                .checkHeroHeaderText(language.getHeroHeader())
                .checkForgotPassText(language.getForgotPassword())
                .checkSubmitButtonText(language.getSubmitButton())
                .checkRegisterButtonText(language.getRegisterButton())
                .enterUserName(userName)
                .enterUserPass(userPass)
                .clickSubmitUser()
                .checkErrorBarVisible()
                .checkErrorBarText(language.getErrorBar())
                .clickCloseNotificationErrorButton()
                .checkErrorBarNotVisible();
        getSoftAssert().assertAll();
    }

}
