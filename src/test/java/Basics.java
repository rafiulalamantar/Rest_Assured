import files.payload;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {

    public static void main(String[] args){
        //Add Place API which add places

        RestAssured.baseURI= "https://rahulshettyacademy.com";
       given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payload.addPlace()).when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("server","Apache/2.4.52 (Ubuntu)");

    }

}
