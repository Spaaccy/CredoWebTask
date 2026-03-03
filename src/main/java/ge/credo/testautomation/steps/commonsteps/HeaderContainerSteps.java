package ge.credo.testautomation.steps.commonsteps;

import ge.credo.testautomation.pages.commonpages.HeaderContainer;
import ge.credo.testautomation.utils.Helpers;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static ge.credo.testautomation.data.Constants.ACTIVE_CLASS;
import static ge.credo.testautomation.utils.BaseSetup.getSoftAssert;

public class HeaderContainerSteps extends HeaderContainer {


    public HeaderContainerSteps(WebDriver driver) {
        super(driver);
    }

    @Step("Check that all landing headers are visible")
    public HeaderContainerSteps checkThatLandingHeadersAreVisible() {
        getLandingHeaders().forEach(Helpers::assertVisible);
        return this;
    }

    @Step("Get header by text: {text}")
    public WebElement getHeaderByText(String text) {
        return getLandingHeaders().stream()
                .filter(header -> header.getText().equals(text))
                .findFirst()
                .orElseThrow();
    }

    @Step("Check that header is active")
    public HeaderContainerSteps checkThatHeaderIsActive(WebElement element) {
        getSoftAssert().assertEquals(element.getAttribute("class"), ACTIVE_CLASS);
        return this;
    }

    @Step("Click on language switcher")
    public HeaderContainer clickOnLanguageSwitcher() {
        getLanguageSwitcherButton().click();
        return this;
    }

}
