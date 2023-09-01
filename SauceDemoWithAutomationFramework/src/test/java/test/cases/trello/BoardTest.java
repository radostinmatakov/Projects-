package test.cases.trello;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.trello.BoardPage;
import pages.trello.BoardsPage;
import com.telerikacademy.testframework.UserActions;
import org.junit.Ignore;
import org.junit.Test;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class BoardTest extends BaseTest {

    @Test
    public void createBoardWhenCreateBoardClicked() {

        login();

        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.createBoard();

        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.assertListExists("To Do");

        //delete board
    }

    @Test
    public void createNewCardInExistingBoardWhenCreateCardClicked() {

        login();
        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.clickOnBoard("My First Board");

        // Create a new card
        BoardPage boardPage = new BoardPage(actions.getDriver());

        boardPage.addCardToList("Card Title");

        // Assert that the newly created card exists on the board
        boardPage.assertListExists("Card Title");

        ///delete
    }

    @Test
    public void moveCardBetweenStatesWhenDragAndDropIsUsed() {

        login();
        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.clickOnBoard("My First Board");

        // Create a new card
        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.assertListExists("Card Title");

        boardPage.moveCardToList("Card Title", "Doing");

        // Assert that the newly created card exists on the board
        //boardPage.assertListExists("Card Title");

    }

    @Ignore
    @Test
    public void deleteBoardWhenDeleteButtonIsClicked() {
        login();
        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.clickOnBoard("My First Board");

        // Create a new card
        BoardPage boardPage = new BoardPage(actions.getDriver());

        // Assert that the newly created card exists on the board
        boardPage.assertListExists("Card Title");

        boardPage.deleteCardFromList("Card Title");

        Assert.assertNull(
                "Card title should not be visible after deletion",
                boardPage.getCardTitle("Card Title"));

    }
}
