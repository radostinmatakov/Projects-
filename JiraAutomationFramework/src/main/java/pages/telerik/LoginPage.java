package pages.telerik;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends TelerikHomePage{
    public LoginPage(WebDriver driver) {
        super(driver, "telerik.loginPage");
    }

    public void loginUser(String userKey) {
        String username = getConfigPropertyByKey("telerik.users." + userKey + ".username");
        String password = getConfigPropertyByKey("telerik.users." + userKey + ".password");

        String expectedUrl = getConfigPropertyByKey("telerik.domain.url");
        navigateToPage();
        assertPageNavigatedWithParams(expectedUrl);
        actions.waitForElementVisible("telerik.homePage.loginButton");
        actions.clickElement("telerik.homePage.loginButton");
        actions.waitForElementVisible("telerik.loginPage.emailInput");
        actions.typeValueInField(username, "telerik.loginPage.emailInput");
        actions.typeValueInField(password, "telerik.loginPage.passwordInput");
        actions.clickElement("telerik.loginPage.signInButton");

    }

    public void assertAvatarExists(String profileName) {
        actions.waitForElementVisible("telerik.homePage.avatar", profileName);
        actions.assertElementPresentWithArg("telerik.homePage.avatar",profileName);
    }
}
