package pages.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.telerikacademy.testframework.UserActions.wait;

public class BoardPage extends BaseTrelloPage {

    public BoardPage(WebDriver driver) {
        super(driver, "trello.boardPage");
    }

    public void addCardToList(String cardName) {


        //Clicking add card button

        actions.waitForElementVisible("trello.boardPage.addCard");
        actions.clickElement("trello.boardPage.addCard");

        // Locate the input field for card name and enter the cardName
        actions.waitForElementVisible("trello.boardPage.cardInput");
        actions.sendKeys("trello.boardPage.cardInput", cardName);

        // Locate the "Add" or "Create" button to submit the new card
        WebElement addButton = driver.findElement(By.xpath("//input[@class='nch-button nch-button--primary confirm mod-compact js-add-card']"));
        addButton.click();

    }

    public void addListProcess(){

        actions.waitForElementVisible("trello.boardPage.listByName");
        actions.clickElement("trello.boardPage.listByName");

        //input list name
        actions.waitForElementVisible("trello.boardPage.listInput");
        actions.sendKeys("trello.boardPage.listInput", "List Name");

        //Clicking add list button
        actions.waitForElementVisible("trello.boardPage.addListButton");
        actions.clickElement("trello.boardPage.addListButton");
    }


    public void moveCardToList(String cardName, String listName) {
        actions.dragAndDrop(cardName,listName);

    }

    public boolean assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
        return false;
    }

    public void deleteCardFromList(String cardName) {
        actions.mouseHoverBy("trello.boardPage.cardEditIcon");
        // Locate the "Add" or "Create" button to submit the new card
        WebElement deleteButton = driver.findElement(By.xpath("//a[@data-testid='quick-card-editor-archive']"));
        deleteButton.click();

    }

    public WebElement getCardTitle(String cardName) {

        return driver.findElement(By.xpath("//span[text()='"+cardName+"']"));

    }
}
