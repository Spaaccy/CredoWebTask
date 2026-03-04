package ge.credo.testautomation.data.dataproviders;

import ge.credo.testautomation.data.enums.Language;
import org.testng.annotations.DataProvider;

import static ge.credo.testautomation.utils.Helpers.randomPassword;
import static ge.credo.testautomation.utils.Helpers.randomUserName;

public class LoginDataProvider {

    @DataProvider(name = "testDataForLogin")
    public Object[][] getTestData() {
        return new Object[][]{
                {Language.SVAN, randomUserName(), randomPassword()},
                {Language.GEO, randomUserName(), randomPassword()},
                {Language.ENGLISH, randomUserName(), randomPassword()},
                {Language.MEGRELIAN, randomUserName(), randomPassword()}
        };
    }

}
