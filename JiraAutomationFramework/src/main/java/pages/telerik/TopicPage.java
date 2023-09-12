package pages.telerik;

import com.telerikacademy.testframework.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class TopicPage extends BasePage {

    public TopicPage(WebDriver driver, String urlKey) {
        super(driver, "telerik.topic.page");
    }

    public void navigateToTheGivenTopic() {

        actions.waitForElementVisible("//button[@id='search-button']");
        actions.clickElement("//button[@id='search-button']");
        actions.waitForElementVisible("//input[@id='search-term']");
        actions.clickElement("//input[@id='search-term']");
        actions.sendKeys("//input[@id='search-term']", "Alpha 50 QA - We are awesome and great");
        actions.keyboardActionEnter("//input[@id='search-term']");
        actions.waitForElementVisible("(//span[@class='search-highlight' and text()='Alpha 50 QA - We are awesome and great'])[1]");
        actions.clickElement("(//span[@class='search-highlight' and text()='Alpha 50 QA - We are awesome and great'])[1]");

    }

    public void assertCommentToTheGivenTopic() {

        actions.waitForElementVisible("//button[@title='begin composing a reply to this post']");
        actions.clickElement("//button[@title='begin composing a reply to this post']");
        actions.waitForElementVisible("//textarea[@aria-label='Type here. Use Markdown, BBCode, or HTML to format. Drag or paste images.']");
        actions.sendKeys("//textarea[@aria-label='Type here. Use Markdown, BBCode, or HTML to format. Drag or paste images.']", "> Hi, I am Timur, and I did it :smile: :partying_face: :beers: :see_no_evil: :hear_no_evil: :speak_no_evil: :heart:");
        actions.clickElement("//button[@title='Or press Ctrl+Enter']");
        actions.assertElementPresent("//article[@data-user-id='1851']");

    }

    public void deleteTheComment() {

        actions.clickElement("//button[@class='widget-button btn-flat show-more-actions no-text btn-icon']");
        actions.waitForElementClickable("//button[@class='widget-button btn-flat delete no-text btn-icon']");
        actions.clickElement("//button[@class='widget-button btn-flat delete no-text btn-icon']");
    }
}
