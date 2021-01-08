package com.gracenote.Miscellaneous;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.pages.ChannelListOnPincode;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedLCNVerifcationForUsers {

	private Logger logger = Logger.getLogger(UpdatedLCNVerifcationForUsers.class);

	@Test()
	public void gapZapGridLoading(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName);

			for (int i = 2; i <= 7; i++) {
				clp.DefaultHeadendSelector(driver, testCaseName, "USA", i);
			}
		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}
}
