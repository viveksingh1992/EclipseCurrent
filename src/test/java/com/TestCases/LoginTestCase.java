package com.TestCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.Actions.LoginActions;
import com.PageObjects.LoginPageObjects;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.Environment;
import com.utilities.Log;
import com.utilities.TestBase;
import com.utilities.TestStatistics;
import com.utilities.WebDriverUtils;
import org.testng.annotations.Listeners;

@Listeners(com.utilities.TestStatistics.class)
public class LoginTestCase extends TestBase {

@Test(retryAnalyzer = TestStatistics.class)
	public void LoginTest() throws Exception {
		DOMConfigurator.configure("Log4j.xml");
		try {
			Log.startTestCase("LoginTest");
			boolean status = LoginActions.LoginAction();
			Assert.assertEquals(WebDriverUtils.TextChecker(LoginPageObjects.WelcomeText()),
				Environment.ReadExcelData("Login_Validator", 1, 0));
			Assert.assertTrue(status, "Test case failed");
			getTest().log(LogStatus.PASS, Environment.ReadExcelData("Login_Validator", 2, 0));
			Log.endTestCase("LoginTest");
		} catch (Exception e) {
			WebDriverUtils.TakeScreenShot();
			getTest().log(LogStatus.FAIL, Environment.ReadExcelData("Login_Validator", 3, 0));
			Log.error(e);
			Log.info(Environment.ReadExcelData("Global_Validater", 2, 1));
			Log.endTestCase("LoginTest");
			Assert.fail(e.getMessage());
		}
	}
}