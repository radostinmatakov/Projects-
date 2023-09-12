package api;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.telerikacademy.testframework.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.codehaus.groovy.ast.expr.UnaryMinusExpression;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TrelloApi {

    private RequestSpecification getRestAssured() {
        Gson deserializer = new Gson();
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri(PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("trello.api.baseUrl"))
                .queryParam("key", PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("trello.users.user.apiKey"))
                .queryParam("token", PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("trello.users.user.apiToken"));
    }

    //Authenticate
    public List<String> getUserBoards() {
        return getRestAssured()
                .get("/members/me")
                .thenReturn()
                .jsonPath()
                .get("idBoards");

    }

    public BoardModel createBoard(String boardName, String description){
       return getRestAssured()
                .queryParam("name",boardName)
                .queryParam("description",description)
                .when()
                .post("/boards/")
                .then()
                .extract()
                .response()
               .as(BoardModel.class);
    }

    public BoardModel deleteBoard(String boardId){
        return getRestAssured()
                .when()
                .delete("boards/" + boardId)
                .then()
                .extract()
                .response()
                .as(BoardModel.class);
    }

    public ListModel createList(String boardId, String name){
        return getRestAssured()
                .when()
                .queryParam("name", name)
                .queryParam("idBoard", boardId)
                .post("/lists")
                .then()
                .extract()
                .response()
                .as(ListModel.class);
    }

    public Response createCard(String listId, String name){
        return getRestAssured()
                .when()
                .queryParam("name", name)
                .queryParam("idList", listId)
                .post("/cards")
                .then()
                .extract()
                .response();
    }



    //API: Create a board
    //API: Create 2 lists
    //API: Create a card
    //API: Delete board


}
