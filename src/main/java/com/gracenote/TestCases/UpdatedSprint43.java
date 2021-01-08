package com.gracenote.TestCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.pages.IndexPage;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedSprint43 {

	private static Logger	logger	= Logger.getLogger(UpdatedSprint43.class);

	IndexPage				ip		= new IndexPage();

	@Test
	public void ValidateTrackerScriptCode() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			Assert.assertTrue(ip.getScriptText(driver, testCaseName).contains(gap.getGoogleAnalyticsTag()),
			        "Our web page does not contain Google Analytics Tracker information :: " + driver.getCurrentUrl());
			Assert.assertTrue(ip.getScriptText(driver, testCaseName).contains(gap.getComscoreAnalyticsTag()),
			        "Our web page does not contain Comscore Analytics Tracker information :: " + driver.getCurrentUrl());

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test
	public void ValidateAdsFile() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL() + "/ads.txt");

			Assert.assertTrue(driver.getPageSource().contains(gap.getAdsFileText()),
			        "Ads File is missing from our site :: " + driver.getCurrentUrl());

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}

	}

}
