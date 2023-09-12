package test.cases.jira;
import org.junit.BeforeClass;
import pages.trello.LoginPage;
import com.telerikacademy.testframework.UserActions;
public class BaseTest {
    UserActions actions = new UserActions();
    pages.jira.LoginPage loginPage = new pages.jira.LoginPage(actions.getDriver());

    @BeforeClass
    public static void setUp() {

        UserActions.loadBrowser("jira.homePage");

    }


//    @After
//    public void tearDown() {
//
//        UserActions.quitDriver();
//    }

    public void login() {

        LoginPage loginPage = new LoginPage(actions.getDriver());
        loginPage.loginUser("user");
    }

}
