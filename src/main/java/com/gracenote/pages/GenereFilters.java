package com.gracenote.pages;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Locators.GenereFiltersLocators;
import com.gracenote.Utilities.Screenshot;
import com.gracenote.constants.IConstants;

public class GenereFilters {

	private static Logger	logger	= Logger.getLogger(GenereFilters.class);

	WebElementActions		wea		= WebElementActions.getWebElementActionsObject();	

	private WebElement getAllChannelsFilterButton(WebDriver driver, String testCaseName) {
		WebElement AllChannels = null;

		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GenereFiltersLocators.Main_Button_All_Channels_Filter));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(GenereFiltersLocators.Main_Button_All_Channels_Filter));
			AllChannels = driver.findElement(GenereFiltersLocators.Main_Button_All_Channels_Filter);
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return AllChannels;
	}

	private WebElement getAllChannelsFilter(WebDriver driver, String testCaseName) {
		WebElement AllChannels = null;

		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GenereFiltersLocators.Filter_Button_All_Channels));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(GenereFiltersLocators.Filter_Button_All_Channels));
			AllChannels = driver.findElement(GenereFiltersLocators.Filter_Button_All_Channels);
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return AllChannels;
	}

	private WebElement getStarredChannelsFilter(WebDriver driver, String testCaseName) {
		WebElement StarredChannels = null;

		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GenereFiltersLocators.Filter_Button_Starred_Channels));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(GenereFiltersLocators.Filter_Button_Starred_Channels));

			StarredChannels = driver.findElement(GenereFiltersLocators.Filter_Button_Starred_Channels);
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return StarredChannels;
	}

	private WebElement getMovieGenereFilter(WebDriver driver, String testCaseName) {
		WebElement MovieFilter = null;

		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GenereFiltersLocators.Filter_Button_Movies_Channels));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(GenereFiltersLocators.Filter_Button_Movies_Channels));

			MovieFilter = driver.findElement(GenereFiltersLocators.Filter_Button_Movies_Channels);
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return MovieFilter;
	}

	private WebElement getSportsGenereFilter(WebDriver driver, String testCaseName) {
		WebElement SportsFilter = null;

		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GenereFiltersLocators.Filter_Button_Sports_Channels));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(GenereFiltersLocators.Filter_Button_Sports_Channels));

			SportsFilter = driver.findElement(GenereFiltersLocators.Filter_Button_Sports_Channels);
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return SportsFilter;
	}

	private WebElement getFamilyGenereFilter(WebDriver driver, String testCaseName) {
		WebElement FamilyFilter = null;

		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GenereFiltersLocators.Filter_Button_Family_Channels));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(GenereFiltersLocators.Filter_Button_Family_Channels));

			FamilyFilter = driver.findElement(GenereFiltersLocators.Filter_Button_Family_Channels);

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return FamilyFilter;
	}

	private WebElement getNewsGenereFilter(WebDriver driver, String testCaseName) {
		WebElement NewsFilter = null;

		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GenereFiltersLocators.Filter_Button_News_Channels));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(GenereFiltersLocators.Filter_Button_News_Channels));

			NewsFilter = driver.findElement(GenereFiltersLocators.Filter_Button_News_Channels);
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return NewsFilter;
	}

	private WebElement getTalkGenereFilter(WebDriver driver, String testCaseName) {
		WebElement TalkFilter = null;

		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GenereFiltersLocators.Filter_Button_Talk_Channels));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(GenereFiltersLocators.Filter_Button_Talk_Channels));

			TalkFilter = driver.findElement(GenereFiltersLocators.Filter_Button_Talk_Channels);
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return TalkFilter;
	}

	public void clickAllChannelsDropdownButton(WebDriver driver, String testCaseName) {
		try {
			wea.clickJs(driver,getAllChannelsFilterButton(driver, testCaseName));
			Screenshot.captureScreenshot(driver, testCaseName, "After clicking on All Filters Dropdown Button");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public void showAllChannels(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {
			clickAllChannelsDropdownButton(driver, testCaseName);

			wea.click(getAllChannelsFilter(driver, testCaseName));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public void showStarredChannels(WebDriver driver, String testCaseName) {
		try {
			clickAllChannelsDropdownButton(driver, testCaseName);

			wea.clickJs(driver,getStarredChannelsFilter(driver, testCaseName));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public void showMoviesChannels(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {Thread.sleep(4000);
			clickAllChannelsDropdownButton(driver, testCaseName);
			Thread.sleep(2000);
			wea.click(getMovieGenereFilter(driver, testCaseName));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public void showSportsChannels(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {
			clickAllChannelsDropdownButton(driver, testCaseName);

			wea.click(getSportsGenereFilter(driver, testCaseName));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public void showFamilyChannels(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {
			clickAllChannelsDropdownButton(driver, testCaseName);

			wea.click(getFamilyGenereFilter(driver, testCaseName));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public void showNewsChannels(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {
			clickAllChannelsDropdownButton(driver, testCaseName);

			wea.click(getNewsGenereFilter(driver, testCaseName));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public void showTalkChannels(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {
			clickAllChannelsDropdownButton(driver, testCaseName);

			wea.click(getTalkGenereFilter(driver, testCaseName));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	/**
	 * @description to fetch all filters text
	 * @param driver
	 * @param testCaseName
	 * @return
	 */

	public List<String> getGenereFiltersText(WebDriver driver, String testCaseName) {

		logger.info("Reading texts from Genere Filteres");

		List<String> genereFilterText = new LinkedList<String>();

		try {

			genereFilterText.add(getAllChannelsFilterButton(driver, testCaseName).getText());

			// to view list of other filters
			clickAllChannelsDropdownButton(driver, testCaseName);

			genereFilterText.add(getStarredChannelsFilter(driver, testCaseName).getText());
			genereFilterText.add(getMovieGenereFilter(driver, testCaseName).getText());
			genereFilterText.add(getSportsGenereFilter(driver, testCaseName).getText());
			genereFilterText.add(getFamilyGenereFilter(driver, testCaseName).getText());
			genereFilterText.add(getNewsGenereFilter(driver, testCaseName).getText());
			genereFilterText.add(getTalkGenereFilter(driver, testCaseName).getText());

			logger.info("Genere Filteres text reading Completed.");
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return genereFilterText;
	}

}
