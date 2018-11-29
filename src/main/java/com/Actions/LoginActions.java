package com.Actions;

import com.PageObjects.LoginPageObjects;
import com.utilities.Environment;
import com.utilities.Log;
import com.utilities.WebDriverUtils;
import io.qameta.allure.Step;

public class LoginActions {
	static boolean status = false;
	@Step("Login Test case")
	public static boolean LoginAction() throws Exception {

		WebDriverUtils.goToURL(Environment.ReadExcelData("URL", 1, 0));
		WebDriverUtils.InputValues(LoginPageObjects.UsernameField(), Environment.ReadExcelData("Credentials", 1, 0));
		WebDriverUtils.ClickAction(LoginPageObjects.NextButtonEmail());
		WebDriverUtils.InputValues(LoginPageObjects.PasswordField(), Environment.ReadExcelData("Credentials", 1, 1));
		WebDriverUtils.ClickAction(LoginPageObjects.SignInButton());
		WebDriverUtils.ClickAction(LoginPageObjects.YesButton());
		WebDriverUtils.ClickAction(LoginPageObjects.DoneButton());
		if(WebDriverUtils.TextChecker(LoginPageObjects.WelcomeText()).contentEquals(Environment.ReadExcelData("Login_Validator", 1, 0))) 
		{
			Log.info(Environment.ReadExcelData("Global_Validater", 1, 1));
			status = true;	
		}
		else 
		Log.info(Environment.ReadExcelData("Global_Validater", 2, 1));
		return status;
	}
}