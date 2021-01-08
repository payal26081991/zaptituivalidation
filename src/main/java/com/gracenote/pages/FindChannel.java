package com.gracenote.pages;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.Locators.FindChannelLocators;
import com.gracenote.Utilities.Screenshot;
import com.gracenote.constants.IConstants;

public class FindChannel {

	private static Logger	logger	= Logger.getLogger(FindChannel.class);

	WebElementActions		wea		= WebElementActions.getWebElementActionsObject();

	public String getFindChannelButtonText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(FindChannelLocators.Text_Button_Find_Channels).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getFindChannelLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(FindChannelLocators.Label_Find_Channels).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getFindChannelPlaceholderText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(FindChannelLocators.Text_Find_Channels_Placeholder_Text).getAttribute("placeholder");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getAddToStarredChannelButtonText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(FindChannelLocators.Text_Button_Add_To_Starred_Channel).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getGoToMyStarredChannelButtonText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(FindChannelLocators.Text_Button_Go_To_My_Starred_Channel).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public void closeFindChannelPopup(WebDriver driver, String testCaseName) {
		try {
			wea.click(driver.findElement(FindChannelLocators.Button_Close_Find_Channel));
			logger.info("After Closing Find Channel Popup");
			Screenshot.captureScreenshot(driver, testCaseName, "Find Channel Popup Closed");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	/**
	 * @description to fetch all text from Find Channel Popup
	 * @param driver
	 * @param testCaseName
	 * @return
	 */
	public List<String> getFindChannelText(WebDriver driver, String testCaseName) {

		logger.info("Reading texts from Find Channel PopUp");

		List<String> findChannelPopupText = new LinkedList<String>();

		findChannelPopupText.add(getFindChannelButtonText(driver, testCaseName));

		openFindChannelPopup(driver, testCaseName);

		findChannelPopupText.add(getFindChannelLabelText(driver, testCaseName));
		findChannelPopupText.add(getFindChannelPlaceholderText(driver, testCaseName));
		findChannelPopupText.add(getAddToStarredChannelButtonText(driver, testCaseName));
		findChannelPopupText.add(getGoToMyStarredChannelButtonText(driver, testCaseName));

		logger.info("Find Channel Popup text reading Completed.");

		closeFindChannelPopup(driver, testCaseName);

		return findChannelPopupText;
	}

	public void openFindChannelPopup(WebDriver driver, String testCaseName) {
		try {
			wea.click(driver.findElement(FindChannelLocators.Button_Find_Channels));
			logger.info("After Opening Find Channel Popup");
			Screenshot.captureScreenshot(driver, testCaseName, "Find Channel Popup Opened");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

}
