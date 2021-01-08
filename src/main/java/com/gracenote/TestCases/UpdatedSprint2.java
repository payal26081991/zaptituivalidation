package com.gracenote.TestCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.pages.ChannelListOnPincode;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedSprint2 {

	private Logger logger = Logger.getLogger(UpdatedSprint2.class);

	@Test(dataProvider = "testDataProvider", dataProviderClass = com.gracenote.resources.ExcelReader.class, groups = "sanity")
	public void GridChannelsCountValidation(GlobalInputData gid) {
		throw new SkipException("this is handeled by com.gracenote.Miscellaneous.ChannelNames Class");
	}

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class, groups = "sanity")
	public void HeadendGridChannelsLoading(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {
			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			logger.info("Country : " + gid.getCountryName() + " zipcode : " + gid.getZipCode() + " providerTab : " + gid.getProviderTab()
			        + " Proider is : " + gid.getSelectedProvider());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);

			clp.ChannelShowsTimeVerfication(driver, testCaseName, gid);

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}

	}

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class, groups = "sanity")
	public void TVGridEpandEachShowcard(GlobalInputData gid) {

		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {
			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			logger.info("Country : " + gid.getCountryName() + " ,zipcode : " + gid.getZipCode() + " ,providerTab : " + gid.getProviderTab()
			        + " ,Proider is : " + gid.getSelectedProvider());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);

			clp.ChannelShowsTimeVerfication(driver, testCaseName, gid);

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}
}
