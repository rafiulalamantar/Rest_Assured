package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestDescriptionListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String desc = result.getMethod().getDescription();
        if(desc != null && !desc.isEmpty()){
            System.out.println("STARTING TEST: " + desc);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String desc = result.getMethod().getDescription();
        if(desc != null && !desc.isEmpty()){
            System.out.println("PASSED TEST: " + desc);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String desc = result.getMethod().getDescription();
        if(desc != null && !desc.isEmpty()){
            System.out.println("FAILED TEST: " + desc);
        }
    }
}
