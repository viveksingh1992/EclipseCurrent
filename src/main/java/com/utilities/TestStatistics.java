package com.utilities;

import java.util.ArrayList;
import java.util.List;
import org.mortbay.log.Log;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class TestStatistics extends TestBase implements ITestListener, IRetryAnalyzer {

	List<ITestNGMethod> passedtests = new ArrayList<ITestNGMethod>();
	List<ITestNGMethod> failedtests = new ArrayList<ITestNGMethod>();
	List<ITestNGMethod> skippedtests = new ArrayList<ITestNGMethod>();
	private int retryCount = 0;
	private static final int maxRetryCount = 1;

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		teardownTest(result);
		passedtests.add(result.getMethod());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		teardownTest(result);
		failedtests.add(result.getMethod());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		teardownTest(result);
		skippedtests.add(result.getMethod());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		context.getStartDate();
		Log.info("========TEST START========");
	}

	@Override
	public void onFinish(ITestContext context) {
		context.getEndDate();
		Log.info("========TEST FINISH========");
	}

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}

	private void teardownTest(ITestResult result) {
		String status = result.isSuccess() ? "SUCCESS" : "FAILURE";
		com.utilities.Log.info("======" + status + "======");
		com.utilities.Log.info("Test: " + result.getInstanceName() + "." + result.getName());
	}

}