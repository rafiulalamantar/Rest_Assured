import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicsJsonFileInclude {

    public static void main(String[] args) throws IOException {
        //Add Place API which add places

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        Response response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("/GitHub/Rest_Assured/addPlace.json")))).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response();
        System.out.println(response.asString());

        String responseString = response.asString();
        JsonPath jsonPath = new JsonPath(responseString);
        String placeId = jsonPath.getString("place_id");
        System.out.println(placeId);

        String newAddress = "70 winter walk, USA";

        given()
                .log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{ \"place_id\":\"" + placeId + "\", \"address\":\"" + newAddress + "\", \"key\":\"qaclick123\" }")
                .when()
                .put("maps/api/place/update/json")
                .then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"));



        String getPlaceResponse = given()
                .log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath jsonPath1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = jsonPath1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddress);



    }

}
