import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilder {
    @Test(description = "Verify that the Add Place API request is built using RequestSpecBuilder with Java object serialization to JSON and the response is validated using ResponseSpecBuilder")
    public void addPlace() {

        // ---------- Payload Preparation ----------
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

        // ---------- Request Specification ----------
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .build();

        // ---------- Response Specification ----------
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        // ---------- API Call ----------
        Response response =
                given()
                        .spec(requestSpec)
                        .body(addPlace)
                        .when()
                        .post("/maps/api/place/add/json")
                        .then()
                        .spec(responseSpec)
                        .extract().response();

        // ---------- Validation ----------
        String responseBody = response.asString();
        System.out.println(responseBody);

        Assert.assertTrue(responseBody.contains("\"status\":\"OK\""));
    }

}