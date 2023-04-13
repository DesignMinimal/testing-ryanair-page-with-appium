package com.daniela.manolova.ryanair.pageobjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.daniela.manolova.ryanair.utils.android.Actions;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WelcomePage extends Actions {

	AndroidDriver driver;

	public WelcomePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		try {
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AndroidFindBy(id = "com.ryanair.cheapflights:id/agree_cta")
	private WebElement agreeTab;

	@AndroidFindBy(id = "com.ryanair.cheapflights:id/cta_login")
	private WebElement loginButton;

	public void clickAgree() {
		agreeTab.click();
		// (driver).hideKeyboard();

	}

	public SignInPage logIn() {
		waitForElementToAppear(loginButton, driver);
		loginButton.click();
		return new SignInPage(driver);
	}

	public void setActivity() {
		Activity activity = new Activity("com.ryanair.cheapflights",
				"com.ryanair.cheapflights.ui.mandatorylogin.MandatoryLoginActivity");
		(driver).startActivity(activity);	
	}

}
