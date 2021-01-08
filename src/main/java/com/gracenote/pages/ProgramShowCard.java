package com.gracenote.pages;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Locators.IndexPageLocators;
import com.gracenote.Locators.ProgramShowCardLocators;
import com.gracenote.Utilities.Screenshot;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.constants.IConstants;

public class ProgramShowCard {

	private static Logger	logger	= Logger.getLogger(GenereFilters.class);
	WebElementActions		wea		= WebElementActions.getWebElementActionsObject();
	GenereFilters			gf		= new GenereFilters();
	SignupLogin				sl		= new SignupLogin();

	private WebElement getProgramImage(WebDriver driver, String testCaseName) {

		WebElement ProgramImage = null;

		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Program_Image));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Program_Image));
			ProgramImage = driver.findElement(ProgramShowCardLocators.Program_Image);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Program_Image is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return ProgramImage;
	}

	private WebElement getProgramName(WebDriver driver, String testCaseName) {

		WebElement ProgramName = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Label_Program_Name));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Label_Program_Name));
			ProgramName = driver.findElement(ProgramShowCardLocators.Label_Program_Name);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Program_Name is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return ProgramName;
	}

	private List<JSONObject> getCast(WebDriver driver, String testCaseName) {
		List<JSONObject> CastArray = new LinkedList<JSONObject>();

		try {

			List<WebElement>	castList	= driver.findElements(ProgramShowCardLocators.Cast_List);

			int					i			= 1;
			for (WebElement cast : castList) {
				JSONObject IndividualCast = new JSONObject();

				IndividualCast.put("name",
				        driver.findElement(By.xpath("//section[@class='cast']//div[contains(@class,'cast-crew-list')]/div[" + i + "]/span"))
				                .getText());

				IndividualCast.put("role",
				        driver.findElement(
				                By.xpath("//section[@class='cast']//div[contains(@class,'cast-crew-list')]/div[" + i + "]/span/h1"))
				                .getText());

				IndividualCast.put("screenName",
				        driver.findElement(
				                By.xpath("//section[@class='cast']//div[contains(@class,'cast-crew-list')]/div[" + i + "]/span/h2"))
				                .getText());

				CastArray.add(IndividualCast);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return CastArray;
	}

	private List<JSONObject> getCrew(WebDriver driver, String testCaseName) {
		List<JSONObject> CrewArray = new LinkedList<JSONObject>();

		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Crew_List));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Crew_List));
			List<WebElement>	castList	= driver.findElements(ProgramShowCardLocators.Crew_List);

			int					i			= 1;
			for (WebElement cast : castList) {
				JSONObject IndividualCrew = new JSONObject();

				IndividualCrew.put("name",
				        driver.findElement(By.xpath("//section[@class='crew']//div[contains(@class,'cast-crew-list')]/div[" + i + "]/span"))
				                .getText());

				IndividualCrew.put("role",
				        driver.findElement(
				                By.xpath("//section[@class='crew']//div[contains(@class,'cast-crew-list')]/div[" + i + "]/span/h1"))
				                .getText());

				CrewArray.add(IndividualCrew);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return CrewArray;
	}

	public WebElement getCastCrewTab(WebDriver driver, String testCaseName) {
		WebElement CastCrewTab = null;

		try {

			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
			        By.xpath(".//*[@id='program-details']//div[@class='nav nav-tabs tab-content section_border']/li[4]/a")));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(
			        By.xpath(".//*[@id='program-details']//div[@class='nav nav-tabs tab-content section_border']/li[4]/a")));

			if (driver.getCurrentUrl().contains("programSeriesId=MV"))
				CastCrewTab = driver.findElement(
				        By.xpath(".//*[@id='program-details']//div[@class='nav nav-tabs tab-content section_border']/li[3]/a"));
			else
				CastCrewTab = driver.findElement(
				        By.xpath(".//*[@id='program-details']//div[@class='nav nav-tabs tab-content section_border']/li[4]/a"));
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return CastCrewTab;
	}

	public List<WebElement> getSeasons(WebDriver driver, String testCaseName) {
		List<WebElement> Seasons = null;

		try {

			// new
			// WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@id='jump-to-seaon']/../ul/li/a")));
			// new
			// WebDriverWait(driver,60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='jump-to-seaon']/../ul/li/a")));

			Seasons = driver.findElements(By.xpath(".//*[@id='jump-to-seaon']/../ul[@class='dropdown-menu']/li/a"));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return Seasons;

	}

	public void backToGrid(WebDriver driver, String testCaseName)
	{
		try
		{
			wea.click(getBackToGridButton(driver,testCaseName));
			
			new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(IndexPageLocators.All_Show_Cards));
			
			Screenshot.captureScreenshot(driver, testCaseName, "After clicking on back to grid ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("Button_Back_To_Grid is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}
	
	
	private WebElement getBackToGridButton(WebDriver driver, String testCaseName) {

		WebElement backToGrid = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Button_Back_To_Grid));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Button_Back_To_Grid));
			backToGrid = driver.findElement(ProgramShowCardLocators.Button_Back_To_Grid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Button_Back_To_Grid is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return backToGrid;
	}

	private WebElement getSearchBox(WebDriver driver, String testCaseName) {

		WebElement searchBox = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Textbox_Search));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Textbox_Search));
			searchBox = driver.findElement(ProgramShowCardLocators.Textbox_Search);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Textbox_Search is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return searchBox;
	}

	private WebElement getSignUpOrLoginButton(WebDriver driver, String testCaseName) {

		WebElement signUpButton = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Button_SignUp_Or_Login));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Button_SignUp_Or_Login));
			signUpButton = driver.findElement(ProgramShowCardLocators.Button_SignUp_Or_Login);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Button_SignUp_Or_Login is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return signUpButton;
	}

	private WebElement getAddToMyShowsButton(WebDriver driver, String testCaseName) {

		WebElement addToMyShowButton = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Button_Add_To_My_Shows));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Button_Add_To_My_Shows));
			addToMyShowButton = driver.findElement(ProgramShowCardLocators.Button_Add_To_My_Shows);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Button_Add_To_My_Shows is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return addToMyShowButton;
	}

	private WebElement getGenereLabel(WebDriver driver, String testCaseName) {

		WebElement genereLabel = null;

		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.Label_Genere_Type));
			genereLabel = driver.findElement(ProgramShowCardLocators.Label_Genere_Type);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Genere_Type is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return genereLabel;
	}

	private WebElement getOverviewTab(WebDriver driver, String testCaseName) {

		WebElement tabOverview = null;

		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Tab_Overview));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Tab_Overview));
			tabOverview = driver.findElement(ProgramShowCardLocators.Tab_Overview);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Tab_Overview is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return tabOverview;
	}

	private WebElement getUpcomingEpisodeTab(WebDriver driver, String testCaseName) {

		WebElement tabUpcomingEpisode = null;

		try {
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Tab_Upcoming_Episodes));
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Tab_Upcoming_Episodes));
			tabUpcomingEpisode = driver.findElement(ProgramShowCardLocators.Tab_Upcoming_Episodes);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Tab_Upcoming_Episodes is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return tabUpcomingEpisode;
	}

	private WebElement getUpcomingAiringsTab(WebDriver driver, String testCaseName) {

		WebElement tabUpcomingEpisode = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Tab_Upcoming_Airings));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Tab_Upcoming_Airings));
			tabUpcomingEpisode = driver.findElement(ProgramShowCardLocators.Tab_Upcoming_Airings);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Tab_Upcoming_Airings is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return tabUpcomingEpisode;
	}

	public WebElement getEpisodeGuideTab(WebDriver driver, String testCaseName) {

		WebElement tabEpisodeGuide = null;

		try {
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Tab_Episode_Guide));
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Tab_Episode_Guide));
			tabEpisodeGuide = driver.findElement(ProgramShowCardLocators.Tab_Episode_Guide);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Tab_Episode_Guide is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return tabEpisodeGuide;
	}

	private WebElement getCastAndCrewTab(WebDriver driver, String testCaseName) {

		WebElement tabCastAndCrew = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Tab_Cast_And_Crew));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Tab_Cast_And_Crew));
			tabCastAndCrew = driver.findElement(ProgramShowCardLocators.Tab_Cast_And_Crew);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Tab_Cast_And_Crew is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return tabCastAndCrew;
	}

	private WebElement getUpcomingEpisodeLabel(WebDriver driver, String testCaseName) {

		WebElement labelUpcomingEpisode = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Label_Upcoming_Episode_Airings));
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Label_Upcoming_Episode_Airings));
			labelUpcomingEpisode = driver.findElement(ProgramShowCardLocators.Label_Upcoming_Episode_Airings);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Upcoming_Episode_Airings is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return labelUpcomingEpisode;
	}

	private WebElement getAiringLabelOverviewTab(WebDriver driver, String testCaseName) {

		WebElement airingLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Label_Airing_Overview_Tab));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Label_Airing_Overview_Tab));
			airingLabel = driver.findElement(ProgramShowCardLocators.Label_Airing_Overview_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Airing_Overview_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return airingLabel;
	}

	private List<WebElement> getAiringLabelUpcomingEpisodeTab(WebDriver driver, String testCaseName) {

		List<WebElement> airingLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Label_Airing_Upcoming_Episodes));

			airingLabel = driver.findElements(ProgramShowCardLocators.Label_Airing_Upcoming_Episodes);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Airing_Upcoming_Episodes is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return airingLabel;
	}

	private List<WebElement> getAiringLabelEpisodeGuideTab(WebDriver driver, String testCaseName) {

		List<WebElement> airingLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Label_Airing_Episodes_Guide_Tab));
			airingLabel = driver.findElements(ProgramShowCardLocators.Label_Airing_Episodes_Guide_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Airing_Episodes_Guide_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return airingLabel;
	}

	private List<WebElement> getFindItOnLabelOverviewTab(WebDriver driver, String testCaseName) {

		List<WebElement> findItOnLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Label_Find_It_On_Overview_Tab));
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Label_Find_It_On_Overview_Tab));
			findItOnLabel = driver.findElements(ProgramShowCardLocators.Label_Find_It_On_Overview_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Find_It_On_Overview_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return findItOnLabel;
	}

	private List<WebElement> getFindItOnLabelUpcomingEpisodeTab(WebDriver driver, String testCaseName) {

		List<WebElement> findItOnLabel = null;

		try {
			new WebDriverWait(driver, 60).until(
			        ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Label_Find_It_On_Upcoming_Episode_Tab));
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Label_Find_It_On_Upcoming_Episode_Tab));
			findItOnLabel = driver.findElements(ProgramShowCardLocators.Label_Find_It_On_Upcoming_Episode_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Find_It_On_Upcoming_Episode_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return findItOnLabel;
	}

	private List<WebElement> getUpcomingAiringButton(WebDriver driver, String testCaseName) {

		List<WebElement> upcomingAiringButton = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Button_Upcoming_Airings));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Button_Upcoming_Airings));
			upcomingAiringButton = driver.findElements(ProgramShowCardLocators.Button_Upcoming_Airings);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Button_Upcoming_Airings is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return upcomingAiringButton;
	}

	private WebElement getSeeAllUpcomingEpisodeButton(WebDriver driver, String testCaseName) {

		WebElement buttonUpcomingEpisode = null;

		try {
			new WebDriverWait(driver, 60).until(
			        ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Button_See_All_Upcoming_Episode_Airings));
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Button_See_All_Upcoming_Episode_Airings));
			buttonUpcomingEpisode = driver.findElement(ProgramShowCardLocators.Button_See_All_Upcoming_Episode_Airings);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Button_See_All_Upcoming_Episode_Airings is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return buttonUpcomingEpisode;
	}

	private WebElement getWatchOnlineDropdown_OverviewTab(WebDriver driver, String testCaseName) {

		WebElement dropdownWatchOnline = null;

		try {
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.Dropdown_Watch_Online_Overview_Tab));
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Dropdown_Watch_Online_Overview_Tab));
			dropdownWatchOnline = driver.findElement(ProgramShowCardLocators.Dropdown_Watch_Online_Overview_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Dropdown_Watch_Online_Overview_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return dropdownWatchOnline;
	}

	private WebElement getWatchOnlineDropdown_UpcomingEpisodeTab(WebDriver driver, String testCaseName) {

		WebElement dropdownWatchOnline = null;

		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions
			        .visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Dropdown_Watch_Online_Upcoming_Episode_Tab));
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Dropdown_Watch_Online_Upcoming_Episode_Tab));
			dropdownWatchOnline = driver.findElement(ProgramShowCardLocators.Dropdown_Watch_Online_Upcoming_Episode_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Dropdown_Watch_Online_Upcoming_Episode_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return dropdownWatchOnline;
	}

	private List<WebElement> getWatchOnlineDropdown_EpisodeGuideTab(WebDriver driver, String testCaseName) {

		List<WebElement> dropdownWatchOnline = null;

		try {
			new WebDriverWait(driver, 20).until(
			        ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Dropdown_Watch_Online_Episode_Guide_Tab));
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Dropdown_Watch_Online_Episode_Guide_Tab));
			dropdownWatchOnline = driver.findElements(ProgramShowCardLocators.Dropdown_Watch_Online_Episode_Guide_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Dropdown_Watch_Online_Episode_Guide_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return dropdownWatchOnline;
	}

	private WebElement getCastAndCrewLabel(WebDriver driver, String testCaseName) {

		WebElement labelCastAndCrew = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Label_Cast_And_Crew_Overview_Tab));
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Label_Cast_And_Crew_Overview_Tab));
			labelCastAndCrew = driver.findElement(ProgramShowCardLocators.Label_Cast_And_Crew_Overview_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Cast_And_Crew_Overview_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return labelCastAndCrew;
	}

	private WebElement getSeeAllCastAndCrewButton(WebDriver driver, String testCaseName) {

		WebElement buttonCastAndCrew = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Button_See_All_Cast_And_Crew));
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Button_See_All_Cast_And_Crew));
			buttonCastAndCrew = driver.findElement(ProgramShowCardLocators.Button_See_All_Cast_And_Crew);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Button_See_All_Cast_And_Crew is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return buttonCastAndCrew;
	}

	private WebElement getCastLabel(WebDriver driver, String testCaseName) {

		WebElement castLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Header_Label_Cast));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Header_Label_Cast));
			castLabel = driver.findElement(ProgramShowCardLocators.Header_Label_Cast);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Header_Label_Cast is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return castLabel;
	}

	private WebElement getCrewLabel(WebDriver driver, String testCaseName) {

		WebElement crewLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Header_label_Crew));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Header_label_Crew));
			crewLabel = driver.findElement(ProgramShowCardLocators.Header_label_Crew);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Header_label_Crew is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return crewLabel;
	}

	private WebElement getSimilarShowLabel(WebDriver driver, String testCaseName) {

		WebElement similarShowLabel = null;

		try {
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Label_Similar_Shows));
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Label_Similar_Shows));
			similarShowLabel = driver.findElement(ProgramShowCardLocators.Label_Similar_Shows);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Similar_Shows is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return similarShowLabel;
	}

	private WebElement getSeasonsDropdown(WebDriver driver, String testCaseName) {

		WebElement seasonDropdown = null;

		try {
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Dropdwon_Seasons));
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Dropdwon_Seasons));
			seasonDropdown = driver.findElement(ProgramShowCardLocators.Dropdwon_Seasons);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Dropdwon_Seasons is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return seasonDropdown;
	}

	private WebElement getSeasonsLabelDropdownTitle(WebDriver driver, String testCaseName) {

		WebElement seasonLabel = null;

		try {
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Label_Season_Dropdown_Title));
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Label_Season_Dropdown_Title));
			seasonLabel = driver.findElement(ProgramShowCardLocators.Label_Season_Dropdown_Title);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Season_Dropdown_Title is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return seasonLabel;
	}

	private WebElement getSeasonsIndexLabelDropdownTitle(WebDriver driver, String testCaseName) {

		WebElement seasonIndexLabel = null;

		try {
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ProgramShowCardLocators.Label_Season_Index_Dropdown_Title));
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Label_Season_Index_Dropdown_Title));
			seasonIndexLabel = driver.findElement(ProgramShowCardLocators.Label_Season_Index_Dropdown_Title);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Season_Index_Dropdown_Title is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return seasonIndexLabel;
	}

	private List<WebElement> getAllSeasonsLabelFromDropdown(WebDriver driver, String testCaseName) {

		List<WebElement> allSeasonLabel = null;

		try {
			wea.click(getSeasonsDropdown(driver, testCaseName));

			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.Label_Seasons_From_Dropdown));
			new WebDriverWait(driver, 20)
			        .until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Label_Seasons_From_Dropdown));
			allSeasonLabel = driver.findElements(ProgramShowCardLocators.Label_Seasons_From_Dropdown);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Seasons_From_Dropdown is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return allSeasonLabel;
	}

	private List<WebElement> getCastCrewType_OvewviewTab(WebDriver driver, String testCaseName) {

		List<WebElement> type = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.List_Cast_Crew_Type_Overview_Tab));
			type = driver.findElements(ProgramShowCardLocators.List_Cast_Crew_Type_Overview_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("List_Cast_Crew_Type_Overview_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return type;
	}

	private List<WebElement> getCastCrewType_CastCrewTab(WebDriver driver, String testCaseName) {

		List<WebElement> type = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.List_Cast_Crew_Type_Cast_Crew_Tab));
			type = driver.findElements(ProgramShowCardLocators.List_Cast_Crew_Type_Cast_Crew_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("List_Cast_Crew_Type_Cast_Crew_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return type;
	}

	private List<WebElement> getEpisodeLabelOverviewTab(WebDriver driver, String testCaseName) {

		List<WebElement> episodeLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.Label_Episode_Overview_Tab));
			episodeLabel = driver.findElements(ProgramShowCardLocators.Label_Episode_Overview_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Episode_Overview_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return episodeLabel;
	}

	private List<WebElement> getSeasonLabelOverviewTab(WebDriver driver, String testCaseName) {

		List<WebElement> seasonLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.Label_Season_Overview_Tab));
			seasonLabel = driver.findElements(ProgramShowCardLocators.Label_Season_Overview_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Season_Overview_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return seasonLabel;
	}

	private List<WebElement> getProgramTimingsLabelOverviewTab(WebDriver driver, String testCaseName) {

		List<WebElement> seasonLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.Label_Program_Timimgs_Overview_Tab));
			seasonLabel = driver.findElements(ProgramShowCardLocators.Label_Program_Timimgs_Overview_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Program_Timimgs_Overview_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return seasonLabel;
	}

	private List<WebElement> getProgramTimingsLabelUpcomingEpisodeTab(WebDriver driver, String testCaseName) {

		List<WebElement> seasonLabel = null;

		try {
			new WebDriverWait(driver, 60).until(
			        ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.Label_Program_Timimgs_Upcoming_Episode_Tab));
			seasonLabel = driver.findElements(ProgramShowCardLocators.Label_Program_Timimgs_Upcoming_Episode_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Program_Timimgs_Upcoming_Episode_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return seasonLabel;
	}

	private List<WebElement> getEpisodeLabelUpcomingEpisodeTab(WebDriver driver, String testCaseName) {

		List<WebElement> episodeLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.Label_Episode_Upcoming_Episodes_Tab));
			episodeLabel = driver.findElements(ProgramShowCardLocators.Label_Episode_Upcoming_Episodes_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Episode_Upcoming_Episodes_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return episodeLabel;
	}

	private List<WebElement> getSeasonLabelUpcomingEpisodeTab(WebDriver driver, String testCaseName) {

		List<WebElement> seasonLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.Label_Season_Upcoming_Episodes_Tab));
			seasonLabel = driver.findElements(ProgramShowCardLocators.Label_Season_Upcoming_Episodes_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Season_Upcoming_Episodes_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return seasonLabel;
	}

	private List<WebElement> getEpisodeLabelEpisodeGuideTab(WebDriver driver, String testCaseName) {

		List<WebElement> episodeLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.Label_Episode_Episodes_Guide_Tab));
			episodeLabel = driver.findElements(ProgramShowCardLocators.Label_Episode_Episodes_Guide_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Episode_Episodes_Guide_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return episodeLabel;
	}

	private List<WebElement> getSeasonLabelEpisodeGuideTab(WebDriver driver, String testCaseName) {

		List<WebElement> seasonLabel = null;

		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfElementLocated(ProgramShowCardLocators.Label_Season_Episodes_Guide_Tab));
			seasonLabel = driver.findElements(ProgramShowCardLocators.Label_Season_Episodes_Guide_Tab);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Label_Season_Episodes_Guide_Tab is not present");
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return seasonLabel;
	}

	public void getDuplicateCast(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {
			List<JSONObject> DuplicateEntry = findDuplicates(getCast(driver, testCaseName));
			if (DuplicateEntry.size() > 0) {
				logger.error("Found Duplicate in Cast " + DuplicateEntry);
				Assert.fail("Found Duplicate in Cast " + DuplicateEntry);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

	}

	public void getDuplicateCrew(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {
			List<JSONObject> DuplicateEntry = findDuplicates(getCrew(driver, testCaseName));
			if (DuplicateEntry.size() > 0) {
				logger.error("Found Duplicate in Crew " + DuplicateEntry);
				Assert.fail("Found Duplicate in Crew " + DuplicateEntry);
			}

		} catch (Exception e) {
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}

	}

	private List<JSONObject> findDuplicates(List<JSONObject> listContainingDuplicates) {
		Boolean				flag			= false;

		List<JSONObject>	duplicateArray	= new LinkedList<JSONObject>();

		for (int i = 0; i < listContainingDuplicates.size(); i++) {
			for (int j = 0; j < listContainingDuplicates.size(); j++) {

				if (i != j && listContainingDuplicates.get(i).toString().equalsIgnoreCase(listContainingDuplicates.get(j).toString())) {
					// System.out.println("value of i : " + i + " JSON Object is : "
					// +listContainingDuplicates.get(i));
					// System.out.println("value of j : " + j+ " JSON Object is : "
					// +listContainingDuplicates.get(j));
					flag = true;

					if (flag && i < j) {
						JSONObject respJson = new JSONObject();
						respJson.put("first value at", i);
						respJson.put("second value at", j);
						respJson.put("JSON value is", listContainingDuplicates.get(i));
						duplicateArray.add(respJson);
					}
				}
			}

		}

		return duplicateArray;
	}

	public void gridLoadingTimeWait(WebDriver driver, String testCaseName) {
		try {
			Thread.sleep(8000);

			Screenshot.captureScreenshot(driver, testCaseName, "Program Show Card Grid Loading Completed");

			logger.info("Program Show Card Grid Loading Completed");

		} catch (Exception e) {

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			logger.error("Loader Object went off from DOM");
		}

	}

	public List<String> getAllProgramTimingsStringFromOverviewTab(WebDriver driver, String testCaseName) {
		List<String> programTimingsText = new LinkedList<String>();
		for (WebElement ele : getProgramTimingsLabelOverviewTab(driver, testCaseName)) {
			String[] s = ele.getText().split(" ");
			if (s[0].matches("^[a-zA-Z]*$")) {
				programTimingsText.add(s[0]);
			}

			if (s[1].matches("^[a-zA-Z]*$")) {
				programTimingsText.add(s[1]);
			}
		}

		return programTimingsText;
	}

	public List<String> getAllProgramTimingsStringFromUpcomingEpisodeTab(WebDriver driver, String testCaseName) {
		List<String> programTimingsText = new LinkedList<String>();
		for (WebElement ele : getProgramTimingsLabelUpcomingEpisodeTab(driver, testCaseName)) {
			String[] s = ele.getText().split(" ");
			if (s[0].matches("^[a-zA-Z]*$")) {
				programTimingsText.add(s[0]);
			}

			if (s[1].matches("^[a-zA-Z]*$")) {
				programTimingsText.add(s[1]);
			}
		}

		return programTimingsText;
	}

	/**
	 * @description to fetch all text from Program Show Card Page
	 * @param driver
	 * @param testCaseName
	 * @return
	 */
	public List<String> getProgramShowCardPageText(WebDriver driver, String testCaseName) {

		// to differentiate between upcoming episodes and airings
		boolean			 isUpcomingEpisodeTabDisplayed	= true;
		boolean			 isWatchOnlineDropdownDisplayed	= true;
		List<WebElement> elements 						= null;
		logger.info("Reading texts from Program Show Card Page");

		List<String> programShowCardPageText = new LinkedList<String>();

		programShowCardPageText.add(getBackToGridButton(driver, testCaseName).getText());
		programShowCardPageText.add(getSearchBox(driver, testCaseName).getAttribute("placeholder"));		

		try {
			programShowCardPageText.add(getSignUpOrLoginButton(driver, testCaseName).getText());
		} catch (NullPointerException e) {
			logger.error("Exception occured while locating SignUp popup on Program Show Card Page.");
			programShowCardPageText.add(sl.getSignedUpUserName(driver, testCaseName).getText().split(" ")[0]);
		}

		programShowCardPageText.add(getAddToMyShowsButton(driver, testCaseName).getText());
		programShowCardPageText.add(getOverviewTab(driver, testCaseName).getText());
		try {

			programShowCardPageText.add(getUpcomingEpisodeTab(driver, testCaseName).getText());
		} catch (NullPointerException e) {
			logger.error("Exception Occured while locating UpcomingEpisodes Tab");
			isUpcomingEpisodeTabDisplayed = false;
			programShowCardPageText.add(getUpcomingAiringsTab(driver, testCaseName).getText());
		}

		try {
			programShowCardPageText.add(getEpisodeGuideTab(driver, testCaseName).getText());
		} catch (NullPointerException e) {
			logger.error("Exception Occured while locating EpisodeGuide Tab");
		}

		programShowCardPageText.add(getCastAndCrewTab(driver, testCaseName).getText());

		/** Fetch text from Overview Tab **/
		programShowCardPageText.add(getUpcomingEpisodeLabel(driver, testCaseName).getText());
		programShowCardPageText.add(getSeeAllUpcomingEpisodeButton(driver, testCaseName).getText());
		try {			
			programShowCardPageText.add(getAiringLabelOverviewTab(driver, testCaseName).getText());
		} catch (NullPointerException e) {		
			logger.error("Exception Occured while locating Label_Airing_Overview_Tab");
		}

		try {
			programShowCardPageText.add(getWatchOnlineDropdown_OverviewTab(driver, testCaseName).getText());
		} catch (NullPointerException e) {
			isWatchOnlineDropdownDisplayed = false;
			logger.error("Exception Occured while locating WatchOnline Dropdown");
		}

		try {
			programShowCardPageText.add(getSimilarShowLabel(driver, testCaseName).getText());
		} catch (NullPointerException e) {
			logger.error("Exception Occured while locating Similar Show Text");
		}

		// to fetch Season Label from Episode Details
		elements = getSeasonLabelOverviewTab(driver, testCaseName);
		if( elements!=null && elements.size()>0)
		{
			for (WebElement ele : elements) {
				programShowCardPageText.add(ele.getText());
			}
		}
		
		// to fetch episode label from Episode Details
		elements = getEpisodeLabelOverviewTab(driver, testCaseName);
		if( elements!=null && elements.size()>0)
		{
			for (WebElement ele : elements) {
				programShowCardPageText.add(ele.getText());
			}
		}
		
		// to fetch Find It on Text
		elements = getFindItOnLabelOverviewTab(driver, testCaseName);
		if( elements!=null && elements.size()>0)
		{
			for (WebElement ele : elements) {
				programShowCardPageText.add(ele.getText());
			}
		}
		
		System.out.println("Overview tab cpatured suceesfully");
		
		// to fetch Program Timings Text
		// programShowCardPageText.addAll(getAllProgramTimingsStringFromOverviewTab(driver,
		// testCaseName));

		// to scroll to bottom
		wea.scrollPageBottom(driver);

		programShowCardPageText.add(getCastAndCrewLabel(driver, testCaseName).getText());
		programShowCardPageText.add(getSeeAllCastAndCrewButton(driver, testCaseName).getText());		

		// to scroll to top
		wea.scrollPageTop(driver);

		/** to fetch text from Upcoming Episode Tab **/

		// to select between upcoming episodes and airing
		if (isUpcomingEpisodeTabDisplayed) {
			wea.click(getUpcomingEpisodeTab(driver, testCaseName));

			// to fetch Season Label from Episode Details
			
			elements = getEpisodeLabelOverviewTab(driver, testCaseName);
			if( elements!=null && elements.size()>0)
			{
				for (WebElement ele :elements) {
					programShowCardPageText.add(ele.getText());
				}
			}
			
			// to fetch episode label from Episode Details
			elements = getEpisodeLabelUpcomingEpisodeTab(driver, testCaseName);
			if( elements!=null && elements.size()>0)
			{
				for (WebElement ele : elements) {
					programShowCardPageText.add(ele.getText());
				}
			}
			
			// to get all airing text present on page
			elements = getAiringLabelUpcomingEpisodeTab(driver, testCaseName);
			if( elements!=null && elements.size()>0)
			{
				for (WebElement ele : elements) {
					programShowCardPageText.add(ele.getText());
				}
			}
			
			// to fetch all Find It On text present on page
			elements = getFindItOnLabelUpcomingEpisodeTab(driver, testCaseName);
			if( elements!=null && elements.size()>0)
			{
				for (WebElement ele : elements) {
					programShowCardPageText.add(ele.getText());
				}
			}
		
			// to fetch Program Timings Text
			// programShowCardPageText.addAll(getAllProgramTimingsStringFromUpcomingEpisodeTab(driver,
			// testCaseName));
			
			
			
		} else {
			wea.click(getUpcomingAiringsTab(driver, testCaseName));
		}

		if (isWatchOnlineDropdownDisplayed) {
			programShowCardPageText.add(getWatchOnlineDropdown_UpcomingEpisodeTab(driver, testCaseName).getText());
		}
		
		System.out.println("Upcoming Episode tab cpatured suceesfully");
		
		
		/** to fetch text from Episode Guide Tab **/

		try {
			wea.click(getEpisodeGuideTab(driver, testCaseName));

			programShowCardPageText.add(getSeasonsLabelDropdownTitle(driver, testCaseName).getText());

			int i = Integer.parseInt(getSeasonsIndexLabelDropdownTitle(driver, testCaseName).getText());
			if (i > 0) {
				for (WebElement ele : getAllSeasonsLabelFromDropdown(driver, testCaseName)) {
					programShowCardPageText.add(ele.getText());
				}
				wea.click(getSeasonsDropdown(driver, testCaseName));
			}

			// to fetch Season Label from Episode Details
			for (WebElement ele : getSeasonLabelEpisodeGuideTab(driver, testCaseName)) {
				programShowCardPageText.add(ele.getText());
			}

			// to fetch episode label from Episode Details
			for (WebElement ele : getEpisodeLabelEpisodeGuideTab(driver, testCaseName)) {
				programShowCardPageText.add(ele.getText());
			}

			// fetching Upcoming Airing Button Text
			for (WebElement ele : getUpcomingAiringButton(driver, testCaseName)) {
				programShowCardPageText.add(ele.getText());
			}

			// fetching Airing/Aired label text
			for (WebElement ele : getAiringLabelEpisodeGuideTab(driver, testCaseName)) {
				programShowCardPageText.add(ele.getText());
			}

			// fetching Watch Online Dropdown Text
			for (WebElement ele : getWatchOnlineDropdown_EpisodeGuideTab(driver, testCaseName)) {
				programShowCardPageText.add(ele.getText());
			}

		} catch (NullPointerException e) {
			logger.error("Error while clicking Episode Guide Tab");
		}
		
		System.out.println("Episode guide tab cpatured suceesfully");
		
		// code to be added post span are created.

		/** to fetch text from Cast & Crew Tab **/
		wea.click(getCastAndCrewTab(driver, testCaseName));

		programShowCardPageText.add(getCastLabel(driver, testCaseName).getText());
		programShowCardPageText.add(getCrewLabel(driver, testCaseName).getText());		

		logger.info("Program Show Card Page text reading Completed.");
		System.out.println("cast and crew tab cpatured suceesfully");
		return programShowCardPageText;

	}
}
