import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sumValidation {

    @Test(description = "Verify total purchase amount equals sum of course price multiplied by copies")
    public void validatePurchaseAmountSum() {

        int sum = 0;

        JsonPath jsonPath = new JsonPath(payload.coursePrice());
        int courseCount = jsonPath.getInt("courses.size()");

        for (int i = 0; i < courseCount; i++) {
            int price = jsonPath.getInt("courses[" + i + "].price");
            int copies = jsonPath.getInt("courses[" + i + "].copies");
            sum += price * copies;
        }

        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        Assert.assertEquals(
                sum,
                purchaseAmount,
                "Mismatch between calculated sum and dashboard purchase amount"
        );
    }
}
