package com.gracenote.pages;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.Locators.IndexPageLocators;
import com.gracenote.Locators.MyShowsLocators;
import com.gracenote.Utilities.Screenshot;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.constants.IConstants;

/**
 * @author kunal.ashar
 *
 */
public class MyShows {

	Logger				logger	= Logger.getLogger(MyShows.class);
	WebElementActions	wea		= WebElementActions.getWebElementActionsObject();
	GenereFilters		gf		= new GenereFilters();

	public void clickOnMyShowsTab(WebDriver driver, String testCaseName) {
		try {
			new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Text_Menu_My_Shows));
			wea.click(driver.findElement(IndexPageLocators.Text_Menu_My_Shows));

			gridLoadingTimeWait(driver, testCaseName);

			Screenshot.captureScreenshot(driver, testCaseName, "After clicking on My Shows Tab");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public String getLabelNextOnTvText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(MyShowsLocators.Text_Next_on_TV).getText();
			return s;

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public void gridLoadingTimeWait(WebDriver driver, String testCaseName) {
		try {

			Thread.sleep(8000);

			Screenshot.captureScreenshot(driver, testCaseName, "MyShows Grid Loading Completed");

			logger.info("MyShows Grid Loading Completed");

		} catch (Exception e) {

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			logger.error("Loader Object went off from DOM");
		}

	}

	public String getButtonAiringFirstText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(MyShowsLocators.Button_Airing_First).getText();
			return s;

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getButtonAlphabeticalText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(MyShowsLocators.Button_Alphabetical).getText();
			return s;

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getLabelNoShowHaveBeenFavouritedText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(MyShowsLocators.Text_No_Favourite_Show).getText();
			return s;

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getButtonInMyShowsText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(MyShowsLocators.Button_Add_To_My_Shows).getText();
			return s;

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getButtonSeeAllUpcomingEpisodesText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(MyShowsLocators.Button_See_All_Upcoming_Episodes).getText();
			return s;

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getLabelOnFromShowCardDetails(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(MyShowsLocators.Label_On_Show_Card_Details).getText();
			return s;

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public List<String> getMyShowsPageText(WebDriver driver, String testCaseName) {

		logger.info("Reading texts from My Shows Page");

		List<String> myShowsPageText = new LinkedList<String>();

		// to navigate to My Shows Tab
		clickOnMyShowsTab(driver, testCaseName);

		myShowsPageText.add(getLabelNextOnTvText(driver, testCaseName));
		myShowsPageText.add(getButtonAiringFirstText(driver, testCaseName));
		myShowsPageText.add(getButtonAlphabeticalText(driver, testCaseName));
		myShowsPageText.add(getLabelNoShowHaveBeenFavouritedText(driver, testCaseName));

		// mark one show as favorite
		try {
			wea.click(driver.findElement(IndexPageLocators.Text_Menu_Tv_Listing));
			gridLoadingTimeWait(driver, testCaseName);

			// filter programs to avoid paid programs
			gf.showMoviesChannels(driver, testCaseName, null);

			// click on first program
			wea.click(driver.findElement(IndexPageLocators.All_Show_Cards));

			myShowsPageText.add(getLabelOnFromShowCardDetails(driver, testCaseName));

			// add program to favorite
			wea.scrollPageDownByOffset(driver);
			wea.click(driver.findElement(MyShowsLocators.Button_Add_To_My_Shows));

			// navigate back to My Shows Tab
			wea.click(driver.findElement(IndexPageLocators.Text_Menu_My_Shows));

			// wait till grid is loaded
			gridLoadingTimeWait(driver, testCaseName);

		} catch (Exception e) {
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}

		myShowsPageText.add(getButtonInMyShowsText(driver, testCaseName));
		myShowsPageText.add(getButtonSeeAllUpcomingEpisodesText(driver, testCaseName));

		logger.info("My Shows Page text reading Completed.");
		return myShowsPageText;
	}

}
