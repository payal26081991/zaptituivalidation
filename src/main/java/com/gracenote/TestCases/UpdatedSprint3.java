package com.gracenote.TestCases;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.pages.IndexPage;
import com.gracenote.pages.SignupLogin;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedSprint3 {

	private Logger logger = Logger.getLogger(UpdatedSprint3.class);

	@Test
	public void userSignUp() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		SignupLogin						sl				= new SignupLogin();
		GlobalInputData					gid				= new GlobalInputData();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			// wea.click(ip.getSkipTour(driver, testCaseName));

			sl.signUp(driver, testCaseName, gid);

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test
	public void resetPassword() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		WebElementActions				wea				= WebElementActions.getWebElementActionsObject();
		SignupLogin						sl				= new SignupLogin();
		GlobalInputData					gid				= new GlobalInputData();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			wea.click(ip.getSkipTour(driver, testCaseName));

			ip.ClickOnSignupLogin(driver, testCaseName);

			sl.forgotPassword(driver, testCaseName, gid);

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test
	public void jumpToTimeVerificationGridBar() {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		WebElementActions				wea				= WebElementActions.getWebElementActionsObject();
		GlobalInputData					gid				= new GlobalInputData();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			wea.click(ip.getSkipTour(driver, testCaseName));

			String				jumpTotimeString	= "";

			List<WebElement>	jumpToTimeOptions	= ip.getJumpToTimeDropDownValues(driver, testCaseName);
			logger.info("Our list size is :: " + jumpToTimeOptions.size());

			for (WebElement elm : jumpToTimeOptions) {
				wea.click(ip.jumpToTimeElem(driver, testCaseName));

				jumpTotimeString = elm.getText();
				logger.info("Text if web element is :: " + jumpTotimeString);

				if (!(jumpTotimeString.equalsIgnoreCase("Now")) && !(jumpTotimeString.equalsIgnoreCase("Primetime"))
				        && !(jumpTotimeString.equalsIgnoreCase("Daytime")) && (jumpTotimeString.length() > 0)) {
					// wea.click(ip.jumpToTimeElem(NewSiteDriver));
					// Thread.sleep(5000);
					new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(elm));
					wea.click(elm);

					Assert.assertEquals(ip.gridStartTime(driver, testCaseName), jumpTotimeString,
					        "Matching the start of TV Grid time with jump to time drop down selected");
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
