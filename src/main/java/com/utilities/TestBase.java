package com.utilities;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

@Listeners(com.utilities.TestStatistics.class)
public class TestBase {
	static ExtentTest test;

	public static ExtentTest getTest() {
		return test;
	}

	public static void setTest(ExtentTest test) {
		TestBase.test = test;
	}

	static ExtentReports report;

	public static WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) throws Exception {

		WebDriverUtils.setTestName(method.getName());
		driver = WebDriverUtils.openBrowser();
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		test = report.startTest((method.getName()));
	}

	@AfterMethod(alwaysRun = true)
	public static void tearDown() throws Exception {

		if (driver != null) {
			try {
				driver.quit();
				Log.info(Environment.ReadExcelData("Global_Validater", 4, 1));
				report.endTest(test);
				report.flush();
			} catch (Exception x) {
				Log.error(x);
				Log.info(Environment.ReadExcelData("Global_Validater", 3, 1));
			}
		}
	}

	@AfterSuite(alwaysRun = true)
	public void suiteTearDown() throws IOException {
		EmailReport.sendReportByGMail(Environment.ReadExcelData("EmailData", 0, 1),
				Environment.ReadExcelData("EmailData", 1, 1), Environment.ReadExcelData("EmailData", 2, 1),
				Environment.ReadExcelData("EmailData", 3, 1) + "( " + WebDriverUtils.getCurrentTimeUsingCalendar()
						+ " )");
		Log.info(Environment.ReadExcelData("Global_Validater", 7, 1));
	}
}