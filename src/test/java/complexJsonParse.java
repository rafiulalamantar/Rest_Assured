import files.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {

    public static void main(String[] args){

        JsonPath jsonPath = new JsonPath(payload.coursePrice());
        int count = jsonPath.getInt("courses.size()");
        System.out.println(count);

        //print purchase amount
        int totalAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        String titleFirstCourse = jsonPath.get("courses[0].title");
        System.out.println(titleFirstCourse);
    }
}
