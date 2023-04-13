package com.daniela.manolova.ryanair.utils.android;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Utils {

	public AppiumDriverLocalService service;

	public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
		// explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf((ele)));
	}

	public WebElement waitForVisibilityOfElement(WebElement ele, AppiumDriver driver) {
		// explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf((ele)));
		return ele;
	}

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public AppiumDriverLocalService startAppiumServer(String ipAdress, int port) {

		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\manolova\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAdress).usingPort(port).build();
		service.start();
		return service;
	}

	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {

		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\report" + testCaseName + ".png";

		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}
}
