import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class oAuthTestPojo {

    String[] coursesTitles={"Selenium Webdriver Java","Cypress","Protractor"};
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
    @Test(description = "Verify that the courses title are present in the Web Automation courses")
    public void getCoursesTitle(){        String response = given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
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

        ArrayList <String> actualList = new ArrayList<String>();
        List < WebAutomation> webAutomations = getCourse.getCourses().getWebAutomation();
        for (int j=0; j<webAutomations.size(); j++){
            actualList.add(webAutomations.get(j).getCourseTitle());
        }
        List <String> expectedList = Arrays.asList(coursesTitles);
        Assert.assertTrue(actualList.equals(expectedList));
    }
}
