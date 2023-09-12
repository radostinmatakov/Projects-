package test.cases.jira;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.jira.YourWorkPage;
import pages.jira.LoginPage;

public class JiraTests extends BaseTest {

    LoginPage loginPage = new LoginPage(actions.getDriver());
    YourWorkPage yourWorkPage = new YourWorkPage(actions.getDriver(), "jira.homePage");


    @Before
    public void verifySuccessfulUserLogin() {
        loginPage.loginUser("admin");
        loginPage.assertAvatarExists();
    }

    @Test
    public void validateStoryCreation() throws InterruptedException {

        //assign key values
        yourWorkPage.createAnIssueByType("project", "jira.issue.issueTypeStory", "jira.story.summary", "jira.story.description", "your-user", "Highest", "Severity-1");

        yourWorkPage.assertStoryCreation();
        actions.refreshPage();

        Thread.sleep(5000);
        int count = yourWorkPage.findCreatedIssueCount("jira.story.summary");
        Assert.assertEquals("One story has been created", 1, count);

    }

    @Test
    public void validateBugCreation() throws InterruptedException {

        yourWorkPage.createAnIssueByType("project", "jira.issue.issueTypeBug", "jira.bug.summary", "This is the description of the issue", "your-user", "Highest", "Severity-1");
        yourWorkPage.assertBugCreation();

        actions.refreshPage();
        int count = yourWorkPage.findCreatedIssueCount("jira.bug.summary");

        Assert.assertEquals("One bug has been created", 1, count);

    }

    @Test
    public void validateLinkingBugToStory() throws InterruptedException {

        yourWorkPage.copyLastStoryKey();
        yourWorkPage.navigateToPage();
        yourWorkPage.linkBugToStory();
        yourWorkPage.assertBugLinkedToStory();
    }
}
