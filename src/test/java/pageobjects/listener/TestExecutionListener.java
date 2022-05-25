package pageobjects.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.qameta.allure.Attachment;
import utilities.Base;

public class TestExecutionListener implements ITestListener {
	private WebDriver driver;
	public void onTestStart(ITestResult result) {
	    // not implemented
	  }
	
	public void onTestSuccess(ITestResult result) {
	    // not implemented
	  }
	
	public void onTestFailure(ITestResult iTestResult) {
	    System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
	   
	    driver = Base.driver; 
	     if(driver instanceof WebDriver) {
	    	 System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
	    	 saveFailureScreenShot(driver);
	     }
	     saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
	  }
	
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }
	
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }
	
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	//Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}

	//Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) { 
		return message;
	}
	
	
	
}
