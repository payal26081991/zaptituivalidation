package com.gracenote.TestCases;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.pages.ChannelListOnPincode;
import com.gracenote.pages.IndexPage;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedSprint7 {

	Logger logger = Logger.getLogger(UpdatedSprint7.class);

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void callSignLcnRemoval(GlobalInputData gid) {
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

			List<String>	callSign	= ip.getCallSignText(driver, testCaseName);

			List<String>	lcn			= ip.getLCNText(driver, testCaseName);

			Assert.assertEquals(callSign.size(), lcn.size());

			/*for (int i = 0; i < callSign.size(); i++) {
				logger.info("call sign is  " + callSign.get(i) + " LCN is " + lcn.get(i));
				Assert.assertEquals(callSign.get(i).contains(lcn.get(i)), false, "Channel name is containing LCN " + callSign.get(i));
			}*/

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void VerfiyJumtoDateOptionCounts(GlobalInputData gid) {

		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			List<WebElement> JumtoDateOptionCounts = ip.getJumpToDateDropDownValues(driver, testCaseName);

			Assert.assertEquals(JumtoDateOptionCounts.size(), 14);

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void VerfiyJumtoTimeOptionCounts(GlobalInputData gid) {

		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			List<WebElement> JumtoTimeOptionCounts = ip.getJumpToTimeDropDownValues(driver, testCaseName);

			Assert.assertEquals(JumtoTimeOptionCounts.size(), 27);

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test(dataProvider = "TestArrayDataProvider", dataProviderClass = com.gracenote.resources.ArrayDataProvider.class)
	public void validatePrimetime(String Country, String Timezone, String PrimeTime) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName);

			clp.oneOfDefaultTimezone(driver, testCaseName, Country, Timezone);

			ip.goToPrimeTime(driver, testCaseName);

			clp.gridLoadingTimeWait(driver, testCaseName);

			String gridStartTime = clp.gridStartTime(driver, testCaseName);

			switch (PrimeTime) {
				case "7:00 PM": {
					Assert.assertEquals(gridStartTime, PrimeTime, "Grid start time doesn't match with Provided Primetime.");
					break;
				}
				case "8:00 PM": {
					Assert.assertEquals(gridStartTime, PrimeTime);
					break;
				}
				default: {
					logger.error("Grid Start time Does not match with mapped time Country : " + Country + " : TimeZone as : " + Timezone
					        + " : Expected startTime is : " + PrimeTime + " : and Start of grid time is : " + gridStartTime);
					Assert.fail("Grid Start time Does not match with mapped time Country : " + Country + " : TimeZone as : " + Timezone
					        + " : Expected startTime is : " + PrimeTime + " : and Start of grid time is : " + gridStartTime);
				}
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
