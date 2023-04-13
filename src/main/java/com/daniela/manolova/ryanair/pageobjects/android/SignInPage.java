package com.daniela.manolova.ryanair.pageobjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.daniela.manolova.ryanair.utils.android.Actions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignInPage extends Actions {
	AndroidDriver driver;

	public SignInPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		try {
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AndroidFindBy(id = "com.ryanair.cheapflights:id/my_ryanair_email_text_input")
	private WebElement usernameField;
	
	@AndroidFindBy(id = "com.ryanair.cheapflights:id/my_ryanair_password_text_input")
	private WebElement passwordField;
	
	@AndroidFindBy(id = "com.ryanair.cheapflights:id/login")
	private WebElement logInButton;
	
	@AndroidFindBy(id = "com.ryanair.cheapflights:id/title")
	private WebElement registerMessage;
	
	@AndroidFindBy(id = "com.ryanair.cheapflights:id/textinput_error")
	private WebElement emailErrorMessage;


	
	public void enterUsername(String name) {
		usernameField.sendKeys(name);
	}
	
	public void enterPassword(String pass) {
		passwordField.sendKeys(pass);
	}

	public void clickLogInButton() {
		(driver).hideKeyboard();
		logInButton.click();
	}

	public boolean doesTextEquals(String text) {
		this.waitForVisibilityOfElement(registerMessage, driver);
		String message = registerMessage.getAttribute("text");
		return message.equals(text);
	}
	
	public boolean isErrorMessageDisplayed(String text) {
		this.waitForVisibilityOfElement(emailErrorMessage, driver);
		String message = emailErrorMessage.getAttribute("text");
		return message.equals(text);
	}
}
