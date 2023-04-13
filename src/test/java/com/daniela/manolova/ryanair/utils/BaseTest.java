package com.daniela.manolova.ryanair.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.daniela.manolova.ryanair.pageobjects.android.WelcomePage;
import com.daniela.manolova.ryanair.utils.android.Utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseTest extends Utils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public WelcomePage welcomePage;

	// always run stays for running no matter which group is run
	@BeforeMethod(alwaysRun = true)
	public void ConfigureAppium() throws IOException {

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\data.properties");
		prop.load(fis);

		String ipAddress = prop.getProperty("IpAddress");
		String port = prop.getProperty("Port");
		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		// create driver that builds the environment
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName")); // emulator
		options.setApp(System.getProperty("user.dir")
				+ "\\src\\test\\resources\\apps\\Ryanair_3.151.0_Apkpure.apk");

		// create driver
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		welcomePage = new WelcomePage(driver);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
