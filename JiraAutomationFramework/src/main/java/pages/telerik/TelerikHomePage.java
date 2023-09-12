package pages.telerik;

import com.telerikacademy.testframework.Utils;
import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.getConfigPropertyByKey;

public class TelerikHomePage extends BasePage {

    static String title, desc;
    public TelerikHomePage(WebDriver driver, String urlKey) {
        super(driver, urlKey);
    }

    public void createANewTopic() {

        title = "Topic: "  + Utils.generateRandomString(10);
        desc = "Desc:" + Utils.generateRandomString(101);;
        actions.waitForElementVisible("telerik.homePage.newTopic");
        actions.clickElement("telerik.homePage.newTopic");
        actions.waitForElementVisible2("telerik.createNewTopicPage.actionTitle");
        actions.typeValueInField(title,"telerik.createNewTopicPage.createTitleInput");
        actions.typeValueInField(desc,"telerik.createNewTopicPage.createDescriptionTextArea");
        actions.clickElement("telerik.createNewTopicPage.createNewTopicButton");
    }

    public void assertTopicCreation() {
        actions.waitForElementVisible("telerik.topicPage.topicTitle", title);
        actions.assertElementPresentWithArg("telerik.topicPage.topicTitle", title);
        actions.assertElementPresentWithArg("telerik.topicPage.topicDescription",desc);
    }
}
