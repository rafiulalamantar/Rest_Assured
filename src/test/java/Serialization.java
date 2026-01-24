import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Serialization {
    @Test
    public void addPlace(){
        RestAssured.baseURI="";

         Response response = given().queryParam("key","qaclick123")
                .body("")
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();

    }
}
