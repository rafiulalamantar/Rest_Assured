import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import pojo.Api;
import pojo.GetCourse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class oAuthTestPojo {
    @Test(description = "erify that the course title is 'SoapUI Webservices Testing using Indexing")
    public void oAuthTestWithUrl(){

        String response = given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");

        GetCourse getCourse = given().queryParam("access_token",accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);

        System.out.println(getCourse.getLinkedIn());
        System.out.println(getCourse.getInstructor());

        System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle());

    }
    @Test(description = "Verify that the course title “SoapUI Webservices Testing” is present in the API courses retrieved dynamically")
    public void getCourseTitleAndPrice(){

        String response = given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");

        GetCourse getCourse = given().queryParam("access_token",accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);

        System.out.println(getCourse.getLinkedIn());
        System.out.println(getCourse.getInstructor());

        System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle());

        List <Api> apiCourses = getCourse.getCourses().getApi();
        for (int i=0; i<apiCourses.size();i++){
            if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
                System.out.println(apiCourses.get(i).getPrice());
            }
        }
    }
}
