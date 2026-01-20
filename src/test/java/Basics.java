import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {

    public static void main(String[] args) {
        //Add Place API which add places

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        Response response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(payload.addPlace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response();
        System.out.println(response.asString());

        String responseString = response.asString();
        JsonPath jsonPath = new JsonPath(responseString);
        String placeId = jsonPath.getString("place_id");
        System.out.println(placeId);

        given()
                .log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{ \"place_id\":\"" + placeId + "\", \"address\":\"70 winter walk, USA\", \"key\":\"qaclick123\" }")
                .when()
                .put("maps/api/place/update/json")
                .then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"));


    }

}
