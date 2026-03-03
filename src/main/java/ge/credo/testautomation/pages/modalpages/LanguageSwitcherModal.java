package ge.credo.testautomation.pages.modalpages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class LanguageSwitcherModal {

    @FindBy(xpath = "//ul[@class='language-list']//p")
    private List<WebElement> languageButtons;

    @FindBy(css = "div.header > div > p")
    private WebElement popUpHeader;

    @FindBy(css = "div.header > div:last-child")
    private WebElement closeLanguageSwitcherButton;

    public LanguageSwitcherModal(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
