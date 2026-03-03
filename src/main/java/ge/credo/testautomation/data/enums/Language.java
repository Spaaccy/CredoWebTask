package ge.credo.testautomation.data.enums;

import lombok.Getter;

@Getter
public enum Language {

    SVAN(
            "სვანური",
            "სისტემათეისგა სგა ლიზი",
            "მონაცემოლე აღდგენა",
            "სგა ლიზი",
            "რეგისტრაცია",
            "ქა ეხიირ კრედოშ მობაილ აპლიკაცია",
            "მონაცემოლ სწორ დემეგ ლი"
    ),
    MEGRELIAN(
            "მეგრული",
            "სისტემაშე შესვლაჲ",
            "გორინა",
            "ინულა",
            "არ ვიცი მეგრულიი",
            "გადმოწერე კრედო ბანკიშ მობილური აპლიკაციაჲ",
            "მონაცემეფი არასწორო რე"
    ),

    ENGLISH(
            "English",
            "Login",
            "Forgot Credentials",
            "Login",
            "Registration",
            "Download Credo Bank Mobile Application",
            "Incorrect data"
    );

    private final String
            languageName,
            loginHeader,
            forgotPassword,
            registerButton,
            submitButton,
            heroHeader,
            errorBar;

    Language(String languageName, String loginHeaderText, String forgotPasswordText, String submitText, String registerText, String heroHeaderText, String errorBarText) {
        this.languageName = languageName;
        this.loginHeader = loginHeaderText;
        this.forgotPassword = forgotPasswordText;
        this.registerButton = registerText;
        this.submitButton = submitText;
        this.heroHeader = heroHeaderText;
        this.errorBar = errorBarText;
    }
}

