package com.gracenote.Miscellaneous;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedImageLookup {
	private static Logger logger = Logger.getLogger(UpdatedImageLookup.class);

	@Test(dataProvider = "testDataProvider", dataProviderClass = com.gracenote.resources.ExcelReader.class, groups = "misc")
	public void ClassicImageLookup(GlobalInputData gid) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();

		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			logger.info("Running testcase for Data:" + gid.toString());

			String DestinationURL = "http://onthedemo.com/program/" + gid.getTMSID();

			driver = ds.getDriver(testCaseName, gap.getBrowser(), DestinationURL);

			String				recommendationListXpath	= ".//*[@id='recommendations']/a/img";

			List<WebElement>	recommendationList		= driver.findElements(By.xpath(recommendationListXpath));

			String				recommendationLink		= "";

			String				format					= "";

			String				formatContainText		= "h3_aa.jpg";

			for (WebElement recommendation : recommendationList) {

				recommendationLink = recommendation.getAttribute("src");

				format = recommendationLink.split("/")[recommendationLink.split("/").length - 1];
				logger.info("Recommendation Link is :: " + recommendationLink);
				logger.info("Format is :: " + format);
				if (format.contains(formatContainText))

				{
					logger.info("PASS");
				} else {

					logger.error(
					        "FAIL: It does not contain the expected text. Value is  " + format + " TMS id of same is :: " + gid.getTMSID());

					break;
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
