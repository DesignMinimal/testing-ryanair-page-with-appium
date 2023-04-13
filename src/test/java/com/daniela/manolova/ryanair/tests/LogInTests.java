package com.daniela.manolova.ryanair.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.daniela.manolova.ryanair.pageobjects.android.SignInPage;
import com.daniela.manolova.ryanair.utils.BaseTest;

public class LogInTests extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Smoke" })
	public void logInTest(HashMap<String, String> input) throws InterruptedException {
		
		
		welcomePage.clickAgree();
		SignInPage signInPage = welcomePage.logIn();
		signInPage.enterUsername(input.get("username"));
		signInPage.enterPassword(input.get("password"));
		boolean isValidTest = Boolean.valueOf(input.get("isValidData"));
		signInPage.clickLogInButton();

		if (isValidTest) {
			Assert.assertTrue(signInPage.doesTextEquals("Register this devic"));
		} else {
			String expectedErrorMessage;
			if (Boolean.valueOf(input.get("isEmailCorrect"))) {
				expectedErrorMessage = "Password must be at least 8 characters long and include at least one upper case letter, at least one lower case letter and at least one numeric digit.";
			} else {
				expectedErrorMessage = "This email doesn't work";
			}
			Assert.assertTrue(signInPage.isErrorMessageDisplayed(expectedErrorMessage),
					"Expected error message: " + expectedErrorMessage + " is not displayed.");
		}

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\testDataLogInTest.json");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };

	}

	/*
	 * @BeforeMethod public void setup() throws InterruptedException { //sets up
	 * always the initial home screen to be set on every new test, cleaning the test
	 * data, but don't start the server again
	 * 
	 * // cmd -> adb devices gives you the devices you have open (emulators and so
	 * on) // cmd -> adb shell dumpsys window | find "mCurrentFocus" - for Windows
	 * 
	 * // this gives you the package name and the activity name of what is open in
	 * your // emulator so that you can directly hop onto the right page
	 * 
	 * //MandatoryLoginActivity Activity activity = new
	 * Activity("com.google.android.gm",
	 * "com.google.android.gm.welcome.WelcomeTourActivity");
	 * (driver).startActivity(activity); Thread.sleep(2000); }
	 */
}
