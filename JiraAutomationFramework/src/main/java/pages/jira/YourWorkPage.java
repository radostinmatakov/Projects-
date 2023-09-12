package pages.jira;

import com.telerikacademy.testframework.Utils;
import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class YourWorkPage extends BasePage {

    int previousCount = 0;

    public YourWorkPage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }

    public void createAnIssueByType(String projectKey, String issueTypeKey, String summaryKey,
                                    String descriptionKey, String assigneeKey,
                                    String priorityKey, String labelsKey) throws InterruptedException {

        String project = Utils.getConfigPropertyByKey(projectKey);
        String issue = Utils.getConfigPropertyByKey(issueTypeKey);
        String summary = Utils.getConfigPropertyByKey(summaryKey);
        String description = Utils.getConfigPropertyByKey(descriptionKey);
        String reporter = Utils.getConfigPropertyByKey(assigneeKey);
        String priority = Utils.getConfigPropertyByKey(priorityKey);
        String labels = Utils.getConfigPropertyByKey(labelsKey);
        // String sprint = Utils.getConfigPropertyByKey(sprintKey);

        //create item and assert
        previousCount = issueCount(summary);
        actions.waitForElementVisible("jira.homePage.createItemButton");
        actions.clickElement("jira.homePage.createItemButton");
        assertCreateModelPresent();

        selectIssueType(issue, "jira.homePage.issueType", "jira.homePage.issueTypeDropDownNavigate");
        typeSummaryAndDescFields(summary, "jira.homePage.summaryInput");

        typeSummaryAndDescFields(description, "jira.homePage.descriptionField");

        selectField(reporter, "jira.homePage.assigneeReporter", "jira.homePage.assigneeInputField");

        //priority
        actions.waitForElementVisible("//input[@id='priority-field']");
        actions.clickElement("//input[@id='priority-field']/..");
        actions.typeValueInField(priority, "//input[@id='priority-field']");
        Thread.sleep(3000);
        actions.keyboardActionEnter("//input[@id='priority-field']");

        //labels
        selectField(labels, "jira.homePage.selectLabelField", "jira.homePage.labelField");

        //actions.clickElement("//span[text()='browse']");
        driver.findElement(By.xpath(("//input[@data-testid='media-picker-file-input']")))
                .sendKeys("C:\\Users\\timda\\OneDrive\\Desktop\\BMWX5\\IMG_9687.JPG");

        actions.waitForElementVisible("//div[@data-testid='media-file-card-view']");
        Thread.sleep(3000);
        actions.clickElement("//button[@data-testid='issue-create.common.ui.footer.create-button']");


    }

    public void createAStory(String projectKey, String issueTypeKey, String summaryKey,
                             String descriptionKey, String assigneeKey,
                             String priorityKey, String labelsKey) {

        String project = Utils.getConfigPropertyByKey(projectKey);
        String issue = Utils.getConfigPropertyByKey(issueTypeKey);
        String summary = Utils.getConfigPropertyByKey(summaryKey);
        String description = Utils.getConfigPropertyByKey(descriptionKey);
        String assignee = Utils.getConfigPropertyByKey(assigneeKey);
        String priority = Utils.getConfigPropertyByKey(priorityKey);
        String labels = Utils.getConfigPropertyByKey(labelsKey);
        // String sprint = Utils.getConfigPropertyByKey(sprintKey);


        //create item and assert
        previousCount = issueCount(summary);
        actions.waitForElementVisible("jira.homePage.createItemButton");
        actions.clickElement("jira.homePage.createItemButton");
        assertCreateModelPresent();


        actions.waitForElementClickable("jira.homePage.issueTypeDropDown");
        actions.clickElement("jira.homePage.issueTypeDropDown");

        actions.waitForElementVisible("//div[@id='issue-create.ui.modal.create-form.type-picker.issue-type-select']");
        actions.keyboardActionArrowDown("(//input[@aria-autocomplete='list'])[2]");
        actions.keyboardActionEnter("(//input[@aria-autocomplete='list'])[2]");

        //summary
        actions.waitForElementVisible("jira.homePage.summaryInput");
        actions.typeValueInField(summary, "jira.homePage.summaryInput");

        //description
        actions.clickElement("jira.homePage.descriptionField");
        actions.typeValueInField(description, "jira.homePage.descriptionField");

        //selectField(assignee, "jira.homePage.assigneeReporter");
        actions.clickElement("(//div[@class='fabric-user-picker__control css-1c1zckh-control'])[1]");
        actions.waitForElementVisible("(//input[@aria-autocomplete='list'])[4]");
        actions.typeValueInField(assignee, "(//input[@aria-autocomplete='list'])[4]");
        actions.keyboardActionEnter("(//input[@aria-autocomplete='list'])[4]");

        //selectField(priority, "jira.homePage.priorityField");
        //Actions act=new Actions(driver); act.moveToElement(element).click().perform()
        actions.waitForElementVisible("//input[@id='priority-field']");
        actions.clickElement("//input[@id='priority-field']/..");
        actions.typeValueInField(priority, "(//input[@aria-autocomplete='list'])[9]");
        actions.keyboardActionEnter("//input[@id='priority-field']");

        //labels
        actions.clickElement("//div[text()='Select label']");
        actions.waitForElementVisible("//input[@id='labels-field']");
        actions.typeValueInField(labels, "//input[@id='labels-field']");
        actions.keyboardActionEnter("//input[@id='labels-field']");


        //Create issue
        actions.clickElement("//button[@data-testid='issue-create.common.ui.footer.create-button']");


        //selectField(labels, "jira.homePage.labelField");
    }

    public void copyLastStoryKey() throws InterruptedException {


        actions.waitForElementClickable("(//h4)[2]");
        actions.clickElement("(//h4)[2]");
        Thread.sleep(4000);
        actions.mouseHoverBy("//span[@data-testid='issue.common.component.permalink-button.button.link-icon']");

    }

    public void linkBugToStory() {

        actions.clickElement("//h4[text()='This is the summary of the bug']");

        actions.waitForElementClickable("//button[@data-testid='issue.issue-view.views.issue-base.foundation.quick-add.quick-add-item.link-issue']");
        actions.clickElement("//button[@data-testid='issue.issue-view.views.issue-base.foundation.quick-add.quick-add-item.link-issue']");

        actions.waitForElementClickable("//input[@id='issue-link-search']");
        actions.clickElement("//div[@data-testid='issue.views.issue-base.content.issue-links.add.issue-search-container']");
        actions.sendKeys("//input[@id='issue-link-search']", Keys.CONTROL + "v");
        actions.keyboardActionEnter("//input[@id='issue-link-search']");

//        actions.sendKeys("//div[@id='jira-frontend']",Keys.CONTROL + Keys.ARROW_DOWN);
//        actions.sendKeys(Keys.chord(Keys.CONTROL, Keys.ARROW_DOWN));


        actions.scrollDownInPage("//div[@data-testid='issue.issue-view.views.issue-base.content.issue-links.add.issue-links-add-view.link-button']");
        actions.waitForElementVisible("//div[@data-testid='issue.issue-view.views.issue-base.content.issue-links.add.issue-links-add-view.link-button']");
        actions.clickElement("//div[@data-testid='issue.issue-view.views.issue-base.content.issue-links.add.issue-links-add-view.link-button']");


    }

    public void assertBugLinkedToStory (){

        actions.clickElement("//span[@class and text()='Creating an issue from selenium']");
        actions.waitForElementVisible("//span[@data-item-title='true' and text()='This is the summary of the bug']");
        actions.assertElementPresent("//span[@data-item-title='true' and text()='This is the summary of the bug']");

    }

    public void createABug(String projectKey, String issueTypeKey, String summaryKey,
                           String descriptionKey, String assigneeKey,
                           String priorityKey, String labelsKey) {

        String project = Utils.getConfigPropertyByKey(projectKey);
        String issue = Utils.getConfigPropertyByKey(issueTypeKey);
        String summary = Utils.getConfigPropertyByKey(summaryKey);
        String description = Utils.getConfigPropertyByKey(descriptionKey);
        String assignee = Utils.getConfigPropertyByKey(assigneeKey);
        String priority = Utils.getConfigPropertyByKey(priorityKey);
        String labels = Utils.getConfigPropertyByKey(labelsKey);

        //Create Issue button
        actions.waitForElementVisible("jira.homePage.createItemButton");
        actions.clickElement("jira.homePage.createItemButton");
        assertCreateModelPresent();


        actions.waitForElementClickable("jira.homePage.issueTypeDropDown");
        actions.clickElement("jira.homePage.issueTypeDropDown");

        //Select Issue type
        actions.waitForElementVisible("//div[@id='issue-create.ui.modal.create-form.type-picker.issue-type-select']");
        actions.keyboardActionArrowDown("(//input[@aria-autocomplete='list'])[2]");
        actions.keyboardActionArrowDown("(//input[@aria-autocomplete='list'])[2]");
        actions.keyboardActionEnter("(//input[@aria-autocomplete='list'])[2]");

        //summary
        actions.waitForElementVisible("jira.homePage.summaryInput");
        actions.typeValueInField(summary, "jira.homePage.summaryInput");

        //description
        actions.clickElement("jira.homePage.descriptionField");
        actions.typeValueInField(description, "jira.homePage.descriptionField");

        //selectField(assignee, "jira.homePage.assigneeReporter");
        actions.clickElement("(//div[@class='fabric-user-picker__control css-1c1zckh-control'])[1]");
        actions.waitForElementVisible("(//input[@aria-autocomplete='list'])[4]");
        actions.typeValueInField(assignee, "(//input[@aria-autocomplete='list'])[4]");
        actions.keyboardActionEnter("(//input[@aria-autocomplete='list'])[4]");

        //selectField(priority, "jira.homePage.priorityField");
        actions.clickElement("(//div[@data-value=''])[5]");
        actions.typeValueInField(priority, "//input[@id='priority-field']");
        actions.clickElement("//input[@id='priority-field']");
        actions.keyboardActionEnter("//input[@id='priority-field']");

        //labels
        actions.clickElement("//div[text()='Select label']");
        actions.waitForElementVisible("//input[@id='labels-field']");
        actions.typeValueInField(labels, "//input[@id='labels-field']");
        actions.keyboardActionEnter("//input[@id='labels-field']");


        //Create issue
        actions.clickElement("//button[@data-testid='issue-create.common.ui.footer.create-button']");


    }

    public int issueCount(String title) {
        String locator = format("//h4[text()='%s']", title);
        return driver.findElements(By.xpath(locator)).size();
    }

    public void assertCreateModelPresent() {

        actions.assertElementPresent("jira.homePage.createIssueTitle");
    }

    public void chooseIssueType(String issueType) {

        actions.clickElement("jira.homePage.issueTypeDropDown");
        actions.typeValueInField(issueType, "jira.homePage.issueTypeDropDown");
        actions.keyboardActionEnter("jira.homePage.issueTypeDropDown");

    }

    public void assignAssignee(String assignee) {

        actions.clickElement("jira.homePage.assigneeReporter");
        actions.typeValueInField(assignee, "jira.homePage.assigneeReporter");
        actions.keyboardActionEnter("jira.homePage.assigneeReporter");

    }

    public void selectReporterField(String value, String key) {


        actions.waitForElementVisible(key);
        actions.clickElement(key);

        actions.waitForElementVisible(key);
        actions.typeValueInField(value, key);

        actions.waitForElementVisible(key);
        actions.keyboardActionEnter(key);


    }

    public void selectField(String value, String fieldKey, String key) {

        actions.clickElement(fieldKey);
        actions.waitForElementVisible(key);
        actions.typeValueInField(value, key);
        actions.keyboardActionEnter(key);


    }

    public void typeSummaryAndDescFields(String value, String key) {

        actions.waitForElementVisible(key);
        actions.clickElement(key);
        actions.typeValueInField(value, key);
    }

    public void assertStoryCreation() {

        actions.waitForElementPresent("jira.homePage.createdIssueNotification");

    }

    public void assertBugCreation() {

        actions.waitForElementPresent("jira.homePage.createdIssueNotification");

    }

    public void selectIssueType(String issueType, String fieldKey, String key) {

        actions.waitForElementClickable("jira.homePage.issueTypeDropDown");
        actions.clickElement("jira.homePage.issueTypeDropDown");

        switch (issueType) {
            case "Bug":
                actions.waitForElementVisible(fieldKey);
                actions.keyboardActionArrowDown(key);
                actions.keyboardActionArrowDown(key);
                actions.keyboardActionEnter(key);
                break;
            case "Story":
                actions.waitForElementVisible(fieldKey);
                actions.keyboardActionArrowDown(key);
                actions.keyboardActionEnter(key);
                break;
        }
    }

    public int findCreatedIssueCount(String key) {

        String title = Utils.getConfigPropertyByKey(key);
        int afterCreation = issueCount(title);
        int count = afterCreation - previousCount;
        return count;
    }
}
