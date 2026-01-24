import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class Serialization {
    @Test(description = "Verify successful serialization of request data from Java object to JSON and validate the API response")
    public void addPlace() {
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setName("Rafiul Alam Antar");
        addPlace.setPhone_number("(+91) 983 98765 776");
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setWebsite("https://rahulshettyacademy.com");
        addPlace.setLanguage("English");

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        addPlace.setTypes(types);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);

        // ---------- Request ----------
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        Response response =
                given()
                        .log().all().queryParam("key", "qaclick123")
                        .header("Content-Type", "application/json")
                        .body(addPlace)
                        .when()
                        .post("/maps/api/place/add/json")
                        .then()
                        .statusCode(200)
                        .extract().response();

        // ---------- Validation ----------
        String responseBody = response.asString();
        System.out.println(responseBody);

        Assert.assertTrue(responseBody.contains("OK"));

    }
}