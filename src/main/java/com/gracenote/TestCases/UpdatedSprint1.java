package com.gracenote.TestCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.pages.ChannelListOnPincode;
import com.gracenote.pages.IndexPage;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedSprint1 {

	private static Logger logger = Logger.getLogger(UpdatedSprint1.class);

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class, groups = "sanity")
	public void verifyHeadendNameDeviceType(GlobalInputData gid) {

		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);

			clp.HeadendGridLoader(driver, testCaseName, gid);

			String HeadendNameDeviceTypePostalCode = ip.getHeadendNameNameDeviceType(driver, testCaseName).trim();

			if (HeadendNameDeviceTypePostalCode.equals("Local Over the Air Broadcast"))
				Assert.assertEquals(HeadendNameDeviceTypePostalCode.trim(), gid.getSelectedProvider() + " (" + gid.getZipCode() + ")");
			else
				Assert.assertEquals(HeadendNameDeviceTypePostalCode.trim(), gid.getSelectedProvider() + " (" + gid.getZipCode() + ")");

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test(groups = "sanity")
	public void checkProvidersforPincodes() throws InterruptedException {

		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName);

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}

	}

}
