import files.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {

    public static void main(String[] args){

        JsonPath jsonPath = new JsonPath(payload.coursePrice());
        int count = jsonPath.getInt("courses.size()");
        System.out.println(count);
    }
}
