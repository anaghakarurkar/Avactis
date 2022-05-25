package pageobjects.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Attachment;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import utilities.Base;

public class AllureStepListener implements StepLifecycleListener {
	
	protected static Logger logger = LogManager.getLogger(AllureStepListener.class);
	
	@Override
    public void beforeStepStop(final StepResult result) {
			
		if(result.getStatus().equals(Status.FAILED) || result.getStatus().equals(Status.BROKEN)) {
			screenshot();
			result.setStatus(Status.FAILED);
	    }
		
	}

		 @Attachment(value = "Screenshot", type = "image/png")
		 public byte[] screenshot() {
				WebDriver driverInstance = Base.driver;
				byte[] screenshotByte = null;
					 screenshotByte = ((TakesScreenshot) driverInstance).getScreenshotAs(OutputType.BYTES);
				return screenshotByte;
		   
		 }
	}