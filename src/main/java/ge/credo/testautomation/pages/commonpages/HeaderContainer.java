package ge.credo.testautomation.pages.commonpages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class HeaderContainer {
    @FindBy(css = "div#landingHeader1 a")
    private List<WebElement> landingHeaders;

    @FindBy(css = "div#languageSwitcherBtn")
    private WebElement languageSwitcherButton;


    public HeaderContainer(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
