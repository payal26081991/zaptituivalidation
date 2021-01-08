package com.gracenote.TestCases;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Listeners.TestNgListeners;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.constants.IConstants;
import com.gracenote.pages.AccountSetting;
import com.gracenote.pages.ChannelListOnPincode;
import com.gracenote.pages.FindChannel;
import com.gracenote.pages.GenereFilters;
import com.gracenote.pages.GridPreferences;
import com.gracenote.pages.IndexPage;
import com.gracenote.pages.ProgramShowCard;
import com.gracenote.pages.SignupLogin;
import com.gracenote.resources.CompareMultilingualData;
import com.gracenote.resources.ExcelReader;
import com.gracenote.resources.ExcelWriter;
import com.gracenote.resources.QueryResult;
import com.gracenote.resources.WebDriverSetup;

public class MultilingualCases {

	Logger logger = Logger.getLogger(MultilingualCases.class);

	@Test(dataProvider = "TestDataProvidersMultilingual", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void getIndexPageText(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();
		FindChannel						fc				= new FindChannel();
		GenereFilters					gf				= new GenereFilters();
		GridPreferences					gp				= new GridPreferences();
		List<String>					textList		= new LinkedList<String>();
		ExcelWriter						eWriter			= new ExcelWriter();
		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			//			logger.info("Redirecting to " + gap.getNewSiteURL() + gap.getGapzapAffiliateURL() + gap.getLanguageEncodingURL() + gid.getLangCode());
			//
			//			driver = ds.getDriver(testCaseName, gap.getBrowser(),gap.getNewSiteURL() + gap.getGapzapAffiliateURL() + gap.getLanguageEncodingURL() + gid.getLangCode());

			logger.info("Redirecting to " + gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getMultiLingualAffiliateTag());

			driver = ds.getDriver(testCaseName, gap.getBrowser(),gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getMultiLingualAffiliateTag());

			clp.gridLoadingTimeWait(driver, testCaseName);

			/** Get Header Tabs and Search Text **/

			textList.addAll(ip.getIndexPageText(driver, testCaseName));

			/** Get Filters Text **/

			textList.addAll(gf.getGenereFiltersText(driver, testCaseName));

			/** Get Find Channel Text **/

			textList.addAll(fc.getFindChannelText(driver, testCaseName));

			/** Get Grid Preference Text **/

			textList.addAll(gp.getGridPreferenceText(driver, testCaseName));

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
			eWriter.writeMultilingualExcel(gid.getLangCode(), gid.getLangID(), textList, testCaseName);

			validateMultilingualTextFromDB(testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProvidersMultilingual", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void getSignUpText(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		SignupLogin						sl				= new SignupLogin();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		List<String>					textList		= new LinkedList<String>();
		ExcelWriter						eWriter			= new ExcelWriter();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			//			logger.info("Redirecting to " + gap.getNewSiteURL() + gap.getGapzapAffiliateURL() + gap.getLanguageEncodingURL() + gid.getLangCode());
			//
			//			driver = ds.getDriver(testCaseName, gap.getBrowser(),
			//			        gap.getNewSiteURL() + gap.getGapzapAffiliateURL() + gap.getLanguageEncodingURL() + gid.getLangCode());

			logger.info("Redirecting to " + gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getMultiLingualAffiliateTag());

			driver = ds.getDriver(testCaseName, gap.getBrowser(),gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getMultiLingualAffiliateTag());

			clp.gridLoadingTimeWait(driver, testCaseName);

			textList.addAll(sl.getSignUpText(driver, testCaseName));

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
			eWriter.writeMultilingualExcel(gid.getLangCode(), gid.getLangID(), textList, testCaseName);
			validateMultilingualTextFromDB(testCaseName);
		}
	} 

	@Test(dataProvider = "TestDataProvidersMultilingual", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void getProgramShowCardText(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();
		IndexPage						ip				= new IndexPage();
		ProgramShowCard					psc				= new ProgramShowCard();		

		List<String>					textList		= new LinkedList<String>();
		ExcelWriter						eWriter			= new ExcelWriter();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			//			logger.info("Redirecting to " + gap.getNewSiteURL() + gap.getGapzapAffiliateURL() + gap.getLanguageEncodingURL() + gid.getLangCode());
			//
			//			driver = ds.getDriver(testCaseName, gap.getBrowser(),
			//			        gap.getNewSiteURL() + gap.getGapzapAffiliateURL() + gap.getLanguageEncodingURL() + gid.getLangCode());

			logger.info("Redirecting to " + gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getMultiLingualAffiliateTag());

			driver = ds.getDriver(testCaseName, gap.getBrowser(),gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getMultiLingualAffiliateTag());

			clp.gridLoadingTimeWait(driver, testCaseName);

			ip.searchProgram(driver, testCaseName, "The Tonight Show Starring Jimmy Fallon");

			ip.selectFirstProgramFromSearchList(driver, testCaseName);

			textList.addAll(psc.getProgramShowCardPageText(driver, testCaseName));

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
			eWriter.writeMultilingualExcel(gid.getLangCode(), gid.getLangID(), textList, testCaseName);
			validateMultilingualTextFromDB(testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProvidersMultilingual", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void getAccountSettingText(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		SignupLogin						sl				= new SignupLogin();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();
		AccountSetting					as				= new AccountSetting();
		GridPreferences					gp				= new GridPreferences();
		WebElementActions				wea				= WebElementActions.getWebElementActionsObject();

		List<String>					textList		= new LinkedList<String>();
		ExcelWriter						eWriter			= new ExcelWriter();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			//			logger.info("Redirecting to " + gap.getNewSiteURL() + gap.getGapzapAffiliateURL() + gap.getLanguageEncodingURL() + gid.getLangCode());
			//
			//			driver = ds.getDriver(testCaseName, gap.getBrowser(),
			//			        gap.getNewSiteURL() + gap.getGapzapAffiliateURL() + gap.getLanguageEncodingURL() + gid.getLangCode());


			logger.info("Redirecting to " + gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getMultiLingualAffiliateTag());

			driver = ds.getDriver(testCaseName, gap.getBrowser(),gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getMultiLingualAffiliateTag());

			clp.gridLoadingTimeWait(driver, testCaseName);

			gid.setUserEmail(RandomStringUtils.random(5, true, true) + "@gmail.com");
			gid.setPassword(RandomStringUtils.random(5, true, true));

			sl.userSignUp(driver, testCaseName, gid);

			// closing change provider popup
		//	gp.closeGridPreferencePopup(driver, testCaseName);

			wea.click(sl.getSignedUpUserName(driver, testCaseName));
			wea.click(sl.getUserActionAccountSettings(driver, testCaseName));

			Thread.sleep(2000);

			textList.addAll(as.getAccountSettingPageText(driver, testCaseName));

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
			eWriter.writeMultilingualExcel(gid.getLangCode(), gid.getLangID(), textList, testCaseName);
			validateMultilingualTextFromDB(testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProvidersMultilingual", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void getSingleProviderText(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		List<String>					textList		= new LinkedList<String>();
		ExcelWriter						eWriter			= new ExcelWriter();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			//			logger.info("Redirecting to " + gap.getNewSiteURL() + gap.getAffiliateURL() + "truro4ed" + gap.getLanguageEncodingURL()
			//			        + gid.getLangCode() + "&gt=sp");
			//
			//			driver = ds.getDriver(testCaseName, gap.getBrowser(),
			//			        gap.getNewSiteURL() + gap.getAffiliateURL() + "truro4ed" + gap.getLanguageEncodingURL() + gid.getLangCode() + "&gt=sp");
			//			
			//			logger.info("Redirecting to " + gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + "truro4ed" + gap.getLanguageEncodingURL() + gid.getLangCode() + "&gt=sp");
			//
			//			driver = ds.getDriver(testCaseName, gap.getBrowser(),gap.getNewSiteURLAffiliates()+ gap.getAffiliateURL() + "truro4ed" + gap.getLanguageEncodingURL() + gid.getLangCode() + "&gt=sp");

			logger.info("Redirecting to " + gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getSingleProviderAffiliateTag());

			driver = ds.getDriver(testCaseName, gap.getBrowser(),gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getSingleProviderAffiliateTag());

			clp.gridLoadingTimeWait(driver, testCaseName);

			textList.addAll(clp.getChangeLocationTextSingleProvider(driver, testCaseName));

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
			eWriter.writeMultilingualExcel(gid.getLangCode(), gid.getLangID(), textList, testCaseName);
			validateMultilingualTextFromDB(testCaseName);
		}
	}

	@Test(dataProvider = "TestDataProvidersMultilingual", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void getSingleStationText(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		List<String>					textList		= new LinkedList<String>();
		ExcelWriter						eWriter			= new ExcelWriter();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());
			
			logger.info("Redirecting to " + gap.getNewSiteURLAffiliates() + gap.getSingleStaionURL() + gid.getSingleStationAffiliateTag());

			driver = ds.getDriver(testCaseName, gap.getBrowser(),
					gap.getNewSiteURLAffiliates() + gap.getSingleStaionURL() + gid.getSingleStationAffiliateTag());

			ip.gridLoadingTimeWait(driver, testCaseName);

			textList.addAll(ip.getSingleStationPageText(driver, testCaseName));

		} catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
			eWriter.writeMultilingualExcel(gid.getLangCode(), gid.getLangID(), textList, testCaseName);
			validateMultilingualTextFromDB(testCaseName);
		}
	}

	public void validateMultilingualTextFromDB(String testCaseName) {

		if (ExcelReader.MultilingualData.size() == TestNgListeners.testCaseOccurance.get(testCaseName)) {

			ExcelReader				er						= new ExcelReader();
			ExcelWriter				ew						= new ExcelWriter();
			ResultSet				rs;
			QueryResult				qrs						= new QueryResult();
			CompareMultilingualData	cmd						= new CompareMultilingualData();

			// String testCaseName = ConfigureTestCaseName.currentTestCaseName();
			List<Integer>			langIds					= new LinkedList<Integer>();
			List<String>			excelEnglishLangValues	= new LinkedList<String>();

			try {

				// reading all values from excel file
				Map<String, LinkedList<String>> multilingualData = er.readGeneratedMultilingualFile(testCaseName);

				// reading dataKey for each language
				for (String key : multilingualData.keySet()) {

					if (key.split("_")[0].equalsIgnoreCase(IConstants.ENGLISH_LANGAUGE_CODE)) {
						// to fetch all values from English Language
						excelEnglishLangValues = multilingualData.get(key);

					} else {

						// fetching all languages id from ketSet
						int langId = Integer.parseInt(key.split("_")[1]);

						langIds.add(langId);
					}
				}

				// get db connectivity
				qrs.setConnectivity();

				// Iterating on all language id's present in excel
				for (Integer id : langIds) {

					// to store all values fetched from DB for selected language
					List<String> dbLangValues = new LinkedList<String>();

					// Iterating on all the text present for english language
					for (String value : excelEnglishLangValues) {
						if (value != null) {

							value = value.replace("'", "\\'");

							String query = "SELECT * FROM uicomponentlanguagetext ent JOIN uicomponentlanguagetranslation trans ON ent.id =trans.engid WHERE englishtext LIKE '"
									+ value + "' AND languageid=" + id;

							// fetch result set
							rs = qrs.getMSSQLData(query);

							// to move pointer to data row
							String result = null;
							if (rs.next() == false) {
								result = IConstants.NO_RESULT_FOUND;
							} else {
								result = rs.getString(IConstants.MULTILINGUAL_COLUMN_NAME_TRANSLATION);
							}

							dbLangValues.add(result);
						}
					}

					// writing values in Excel file
					ew.writeMultilingualExcel("DB", id + "", dbLangValues, testCaseName);
				}

				// to close DB connection
				qrs.CleanUpDBConnections();

			} catch (Exception e) {
				TestCaseExecutionStatus.markAsFail(testCaseName, e);
			} finally {
				try {
					// update UI comparison status
					cmd.startComparison(testCaseName);
				} catch (Exception e) {
					logger.error("Exception occured while comparing excel data.", e);
				}
			}
		}
	}

}
