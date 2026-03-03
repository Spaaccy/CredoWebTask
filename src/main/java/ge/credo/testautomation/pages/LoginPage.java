package ge.credo.testautomation.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage {


    @FindBy(css = "input#userName")
    private WebElement userNameInput;

    @FindBy(css = "input#newPass")
    private WebElement userPassInput;

    @FindBy(css = "p#forgotPass")
    private WebElement forgotPasswordButton;

    @FindBy(css = "section#authloading > p")
    private WebElement loginHeader;

    @FindBy(css = ".right h3")
    private WebElement heroHeader;

    @FindBy(css = "section.notification-container.error")
    private WebElement errorNotificationBar;

    @FindBy(css = "button#submitAuth")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@class='secondary' or @id='subbnitButton']")
    private WebElement registerButton;

    @FindBy(css = ".notification-container.error .close")
    private WebElement closeErrorNotificationButton;

    @FindBy(css = "div#togglePass")
    private WebElement passwordVisibilityToggle;

    @FindBy(css = "input#checkBoxSavedUser")
    private WebElement saveUserCheckBox;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
