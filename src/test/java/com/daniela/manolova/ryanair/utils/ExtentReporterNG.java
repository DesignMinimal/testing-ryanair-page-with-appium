package com.daniela.manolova.ryanair.utils;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports extent;

	@BeforeTest
	public static ExtentReports getReporterObject() {
		
		// path stored reports
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		// reporter configurations
		reporter.config().setReportName("Web Automation Results");

		// renames browser tab
		reporter.config().setDocumentTitle("Test Results");

		// creates and consolidates all test execution
		extent = new ExtentReports();

		// attaches ExtentSparkReporter for basic configurations
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Daniela Manolova");

		return extent;
	}
}
