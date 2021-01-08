package com.gracenote.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.gracenote.Listeners.TestNgListeners;
import com.gracenote.constants.IConstants;

/**
 * 
 * @author kunal.ashar
 * @description to capture screenshot
 *
 */
public class Screenshot {

	private static Logger logger = Logger.getLogger(Screenshot.class);

	public static void captureScreenshot(WebDriver driver, String testCaseName, String fileName) {

		try {

			File	screenshot		= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// to maintain sequence of screenshot as per testcase iteration.
			int		iterationCount	= TestNgListeners.testCaseOccurance.get(testCaseName);

			File	destination		= new File(IConstants.TEMP_SCREENSHOT_PATH + "/" + testCaseName + "/" + iterationCount + "_"
			        + new Date().getTime() + "_" + fileName + ".png");

			FileUtils.copyFile(screenshot, destination);

		} catch (IOException e) {
			logger.error("Error while creating screenshot." + e);
		}
		catch(Exception e)
		{
			logger.error("Error while creating screenshot." + e);
		}
	}
}
