package configuration;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestBase {
    private RequestSpecification requestSpecification;

    public RequestBase defineRequestSpecification(String url, String basePath) {
        this.requestSpecification = given()
                .baseUri(url)
                .basePath(basePath)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
        return this;
    }

    public Response getRequestResponse() {
        return requestSpecification.when()
                .get()
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }

    public Map<String, Float> getMapFromPath(Response response, String path){
        return response.then()
                .extract()
                .jsonPath()
                .getMap(path);
    }

    public String getStringFromPath(Response response, String path){
        return response.then()
                .extract()
                .path(path);
    }

    public void validateJSONSchema(Response response, String schemaPath){
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }
}
