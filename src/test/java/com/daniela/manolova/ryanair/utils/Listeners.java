package com.daniela.manolova.ryanair.utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.daniela.manolova.ryanair.utils.android.Utils;

import io.appium.java_client.AppiumDriver;

public class Listeners extends Utils implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ExtentTest test;
	AppiumDriver driver;

	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());

	}

	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());

		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver),
					result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test passed");
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
