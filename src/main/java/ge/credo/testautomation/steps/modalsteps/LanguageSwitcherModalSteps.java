package ge.credo.testautomation.steps.modalsteps;

import ge.credo.testautomation.pages.modalpages.LanguageSwitcherModal;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static ge.credo.testautomation.utils.Helpers.assertVisible;

public class LanguageSwitcherModalSteps extends LanguageSwitcherModal {

    public LanguageSwitcherModalSteps(WebDriver driver) {
        super(driver);
    }

    @Step("Check popup header is visible")
    public LanguageSwitcherModalSteps checkPopUpHeaderVisible() {
        assertVisible(getPopUpHeader());
        return this;
    }

    @Step("Click language by text: {text}")
    public LanguageSwitcherModalSteps clickLanguageByText(String text) {
        getLanguageButtons().stream()
                .filter(header -> header.getText().equals(text))
                .findFirst()
                .orElseThrow()
                .click();
        return this;
    }
}
