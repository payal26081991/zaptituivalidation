package com.gracenote.pages;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.Locators.ChannelListOnPincodeLocators;
import com.gracenote.Locators.IndexPageLocators;
import com.gracenote.Locators.ProgramShowCardLocators;
import com.gracenote.Locators.SignupLoginLocators;
import com.gracenote.Utilities.Screenshot;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.constants.IConstants;
import com.mysql.jdbc.Driver;

public class IndexPage {
	private static Logger	logger	= Logger.getLogger(IndexPage.class);

	ChannelListOnPincode	clp		= new ChannelListOnPincode();
	WebElementActions		wea		= WebElementActions.getWebElementActionsObject();

	private List<WebElement> getChannelLCN(WebDriver driver, String testCaseName) {
		new WebDriverWait(driver, 100)
		.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@class='channel-card__channelNum']")));
		List<WebElement> channelLCN = driver.findElements(By.xpath(".//*[@class='channel-card__channelNum']"));
		return channelLCN;
	}

	public WebElement getSkipTour(WebDriver driver, String testCaseName) {
		WebElement skipTour = driver.findElement(IndexPageLocators.Button_Skip_Tour);
		return skipTour;
	}

	private List<WebElement> getChannelCallSign(WebDriver driver, String testCaseName) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@class='channel-card__callSign']")));
		List<WebElement> channelcallSign = driver.findElements(By.xpath(".//*[@class='channel-card__callSign']"));
		return channelcallSign;
	}

	private WebElement getHadendNameWebElement(WebDriver driver, String testCaseName) {

		return new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'preferences-bar')]/h3")));
	}

	private List<WebElement> jumpToDateDropDownOptions(WebDriver driver, String testCaseName) {
		new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Dropdown_Jump_To_Date));

		List<WebElement> jumpToDateOptions = driver.findElements(IndexPageLocators.DropdownList_Jump_To_Date);
		return jumpToDateOptions;
	}

	private List<WebElement> jumpToTimeDropDownOptions(WebDriver driver, String testCaseName) {

		//List<WebElement> jumpToDateOptions = 	new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Dropdown_Jump_To_Time));
		List<WebElement> jumpToDateOptions = driver.findElements(IndexPageLocators.DropdownList_Jump_To_Time);
		return jumpToDateOptions;
	}

	private WebElement signupLoginBtn(WebDriver driver, String testCaseName) {
		WebElement signupLoginBtnUI = null;

		try {
			new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SignupLoginLocators.Button_Sign_Up));

			signupLoginBtnUI = driver.findElement(SignupLoginLocators.Button_Sign_Up);

		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return signupLoginBtnUI;
	}

	public WebElement logoutBtn(WebDriver driver, String testCaseName) {
		WebElement logoutbtn = null;
		try {
			logoutbtn = driver.findElement(IndexPageLocators.Link_Sign_Out_User_Action);

		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return logoutbtn;
	}

	private WebElement userProfileLink(WebDriver driver, String testCaseName) {
		WebElement UserProfilebtn = null;
		try {
			logger.warn("waiting for Element to be clickable");

			new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(IndexPageLocators.Dropdown_User_Profile));

			UserProfilebtn = driver.findElement(IndexPageLocators.Dropdown_User_Profile);

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return UserProfilebtn;
	}

	public WebElement getContactUS(WebDriver driver, String testCaseName) {
		WebElement ContactUs = null;

		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Label_Contact_Us));
			ContactUs = driver.findElement(IndexPageLocators.Label_Contact_Us);
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail("Error while getting Contact us Web Element", e);
		}
		return ContactUs;
	}

	public WebElement getCopyright(WebDriver driver, String testCaseName) {
		WebElement Copyright = null;

		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Label_Copyright));
			Copyright = driver.findElement(IndexPageLocators.Label_Copyright);
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail("Error while getting Copyrioght Web Element", e);
		}
		return Copyright;
	}

	public String getPoweredBy(WebDriver driver, String testCaseName) {
		WebElement	Powered_1	= null;
		String		PoweredBy	= "";

		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Label_PoweredBy));
			Powered_1	= driver.findElement(IndexPageLocators.Label_PoweredBy);

			PoweredBy	= Powered_1.getText();
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail("Error while getting Powered By Web Element", e);
		}
		return PoweredBy;
	}

	public String getPoweredByLink(WebDriver driver, String testCaseName) {
		WebElement	Powered_URL		= null;
		String		Powered_URLtext	= "";

		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Link_PoweredBy));
			Powered_URL		= driver.findElement(IndexPageLocators.Link_PoweredBy);
			Powered_URLtext	= Powered_URL.getAttribute("href");
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail("Error while getting Powered By Web Element hyper link", e);
		}
		return Powered_URLtext;
	}

	public WebElement getTVListingLink(WebDriver driver, String testCaseName) {
		WebElement TVListing = null;

		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Link_Tv_Listings));
			TVListing = driver.findElement(IndexPageLocators.Link_Tv_Listings);
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail("Error while getting TV Listing Web Element", e);
		}
		return TVListing;
	}

	public WebElement getTVListingByNumberLink(WebDriver driver, String testCaseName) {
		WebElement TVListingByNo = null;

		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Link_Tv_By_The_Numbers));
			TVListingByNo = driver.findElement(IndexPageLocators.Link_Tv_By_The_Numbers);
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail("Error while getting TV Listing by numbers Web Element", e);
		}
		return TVListingByNo;
	}

	public WebElement jumpToTimeElem(WebDriver driver, String testCaseName) {
		WebElement jumpToTime = null;

		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Dropdown_Jump_To_Time));

			jumpToTime = driver.findElement(IndexPageLocators.Dropdown_Jump_To_Time);
		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return jumpToTime;
	}

	private WebElement PrimeTimeElement(WebDriver driver, String testCaseName) {
		WebElement primeTime = null;

		try {
			new WebDriverWait(driver, 90).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Dropdown_Jump_To_Time));

			wea.click(jumpToTimeElem(driver, testCaseName));
			primeTime = driver.findElement(IndexPageLocators.Dropdown_Option_Primetime);

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return primeTime;
	}

	private WebElement getGridStartTime(WebDriver driver, String testCaseName) {
		WebElement GridStartTime = null;

		try {
			GridStartTime = driver.findElement(IndexPageLocators.Button_Grid_Start_Time);
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail("Error which getting TV Grid Time Bar Start time Web Element", e);
		}

		return GridStartTime;
	}

	private List<WebElement> getTrackerScripts(WebDriver driver, String testCaseName) {
		List<WebElement> ScriptElement = null;

		try {
			new WebDriverWait(driver, 50)
			.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[contains(@class,'change-provider-btn')]")));
			// Thread.sleep(1000);
			ScriptElement = driver.findElements(By.xpath("/html/head/script"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error while getting script tags from website page", e);
		}

		return ScriptElement;
	}

	public List<String> getChannels(WebDriver driver, String testCaseName) {
		new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ChannelListOnPincodeLocators.Text_Channel_Name));

		List<WebElement>	ChannelNamesWebElements	= driver.findElements(ChannelListOnPincodeLocators.Text_Channel_Name);

		List<String>		ChannelNames			= new ArrayList<String>();

		for (WebElement elm : ChannelNamesWebElements) {
			ChannelNames.add(elm.getText());
		}
		return ChannelNames;
	}

	public List<String> getLCNText(WebDriver driver, String testCaseName) {
		List<WebElement>	channelLCN		= getChannelLCN(driver, testCaseName);

		List<String>		channelLcnText	= new ArrayList<>();

		for (WebElement elm : channelLCN) {
			channelLcnText.add(elm.getText());
		}

		return channelLcnText;
	}

	public void ClickOnSignupLogin(WebDriver driver, String testCaseName) {
		try {
			wea.click(signupLoginBtn(driver, testCaseName));

		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public void clickUserPorfile(WebDriver driver, String testCaseName) {
		try {
			// wea.click(getSkipTour(driver));
			wea.click(userProfileLink(driver, testCaseName));

		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public void logOut(WebDriver driver, String testCaseName) {
		try {
			wea.click(logoutBtn(driver, testCaseName));
			clp.gridLoadingTimeWait(driver, testCaseName);

		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public List<String> getCallSignText(WebDriver driver, String testCaseName) {
		List<WebElement>	channelLCN		= getChannelCallSign(driver, testCaseName);

		List<String>		channelLcnText	= new ArrayList<>();

		for (WebElement elm : channelLCN) {
			channelLcnText.add(elm.getText());
		}

		return channelLcnText;
	}

	public List<WebElement> getJumpToDateDropDownValues(WebDriver driver, String testCaseName) {
		try {
			wea.click(getSkipTour(driver, testCaseName));
		} catch (Exception e) {

			e.printStackTrace();
		}
		List<WebElement> JumpToDateDropDownValues = new ArrayList<WebElement>();
		try {
			JumpToDateDropDownValues = jumpToDateDropDownOptions(driver, testCaseName);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed while getting jump to date options", e);
		}
		return JumpToDateDropDownValues;
	}

	public List<WebElement> getJumpToTimeDropDownValues(WebDriver driver, String testCaseName) {
		try {
			wea.click(getSkipTour(driver, testCaseName));

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<WebElement> JumpToTimeDropDownValues = new ArrayList<WebElement>();
		try {

			JumpToTimeDropDownValues = jumpToTimeDropDownOptions(driver, testCaseName);

		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return JumpToTimeDropDownValues;
	}

	public String jumpToDateOption(WebDriver driver , String testCaseName , String optionNumber) throws InterruptedException
	{	
		Thread.sleep(3000);
		try
		{	
			//clicking on button to make dropdown list available
			WebElement mainButton	=	new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(IndexPageLocators.Dropdown_Jump_To_Date));
			wea.clickJs(driver, mainButton);
			
			//clicking on nth option from list
			String 		jumpToDateXpath		=	"(//*[@class='jump-to-filters']/div[contains(@class,'jump-to-date')]/ul[@class='dropdown-menu']/li/a)["+optionNumber+"]";
			WebElement	dateOption			=	new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath(jumpToDateXpath)));
			wea.clickJs(driver,dateOption);
			
			Screenshot.captureScreenshot(driver, testCaseName, "After changing date from jump to dropdown ");
			
			return (new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(IndexPageLocators.Dropdown_Jump_To_Date))).getText();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
		Thread.sleep(3000);
		return null;
	}
	
	
	public String jumpToLastDateOption(WebDriver driver , String testCaseName , String optionNumber) throws InterruptedException
	{	
		Thread.sleep(5000);
		try
		{	
			//clicking on button to make dropdown list available
			WebElement mainButton	=	new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(IndexPageLocators.Dropdown_Jump_To_Date));
			wea.clickJs(driver, mainButton);
			
			//clicking on nth option from list
			String 		jumpToDateXpath		=	"(//*[@class='jump-to-filters']/div[contains(@class,'jump-to-date')]/ul[@class='dropdown-menu']/li/a)["+optionNumber+"]";
			WebElement	dateOption			=	new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath(jumpToDateXpath)));
			wea.clickJs(driver,dateOption);
			
			Screenshot.captureScreenshot(driver, testCaseName, "After changing date from jump to dropdown ");
			
			return (new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(IndexPageLocators.Dropdown_Jump_To_Date))).getText();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
		Thread.sleep(3000);
		return null;
	}
	
	
	public String getCurrentTime(WebDriver driver , String testCaseName)
	{
		try
		{	
			Screenshot.captureScreenshot(driver, testCaseName, "Getting current time");
			return (new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(IndexPageLocators.Dropdown_Jump_To_Time))).getText();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
		
		return null;
	}	
	
	
	public String getCurrentDate(WebDriver driver , String testCaseName) throws InterruptedException
	{Thread.sleep(4000);
		try
		{	
			Screenshot.captureScreenshot(driver, testCaseName, "Getting current date");
			return (new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(IndexPageLocators.Dropdown_Jump_To_Date))).getText();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
		
		return null;
	}
	
	public String jumpToTimeOption(WebDriver driver , String testCaseName , String optionNumber) throws InterruptedException
	{	
		Thread.sleep(5000);
		try
		{	
			//clicking on button to make dropdown list available
			WebElement	mainButton	= new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(IndexPageLocators.Dropdown_Jump_To_Time));
			wea.clickJs(driver,mainButton);
			
			//clicking on nth option from list
			String 		jumpToTimeHourXpath = "(//*[@class='jump-to-filters']/div[contains(@class,'jump-to-time')]/ul[@class='dropdown-menu']/li/a)["+optionNumber+"]";
			WebElement	timeOption			=	new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(jumpToTimeHourXpath)));
			wea.clickJs(driver,timeOption);
			
			Screenshot.captureScreenshot(driver, testCaseName, "After changing time from jump to dropdown ");
			
			return (new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(IndexPageLocators.Dropdown_Jump_To_Time))).getText();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
		Thread.sleep(3000);
		return null;
	}
	
	
	public String jumpToLastTimeOption(WebDriver driver , String testCaseName , String optionNumber) throws InterruptedException
	{	
		Thread.sleep(5000);
		try
		{	
			//clicking on button to make dropdown list available
			WebElement	mainButton	= new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(IndexPageLocators.Dropdown_Jump_To_Time));
			wea.clickJs(driver,mainButton);
			
			//clicking on nth option from list
			String 		jumpToTimeHourXpath = "(//*[@class='jump-to-filters']/div[contains(@class,'jump-to-time')]/ul[@class='dropdown-menu']/li/a)["+optionNumber+"]";
			WebElement	timeOption			=	new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(jumpToTimeHourXpath)));
			wea.clickJs(driver,timeOption);
			
			Screenshot.captureScreenshot(driver, testCaseName, "After changing time from jump to dropdown ");
			
			return (new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfElementLocated(IndexPageLocators.Dropdown_Jump_To_Time))).getText();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
		Thread.sleep(3000);
		return null;
	}
	

	public void clickOnPrintButton(WebDriver driver , String testCaseName)
	{
		try
		{	
			wea.clickJs(driver, new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(IndexPageLocators.Text_Menu_Print)));
			Screenshot.captureScreenshot(driver, testCaseName, "After clicking on print button");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
		
	}

	public List<WebElement> getAllMovieTitles(WebDriver driver , String testCasename)
	{
		try {
			
			return new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfAllElementsLocatedBy(IndexPageLocators.All_Movie_details));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCasename, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return null;
	}
	
	public void openRandomShowOverviewPage(WebDriver driver , String testCaseName)
	{
		try
		{	
			List<WebElement> showcards	= new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfAllElementsLocatedBy(IndexPageLocators.All_Show_Cards));
			int randomNumber 			= getRandomNumber();
			
			WebElement randomShowCard = showcards.get(randomNumber);
			randomShowCard.click();
		
			WebElement showTitleLink = new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(IndexPageLocators.Opened_ShowCard));
			wea.clickJs(driver, showTitleLink);
			
			new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(ProgramShowCardLocators.Button_Add_To_My_Shows));
			
			Screenshot.captureScreenshot(driver, testCaseName, "After opening a random show details");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}
	
	public void openAndCloseRandomShowcard(WebDriver driver , String testCaseName)
	{
		try
		{	
			List<WebElement> showcards	= new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfAllElementsLocatedBy(IndexPageLocators.All_Show_Cards));
			int randomNumber 			= getRandomNumber();
			
			WebElement randomShowCard = showcards.get(randomNumber);
			randomShowCard.click();
		
			Screenshot.captureScreenshot(driver, testCaseName, "After opening a random show details");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}
	
	public int getRandomNumber()
	{
		Random	rand			=	new Random();
		int		randomNumber	=	rand.nextInt(30);
		
		if(randomNumber>15)
		{
			return randomNumber;
		}
		else
		{
			getRandomNumber();
		}
		
		return 22;
	}

	public String gridStartTime(WebDriver newSiteDriver, String testCaseName) {

		WebElement gridStartTime = getGridStartTime(newSiteDriver, testCaseName);
		return gridStartTime.getText();
	}

	public void goToPrimeTime(WebDriver driver, String testCaseName) {
		wea.click(PrimeTimeElement(driver, null));
		logger.info("Time changed to PrimeTime");
	}

	public String getHeadendNameNameDeviceType(WebDriver driver, String testCaseName) {
		String		HeadendNameDevice	= "";
		WebElement	SelectHeadend		= getHadendNameWebElement(driver, HeadendNameDevice);
		if (!SelectHeadend.getText().contains("Local Over the Air Broadcast")) {
			HeadendNameDevice = SelectHeadend.getText().split("\"")[0];
			logger.info("Selected Headend Name Device Type and postal Code is :: " + HeadendNameDevice);
		} else
			HeadendNameDevice = SelectHeadend.getText();

		return HeadendNameDevice;
	}

	public String getScriptText(WebDriver driver, String testCaseName) {
		List<WebElement>	scripts		= getTrackerScripts(driver, testCaseName);
		StringBuilder		scriptText	= new StringBuilder();

		for (WebElement script : scripts)
			scriptText = scriptText.append(((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML", script));

		String scriptTextInfo = scriptText.toString();

		return scriptTextInfo;
	}

	public String getTVListingText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(IndexPageLocators.Text_Menu_Tv_Listing).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getMyShowsText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(IndexPageLocators.Text_Menu_My_Shows).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getPrintText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(IndexPageLocators.Text_Menu_Print).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getSearchPlaceholderText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(IndexPageLocators.Text_Search_Placeholder_Text).getAttribute("placeholder");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getSignUpOrLoginButtonText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(IndexPageLocators.Text_Button_SignUp_Or_Login).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getChangeProviderOrTimezoneButtonText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(IndexPageLocators.Text_Button_Change_Provider_Timezone).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getLabelJumpToFilterText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(IndexPageLocators.Label_Jumo_To_Filer).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public List<String> getJumpToDateDropdownText(WebDriver driver, String testCaseName) {
		List<String> s = new LinkedList<String>();

		try {
			wea.click(driver.findElement(IndexPageLocators.Dropdown_Jump_To_Date));
			List<WebElement> ele = driver.findElements(IndexPageLocators.DropdownList_Jump_To_Date);

			for (WebElement e : ele) {
				s.add(e.getText());
			}

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public List<String> getJumpToTimeDropdownText(WebDriver driver, String testCaseName) {
		List<String> s = new LinkedList<String>();

		try {
			wea.click(jumpToTimeElem(driver, testCaseName));

			List<WebElement> ele = driver.findElements(IndexPageLocators.DropdownList_Jump_To_Time);

			for (WebElement e : ele) {
				String text = e.getText().trim();
				if(text.length() <= 0)
				{
					s.add(wea.getTextJs(driver, e));
				}
				else
				{
					s.add(e.getText());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getScrollToTopText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			wea.scrollPageDownByOffset(driver);
			//s = driver.findElement(IndexPageLocators.Label_Scroll_To_Top).getText();
			s = new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(IndexPageLocators.Label_Scroll_To_Top)).getText();
			wea.scrollPageTop(driver);
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getViewLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(IndexPageLocators.Label_View_Single_Station).getText();
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getListLinkText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(IndexPageLocators.Link_List_Single_Station).getText();
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getGridLinkText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(IndexPageLocators.Link_Grid_Single_Station).getText();
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getChannelsLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(IndexPageLocators.Label_Channels_Single_Station).getText();
		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public List<String> getAllHeadersTextSingleStation(WebDriver driver, String testCaseName) {
		List<String>		allHeadersText	= new ArrayList<String>();

		List<WebElement>	elements		= driver.findElements(IndexPageLocators.All_Headers_Single_Station);

		for (WebElement e : elements) {

			String[] s = e.getText().split(",");
			allHeadersText.add(s[0].trim());

		}

		return allHeadersText;
	}

	public void gridLoadingTimeWait(WebDriver driver, String testCaseName) {
		try {
			Thread.sleep(8000);

			Screenshot.captureScreenshot(driver, testCaseName, "Index Page Grid Loading Completed");

			logger.info("Index Page Grid Loading Completed");

		} catch (Exception e) {

			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);

			logger.error("Loader Object went off from DOM");
		}
	}

	public void searchProgram(WebDriver driver, String testCaseName, String programName) throws InterruptedException {

		driver.findElement(IndexPageLocators.Text_Search_Placeholder_Text).sendKeys(programName);

		Thread.sleep(4000);

		Screenshot.captureScreenshot(driver, testCaseName, "After Searching Program");
	}

	public void selectFirstProgramFromSearchList(WebDriver driver, String testCaseName) {

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(IndexPageLocators.First_Program_From_Search_List).click();
		gridLoadingTimeWait(driver, testCaseName);

		Screenshot.captureScreenshot(driver, testCaseName, "After clicking on First Program from Search List");
	}

	/**
	 * @description to fetch all text from Index Page
	 * @param driver
	 * @param testCaseName
	 * @return
	 */
	public List<String> getIndexPageText(WebDriver driver, String testCaseName) {

		logger.info("Reading texts from Index Page");

		List<String> indexPageText = new LinkedList<String>();

		try {

			indexPageText.add(getTVListingText(driver, testCaseName));
			indexPageText.add(getMyShowsText(driver, testCaseName));
			indexPageText.add(getPrintText(driver, testCaseName));
			indexPageText.add(getChangeProviderOrTimezoneButtonText(driver, testCaseName));
			indexPageText.add(getSearchPlaceholderText(driver, testCaseName));
			indexPageText.add(getSignUpOrLoginButtonText(driver, testCaseName));
			indexPageText.add(getLabelJumpToFilterText(driver, testCaseName));
			indexPageText.addAll(getJumpToDateDropdownText(driver, testCaseName));
			indexPageText.addAll(getJumpToTimeDropdownText(driver, testCaseName));
			indexPageText.add(getScrollToTopText(driver, testCaseName));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		logger.info("Index Page text reading Completed.");
		return indexPageText;

	}

	public List<String> getSingleStationPageText(WebDriver driver, String testCaseName) {

		logger.info("Reading texts from Single Station Page");

		List<String> singleStationPageText = new LinkedList<String>();

		singleStationPageText.add(getViewLabelText(driver, testCaseName));
		singleStationPageText.add(getListLinkText(driver, testCaseName));
		singleStationPageText.add(getGridLinkText(driver, testCaseName));
		singleStationPageText.add(getChannelsLabelText(driver, testCaseName));
		singleStationPageText.add(getSearchPlaceholderText(driver, testCaseName));
		singleStationPageText.add(getLabelJumpToFilterText(driver, testCaseName));
		singleStationPageText.addAll(getJumpToDateDropdownText(driver, testCaseName));
		singleStationPageText.addAll(getAllHeadersTextSingleStation(driver, testCaseName));

		logger.info("Single Station Page text reading Completed.");
		return singleStationPageText;
	}
	
	public void clickOnIcon(WebDriver driver, String testCaseName)
	{
		try 
		{
			WebElement showTitleLink = new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(IndexPageLocators.image_icon));
			wea.clickJs(driver, showTitleLink);
			Thread.sleep(5000);
		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		
		
	}
	
	public String getSingleStationDate(WebDriver driver, String testCaseName) {
		String date=" ";
		try
		{
			date= driver.findElement(IndexPageLocators.Dropdown_Jump_To_Date).getText();
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		
		return date;
		
	}
	
	
	public void clickonSingleStationShow(WebDriver driver, String testCaseName)
	{
		try 
		{	
			List<WebElement>	elements		= driver.findElements(By.xpath("(//div[@class='listings-row'])"));

			for(WebElement elem : elements)
			{
				String show= elem.getText();
				if(!show.contains("Paid Programming"))
				{
					WebElement showTitleLink = new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(elem));
					Thread.sleep(2000);
					showTitleLink.click();
					break;
					
				}

			}
			
			//click on show link 
			WebElement showTitleLink = new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(IndexPageLocators.show_title));
			wea.clickJs(driver, showTitleLink);
			Thread.sleep(5000);

		} 
		
		catch (Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		
	}
	
	
	
	public String overviewPageValidate(WebDriver driver, String testCaseName)
	{
		String overviewData=" ";
		try
		{
			overviewData= driver.findElement(IndexPageLocators.overview_details).getText();
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		
		return overviewData;
		
		
		
	}
	
	
	
	public void backToStationBtn(WebDriver driver, String testCaseName)
	{
		
		try
		{
			//click on show link 
			WebElement backtogrid = new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(IndexPageLocators.backtoStationBtn));
			wea.clickJs(driver, backtogrid);
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		
	
		
	}
	
	
	
	public void backToHomeBtn(WebDriver driver, String testCaseName)
	{
		
		try
		{
			//click on show link 
			WebElement backtohome = new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(IndexPageLocators.backtoHomeBtn));
			wea.clickJs(driver, backtohome);
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		
		
	}
	
	
	public void ScrollToLocation(WebDriver driver, String testCaseName, long pos) throws InterruptedException
	{
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0,"+pos+")");
		
		
	}
	
	

	public void clickNextButton(WebDriver driver, String testCaseName)
	{
		
		try
		{
			//click on show link 
			WebElement backtohome = new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(IndexPageLocators.nxtButton));
			wea.clickJs(driver, backtohome);
			Thread.sleep(5000);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		
		
	}
	
	
	
	public Long verifyScrollPosition(WebDriver driver, String testCaseName)
	{
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Long value = (Long) executor.executeScript("return window.pageYOffset;");
		return value;
		
	}
	
	public void reSizeBrowserWindow(WebDriver driver, String testCaseName,int size1, int size2) throws InterruptedException
	{
		 Dimension d = new Dimension(size1,size2);
	     //Resize the current window to the given dimension
	     driver.manage().window().setSize(d);
	     System.out.println(driver.manage().window().getSize());
	     Thread.sleep(2000);
	     
	}
	
	

}
