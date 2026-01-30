package listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.util.ResultsUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureDescriptionListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String description = result.getMethod().getDescription();
        if (description != null && !description.isEmpty()) {
            // Add a step with description at the start
            Allure.getLifecycle().startStep(result.getMethod().getMethodName(), new StepResult().setName(description));
        } else {
            Allure.getLifecycle().startStep(result.getMethod().getMethodName(), new StepResult().setName(result.getMethod().getMethodName()));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.getLifecycle().updateStep(step -> step.setStatus(Status.PASSED));
        Allure.getLifecycle().stopStep();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.getLifecycle().updateStep(step -> step.setStatus(Status.FAILED));
        // Attach exception
        Allure.addAttachment("Failure Reason", result.getThrowable().toString());
        Allure.getLifecycle().stopStep();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.getLifecycle().updateStep(step -> step.setStatus(Status.SKIPPED));
        Allure.getLifecycle().stopStep();
    }

    @Override
    public void onStart(ITestContext context) {
        // Optional: attach suite start step
        Allure.step("Starting Test Suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Allure.step("Finished Test Suite: " + context.getName());
    }
}
