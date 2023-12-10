package com.swagLabsTest.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ITestListenerClass implements ITestListener {
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;

	public void configureReport() {
		htmlReporter=new ExtentSparkReporter("ExtentListenerReport.html");
		reports=new ExtentReports();
		reports.attachReporter(htmlReporter);

		reports.setSystemInfo("Machine", "Zabith's PC");

		reports.setSystemInfo("OS", "Windows10");

		htmlReporter.config().setDocumentTitle("Sweg Lab's Test");
		htmlReporter.config().setReportName("Sweg Lab's test report");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Name of the test method successfully executed: "+result.getName());
		test=reports.createTest(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the Pased test case is: "+result.getName(), ExtentColor.GREEN));

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Name of the test method failed: "+result.getName());
		test=reports.createTest(result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: "+result.getName(), ExtentColor.RED));
		// Get the exception associated with the test failure
	    Throwable exception = result.getThrowable();
	    // Log the exception stack trace
	    
	    if (exception != null) {
	        test.log(Status.FAIL, "Bug Details: " + exception.getMessage());
	    }
	    System.out.println("Bug Is: "+exception.getMessage());
	    
	    // Include timestamp in the screenshot file name
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	    String timestamp = dateFormat.format(new Date());

		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+"_"+timestamp+".png";
		File screenshotFile = new File(screenshotPath);

		if(screenshotFile.exists()) {
			 // Log the screenshot as a media entity
			test.fail("Captured Screenshot is below:",
			        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("Name of the test method skiped: "+result.getName());
		test=reports.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skiped test case is: "+result.getName(), ExtentColor.YELLOW));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		onTestFailure(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		configureReport();
		// Get the test suite name
	    String suiteName = context.getSuite().getName();
	    reports.setSystemInfo("Test Suite", suiteName);
		System.out.println("on start method invoked...");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("on finish method invoked...");
		reports.flush();
	}



}
