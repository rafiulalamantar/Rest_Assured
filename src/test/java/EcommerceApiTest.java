import com.google.common.base.Verify;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetails;
import pojo.Orders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
    public class EcommerceApiTest {

        private String token;
        private String userID;
        private String productID;

        @Test(description = "Verify that a user can login successfully via E-Commerce API using valid credentials and retrieve token and userId")
        public void verifyUserCanLoginSuccessfully(){

            RequestSpecification requestSpecification = new RequestSpecBuilder()
                    .setBaseUri("https://rahulshettyacademy.com")
                    .setContentType(ContentType.JSON)
                    .build();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUserEmail("rahulshetty@gmail.com");
            loginRequest.setUserPassword("Iamking@000");

            RequestSpecification reqLogin = given().relaxedHTTPSValidation().log().all().spec(requestSpecification)
                    .body(loginRequest);
            LoginResponse loginResponse = reqLogin
                    .when().post("/api/ecom/auth/login")
                    .then().log().all().extract().response().as(LoginResponse.class);
            System.out.println(loginResponse.getToken());
            token = loginResponse.getToken();
            userID = loginResponse.getUserId();
            System.out.println(loginResponse.getUserId());

        }
        @Test(description = "Verify user can create product", dependsOnMethods = "verifyUserCanLoginSuccessfully")
        public void verifyUserCanCreateProductSuccessfully(){
            RequestSpecification requestSpecAddProduct = new RequestSpecBuilder()
                    .setBaseUri("https://rahulshettyacademy.com")
                    .addHeader("Authorization",token)
                    .build();

            RequestSpecification addingProduct = given().log().all().spec(requestSpecAddProduct)
                    .param("productName","Laptop")
                    .param("productAddedBy",userID)
                    .param("productCategory","fashion")
                    .param("productSubCategory","shirts")
                    .param("productPrice","11500")
                    .param("productDescription","Lenovo")
                    .param("productFor","men")
                    .multiPart("productImage",new File("/Users/rafiulalamantar/Documents/GitHub/Rest_Assured/computer.jpg"));

            String responseAddProduct = addingProduct.when().post("/api/ecom/product/add-product")
                    .then().log().all().extract().response().asString();
            JsonPath jsonPath = new JsonPath(responseAddProduct);
            productID = jsonPath.get("productId");

        }
        @Test(description = "Verify that user can create order successfully",dependsOnMethods = "verifyUserCanCreateProductSuccessfully")
        public void verifyUserCanCreateOrder(){
            RequestSpecification requestSpecCreateOrder = new RequestSpecBuilder()
                    .setBaseUri("https://rahulshettyacademy.com")
                    .addHeader("Authorization",token)
                    .setContentType(ContentType.JSON)
                    .build();

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setCountry("India");
            orderDetails.setProductOrderedId(productID);
            List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
            orderDetailsList.add(orderDetails);


            Orders orders = new Orders();
            orders.setOrders(orderDetailsList);
            RequestSpecification  createOrderReq = given().log().all().spec(requestSpecCreateOrder).body(orders);
            String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order")
                    .then().log().all().extract().response().asString();
            System.out.println(responseAddOrder);
        }
        @Test(description = "Verify that user delete product successfully",dependsOnMethods = "verifyUserCanCreateOrder")
        public void userCanDeleteOrderSuccessfully(){
            RequestSpecification requestSpecDeleteOrder = new RequestSpecBuilder()
                    .setBaseUri("https://rahulshettyacademy.com")
                    .addHeader("Authorization",token)
                    .setContentType(ContentType.JSON)
                    .build();

            RequestSpecification deleteProductReq = given().log().all().spec(requestSpecDeleteOrder)
                    .pathParam("productId",productID);
            String deleteProductResponse = deleteProductReq.when()
                    .delete("/api/ecom/product/delete-product/{productId}")
                    .then().log().all().extract().response().asString();
            System.out.println(deleteProductResponse);

            JsonPath jsonPathDelete = new JsonPath(deleteProductResponse);
            Assert.assertEquals("Product Deleted Successfully",jsonPathDelete.get("message"));

        }

    }
