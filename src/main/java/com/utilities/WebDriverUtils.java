package com.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	public static WebDriver driver = null;

	private static String testName;

	public static String getTestName() {
		return testName;
	}

	public static void setTestName(String methodName) {
		testName = methodName;
	}

	public static void maximizeWindow() throws Exception {
		try {
			driver.manage().window().maximize();
		} catch (Exception ex) {

		}
	}

	public static WebDriver openBrowser() throws Exception {

		String sBrowserName;

		try {
			testName = getTestName();
			sBrowserName = Browsers.get();
			BrowserEnum currentBrowser = BrowserEnum.valueOf(sBrowserName.toUpperCase());

			switch (currentBrowser) {

			case FIREFOX:
				System.setProperty("webdriver.gecko.driver", Constant.vFirefoxBrowserPath);

				driver = new FirefoxDriver();
				break;

			case CHROME:
				ChromeOptions options = new ChromeOptions();
				System.setProperty("webdriver.chrome.driver", Constant.vChromeBrowserPath);
				options.addArguments("--test-type");
				options.addArguments("--start-maximized");
				options.addArguments("--lang=en");
				driver = new ChromeDriver(options);
				break;

			default:
				String errorMessage = "The browser '" + sBrowserName + "' is not valid.";

				throw new Exception(errorMessage);
			}
			maximizeWindow();

		} catch (Exception ex) {
			System.out.println("no browser");
		}
		return driver;
	}

	public static String currentURL() {
		String URL = driver.getCurrentUrl();
		return URL;
	}

	public static void goToURL(String url) throws Exception {
		try {

			if (driver == null) {

				throw new Exception("Driver is null");
			}
			driver.get(url);

		} catch (Exception ex) {

		}
	}

	public static WebElement FindElementByXpath(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		return element;
	}

	public static WebElement FindElementByID(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
		return element;
	}

	public static WebElement FindElementByCSS(String css) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
		return element;
	}

	public static void InputValues(WebElement FieldElement, String StringToBeEntered) {
		FieldElement.sendKeys(StringToBeEntered);
	}

	public static void ClickAction(WebElement ElementToBeClicked) {
		ElementToBeClicked.click();
	}

	public static String TextChecker(WebElement ElementForText) {
		String valueOfTheElement = ElementForText.getText();
		return valueOfTheElement;
	}

	public static void TakeScreenShot() {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File("C:\\Users\\vsingh\\eclipse-workspace\\EclipseCurrentOffice\\defectScreenShots\\"
							+ getTestName() + ".jpeg"));
			Log.info(Environment.ReadExcelData("Global_Validater", 5, 1));

		} catch (Exception x) {
			Log.error(x);
			Log.info(Environment.ReadExcelData("Global_Validater", 6, 1));
		}
	}

	public enum BrowserEnum {
		FIREFOX, CHROME
	}

}
