package pages.jira;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class LoginPage extends YourWorkPage {

    public static String boardId = null;

    public LoginPage(WebDriver driver) {
        super(driver, "jira.loginPage");
    }

    public void loginUser(String userKey) {
        String username = getConfigPropertyByKey("jira.users." + userKey + ".username");
        String password = getConfigPropertyByKey("jira.users." + userKey + ".password");


        String expectedUrl = getConfigPropertyByKey("jira.domain.url");

        navigateToPage();
        assertPageNavigatedWithParams(expectedUrl);

        actions.waitForElementVisible("jira.loginPage.username");

        actions.typeValueInField(username, "jira.loginPage.username");
        actions.waitForElementVisible("jira.loginPage.continueButton");
        actions.clickElement("jira.loginPage.continueButton");

        actions.waitForElementVisible("jira.loginPage.password");
        actions.typeValueInField(password, "jira.loginPage.password");

        actions.clickElement("jira.loginPage.loginButton");
        actions.waitForElementVisible("jira.homePage.profileAvatar");

    }

    public void assertAvatarExists() {

        actions.assertElementPresent("jira.homePage.profileAvatar");
    }
}
