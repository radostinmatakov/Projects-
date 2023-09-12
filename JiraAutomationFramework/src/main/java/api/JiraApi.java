package api;

import com.google.gson.Gson;
import com.telerikacademy.testframework.PropertiesManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

public class JiraApi {

    private RequestSpecification getRestAssured() {
        Gson deserializer = new Gson();
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri(PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("jira.api.baseURL"))
                .queryParam("email", PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("jira.api.email"))
                .queryParam("token", PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("jira.users.user.apiToken"));
    }

}
