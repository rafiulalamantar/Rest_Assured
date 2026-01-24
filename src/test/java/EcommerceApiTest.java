import com.google.common.base.Verify;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import pojo.LoginRequest;
import pojo.LoginResponse;

import static io.restassured.RestAssured.*;
    public class EcommerceApiTest {

        @Test(description = "Verify that a user can login successfully via E-Commerce API using valid credentials and retrieve token and userId")
        public void ecommerceApiTest(){

            RequestSpecification requestSpecification = new RequestSpecBuilder()
                    .setBaseUri("https://rahulshettyacademy.com")
                    .setContentType(ContentType.JSON)
                    .build();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUserEmail("rahulshetty@gmail.com");
            loginRequest.setUserPassword("Iamking@000");

            RequestSpecification reqLogin = given().log().all().spec(requestSpecification)
                    .body(loginRequest);
            LoginResponse loginResponse = reqLogin
                    .when().post("/api/ecom/auth/login")
                    .then().log().all().extract().response().as(LoginResponse.class);
            System.out.println(loginResponse.getToken());
            System.out.println(loginResponse.getUserId());

        }
    }
