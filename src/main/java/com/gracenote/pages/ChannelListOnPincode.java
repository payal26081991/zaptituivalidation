package com.gracenote.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Locators.ChannelListOnPincodeLocators;
import com.gracenote.Locators.IndexPageLocators;
import com.gracenote.Utilities.Screenshot;
import com.gracenote.constants.IConstants;

public class ChannelListOnPincode {

	private static Logger			logger	= Logger.getLogger(ChannelListOnPincode.class);

	GeneralApplicaitonProperties	gap		= new GeneralApplicaitonProperties();
	WebElementActions				wea		= WebElementActions.getWebElementActionsObject();

	// Block which return the Web Elements

	private WebElement getCountry(WebDriver driver, String testCaseName) {
		WebElement CountrySelect = null;

		try {
			CountrySelect = null;
			new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ChannelListOnPincodeLocators.Dropdown_Country_Selection));

			CountrySelect = driver.findElement(ChannelListOnPincodeLocators.Dropdown_Country_Selection);

		} catch (Exception e) {

			logger.error("Error occured while locating country selection dropdown in change provider popup." + e);
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return CountrySelect;
	}

	public WebElement getchangeProviderTimeZonebtn(WebDriver driver, String testCaseName) {

		logger.info("Locating Change provider or Time Zone button ");

		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ChannelListOnPincodeLocators.Button_Change_Provider_Timezone));

		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(ChannelListOnPincodeLocators.Button_Change_Provider_Timezone));

		WebElement changeProviderTimeZone = driver.findElement(ChannelListOnPincodeLocators.Button_Change_Provider_Timezone);

		Screenshot.captureScreenshot(driver, testCaseName, "After locating change provider timezone button");
		return changeProviderTimeZone;
	}

	private WebElement getZipcodeTextBox(WebDriver driver, String testCaseName) {
		WebElement ZipcodeTextBox = null;
		try {

			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ChannelListOnPincodeLocators.TextBox_Zip_Code));

			ZipcodeTextBox = driver.findElement(ChannelListOnPincodeLocators.TextBox_Zip_Code);

		} catch (Exception e) {

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			logger.error("Exception Occured while locating zip code element." + e);
		}
		return ZipcodeTextBox;
	}

	private WebElement getFindProviderbtn(WebDriver driver, String testCaseName) {
		WebElement FindProviderbtn = null;
		try {

			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ChannelListOnPincodeLocators.Button_Find_Provider));

			FindProviderbtn = driver.findElement(ChannelListOnPincodeLocators.Button_Find_Provider);

		} catch (Exception e) {

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			logger.error("Exception Occured while locating Find Provider button." + e);
		}
		return FindProviderbtn;
	}

	private WebElement getProvidersinTabs(WebDriver driver, String testCaseName, String provide) {
		WebElement Providertab = null;
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(provide)));
			Thread.sleep(12000);
			Providertab = driver.findElement(By.partialLinkText(provide));

		} catch (Exception e) {

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			logger.error("Exception Occured while locating Providers." + e);
		}
		return Providertab;
	}

	private List<WebElement> getChannelsOnGrid(WebDriver driver, String testCaseName) {
		List<WebElement> ChannelsOnGrid = null;
		try {

			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ChannelListOnPincodeLocators.Channel_Row));

			ChannelsOnGrid = driver.findElements(ChannelListOnPincodeLocators.Channel_Row);

			Screenshot.captureScreenshot(driver, testCaseName, "After fetching channels on Grid");

		} catch (Exception e) {

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			logger.error("Exception Occured while fetching channels on grid." + e);
		}
		return ChannelsOnGrid;
	}

	private WebElement getDefaultHeadend(WebDriver driver, int index) {
		WebElement ChannelsOnGrid = null;
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//button[@type='button'])[" + index + "]")));
			ChannelsOnGrid = driver.findElement(By.xpath("(//button[@type='button'])[" + index + "]"));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ChannelsOnGrid;
	}

	private WebElement getShowcardCloseBtn(WebDriver driver, String testCaseName) {
		WebElement ShowcardCloseBtn = null;
		try {

			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ChannelListOnPincodeLocators.Button_Close_Show_Details));

			ShowcardCloseBtn = driver.findElement(ChannelListOnPincodeLocators.Button_Close_Show_Details);

		} catch (Exception e) {

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			logger.error("Exception Occured while locating Show Close Button." + e);
		}
		return ShowcardCloseBtn;
	}

	// Block to return web element Ends Here

	private WebElement getSelectedProvider(WebDriver driver, String testCaseName, String tabSelection, String providerName) {
		try {
			//Thread.sleep(10000);
			wea.click(getProvidersinTabs(driver, testCaseName, tabSelection));

		} catch (Exception e) {
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			e.printStackTrace();
			logger.error("Selecting provider ", e);
		}

		List<WebElement> providers = null;
		try {

			Thread.sleep(8000);

			providers = driver.findElements(ChannelListOnPincodeLocators.All_Provider_List);
		} catch (Exception e) {
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			e.printStackTrace();
		}
		WebElement selectedProviderWebElement = null;
		for (WebElement elems : providers) {
			if (elems.getText().contains(providerName)) {
				selectedProviderWebElement = elems;
				break;
			}
		}
		return selectedProviderWebElement;
	}

	public String gridStartTime(WebDriver driver, String testCaseName) {
		WebElement gridTimebarStart = null;
		try {

			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Button_Grid_Start_Time));
			gridTimebarStart = driver.findElement(IndexPageLocators.Button_Grid_Start_Time);
		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return gridTimebarStart.getText();
	}

	private String gridEndTime(WebDriver driver) {
		WebElement gridTimebarEnd = null;
		try {
			// gridTimebarEnd=driver.findElement(By.xpath("(//button[contains(@class,'grid-header__item')])[6]"));
			driver.manage().window().maximize();
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@class='btn-group-last']/button[6]")));
			gridTimebarEnd = driver.findElement(By.xpath(".//*[@class='btn-group-last']/button[6]"));
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error while getting End of Grid Time bar", e);
			Assert.fail("Error while getting End of Grid Time bar", e);
		}
		return gridTimebarEnd.getText();
	}

	private List<String> providerList(WebDriver driver, String testCaseName, String providers) {

		wea.click(getProvidersinTabs(driver, testCaseName, providers));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			logger.error("Getting provider List ", e);
			Assert.fail(e.getMessage());
		}
		List<WebElement>	ProviderList	= driver.findElements(By.xpath(".//*[@class='provider-results__item']"));
		List<String>		channelList		= new ArrayList<String>();
		for (WebElement em1 : ProviderList) {
			if (em1.getText().trim() != "") {
				channelList.add(em1.getText());
			}
		}
		channelList.removeAll(Arrays.asList("", null));

		// logger.debug("channel list in providerList is "+ channelList);
		return channelList;
	}

	private WebElement oneOfDefaultTimezone(WebDriver driver, String timeZone) {
		WebElement defaultTimezones = driver.findElement(By.xpath("//div[contains(@class,'time-btn')]/button[contains(text(),'" + timeZone + "')]"));
		return defaultTimezones;
	}

	public void oneOfDefaultTimezone(WebDriver driver, String testCaseName, String Country, String timeZone) {
		try {
			wea.selectDropDown(getCountry(driver, testCaseName), Country);
			Thread.sleep(2000);
			wea.click(oneOfDefaultTimezone(driver, timeZone));

			gridLoadingTimeWait(driver, testCaseName);

		} catch (Exception e) {

			e.printStackTrace();

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

	}

	/*
	 * private WebElement getShowCardDetail(WebDriver driver) { WebElement
	 * ShowCardCloseBtn =
	 * driver.findElement(By.xpath("//div[@class='show-detail-card']")); return
	 * ShowCardCloseBtn; }
	 */

	public void HeadendGridLoader(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {

			if (gid.getCountryName().trim().length() > 0) {
				logger.info("Country to select from Drop Down as per Data Provider Object is :: " + gid.getCountryName());
				wea.selectDropDown(getCountry(driver, testCaseName), gid.getCountryName());
			}
			wea.setText(getZipcodeTextBox(driver, testCaseName), gid.getZipCode());

			//wea.click(getFindProviderbtn(driver, testCaseName));
			
			wea.clickJs(driver, getFindProviderbtn(driver, testCaseName));
			Thread.sleep(8000);
			wea.click(getSelectedProvider(driver, testCaseName, gid.getProviderTab(), gid.getSelectedProvider()));

			gridLoadingTimeWait(driver, testCaseName);

			logger.info("Grid Loaded for : " + gid.getZipCode() + " " + gid.getCountryName() + " " + gid.getProviderTab() + " " + gid.getSelectedProvider());

			Screenshot.captureScreenshot(driver, testCaseName, "After selecting provider");

		} catch (Exception e) {

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			Assert.fail(
			        "Grid Loaded for : " + gid.getZipCode() + " " + gid.getCountryName() + " " + gid.getProviderTab() + " " + gid.getSelectedProvider() + " with Error as :: " + e);
		}
	}

	public void headendGridLoader(WebDriver driver, String testCaseName, String Country, String Zipcode, String ProvierTab, String Provider) {
		try {

			if (Country.trim().length() > 0) {
				logger.info("Country to select from Drop Down as per Data Provider Object is :: " + Country);
				wea.selectDropDown(getCountry(driver, testCaseName), Country);
			}
			wea.setText(getZipcodeTextBox(driver, testCaseName), Zipcode);

			wea.click(getFindProviderbtn(driver, testCaseName));

			wea.click(getSelectedProvider(driver, testCaseName, ProvierTab, Provider));

			logger.info("Grid Loaded for : " + Zipcode + " " + Country + " " + ProvierTab + " " + Provider);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Grid Loaded for : " + Zipcode + " " + Country + " " + ProvierTab + " " + Provider, e);
			Assert.fail("Grid Loaded for : " + Zipcode + " " + Country + " " + ProvierTab + " " + Provider + " with Error as :: " + e);

		}
	}

	public void ChannelShowsTimeVerfication(WebDriver driver, String testCaseName, GlobalInputData gid) {
		HeadendGridLoader(driver, testCaseName, gid);
		TVGridChannelLoading(driver, testCaseName, gid);
	}

	public void ChannelShowsTimeVerficationEachDropcell(WebDriver driver, String testCaseName, GlobalInputData gid) {
		HeadendGridLoader(driver, testCaseName, gid);
		TVGridExpandShowcards(driver, testCaseName, gid);
	}

	/*
	 * We would be checking only for presence of web elements and data integrity
	 * will tested in api module
	 * 
	 */
	private void TVGridChannelLoading(WebDriver driver, String testCaseName, GlobalInputData gid) {

		logger.info("starting the GridTimeVerificationNow Mehtod");

		gridLoadingTimeWait(driver, testCaseName);

		List<WebElement> channelList = getChannelsOnGrid(driver, testCaseName);

		/*
		 * String gridStartTime = gridStartTime(driver); String gridEndTime =
		 * gridEndTime(driver);
		 * 
		 * logger.info("Grid Start time is :: " + gridStartTime +
		 * " -- Grid End Time is :: " + gridEndTime);
		 */
		logger.info("Validating the Channel that loaded for Country :" + gid.getCountryName() + " provider name :: " + gid.getSelectedProvider() + " channel count as :: "
		        + channelList.size());

		Assert.assertEquals(channelList.size() > 0, true);
	}

	private void TVGridExpandShowcards(WebDriver driver, String testCaseName, GlobalInputData gid) {

		List<WebElement> channelList = getChannelsOnGrid(driver, testCaseName);

		gridLoadingTimeWait(driver, testCaseName);

		/*
		 * String gridStartTime = gridStartTime(driver); String gridEndTime =
		 * gridEndTime(driver);
		 * 
		 * logger.info("Grid Start time is :: " + gridStartTime +
		 * " -- Grid End Time is :: " + gridEndTime);
		 */

		logger.info("Number of channels in our headend is :: " + channelList.size());

		int showCardsCount = 0;

		for (int i = 0; i < channelList.size(); i++) {

			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				WebDriverWait showcardwait = new WebDriverWait(driver, 60);
				;
				showcardwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'channel-listings')])[" + (i + 1) + "]/div")));
				List<WebElement> showCards = driver.findElements(By.xpath("(//div[contains(@class,'channel-listings')])[" + (i + 1) + "]/div"));

				for (int index = 0; index < showCards.size(); index++) {

					wea.click(showCards.get(index));

					showcardwait.until(ExpectedConditions.visibilityOfElementLocated(ChannelListOnPincodeLocators.Button_Close_Show_Details));

					wea.click(getShowcardCloseBtn(driver, testCaseName));
				}

			} catch (TimeoutException e) {

				logger.error("Row Failed: " + i + " : " + channelList.get(i).getText() + " Show Card Timings are Missing", e);

				Assert.fail(e + ": " + " channel name : " + channelList.get(i).getText() + " Country: " + gid.getCountryName() + " Zipcode: " + gid.getZipCode() + " HeadendName : "
				        + gid.getSelectedProvider() + " : Date is : " + System.currentTimeMillis() + " Show Card Timings are Missing");

				Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			} catch (Exception e) {

				logger.error("Row Failed: " + i + " : " + channelList.get(i).getText() + " Show Card Timings are Missing", e);

				Assert.fail(e + ": " + " channel name : " + channelList.get(i).getText() + " Country: " + gid.getCountryName() + " Zipcode: " + gid.getZipCode() + " HeadendName : "
				        + gid.getSelectedProvider() + " : Date is : " + System.currentTimeMillis() + " Show Card Timings are Missing");

				Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			}
		}

		logger.info(" Country: " + gid.getCountryName() + " Zipcode: " + gid.getZipCode() + " HeadendName : " + gid.getSelectedProvider()
		        + "Total showcards we clicked in current headend are :: " + showCardsCount);

	}

	public void gridLoadingTimeWait(WebDriver driver, String testCaseName) {
		try {
			Thread.sleep(8000);

			Screenshot.captureScreenshot(driver, testCaseName, "Grid Loading Completed");

			logger.info("Grid Loading Completed");

		} catch (Exception e) {

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			logger.error("Loader Object went off from DOM");
		}
	}

	public void DefaultHeadendSelector(WebDriver driver, String testCaseName, String Country, int index) {
		try {
			wea.selectDropDown(getCountry(driver, testCaseName), Country);
			wea.click(getDefaultHeadend(driver, index));
		} catch (Exception e) {
			gap.captureScreenShot(driver, "com.gracenote.Pages.ChannelListonpincode.DefaultHeadendSelector" + Country + "_" + "_" + index);
			logger.error("Error while selecting the Country", e);
		}
	}

	public String getFindProviderText(WebDriver driver, String testCaseName) {
		String providerText = "";

		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@type='submit']")));
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@type='submit']")));
		WebElement providerTextbtn = driver.findElement(By.xpath(".//*[@type='submit']"));

		providerText = providerTextbtn.getText();
		return providerText;

	}

	public String getChangeYourProviderOrTimezoneLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ChannelListOnPincodeLocators.Label_Change_Provider_Or_TimeZone));
			s = driver.findElement(ChannelListOnPincodeLocators.Label_Change_Provider_Or_TimeZone).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getFindProviderLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(ChannelListOnPincodeLocators.Label_Find_Provider).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getSelectTimezoneLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(ChannelListOnPincodeLocators.Label_Select_TimeZone).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getNoProviderFoundLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(ChannelListOnPincodeLocators.Label_No_Providers_Found).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getCableTabText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(ChannelListOnPincodeLocators.Text_Tab_Cable).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getSatelliteTabText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(ChannelListOnPincodeLocators.Text_Tab_Satellite).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getAntennaTabText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(ChannelListOnPincodeLocators.Text_Tab_Antenna).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getSearchZipcodePlaceholderText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(ChannelListOnPincodeLocators.TextBox_Zip_Code).getAttribute("placeholder");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getFindProviderButtonText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(ChannelListOnPincodeLocators.Button_Find_Provider).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getOrLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(ChannelListOnPincodeLocators.Label_Find_Provider_Or_Option).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getSearchTipText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ChannelListOnPincodeLocators.Text_Tip));
			s = driver.findElement(ChannelListOnPincodeLocators.Text_Tip).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public boolean isErrorMessageDisplayed(WebDriver driver, String testCaseName) {
		boolean link = false;
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ChannelListOnPincodeLocators.Text_Error_Message));
			link = wea.isElementDisplayed(driver.findElement(ChannelListOnPincodeLocators.Text_Error_Message));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return link;
	}

	public String getErrorMessageText(WebDriver driver, String testCaseName) {
		String s = null;
		try {

			Thread.sleep(2000);
			if (isErrorMessageDisplayed(driver, testCaseName)) {
				s = driver.findElement(ChannelListOnPincodeLocators.Text_Error_Message).getText();
				Screenshot.captureScreenshot(driver, testCaseName, "After Capturing Error Message");
			} else {
				logger.error("Unable to fetch SignUp or Login Error Message");
				Screenshot.captureScreenshot(driver, testCaseName, "Error Message Not Displayed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public void OpenPopUpforZipCode(WebDriver driver, String testCaseName) {
		try {
			wea.scrollPageTop(driver);
			// Thread.sleep(15000);

			wea.click(getchangeProviderTimeZonebtn(driver, testCaseName));
			Thread.sleep(2000);

			logger.info("After Opening Change provider timezone button");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public void OpenPopUpforZipCode(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {

			wea.click(getchangeProviderTimeZonebtn(driver, testCaseName));
			Screenshot.captureScreenshot(driver, testCaseName, "After opening zip code popup");

		} catch (Exception e) {

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			e.printStackTrace();

			Assert.fail("Error while opening the Zipcode pop-up : " + gid.getCountryName() + " : " + gid.getZipCode() + " : " + gid.getSelectedProvider(), e);
		}
	}

	public void closePopUpforZipCode(WebDriver driver, String testCaseName) {
		try {
			wea.scrollPageTop(driver);
			// Thread.sleep(15000);

			wea.click(driver.findElement(ChannelListOnPincodeLocators.Button_Close_Change_Provider_Timezone));

			logger.info("After Closing Change provider timezone button");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	/**
	 * @description to fetch text of entire Signup Module for Multilingual
	 *              validation
	 * @param driver
	 * @param testCaseName
	 * @return List
	 */
	public List<String> getChannelListOnText(WebDriver driver, String testCaseName) {

		List<String> channelListText = new LinkedList<String>();
		try {

			// to open zipcode popup
			OpenPopUpforZipCode(driver, testCaseName);

			channelListText.add(getChangeYourProviderOrTimezoneLabelText(driver, testCaseName));
			channelListText.add(getFindProviderLabelText(driver, testCaseName));
			channelListText.add(getSearchZipcodePlaceholderText(driver, testCaseName));
			channelListText.add(getFindProviderButtonText(driver, testCaseName));
			channelListText.add(getCableTabText(driver, testCaseName));
			channelListText.add(getSatelliteTabText(driver, testCaseName));
			channelListText.add(getAntennaTabText(driver, testCaseName));
			channelListText.add(getNoProviderFoundLabelText(driver, testCaseName));
			channelListText.add(getOrLabelText(driver, testCaseName));
			channelListText.add(getSelectTimezoneLabelText(driver, testCaseName));

			// to fetch all error messages
			channelListText.addAll(getChangeProviderPopupErrorMessageText(driver, testCaseName));

			// to fetch Tip
			wea.setText(getZipcodeTextBox(driver, testCaseName), "10001");
			wea.click(getFindProviderbtn(driver, testCaseName));
			channelListText.add(getSearchTipText(driver, testCaseName));

			// to close popup
			closePopUpforZipCode(driver, testCaseName);

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return channelListText;

	}

	private List<String> getChangeProviderPopupErrorMessageText(WebDriver driver, String testCaseName) {

		List<String> errorListText = new LinkedList<String>();

		try {
			// to fetch Invalid Zipcode Error Message
			wea.click(getFindProviderbtn(driver, testCaseName));
			errorListText.add(getErrorMessageText(driver, testCaseName));
			logger.info("Error message fetched for : Invalid Zipcode");

			// to fetch No lineups are available for this ZIP or postal code. Please enter a
			// different ZIP or postal code. Error Message
			wea.setText(getZipcodeTextBox(driver, testCaseName), (new Random().nextInt(899999) + 100000) + "");
			wea.click(getFindProviderbtn(driver, testCaseName));
			errorListText.add(getErrorMessageText(driver, testCaseName));
			logger.info("Error message fetched for : No lineups are available for this ZIP or postal code. Please enter a " + "different ZIP or postal code");

			// to fetch Opps ! Something went wrong Error Message
			wea.setText(getZipcodeTextBox(driver, testCaseName), "!@#$%");
			wea.click(getFindProviderbtn(driver, testCaseName));
			errorListText.add(getErrorMessageText(driver, testCaseName));
			logger.info("Error message fetched for : Opps ! Something went wrong");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return errorListText;
	}

	public List<String> getChangeLocationTextSingleProvider(WebDriver driver, String testCaseName) {
		List<String>	changeLocationListText	= new LinkedList<String>();

		String			s						= driver.findElement(IndexPageLocators.Text_Button_Change_Provider_Timezone).getText();

		changeLocationListText.add(s);

		// to open zipcode popup
		OpenPopUpforZipCode(driver, testCaseName);

		changeLocationListText.add(getChangeYourProviderOrTimezoneLabelText(driver, testCaseName));
		changeLocationListText.add(getFindProviderLabelText(driver, testCaseName));
		changeLocationListText.add(getSearchZipcodePlaceholderText(driver, testCaseName));
		changeLocationListText.add(getFindProviderButtonText(driver, testCaseName));

		// to fetch all error messages
		changeLocationListText.addAll(getChangeProviderPopupErrorMessageText(driver, testCaseName));

		// to close popup
		closePopUpforZipCode(driver, testCaseName);

		return changeLocationListText;
	}

	/*
	 * Below method was used when hidden Date field was not Entered by DEV
	 * 
	 *
	 * 
	 * private void StartEndTimeValidator(WebDriver driver,GlobalInputData gid) {
	 * 
	 * driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	 * WebDriverWait wait = new WebDriverWait(driver, 60);
	 * 
	 * List<WebElement> ChannelsOnGrid = getChannelsOnGrid(driver); List<String>
	 * ProgramStartEndTimings;
	 * //logger.info("Number on channels on Grid is :: "+ChannelsOnGrid.size()); int
	 * channelIndex = 1;
	 * 
	 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
	 * "(//div[contains(@class,'channel-listings')])//div[@class='show-card__time']"
	 * )));
	 * 
	 * // Getting Grid Start time from Grid Time bar.
	 * 
	 * String gridStartTime=gridStartTime(driver);
	 * 
	 * String gridEndTime=gridEndTime(driver);
	 * 
	 * 
	 * 
	 * /*try { gridStartTime=format.format(format.parse(gridStartTime)); } catch
	 * (ParseException e1) {
	 * logger.error("Error while parsing Grid Start time : ",e1);
	 * Assert.fail("Error while parsing Grid Start time : ",e1); }
	 * gridEndTime=format.format(calendar.getTime());
	 * 
	 * 
	 * //logger.info("Grid Start and End time : "+gridStartTime+" "+gridEndTime);
	 * int i=0;
	 * 
	 * for(WebElement channel : ChannelsOnGrid) {
	 * 
	 * 
	 * String ChannelName = channel.getAttribute("id");
	 * 
	 * //logger.info("ChannelName :: "+ChannelName);
	 * 
	 * ProgramStartEndTimings = new ArrayList<String>();
	 * 
	 * String WebElementXpath = ".//*[@class='clearfix grid-results']/div/div["
	 * +channelIndex+"]/div[1]/.//span/div[text()!=' - ']";
	 * //logger.info("Channel Index is :: "+channelIndex);
	 * 
	 * List<WebElement> ProgramStartEndTime =
	 * driver.findElements(By.xpath(WebElementXpath));
	 * //logger.info("ProgramStartEndTime Size: "+ProgramStartEndTime.size());
	 * for(WebElement timings : ProgramStartEndTime) { String inputExpression =
	 * timings.getAttribute("innerHTML"); ArrayList<String> programtime =
	 * getTimes(inputExpression); ProgramStartEndTimings.add(programtime.get(0));
	 * //11 pm ProgramStartEndTimings.add(programtime.get(1)); //10 am }
	 * 
	 * //logger.error("ProgramStartEndTimings :: "+ProgramStartEndTimings +
	 * "channel name : "+ ChannelName ); for (int k=1 ;
	 * k<(ProgramStartEndTimings.size()-1);k=k+2) {
	 * //Assert.assertEquals(ProgramStartEndTimings.get(k).trim(),
	 * ProgramStartEndTimings.get((k+1)).trim(),"ProgramStartEndTimings :: "
	 * +ProgramStartEndTimings + "channel name : "+ ChannelName ); try {
	 * //logger.error("loop1 From time :: " +
	 * format.parse(ProgramStartEndTimings.get(k)));
	 * //logger.error("loop1 End Time :: "+format.parse(ProgramStartEndTimings.get(k
	 * +1)));
	 * Assert.assertEquals(compareShowcardTimeSequence(ProgramStartEndTimings.get(k)
	 * , ProgramStartEndTimings.get(k+1)),
	 * 0,"comparing the start and End time of two consecutive that they are in sequence. ProgramStartEndTimings :: "
	 * +ProgramStartEndTimings + "channel name : "+ ChannelName +
	 * " Country: "+gid.getCountryName() + " Zipcode: "+gid.getZipCode() +
	 * " HeadendName : "+gid.getSelectedProvider() + " : Date is : " +
	 * System.currentTimeMillis()); } catch (ParseException e) { // TODO
	 * Auto-generated catch block e.printStackTrace();
	 * logger.error("failed due to : ", e); Assert.fail("failed due to : ", e); }
	 * 
	 * }
	 * 
	 * 
	 * for (int k=0 ; k< ProgramStartEndTimings.size();k=k+2) {
	 * 
	 * try { //logger.error("loop2 From time :: " +
	 * format.parse(ProgramStartEndTimings.get(k)));
	 * //logger.error("loop2 End Time :: "+format.parse(ProgramStartEndTimings.get(k
	 * +1)));
	 * Assert.assertEquals(compareShowcardTimeSequence(ProgramStartEndTimings.get(k)
	 * , ProgramStartEndTimings.get(k+1)),-
	 * 1,"comparing the start of two consecutive shows that they are in sequence. ProgramStartEndTimings :: "
	 * +ProgramStartEndTimings + "channel name : "+ ChannelName +
	 * " Country: "+gid.getCountryName() + " Zipcode: "+gid.getZipCode() +
	 * " HeadendName : "+gid.getSelectedProvider() + " : Date is : " +
	 * System.currentTimeMillis());
	 * 
	 * 
	 * } catch (ParseException e) { e.printStackTrace(); logger.error("", e); }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * try { if (validateProgramStartEndTime(ProgramStartEndTimings.get(0),
	 * gridStartTime) <=0 &&
	 * validateProgramStartEndTime(ProgramStartEndTimings.get((
	 * ProgramStartEndTimings.size())-1), gridEndTime) >=0) {
	 * //logger.info("Row Passed: "+channelIndex+" : "+ChannelName);
	 * 
	 * } else { logger.error("Row Failed: "+channelIndex+" : "+ChannelName+" "
	 * +ProgramStartEndTimings.get(0)+"<="+gridStartTime+" :: "
	 * +ProgramStartEndTimings.get((ProgramStartEndTimings.size())-1)+">="+
	 * gridEndTime +" : " + "channel name : "+ ChannelName +
	 * " Country: "+gid.getCountryName() + " Zipcode: "+gid.getZipCode() +
	 * " HeadendName : "+gid.getSelectedProvider() + " : Date is : " +
	 * System.currentTimeMillis()); logger.
	 * error("validateProgramStartEndTime(firstShowCardStartTime,gridStartTime)= "
	 * +validateProgramStartEndTime(ProgramStartEndTimings.get(0),gridStartTime)
	 * +" : " + "channel name : "+ ChannelName + " Country: "+gid.getCountryName() +
	 * " Zipcode: "+gid.getZipCode() + " HeadendName : "+gid.getSelectedProvider() +
	 * " : Date is : " + System.currentTimeMillis());
	 * logger.error("validateProgramStartEndTime(LastShowCardEndTime,gridEndTime)= "
	 * +validateProgramStartEndTime(ProgramStartEndTimings.get((
	 * ProgramStartEndTimings.size())-1),gridEndTime) +" : " + "channel name : "+
	 * ChannelName + " Country: "+gid.getCountryName() +
	 * " Zipcode: "+gid.getZipCode() + " HeadendName : "+gid.getSelectedProvider() +
	 * " : Date is : " + System.currentTimeMillis()); }
	 * 
	 * ProgramStartEndTimings = null; } catch (ParseException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); Assert.fail(e+ " : " +
	 * "channel name : "+ ChannelName + " Country: "+gid.getCountryName() +
	 * " Zipcode: "+gid.getZipCode() + " HeadendName : "+gid.getSelectedProvider() +
	 * " : Date is : " + System.currentTimeMillis()); }
	 * 
	 * channelIndex++; i++; } }
	 * 
	 * /* Commenting the code as now we verify Grid Data using API and not from UI;
	 * hence as per discussion we would just load UI and check for presence of
	 * elements. *
	 * 
	 * public void GridTimeVerificationNow(WebDriver driver,GlobalInputData gid) {
	 * 
	 * logger.info("starting the GridTimeVerificationNow Mehtod");
	 * 
	 * By NewSite_LoadingSpinner = By.xpath("//div[@class='spinner']"); By
	 * NewSite_ChannelNames = By.xpath("//span[@class='channel-card__callSign']");
	 * 
	 * By NewSite_ShowCardDetail = By.xpath("//div[@class='show-detail-card']");
	 * 
	 * WebDriverWait wait = new WebDriverWait(driver, 10);;
	 * 
	 * 
	 * 
	 * wait.until(ExpectedConditions.invisibilityOfElementLocated(
	 * NewSite_LoadingSpinner));
	 * 
	 * wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	 * NewSite_ChannelNames)); List<WebElement> channelList =
	 * getChannelsOnGrid(driver);
	 * 
	 * 
	 * 
	 * String gridStartTime = gridStartTime(driver); String gridEndTime =
	 * gridEndTime(driver);
	 * 
	 * logger.warn("Grid Start time is :: "+gridStartTime +
	 * " -- Grid End Time is :: " + gridEndTime);
	 * 
	 * try { Thread.sleep(2000); } catch (InterruptedException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); } try{
	 * 
	 * 
	 * for (int i = 0; i < channelList.size(); i++) {
	 * 
	 * try { logger.info(" Date time is " + System.currentTimeMillis());
	 * Thread.sleep(2000); List<WebElement> showCards =
	 * driver.findElements(By.xpath("(//div[contains(@class,'channel-listings')])["
	 * + (i + 1) + "]/div"));
	 * 
	 * wea.click(showCards.get(0)); Thread.sleep(2000); String
	 * firstShowCardStartTime =
	 * driver.findElement(By.xpath("//div[@class='show-detail-card_timeStartEnd']"))
	 * .getAttribute("innerHTML").split("--")[0];
	 * logger.info("Showcard size is : "+showCards.size());
	 * 
	 * if(showCards.size()>1) { Thread.sleep(1000);
	 * wea.click(getShowcardCloseBtn(driver)); Thread.sleep(2000);
	 * wea.click(showCards.get(showCards.size()-1)); }
	 * 
	 * Thread.sleep(2000); String LastShowCardEndTime =
	 * driver.findElement(By.xpath("//div[@class='show-detail-card_timeStartEnd']"))
	 * .getAttribute("innerHTML").split("--")[1];
	 * wea.click(getShowcardCloseBtn(driver));
	 * 
	 * 
	 * if (compareTime(firstShowCardStartTime, gridStartTime) <= 0 &&
	 * compareTime(LastShowCardEndTime, gridEndTime) >= 0) {
	 * logger.info("Row Passed: " + i + " : " + channelList.get(i).getText());
	 * 
	 * } else { logger.error("Row Failed: " + i + " : " +
	 * channelList.get(i).getText() + " " + firstShowCardStartTime + "<=" +
	 * gridStartTime + " :: " + LastShowCardEndTime + ">=" + gridEndTime);
	 * logger.error("compareTimes(firstShowCardStartTime,gridStartTime)=" +
	 * compareTimes(firstShowCardStartTime, gridStartTime));
	 * logger.error("compareTimes(LastShowCardEndTime,gridEndTime)=" +
	 * compareTimes(LastShowCardEndTime, gridEndTime));
	 * Assert.fail("channel name : "+ channelList.get(i).getText() +
	 * " Country: "+gid.getCountryName() + " Zipcode: "+gid.getZipCode() +
	 * " HeadendName : "+gid.getSelectedProvider() + " : Date is : " +
	 * System.currentTimeMillis()); } } catch (TimeoutException e) {
	 * 
	 * logger.error("Row Failed: " + i + " : " + channelList.get(i).getText() +
	 * " Show Card Timings are Missing",e); Assert.fail(e+": "+" channel name : "+
	 * channelList.get(i).getText() + " Country: "+gid.getCountryName() +
	 * " Zipcode: "+gid.getZipCode() + " HeadendName : "+gid.getSelectedProvider() +
	 * " : Date is : " + System.currentTimeMillis() +
	 * " Show Card Timings are Missing"); } catch (ParseException e) { // TODO
	 * Auto-generated catch block e.printStackTrace();
	 * Assert.fail(e+": "+" channel name : "+ channelList.get(i).getText() +
	 * " Country: "+gid.getCountryName() + " Zipcode: "+gid.getZipCode() +
	 * " HeadendName : "+gid.getSelectedProvider() + " : Date is : " +
	 * System.currentTimeMillis() + " Show Card Timings are Missing"); } } }
	 * catch(Exception e) { logger.error("Error while TV Grid" +
	 * " Country: "+gid.getCountryName() + " Zipcode: "+gid.getZipCode() +
	 * " HeadendName : "+gid.getSelectedProvider() + " : Date is : " +
	 * System.currentTimeMillis(),e); Assert.fail("Error while TV Grid"
	 * +" Country: "+gid.getCountryName() + " Zipcode: "+gid.getZipCode() +
	 * " HeadendName : "+gid.getSelectedProvider() + " : Date is : " +
	 * System.currentTimeMillis(),e); }
	 * 
	 * }
	 */

	/*
	 * 
	 * As we are not using Data validation anymore
	 * 
	 * private int compareTime(String date1, String date2) throws ParseException {
	 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm"); Date d1 =
	 * format.parse(date1); Date d2 = format.parse(date2);
	 * 
	 * //System.out.println(d1.toString()+" compare to "+d2.toString()); return
	 * d1.compareTo(d2); }
	 * 
	 * private ArrayList<String> getTimes(String expression) { String[]
	 * split1=expression.split("-->"); ArrayList<String> retVal=new
	 * ArrayList<String>();
	 * 
	 * for(String str:split1) { String val=str.split("<!--")[0].replace("\n","");
	 * if(val.contains(":")) { retVal.add(val); } }
	 * 
	 * return retVal; }
	 */
	/*
	 * 
	 * As we are not using Data validation anymore
	 * 
	 * private int compareShowcardTimeSequence(String date1,String date2) throws
	 * ParseException { SimpleDateFormat format = new
	 * SimpleDateFormat("dd/MM/YYYY HH:mm a"); int date1Hours =
	 * Integer.parseInt(date1.split(":")[0]); int date1Mins =
	 * Integer.parseInt(date1.split(":")[1].split(" ")[0]);
	 * 
	 * int date2Hours = Integer.parseInt(date2.split(":")[0]); int date2Mins =
	 * Integer.parseInt(date2.split(":")[1].split(" ")[0]);
	 * 
	 * if (date1.contains("pm") && date1Hours<12) { date1Hours = date1Hours + 12; }
	 * 
	 * if (date2.contains("pm") && date2Hours<12) { date2Hours = date2Hours + 12; }
	 * 
	 * if (date1.contains("am") && date1Hours==12) { date1Hours = date1Hours - 12; }
	 * 
	 * if (date2.contains("am") && date2Hours==12) { date2Hours = date2Hours - 12; }
	 * 
	 * Calendar calendar1 = Calendar.getInstance();
	 * calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH),
	 * calendar1.get(Calendar.DATE), date1Hours, date1Mins,00);
	 * 
	 * if(date1.contains("pm") && date2.contains("am")) {
	 * calendar1.add(Calendar.DATE, -1); }
	 * 
	 * //System.out.println("Calendar 1 Time: " + calendar1.getTime()); Calendar
	 * calendar2 = Calendar.getInstance();
	 * calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH),
	 * calendar2.get(Calendar.DATE), date2Hours, date2Mins,00);
	 * 
	 * 
	 * //System.out.println("Calendar 2 Time: " + calendar2.getTime());
	 * //logger.info(calendar1.getTimeInMillis()+" "+calendar2.getTimeInMillis());
	 * 
	 * 
	 * if(date1.equalsIgnoreCase(date2)) { Date
	 * d1=format.parse(format.format(calendar1.getTime())); Date
	 * d2=format.parse(format.format(calendar2.getTime()));
	 * 
	 * //System.out.println("Date 1 Time: " + d1.toString());
	 * //System.out.println("Date 2 Time: " + d2.toString());
	 * 
	 * return d1.compareTo(d2); } else { return calendar1.compareTo(calendar2); } }
	 */

	/*
	 * 
	 * As we are not using Data validation anymore
	 * 
	 * public int validateProgramStartEndTime(String date1, String date2) throws
	 * ParseException { SimpleDateFormat format = new
	 * SimpleDateFormat("dd/MM/YYYY HH:mm a"); int date1Hours =
	 * Integer.parseInt(date1.split(":")[0]); int date1Mins =
	 * Integer.parseInt(date1.split(":")[1].split(" ")[0]);
	 * 
	 * int date2Hours = Integer.parseInt(date2.split(":")[0]); int date2Mins =
	 * Integer.parseInt(date2.split(":")[1].split(" ")[0]);
	 * 
	 * if (date1.contains("pm") && date1Hours < 12) { date1Hours = date1Hours + 12;
	 * }
	 * 
	 * if (date2.contains("pm") && date2Hours < 12) { date2Hours = date2Hours + 12;
	 * }
	 * 
	 * if (date1.contains("am") && date1Hours == 12) { date1Hours = date1Hours - 12;
	 * }
	 * 
	 * if (date2.contains("am") && date2Hours == 12) { date2Hours = date2Hours - 12;
	 * }
	 * 
	 * 
	 * Calendar calendar1 = Calendar.getInstance();
	 * calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH),
	 * calendar1.get(Calendar.DATE), date1Hours, date1Mins, 00);
	 * 
	 * if (date1.contains("PM") && date2.contains("AM") &&
	 * (Math.abs(date2Hours-date1Hours))>12) { calendar1.add(Calendar.DATE, -1); }
	 * 
	 * //System.out.println("Calendar 1 Time: " + calendar1.getTime()); Calendar
	 * calendar2 = Calendar.getInstance();
	 * calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH),
	 * calendar2.get(Calendar.DATE), date2Hours, date2Mins, 00);
	 * 
	 * 
	 * //System.out.println("Calendar 2 Time: " + calendar2.getTime());
	 * //System.out.println(calendar1.getTimeInMillis() + " " +
	 * calendar2.getTimeInMillis());
	 * 
	 * 
	 * if (date1.equalsIgnoreCase(date2)) { Date d1 =
	 * format.parse(format.format(calendar1.getTime())); Date d2 =
	 * format.parse(format.format(calendar2.getTime()));
	 * 
	 * //System.out.println("Date 1 Time: " + d1.toString());
	 * //System.out.println("Date 2 Time: " + d2.toString());
	 * 
	 * return d1.compareTo(d2); } else { return calendar1.compareTo(calendar2); } }
	 */

	/*
	 * As we are not using Data validation anymore
	 * 
	 * private int compareTimes(String date1, String date2) throws ParseException {
	 * SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY HH:mm a"); int
	 * date1Hours = Integer.parseInt(date1.split(":")[0]); int date1Mins =
	 * Integer.parseInt(date1.split(":")[1].split(" ")[0]);
	 * 
	 * int date2Hours = Integer.parseInt(date2.split(":")[0]); int date2Mins =
	 * Integer.parseInt(date2.split(":")[1].split(" ")[0]);
	 * 
	 * if (date1.contains("PM") && date1Hours < 12) { date1Hours = date1Hours + 12;
	 * }
	 * 
	 * if (date2.contains("PM") && date2Hours < 12) { date2Hours = date2Hours + 12;
	 * }
	 * 
	 * if (date1.contains("AM") && date1Hours == 12) { date1Hours = date1Hours - 12;
	 * }
	 * 
	 * if (date2.contains("AM") && date2Hours == 12) { date2Hours = date2Hours - 12;
	 * }
	 * 
	 * 
	 * Calendar calendar1 = Calendar.getInstance();
	 * calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH),
	 * calendar1.get(Calendar.DATE), date1Hours, date1Mins, 00);
	 * 
	 * if (date1.contains("PM") && date2.contains("AM") && (Math.abs(date2Hours -
	 * date1Hours)) > 12) { calendar1.add(Calendar.DATE, -1); }
	 * 
	 * //System.out.println("Calendar 1 Time: " + calendar1.getTime()); Calendar
	 * calendar2 = Calendar.getInstance();
	 * calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH),
	 * calendar2.get(Calendar.DATE), date2Hours, date2Mins, 00);
	 * 
	 * 
	 * //System.out.println("Calendar 2 Time: " + calendar2.getTime());
	 * //System.out.println(calendar1.getTimeInMillis() + " " +
	 * calendar2.getTimeInMillis());
	 * 
	 * 
	 * if (date1.equals(date2)) { Date d1 =
	 * format.parse(format.format(calendar1.getTime())); Date d2 =
	 * format.parse(format.format(calendar2.getTime()));
	 * 
	 * //System.out.println("Date 1 Time: " + d1.toString());
	 * //System.out.println("Date 2 Time: " + d2.toString());
	 * 
	 * return d1.compareTo(d2); } else { return calendar1.compareTo(calendar2); } }
	 */

	/*
	 * Method to open every Drop cell and get the hidden date field. We add that
	 * date in List and then iterate over list to find missing or swap slots. as in
	 * compare method we pass start and end time of every drop cell; and data in
	 * list should be in sorted form hence it should always return -1 or 0
	 *
	 * In case there is any swap of data cell we would get value greater than 0 and
	 * that;s when we identify that two program slots are swapped.
	 * 
	 * public void GridTimeChannelSwap(WebDriver driver,GlobalInputData gid) {
	 * 
	 * List<String> showCardTiminngs; By NewSite_LoadingSpinner =
	 * By.xpath("//div[@class='spinner']"); By NewSite_ChannelNames =
	 * By.xpath("//span[@class='channel-card__callSign']");
	 * 
	 * 
	 * WebDriverWait wait = new WebDriverWait(driver, 60);;
	 * wait.until(ExpectedConditions.invisibilityOfElementLocated(
	 * NewSite_LoadingSpinner)); List<WebElement> channelList =
	 * getChannelsOnGrid(driver);
	 * 
	 * wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	 * NewSite_ChannelNames));
	 * 
	 * String gridStartTime = gridStartTime(driver); String gridEndTime =
	 * gridEndTime(driver);
	 * 
	 * logger.info("Grid Start time is :: "+gridStartTime +
	 * " -- Grid End Time is :: " + gridEndTime);
	 * 
	 * int showCardsCount=0;
	 * 
	 * for (int i = 0; i < channelList.size(); i++) {
	 * 
	 * showCardTiminngs = new ArrayList<String>(); try { WebDriverWait showcardwait
	 * = new WebDriverWait(driver, 60);;
	 * showcardwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
	 * "(//div[contains(@class,'channel-listings')])[" + (i + 1) + "]/div")));
	 * List<WebElement> showCards =
	 * driver.findElements(By.xpath("(//div[contains(@class,'channel-listings')])["
	 * + (i + 1) + "]/div"));
	 * 
	 * for(int index=0; index < showCards.size();index++) {
	 * 
	 * wea.click(showCards.get(index));
	 * showcardwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
	 * "//div[@class='show-detail-card_timeStartEnd']")));
	 * 
	 * String [] singleShowCardTiminngs =
	 * driver.findElement(By.xpath("//div[@class='show-detail-card_timeStartEnd']"))
	 * .getAttribute("innerHTML").split("--");
	 * 
	 * showCardTiminngs.add(singleShowCardTiminngs[0]);
	 * showCardTiminngs.add(singleShowCardTiminngs[1]);
	 * wea.click(getShowcardCloseBtn(driver)); }
	 * 
	 * showCardsCount = showCardsCount+showCards.size();
	 * 
	 * for (int k = 1; k < showCardTiminngs.size(); k++) { if
	 * (compareTime(showCardTiminngs.get(k-1),showCardTiminngs.get(k)) <= 0) {
	 * logger.info("Row Passed: " + i + " : " + channelList.get(i).getText());
	 * 
	 * } else { logger.error("Row Failed: " + i + " : " +
	 * channelList.get(i).getText() + " Data in time list is :: " +
	 * showCardTiminngs); logger.error(("channel name : "+
	 * channelList.get(i).getText() + " Country: "+gid.getCountryName() +
	 * " Zipcode: "+gid.getZipCode() + " HeadendName : "+gid.getSelectedProvider() +
	 * " : Date is : " + System.currentTimeMillis()));
	 * Assert.fail("channel name : "+ channelList.get(i).getText() +
	 * " Country: "+gid.getCountryName() + " Zipcode: "+gid.getZipCode() +
	 * " HeadendName : "+gid.getSelectedProvider() + " : Date is : " +
	 * System.currentTimeMillis()); } } } catch (TimeoutException e) {
	 * 
	 * logger.error("Row Failed: " + i + " : " + channelList.get(i).getText() +
	 * " Show Card Timings are Missing",e); Assert.fail(e+": "+" channel name : "+
	 * channelList.get(i).getText() + " Country: "+gid.getCountryName() +
	 * " Zipcode: "+gid.getZipCode() + " HeadendName : "+gid.getSelectedProvider() +
	 * " : Date is : " + System.currentTimeMillis() +
	 * " Show Card Timings are Missing"); } catch (Exception e) { // TODO
	 * Auto-generated catch block logger.error("Row Failed: " + i + " : " +
	 * channelList.get(i).getText() + " Show Card Timings are Missing",e);
	 * Assert.fail(e+": "+" channel name : "+ channelList.get(i).getText() +
	 * " Country: "+gid.getCountryName() + " Zipcode: "+gid.getZipCode() +
	 * " HeadendName : "+gid.getSelectedProvider() + " : Date is : " +
	 * System.currentTimeMillis() + " Show Card Timings are Missing"); } }
	 * 
	 * logger.info(" Country: "+gid.getCountryName() + " Zipcode: "+gid.getZipCode()
	 * + " HeadendName : "+gid.getSelectedProvider() +
	 * "Total showcards we clicked in current headend are :: " + showCardsCount);
	 * 
	 * }
	 */

	/*
	 * Validating the each drop cell in entire TV Grid are expandabale by user
	 */
}
