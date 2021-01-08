package com.gracenote.TestCases;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.pages.ChannelListOnPincode;
import com.gracenote.pages.GridPreferences;
import com.gracenote.pages.IndexPage;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedSprint6 {

	Logger logger = Logger.getLogger(UpdatedSprint6.class);

	/*
	 * TODO : As we are not validating the data with DB hence commenting the
	 * following variable decalration ResultSet localRS; QueryResult qrs = new
	 * QueryResult(); DBQueries dbq = new DBQueries();
	 * 
	 */

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void addMusicChannelGirdPreference(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();
		GridPreferences					gp				= new GridPreferences();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);

			clp.HeadendGridLoader(driver, testCaseName, gid);

			List<String> DefaultChannels = ip.getChannels(driver, testCaseName);

			gp.openGridPreference(driver, testCaseName);

			gp.RemoveGridPrefernces(driver, testCaseName);

			gp.includeMusicChannels(driver, testCaseName);

			gp.SaveGridPrefernce(driver, testCaseName);

			List<String>	DefaultMusicChannels	= ip.getChannels(driver, testCaseName);

			// Below list are used to get the difference of channels and similar (in case
			// required)
			List<String>	sameChannels			= DefaultChannels;
			List<String>	diffChannels			= DefaultMusicChannels;
			List<String>	diffQueryChannels		= new ArrayList<String>();

			diffChannels.removeAll(sameChannels);

			logger.info("Country is :: " + gid.getCountryName() + " Zipcode is :: " + gid.getZipCode() + " provider Tab is :: "
			        + gid.getProviderTab() + " Headed is :: " + gid.getSelectedProvider() + " Music Channels are :: " + diffChannels);

			for (String channel : diffChannels) {
				diffQueryChannels.add("'" + channel + "'");
			}

			// validating the UI Elements for selected preference
			gp.openGridPreference(driver, testCaseName);
			Assert.assertEquals(gp.isMusicSelected(driver, testCaseName), true,
			        " Validating if Music Channel preference is checked after adding in user preference");

			/*
			 * TODO : To validate if channels that added after setting Music as grid
			 * preference as of same type in DB as well.
			 * 
			 * String MusicChannelQuery = dbq.MusicChannels;
			 * 
			 * MusicChannelQuery = MusicChannelQuery.replace("<POSTALCODE>", zipcode);
			 * 
			 * String[] Operator = Headend.split("-", 2); MusicChannelQuery =
			 * MusicChannelQuery.replace("<OPERATOR>", Operator[0].trim());
			 * 
			 * if (Operator[1].trim().equalsIgnoreCase("satellite") ||
			 * Operator[1].trim().equalsIgnoreCase("Digital")) { MusicChannelQuery =
			 * MusicChannelQuery.replace("<DEVICETYPE>", "X"); } else if
			 * (Operator[1].trim().equalsIgnoreCase("cable")) { MusicChannelQuery =
			 * MusicChannelQuery.replace("<DEVICETYPE>", "-"); } else if
			 * (Operator[1].trim().equalsIgnoreCase("Rebuild")) { MusicChannelQuery =
			 * MusicChannelQuery.replace("<DEVICETYPE>", "R"); }
			 * 
			 * 
			 * MusicChannelQuery = MusicChannelQuery.replace("<CALLSIGN>",
			 * diffQueryChannels.toString());
			 * 
			 * MusicChannelQuery.replace("[",""); MusicChannelQuery.replace("]","");
			 * qrs.setConnectivity(); localRS = qrs.getMSSQLData(MusicChannelQuery);
			 * localRS.next(); logger.info("Call Sign is :: " +
			 * localRS.getString("callsign") +
			 * " Tier of same is :: "+localRS.getString("tier"));
			 */

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void addPPVChannelGirdPreference(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();
		GridPreferences					gp				= new GridPreferences();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);
			clp.HeadendGridLoader(driver, testCaseName, gid);

			List<String> DefaultChannels = ip.getChannels(driver, testCaseName);

			gp.openGridPreference(driver, testCaseName);

			gp.RemoveGridPrefernces(driver, testCaseName);

			gp.includePPVChannels(driver, testCaseName);

			gp.SaveGridPrefernce(driver, testCaseName);

			// Thread.sleep(5000);
			List<String>	DefaultMusicChannels	= ip.getChannels(driver, testCaseName);

			// Below list are used to get the difference of channels and similar (in case
			// required)
			List<String>	sameChannels			= DefaultChannels;
			List<String>	diffChannels			= DefaultMusicChannels;

			diffChannels.removeAll(sameChannels);

			logger.info("Country is :: " + gid.getCountryName() + " Zipcode is :: " + gid.getCountryName() + " provider Tab is :: "
			        + gid.getProviderTab() + " Headed is :: " + gid.getSelectedProvider() + " PPV Channels are :: " + diffChannels);

			// validating the UI Elements for selected preference
			gp.openGridPreference(driver, testCaseName);

			Assert.assertEquals(gp.isPPVSelected(driver, testCaseName), true,
			        " Validating if PPV remain selected when user set PPV as grid preference");

			/*
			 * TODO : Validate if channels that added after setting PPV as grid preference
			 * as of same type in DB as well.
			 */

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void addHDChannelGirdPreference(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();
		GridPreferences					gp				= new GridPreferences();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);
			clp.HeadendGridLoader(driver, testCaseName, gid);

			List<String> DefaultChannels = ip.getChannels(driver, testCaseName);

			gp.openGridPreference(driver, testCaseName);

			gp.RemoveGridPrefernces(driver, testCaseName);

			gp.includeHDChannels(driver, testCaseName);

			gp.SaveGridPrefernce(driver, testCaseName);

			// Thread.sleep(5000);
			List<String>	DefaultMusicChannels	= ip.getChannels(driver, testCaseName);

			// Below list are used to get the difference of channels and similar (in case
			// required)
			List<String>	sameChannels			= DefaultChannels;
			List<String>	diffChannels			= DefaultMusicChannels;

			sameChannels.retainAll(diffChannels);

			logger.info("Country is :: " + gid.getCountryName() + " Zipcode is :: " + gid.getZipCode() + " provider Tab is :: "
			        + gid.getProviderTab() + " Headed is :: " + gid.getSelectedProvider() + " HD Channels are :: " + diffChannels);

			// validating the UI Elements for selected reference
			gp.openGridPreference(driver, testCaseName);

			Assert.assertEquals(gp.isHDSelected(driver, testCaseName), true,
			        "Validating if HD channel preference is selected after setting HD Channels for user preference");

			/*
			 * TODO : Validate if channels that are Filtered after setting HD as grid
			 * preference as of same type in DB as well.
			 */

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void addMusicPPVChannelGirdPreference(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();
		GridPreferences					gp				= new GridPreferences();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);
			clp.HeadendGridLoader(driver, testCaseName, gid);

			List<String> DefaultChannels = ip.getChannels(driver, testCaseName);

			gp.openGridPreference(driver, testCaseName);

			gp.RemoveGridPrefernces(driver, testCaseName);

			gp.includeMusicChannels(driver, testCaseName);

			gp.includePPVChannels(driver, testCaseName);

			gp.SaveGridPrefernce(driver, testCaseName);

			// Thread.sleep(5000);
			List<String>	DefaultMusicChannels	= ip.getChannels(driver, testCaseName);

			// Below list are used to get the difference of channels and similar (in case
			// required)
			List<String>	sameChannels			= DefaultChannels;
			List<String>	diffChannels			= DefaultMusicChannels;

			diffChannels.removeAll(sameChannels);

			logger.info("Country is :: " + gid.getCountryName() + " Zipcode is :: " + gid.getZipCode() + " provider Tab is :: "
			        + gid.getProviderTab() + " Headed is :: " + gid.getSelectedProvider() + " PPV Channels are :: " + diffChannels);

			// validating the UI Elements for selected preference
			gp.openGridPreference(driver, testCaseName);

			Assert.assertEquals(gp.isMusicSelected(driver, testCaseName), true);
			Assert.assertEquals(gp.isPPVSelected(driver, testCaseName), true);

			/*
			 * TODO : Validate if channels that added after setting Music & PPV as grid
			 * preference as of same type in DB as well.
			 */

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void addMusicHDChannelGirdPreference(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();
		GridPreferences					gp				= new GridPreferences();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);
			clp.HeadendGridLoader(driver, testCaseName, gid);

			List<String> DefaultChannels = ip.getChannels(driver, testCaseName);

			gp.openGridPreference(driver, testCaseName);

			gp.RemoveGridPrefernces(driver, testCaseName);

			gp.includeMusicChannels(driver, testCaseName);

			gp.includeHDChannels(driver, testCaseName);

			gp.SaveGridPrefernce(driver, testCaseName);

			// Thread.sleep(5000);
			List<String>	DefaultMusicChannels	= ip.getChannels(driver, testCaseName);

			// Below list are used to get the difference of channels and similar (in case
			// required)

			List<String>	sameChannels			= DefaultChannels;
			List<String>	diffChannels			= DefaultMusicChannels;

			sameChannels.retainAll(diffChannels);

			logger.info("Country is :: " + gid.getCountryName() + " Zipcode is :: " + gid.getZipCode() + " provider Tab is :: "
			        + gid.getProviderTab() + " Headed is :: " + gid.getSelectedProvider() + " HD Channels are :: " + diffChannels);

			// validating the UI Elements for selected preference
			gp.openGridPreference(driver, testCaseName);

			Assert.assertEquals(gp.isMusicSelected(driver, testCaseName), true);
			Assert.assertEquals(gp.isHDSelected(driver, testCaseName), true);

			/*
			 * TODO : Validate if channels that are Filtered after setting Music & HD as
			 * grid preference as of same type in DB as well.
			 */

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void addPPVHDChannelGirdPreference(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();
		GridPreferences					gp				= new GridPreferences();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);
			clp.HeadendGridLoader(driver, testCaseName, gid);

			List<String> DefaultChannels = ip.getChannels(driver, testCaseName);

			gp.openGridPreference(driver, testCaseName);

			gp.RemoveGridPrefernces(driver, testCaseName);

			gp.includePPVChannels(driver, testCaseName);

			gp.includeHDChannels(driver, testCaseName);

			gp.SaveGridPrefernce(driver, testCaseName);

			// Thread.sleep(5000);
			List<String>	DefaultMusicChannels	= ip.getChannels(driver, testCaseName);

			// Below list are used to get the difference of channels and similar (in case
			// required)
			List<String>	sameChannels			= DefaultChannels;
			List<String>	diffChannels			= DefaultMusicChannels;

			sameChannels.retainAll(diffChannels);

			logger.info("Country is :: " + gid.getCountryName() + " Zipcode is :: " + gid.getZipCode() + " provider Tab is :: "
			        + gid.getProviderTab() + " Headed is :: " + gid.getSelectedProvider() + " HD Channels are :: " + diffChannels);

			// validating the UI Elements for selected preference
			gp.openGridPreference(driver, testCaseName);

			Assert.assertEquals(gp.isPPVSelected(driver, testCaseName), true);

			Assert.assertEquals(gp.isHDSelected(driver, testCaseName), true);

			/*
			 * TODO : Validate if channels that are Filtered after setting PPV & HD as grid
			 * preference as of same type in DB as well.
			 */

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProviders", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void addMusicPPVHDChannelGirdPreference(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();
		GridPreferences					gp				= new GridPreferences();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName, gid);
			clp.HeadendGridLoader(driver, testCaseName, gid);

			List<String> DefaultChannels = ip.getChannels(driver, testCaseName);

			gp.openGridPreference(driver, testCaseName);

			gp.RemoveGridPrefernces(driver, testCaseName);

			gp.includeMusicChannels(driver, testCaseName);

			gp.includePPVChannels(driver, testCaseName);

			gp.includeHDChannels(driver, testCaseName);

			gp.SaveGridPrefernce(driver, testCaseName);

			// Thread.sleep(5000);
			List<String>	DefaultMusicChannels	= ip.getChannels(driver, testCaseName);

			// Below list are used to get the difference of channels and similar (in case
			// required)
			List<String>	sameChannels			= DefaultChannels;
			List<String>	diffChannels			= DefaultMusicChannels;

			sameChannels.retainAll(diffChannels);

			logger.info("Country is :: " + gid.getCountryName() + " Zipcode is :: " + gid.getZipCode() + " provider Tab is :: "
			        + gid.getProviderTab() + " Headed is :: " + gid.getSelectedProvider() + " HD Channels are :: " + diffChannels);

			// validating the UI Elements for selected preference
			gp.openGridPreference(driver, testCaseName);

			Assert.assertEquals(gp.isMusicSelected(driver, testCaseName), true);
			Assert.assertEquals(gp.isPPVSelected(driver, testCaseName), true);
			Assert.assertEquals(gp.isHDSelected(driver, testCaseName), true);

			/*
			 * TODO : Validate if channels that are Filtered after setting Music, PPV & HD
			 * as grid preference as of same type in DB as well.
			 */

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}

	}
}
