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

public class UpdatedSprint20 {

	Logger logger = Logger.getLogger(UpdatedSprint20.class);

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

			for (int i = 0; i < callSign.size(); i++) {
				logger.info("call sign is  " + callSign.get(i) + " LCN is " + lcn.get(i));
				Assert.assertEquals(callSign.get(i).contains(lcn.get(i)), false, "Channel name is containing LCN " + callSign.get(i));
			}

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

			Assert.assertEquals(JumtoTimeOptionCounts.size(), 26);

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
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName);

			clp.oneOfDefaultTimezone(driver, testCaseName, Country, Timezone);

			String	gridStartTime		= clp.gridStartTime(driver, testCaseName);

			String	expectedStartTime	= PrimeTime;

			switch (expectedStartTime) {
				case "7:00 pm": {
					Assert.assertEquals(gridStartTime, expectedStartTime);
					break;
				}
				case "8:00 pm": {
					Assert.assertEquals(gridStartTime, expectedStartTime);
					break;
				}
				default: {
					logger.error("Grid Start time Does not match with mapped time Country : " + Country + " : TimeZone as : " + Timezone
					        + " : Expected startTime is : " + expectedStartTime + " : and Start of grid time is : " + gridStartTime);
					Assert.fail("Grid Start time Does not match with mapped time Country : " + Country + " : TimeZone as : " + Timezone
					        + " : Expected startTime is : " + expectedStartTime + " : and Start of grid time is : " + gridStartTime);
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

	@Test
	public void verifyContactUs() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			String ContactUsURL = ip.getContactUS(driver, testCaseName).getAttribute("href");

			Assert.assertEquals(ContactUsURL, "https://feedback.zap2it.com/tvlistings");

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test
	public void verifyPoweredBy() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			String PoweredByText = ip.getPoweredBy(driver, testCaseName);

			Assert.assertEquals(PoweredByText, "Powered by Gracenote, Inc.");

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test
	public void verifyTVListingIsVisible() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			WebElement TVListing = ip.getTVListingLink(driver, testCaseName);

			Assert.assertEquals(TVListing.isDisplayed(), true, " TVListing text in Header Ribbon is not displayed");
			Assert.assertEquals(TVListing.isEnabled(), true, "TVListing text in Header Ribbon is not  Clickable");

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test
	public void verifyTVListingByNumberIsVisible() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			WebElement TVListingByNumber = ip.getTVListingByNumberLink(driver, testCaseName);
			Assert.assertEquals(TVListingByNumber.isDisplayed(), true, " TV By nunber text in Header Ribbon is not displayed");
			Assert.assertEquals(TVListingByNumber.isEnabled(), true, "TVByNumber text in Header Ribbon is not  Clickable");

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test
	public void verifyPoweredByLink() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			Assert.assertEquals(ip.getPoweredByLink(driver, testCaseName), gap.getGracenoteURL(),
			        "Gracenote Hyper link in footer dosent match to what specified in input");

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}
}
