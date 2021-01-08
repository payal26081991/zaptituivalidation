package com.gracenote.TestCases;

import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.pages.ProgramShowCard;
import com.gracenote.resources.DBQueries;
import com.gracenote.resources.QueryResult;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedSprint13 {

	private static Logger logger = Logger.getLogger(UpdatedSprint13.class);

	@Test(dataProvider = "TestDataProvidersCastCrew", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void verifyCastCrew(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		WebElementActions				wea				= WebElementActions.getWebElementActionsObject();
		ProgramShowCard					psc				= new ProgramShowCard();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			String Endpoint = gap.getNewSiteURL() + "/overview.html?programSeriesId=" + gid.getProgramSeriesID() + "&tmsId="
			        + gid.getTMSID() + "&aid=gapzap";

			driver = ds.getDriver(testCaseName, gap.getBrowser(), Endpoint);

			psc.gridLoadingTimeWait(driver, testCaseName);

			wea.click(psc.getCastCrewTab(driver, testCaseName));
			psc.getDuplicateCast(driver, testCaseName, gid);

			psc.getDuplicateCrew(driver, testCaseName, gid);

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}

	}

	@Test(dataProvider = "TestDataProvidersEpisodeGuideSeasons", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void verifyEpisodeGuideSeasons(GlobalInputData gid) {

		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		WebElementActions				wea				= WebElementActions.getWebElementActionsObject();
		ProgramShowCard					psc				= new ProgramShowCard();
		DBQueries						dbq				= new DBQueries();
		ResultSet						localRS;
		QueryResult						qrs				= new QueryResult();
		String							Endpoint;

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			Endpoint = gap.getNewSiteURL() + "/overview.html?programSeriesId=" + gid.getProgramSeriesID() + "&tmsId=" + gid.getTMSID()
			        + "&aid=gapzap";

			driver = ds.getDriver(testCaseName, gap.getBrowser(), Endpoint);

			psc.gridLoadingTimeWait(driver, testCaseName);

			wea.click(psc.getEpisodeGuideTab(driver, testCaseName));

			List<WebElement> Season = null;

			Season = psc.getSeasons(driver, testCaseName);

			int		SeasonCounts		= 0;
			String	SeasonsCountQuery	= dbq.SeasonsCount + "'" + gid.getProgramSeriesID() + "'";
			qrs.setConnectivity();
			localRS = qrs.getMSSQLData(SeasonsCountQuery);
			localRS.next();
			SeasonCounts = Integer.parseInt(localRS.getString("SeasonCount"));
			Assert.assertEquals(Season.size(), SeasonCounts, "program for which Season count dosent match with DB are :: " + Endpoint
			        + " Season Count on web Page is :: " + Season.size() + " and Season Count from DB is :: " + SeasonCounts);

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

}
