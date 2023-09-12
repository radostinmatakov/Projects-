package pages.trello;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

    public void addCardToList1(String cardName) {

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

    public void addCardToList2(String cardName) {

        actions.waitForElementVisible("trello.boardPage.placeholderAddCard");
        actions.sendKeys("trello.boardPage.placeholderAddCard",cardName);

        actions.waitForElementClickable("trello.boardPage.clickAddCardButton");
        actions.clickElement("trello.boardPage.clickAddCardButton");
    }

    public void addListProcess() {

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
        actions.waitForElementClickable("//span[text()='"+cardName+"']",cardName);
        actions.dragAndDrop(cardName, listName);

    }

    public void assertListExists(String listName) {
        actions.waitForElementPresent("trello.boardPage.listByName", listName);
    }

    public void assertCardExists(String cardName) {
        actions.waitForElementPresent("trello.boardPage.checkCardNameField", cardName);
    }

    public void assertBoardExists(String boardName) {
        actions.waitForElementPresent("trello.boardPage.boardByName",boardName);
    }

    public void assertAddListExists() {
        actions.waitForElementPresent("trello.boardPage.listWrapper");

    }

    public void assertHomePageNavAfterBoardDeletion() {
        actions.waitForElementPresent("trello.boardPage.homePageContainer");

    }

    public void assertBoardIsDeleted(String boardName) {
        Assert.assertFalse("Board is not deleted", actions.isElementPresent("trello.boardPage.deleteBoardAssertions", boardName));
    }


    public void deleteCardFromList(String cardName) {
        actions.mouseHoverBy("trello.boardPage.cardEditIcon");
        // Locate the "Add" or "Create" button to submit the new card
        WebElement deleteButton = driver.findElement(By.xpath("//a[@data-testid='quick-card-editor-archive']"));
        deleteButton.click();

    }

    public void deleteExistingBoard1(String cardName) {

        actions.mouseHoverBy("trello.boardPage.boardEditIcon");
        // Locate the "Add" or "Create" button to submit the new card
        WebElement deleteButton = driver.findElement(By.xpath("//button[@title='Close board']"));
        actions.waitForElementVisible("trello.boardPage.boardCloseButton");
        actions.waitForElementClickable("trello.boardPage.boardCloseButton");
        deleteButton.click();
        WebElement deleteConfirm = driver.findElement(By.xpath("//button[@title='Close']"));
        actions.waitForElementClickable("//button[@title='Close']");
        deleteConfirm.click();

    }

    public void deleteExistingBoard2() {

        actions.waitForElementClickable("trello.boardPage.showMenuButton");
        actions.clickElement("trello.boardPage.showMenuButton");

        actions.waitForElementClickable("trello.boardPage.closeBoardButton2");
        actions.clickElement("trello.boardPage.closeBoardButton2");

        actions.waitForElementClickable("trello.boardPage.closeButton2");
        actions.clickElement("trello.boardPage.closeButton2");

        actions.waitForElementClickable("trello.boardPage.permanentlyDeleteButton");
        actions.clickElement("trello.boardPage.permanentlyDeleteButton");

        actions.waitForElementClickable("trello.boardPage.permanentlyDeleteConfirmButton");
        actions.clickElement("trello.boardPage.permanentlyDeleteConfirmButton");

    }

    public String getCardTitle(String cardName) {
        try {
            WebElement element = driver.findElement(By.xpath("//span[text()='" + cardName + "']"));
            return element.getText(); // Return the text if the element is found
        } catch (NoSuchElementException e) {
            // Handle the exception if the element is not found
            String errorMessage = e.getLocalizedMessage();
            return null;
        }
    }

}
