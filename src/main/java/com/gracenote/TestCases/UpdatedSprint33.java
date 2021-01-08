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
import com.gracenote.pages.SingleStationListPage;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedSprint33 {

	private static Logger logger = Logger.getLogger(UpdatedSprint33.class);

	@Test(dataProvider = "TestDataProvidersStandardAffiliate", dataProviderClass = com.gracenote.resources.ExcelReader.class, groups = "sanity")
	public void verifyHeadendNameDeviceTypeStandardAffiliate(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();
		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());
			
			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getAffiliateTag());
			
			logger.info("Affiliate URL : " + gap.getNewSiteURL() + gap.getAffiliateURL() + gid.getAffiliateTag());

			ip.gridLoadingTimeWait(driver, testCaseName);

			String HeadendNameDeviceTypePostalCode = ip.getHeadendNameNameDeviceType(driver, testCaseName);

			if (HeadendNameDeviceTypePostalCode.equals("Local Over the Air Broadcast"))
				Assert.assertEquals(HeadendNameDeviceTypePostalCode.trim(), gid.getSelectedProvider() + " (" + gid.getZipCode() + ")");
			else
				Assert.assertEquals(HeadendNameDeviceTypePostalCode.trim(), gid.getSelectedProvider() + " (" + gid.getZipCode() + ")");

			/*
			 * We execute Java script to get text of change provider button on TV Grid. As
			 * text of this button is in CSS in span/::after tag. Hence js is used to get
			 * text
			 */

			String ChangeProviderbuttonText = ip.getChangeProviderOrTimezoneButtonText(driver, testCaseName);

			Assert.assertEquals(ChangeProviderbuttonText.replace("\"", ""), "Change Provider or Time Zone",
			        "failed to verify the text of change provider button on TV Grid for affiliate :: " + gid.getAffiliateTag());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);

			String StandardTVGridFindProviderText = clp.getFindProviderText(driver, testCaseName);

			Assert.assertEquals(StandardTVGridFindProviderText, "Find Providers",
			        "failed to verify text of find provider on change provider pop-up for affiliate : " + gid.getAffiliateTag());

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}

	}

	@Test(dataProvider = "TestDataProvidersSingleProvider", dataProviderClass = com.gracenote.resources.ExcelReader.class, groups = "sanity")
	public void verifyHeadendNameDeviceTypeSingleProvider(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			//driver = ds.getDriver(testCaseName, gap.getBrowser(),gap.getNewSiteURL() + gap.getAffiliateURL() + gid.getAffiliateTag() + "&gt=sp");

			driver = ds.getDriver(testCaseName, gap.getBrowser(),gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getAffiliateTag() + "&gt=sp");
			
			ip.gridLoadingTimeWait(driver, testCaseName);

			String HeadendNameDeviceTypePostalCode = ip.getHeadendNameNameDeviceType(driver, testCaseName);

			if (HeadendNameDeviceTypePostalCode != null) {
				Assert.assertEquals(HeadendNameDeviceTypePostalCode.trim(), gid.getSelectedProvider() + " (" + gid.getZipCode() + ")");
			} else {
				Assert.fail("HeadendNameDeviceTypePostalCode found as null");
			}

			String ChangeProviderbuttonText = ip.getChangeProviderOrTimezoneButtonText(driver, testCaseName);

			if (ChangeProviderbuttonText != null) {
				Assert.assertEquals(ChangeProviderbuttonText.replace("\"", ""), "Change Location",
				        "failed to verify the text of change Zipcode button on TV Grid for affiliate :: " + gid.getAffiliateTag());
			} else {
				Assert.fail("ChangeProviderbuttonText found as null");
			}

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);

			String SingleProviderFindProviderText = clp.getFindProviderText(driver, testCaseName);

			if (SingleProviderFindProviderText != null) {
				Assert.assertEquals(SingleProviderFindProviderText, "Find Lineup",
				        "failed to verify text of find provider on change provider pop-up for affiliate : " + gid.getAffiliateTag());
			} else {
				Assert.fail("SingleProviderFindProviderText found as null");
			}

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProvidersSingleStation", dataProviderClass = com.gracenote.resources.ExcelReader.class, groups = "sanity")
	public void verifyScheduleDisplaySingleStation(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		SingleStationListPage			sslp			= new SingleStationListPage();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			//driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL() + gap.getSingleStaionURL() + gid.getAffiliateTag());
			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getSingleStaionURL() + gid.getAffiliateTag());
			
			ip.gridLoadingTimeWait(driver, testCaseName);

			List<WebElement> ScheudleAiring = sslp.getScheduleAiring(driver, testCaseName);

			Assert.assertEquals(true, ScheudleAiring.size() > 0,
			        " No Airing information is displayed for input URL : " + driver.getCurrentUrl());

			sslp.goToGridView(driver, testCaseName);

			List<WebElement> GridTimeBarValues = sslp.getGridTimeBar(driver, testCaseName);

			Assert.assertEquals(true, GridTimeBarValues.size() > 0,
			        " Grid View of single station not displaying any dates for airing" + driver.getCurrentUrl());

			List<WebElement> GridPrograms = sslp.getProgramAirings(driver, testCaseName);

			Assert.assertEquals(true, GridPrograms.size() > 0,
			        " Grid View of single station not any program for airing" + driver.getCurrentUrl());

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

}
