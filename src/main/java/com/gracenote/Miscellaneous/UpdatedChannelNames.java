package com.gracenote.Miscellaneous;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.pages.ChannelListOnPincode;
import com.gracenote.pages.IndexPage;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedChannelNames {

	Logger logger = Logger.getLogger(UpdatedChannelNames.class);

	public List<String> gapzapChannelGirdNames(String country, String zipcode, String providerTab, String Headend) {
		WebDriver						driver			= null;
		WebDriverSetup					ds				= new WebDriverSetup();
		GeneralApplicaitonProperties	gap				= new GeneralApplicaitonProperties();
		IndexPage						ip				= new IndexPage();
		ChannelListOnPincode			clp				= new ChannelListOnPincode();

		List<String>					AllChannels		= null;
		String							testCaseName	= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			clp.OpenPopUpforZipCode(driver, testCaseName);

			AllChannels = ip.getChannels(driver, testCaseName);

			logger.info("URL is :: " + driver.getCurrentUrl() + "Country is :: " + country + " Zipcode is :: " + zipcode
			        + " provider Tab is :: " + providerTab + " Headed is :: " + Headend + " All Channels are :: " + AllChannels);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
		return AllChannels;
	}

	public List<String> Zap2ITChannelGirdName(String country, String zipcode, String providerTab, String Headend) {

		WebDriver						driver				= null;
		WebDriverSetup					ds					= new WebDriverSetup();
		GeneralApplicaitonProperties	gap					= new GeneralApplicaitonProperties();

		List<String>					Zap2ITSiteChannels	= null;
		String							testCaseName		= ConfigureTestCaseName.currentTestCaseName();

		try {

			driver = ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURL());

			driver.get("http://tvlistings.zap2it.com/tvlistings/ZCGrid.do");

			driver.findElement(By.linkText("Change My Location")).click();

			driver.findElement(By.name("zipcode")).clear();
			driver.findElement(By.name("zipcode")).sendKeys(zipcode);
			driver.findElement(By.name("Go")).click();

			if (Headend.toLowerCase().contains("dish colorado springs")) {
				driver.findElement(By.xpath("//a[contains(text(),'DISH Colorado Springs  (Colorado Springs)')]")).click();

			} else if (Headend.toLowerCase().contains("shaw direct")) {
				driver.findElement(By.xpath("//a[contains(text(),'Shaw Direct (Advanced)  (Canada (West))')]")).click();

			} else if (Headend.toLowerCase().contains("comcast")) {
				driver.findElement(By.linkText("Comcast - Digital (Houston)")).click();

			} else if (Headend.toLowerCase().contains("rcn")) {
				driver.findElement(By.linkText("RCN Cable - Cable")).click();
			}

			else if (Headend.toLowerCase().contains("sony chicago")) {
				driver.findElement(By.linkText("Sony Chicago - Cable")).click();
			} else if (Headend.toLowerCase().contains("dish chicago")) {
				driver.findElement(By.linkText("DISH Chicago")).click();
			} else if (Headend.toLowerCase().contains("sony los")) {
				driver.findElement(By.linkText("Sony Los Angeles - Cable")).click();
			} else if (Headend.toLowerCase().contains("relaytv")) {
				driver.findElement(By.linkText("RELAYTV")).click();
			}

			/*
			 * TestSiteDriver.findElement(By.linkText("Set Preferences")).click(); if
			 * (!(TestSiteDriver.findElement(By.id("showMusic")).isSelected()))
			 * TestSiteDriver.findElement(By.id("showMusic")).click();
			 * 
			 * if (!(TestSiteDriver.findElement(By.id("showRadio")).isSelected()))
			 * TestSiteDriver.findElement(By.id("showRadio")).click();
			 * 
			 * if (!(TestSiteDriver.findElement(By.id("showPPV")).isSelected()))
			 * TestSiteDriver.findElement(By.id("showPPV")).click();
			 * 
			 * if (!(TestSiteDriver.findElement(By.id("showOnDemand")).isSelected()))
			 * TestSiteDriver.findElement(By.id("showOnDemand")).click();
			 * TestSiteDriver.findElement(By.
			 * cssSelector("div.zc-preferences-submit-buttons > input[type=\"submit\"]")).
			 * click();
			 */
			List<WebElement> Zap2ITSite = driver.findElements(By.xpath(".//table[contains(@class,'zc-row')]/tbody/tr/td[1]/span[2]/a"));

			Zap2ITSiteChannels = new ArrayList<String>();

			for (WebElement elem : Zap2ITSite) {
				Zap2ITSiteChannels.add(elem.getText());
			}

			logger.info("URL is :: " + driver.getCurrentUrl() + " Zipcode is :: " + zipcode + " Headed is :: " + Headend
			        + " All Channels are :: " + Zap2ITSiteChannels);
		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}

		return Zap2ITSiteChannels;
	}

	@Test(dataProvider = "TestDataProvidersArray", dataProviderClass = com.gracenote.resources.ExcelReader.class)
	public void channelComparer(String country, String zipcode, String providerTab, String Headend) {
		List<String>	Gapzap	= gapzapChannelGirdNames(country, zipcode, providerTab, Headend);
		List<String>	zap2IT	= Zap2ITChannelGirdName(country, zipcode, providerTab, Headend);

		logger.error("List of Gapzap channels : " + Gapzap);
		logger.error("List of zap2IT channels : " + zap2IT);
		List<String>	sameList	= new ArrayList<String>(zap2IT);
		List<String>	diffList	= new ArrayList<String>(Gapzap);

		sameList.retainAll(diffList);
		diffList.removeAll(sameList);

		logger.error("List of similar channels on both sites : " + sameList);
		logger.error("List of Different channels on both sites : " + diffList);

		Assert.assertEquals(Gapzap.size(), zap2IT.size(),
		        "Failed while comparing the channel count  list of gapzap(actual) with zap2it(expected) Country : " + country
		                + " : zipcode : " + zipcode + " providerTab : " + providerTab + " Headend: " + Headend);

		if (diffList.size() == 0) {
			logger.debug("provider List same in both list are " + sameList);

		} else {
			logger.error("provider List difference in both list are " + diffList);
			Assert.fail("Provider List in old site and new site is not equal " + diffList);
		}
	}

}
