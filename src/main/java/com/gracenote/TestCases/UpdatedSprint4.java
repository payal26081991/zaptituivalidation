package com.gracenote.TestCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.pages.IndexPage;
import com.gracenote.pages.SignupLogin;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedSprint4 {

	private static Logger logger = Logger.getLogger(UpdatedSprint4.class);

	@Test
	public void UserLogin() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		GlobalInputData					gid				= new GlobalInputData();
		SignupLogin						sul				= new SignupLogin();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			// wea.click(ip.getSkipTour(NewSiteDriver));

			ip.ClickOnSignupLogin(driver, testCaseName);

			sul.login(driver, testCaseName, gid);

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}

	}

	@Test
	public void userLogout() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		GlobalInputData					gid				= new GlobalInputData();
		SignupLogin						sul				= new SignupLogin();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			// wea.click(ip.getSkipTour(NewSiteDriver));

			ip.ClickOnSignupLogin(driver, testCaseName);
			sul.login(driver, testCaseName, gid);

			ip.clickUserPorfile(driver, testCaseName);
			ip.logOut(driver, testCaseName);

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}

	}
}
