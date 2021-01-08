package com.gracenote.pages;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.Locators.GridPreferencesLocators;
import com.gracenote.Locators.IndexPageLocators;
import com.gracenote.Utilities.Screenshot;
import com.gracenote.constants.IConstants;

public class GridPreferences {

	private static Logger	logger	= Logger.getLogger(GridPreferences.class);

	WebElementActions		wea		= WebElementActions.getWebElementActionsObject();

	private WebElement showMusicChannels(WebDriver driver, String testCaseName) {
		WebElement ShowMusicChannleChkbox = null;
		new WebDriverWait(driver, 30).until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(GridPreferencesLocators.Checkbox_Grid_Preference_Show_Music_Channels));
		ShowMusicChannleChkbox = driver.findElement(GridPreferencesLocators.Checkbox_Grid_Preference_Show_Music_Channels);

		return ShowMusicChannleChkbox;
	}

	private WebElement showPPVChannels(WebDriver driver, String testCaseName) {
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GridPreferencesLocators.Checkbox_Grid_Preference_Show_PPV_Channels));
		WebElement ShowPPVChannleChkbox = driver.findElement(GridPreferencesLocators.Checkbox_Grid_Preference_Show_PPV_Channels);
		return ShowPPVChannleChkbox;
	}

	private WebElement show3HoursDisplay(WebDriver driver, String testCaseName){
		new WebDriverWait(driver, 30)  
		.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GridPreferencesLocators.Checkbox_Grid_Preference_Show_threehours_Display));
		WebElement ShowThreehoursChkbox = driver.findElement(GridPreferencesLocators.Checkbox_Grid_Preference_Show_threehours_Display);
		return ShowThreehoursChkbox;
	}
	
	private WebElement showSixHoursDisplay(WebDriver driver, String testCaseName){
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GridPreferencesLocators.Checkbox_Grid_Preference_Show_sixhours_Display));
		WebElement ShowSixhoursChkbox = driver.findElement(GridPreferencesLocators.Checkbox_Grid_Preference_Show_sixhours_Display);
		return ShowSixhoursChkbox;
	}

	public WebElement showHDChannels(WebDriver driver, String testCaseName) {
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GridPreferencesLocators.Checkbox_Grid_Preference_Show_HD_Channels));
		WebElement ShowHDChannleChkbox = driver.findElement(GridPreferencesLocators.Checkbox_Grid_Preference_Show_HD_Channels);
		return ShowHDChannleChkbox;
	}

	private WebElement GridPreferenceChangButton(WebDriver driver, String testCaseName) {
		new WebDriverWait(driver, 55).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GridPreferencesLocators.Button_Grid_Preference));
		WebElement GridPrefernceChangeBtn = driver.findElement(GridPreferencesLocators.Button_Grid_Preference);
		return GridPrefernceChangeBtn;
	}

	//

	private WebElement GridPreferenceSaveButton(WebDriver driver, String testCaseName) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GridPreferencesLocators.Button_Grid_Preference_Save));
		WebElement GridPreferenceSaveBtn = driver.findElement(GridPreferencesLocators.Button_Grid_Preference_Save);
		return GridPreferenceSaveBtn;
	}

	public void openGridPreference(WebDriver driver, String testCaseName) {
		GridPreferenceChangButton(driver, testCaseName).click();
		Screenshot.captureScreenshot(driver, testCaseName, "After Opening Grid Preference");
	}

	public void includeMusicChannels(WebDriver driver, String testCaseName) {

		if (!(showMusicChannels(driver, testCaseName).isSelected()))
			wea.clickJs(driver, showMusicChannels(driver, testCaseName));
	}

	public void includePPVChannels(WebDriver driver, String testCaseName) {
		if (!(showPPVChannels(driver, testCaseName).isSelected()))
			wea.clickJs(driver, showPPVChannels(driver, testCaseName));
	}

	public void includeHDChannels(WebDriver driver, String testCaseName) {
		if (!(showHDChannels(driver, testCaseName).isSelected()))
			wea.clickJs(driver, showHDChannels(driver, testCaseName));
	}

	private void excludeMusicChannels(WebDriver driver, String testCaseName) {
		if (showMusicChannels(driver, testCaseName).isSelected())
			showMusicChannels(driver, testCaseName).click();

		Screenshot.captureScreenshot(driver, testCaseName, "After exclusing music channel from grid preference");
	}

	private void excludePPVChannels(WebDriver driver, String testCaseName) {
		if (showPPVChannels(driver, testCaseName).isSelected())
			showPPVChannels(driver, testCaseName).click();

		Screenshot.captureScreenshot(driver, testCaseName, "After exclusing PPV channel from grid preference");
	}

	private void excludeHDChannels(WebDriver driver, String testCaseName) {
		if (showHDChannels(driver, testCaseName).isSelected())
			showHDChannels(driver, testCaseName).click();

		Screenshot.captureScreenshot(driver, testCaseName, "After exclusing music channel from grid preference");
	}

	public void SaveGridPrefernce(WebDriver driver, String testCaseName) {

		Screenshot.captureScreenshot(driver, testCaseName, "Before saving grid preference");

		GridPreferenceSaveButton(driver, testCaseName).click();

		Screenshot.captureScreenshot(driver, testCaseName, "After saving grid preference");
	}

	public void RemoveGridPrefernces(WebDriver driver, String testCaseName) {
		excludeHDChannels(driver, testCaseName);
		excludeMusicChannels(driver, testCaseName);
		excludePPVChannels(driver, testCaseName);
	}

	public boolean isMusicSelected(WebDriver driver, String testCaseName) {
		if (showMusicChannels(driver, testCaseName).isSelected())
			return true;
		else
			return false;
	}

	public boolean isPPVSelected(WebDriver driver, String testCaseName) {
		if (showPPVChannels(driver, testCaseName).isSelected())
			return true;
		else
			return false;
	}

	public boolean isHDSelected(WebDriver driver, String testCaseName) {
		if (showHDChannels(driver, testCaseName).isSelected())
			return true;
		else
			return false;
	}

	public String getGridPreferenceButtonText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(GridPreferencesLocators.Text_Button_Grid_Preference).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getGridPreferenceLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(GridPreferencesLocators.Label_Grid_Preference).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getGridPreferenceFilterLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(GridPreferencesLocators.Label_Filters_Grid_Preference).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getGridPreferenceShowMusicChannelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(GridPreferencesLocators.Text_Grid_Preference_Show_Music_Channels).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getGridPreferenceShowPPVChannelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(GridPreferencesLocators.Text_Grid_Preference_Show_PPV_Channels).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getGridPreferenceShowHDChannelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(GridPreferencesLocators.Text_Grid_Preference_Show_HD_Channels).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getGridPreferenceSaveButtonText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(GridPreferencesLocators.Button_Grid_Preference_Save).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getGridPreferenceCancelButtonText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(GridPreferencesLocators.Button_Grid_Preference_Cancel).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public void closeGridPreferencePopup(WebDriver driver, String testCaseName) {
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GridPreferencesLocators.Button_Grid_Preference_Close));
			wea.click(driver.findElement(GridPreferencesLocators.Button_Grid_Preference_Close));
			logger.info("After Closing Grid Preference Popup");
			Screenshot.captureScreenshot(driver, testCaseName, "Grid Preference Popup Closed");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	/**
	 * @description to fetch all text from Grid Preference popup
	 * @param driver
	 * @param testCaseName
	 * @return
	 */
	public List<String> getGridPreferenceText(WebDriver driver, String testCaseName) {

		logger.info("Reading texts from Grid Preference");

		List<String> gridPreferenceText = new LinkedList<String>();

		gridPreferenceText.add(getGridPreferenceButtonText(driver, testCaseName));

		openGridPreference(driver, testCaseName);

		gridPreferenceText.add(getGridPreferenceLabelText(driver, testCaseName));
		gridPreferenceText.add(getGridPreferenceFilterLabelText(driver, testCaseName));
		gridPreferenceText.add(getGridPreferenceShowMusicChannelText(driver, testCaseName));
		gridPreferenceText.add(getGridPreferenceShowPPVChannelText(driver, testCaseName));
		gridPreferenceText.add(getGridPreferenceShowHDChannelText(driver, testCaseName));
		gridPreferenceText.add(getGridPreferenceSaveButtonText(driver, testCaseName));
		gridPreferenceText.add(getGridPreferenceCancelButtonText(driver, testCaseName));

		closeGridPreferencePopup(driver, testCaseName);

		logger.info("Grid Preference text reading Completed.");

		return gridPreferenceText;
	}

	public void selectThreeHoursdisplay(WebDriver driver,String testCaseName) 
	{	
		if (!(show3HoursDisplay(driver, testCaseName).isSelected()))
			wea.clickJs(driver, show3HoursDisplay(driver, testCaseName));

	}
	
	public void selectSixHoursdisplay(WebDriver driver,String testCaseName) 
	{	
		if (!(showSixHoursDisplay(driver, testCaseName).isSelected()))
			wea.clickJs(driver, showSixHoursDisplay(driver, testCaseName));

	}

	public List<WebElement> getGridHeaderTotalItems(WebDriver driver,String testCaseName)
	{		
		try
		{

			return new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfAllElementsLocatedBy(GridPreferencesLocators.Grid_Preferencde_Total_Items));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return null;


	}
	
	public String getGridStartTime(WebDriver driver,String testCaseName) 
	{	
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GridPreferencesLocators.Grid_start_time));
	    String startTime= driver.findElement(GridPreferencesLocators.Grid_start_time).getText();
		logger.info("After Closing Grid Preference Popup");
		Screenshot.captureScreenshot(driver, testCaseName, "Grid Preference Popup Closed");
		
		return startTime;

	}
	
	public void selectstarredChannelCheckbox(WebDriver driver,String testCaseName)
	{
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(GridPreferencesLocators.Starred_Filter_checkbox));
		WebElement starredChannelbox = driver.findElement(GridPreferencesLocators.Starred_Filter_checkbox);
		if(!starredChannelbox.isSelected())
		{
		wea.clickJs(driver, starredChannelbox);
		SaveGridPrefernce(driver, testCaseName);
		}
		else
		{
		WebElement cancelBtn = driver.findElement(GridPreferencesLocators.Button_Grid_Preference_Cancel);
		wea.clickJs(driver, cancelBtn);
			
		}
		
	}
}
