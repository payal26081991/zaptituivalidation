package com.gracenote.Miscellaneous;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.pages.ChannelListOnPincode;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedGridLoadTimeComparer {

	private Logger logger = Logger.getLogger(UpdatedGridLoadTimeComparer.class);

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class, groups = "TimeComparison")
	public void gapZapGridLoading(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);

			clp.HeadendGridLoader(driver, testCaseName, gid);

			List<WebElement>	channelsData	= driver.findElements(By.xpath(".//*[@class='channel-row']"));

			long				currentTime		= System.currentTimeMillis();

			gap.setGridEndTime((currentTime));

			long Timedifference = (gap.getGridEndTime() - gap.getGridStartTime());

			logger.error("Zipcode :: " + gid.getZipCode() + " and headend as :: " + gid.getSelectedProvider()
			        + " time taken to load grid in milliseconds is :: " + Timedifference + " channel count is :: " + channelsData.size());

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}
}
