package com.gracenote.TestCases;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Locators.IndexPageLocators;
import com.gracenote.Utilities.ConfigureTestCaseName;
import com.gracenote.Utilities.Screenshot;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.constants.IConstants;
import com.gracenote.pages.ChannelListOnPincode;
import com.gracenote.pages.GenereFilters;
import com.gracenote.pages.GridPreferences;
import com.gracenote.pages.IndexPage;
import com.gracenote.pages.ProgramShowCard;
import com.gracenote.pages.SignupLogin;
import com.gracenote.resources.WebDriverSetup;

public class UpdatedSprint69 {

	private static Logger logger = Logger.getLogger(UpdatedSprint69.class);
	
	@Test
	public void verifyGridPreferencesHoursDisplay()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		GlobalInputData					gid				=	new GlobalInputData();
		GridPreferences          		gp				=   new GridPreferences();

		try
		{	

	//		driver 	= 	ds.getDriver(testCaseName, gap.getgap.getBrowser()(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());
			
			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());

			//open grid preferences 
			gp.openGridPreference(driver, testCaseName);

			//select no. of hours displayed as 3-hours
			gp.selectThreeHoursdisplay(driver, testCaseName);

			//save grid preferences
			gp.SaveGridPrefernce(driver, testCaseName);

			//getting currently displayed numbers of grid header item
			List<WebElement> threeHoursList = gp.getGridHeaderTotalItems(driver, testCaseName);
			
			//open grid preferences 
			gp.openGridPreference(driver, testCaseName);
					
			//select no. of hours displayed as 6-hours
			gp.selectSixHoursdisplay(driver, testCaseName);
			
			//save grid preferences
			gp.SaveGridPrefernce(driver, testCaseName);

			//getting currently displayed numbers of grid header item
			List<WebElement> sixHoursList = gp.getGridHeaderTotalItems(driver, testCaseName);
			
			//verifying 3hours Grid preference display
			int totalItems=(threeHoursList.size())-3;
			logger.info("TotalGridItems:"+totalItems+" "+"selectedGridPreferences:"+"3");
			Assert.assertEquals(totalItems, 3);
			
			//verifying 3hours Grid preference display
			totalItems=(sixHoursList.size())-6;
			logger.info("TotalGridItems:"+totalItems+" "+"selectedGridPreferences:"+"6");
			Assert.assertEquals(totalItems, 6);

		}
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}



	@Test
	public void verifyTimeSettingWithChangingDate()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		GlobalInputData					gid				=	new GlobalInputData();

		try
		{	

			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());

			//setting time
			String selectedTime = ip.jumpToTimeOption(driver, testCaseName, gid.getJumpToTime());

			//changing the date
			String selectedDate = ip.jumpToDateOption(driver, testCaseName, gid.getJumpToDate());

			//getting currently displayed time
			String currentTime	= ip.getCurrentTime(driver, testCaseName);

			//getting current date
			String currentDate	= ip.getCurrentDate(driver, testCaseName);

			//verifying time
			System.out.println("currentTime"+currentTime+"selectedTime"+selectedTime);
			Assert.assertEquals(currentTime, selectedTime);

			//verifying date
			System.out.print("selectedDate"+selectedDate+"currentDate"+currentDate);
			Assert.assertEquals(selectedDate, currentDate);

		}
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test
	public void verifyTimeSettingWithPrint()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		GlobalInputData					gid				=	new GlobalInputData();

		try
		{	

			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());

			//setting time
			String selectedTime = ip.jumpToTimeOption(driver, testCaseName, gid.getJumpToTime());

			//clicking on print button
			ip.clickOnPrintButton(driver, testCaseName);
			Thread.sleep(4000);
			//getting currently displayed time
			String currentTime	= ip.getCurrentTime(driver, testCaseName);

			Assert.assertEquals(currentTime, selectedTime);

		}
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}


	@Test
	public void verifyTimeSettingWithChangingTimezone()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		ChannelListOnPincode			clp				= 	new ChannelListOnPincode();
		GlobalInputData					gid				=	new GlobalInputData();

		try
		{	

			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());

			//setting time
			String selectedTime = ip.jumpToTimeOption(driver, testCaseName, gid.getJumpToTime());

			//changing timezone
			clp.OpenPopUpforZipCode(driver, testCaseName);

			clp.oneOfDefaultTimezone(driver, testCaseName, "CAN", gid.getTimeZone());

			//getting currently displayed time
			String currentTime	= ip.getCurrentTime(driver, testCaseName);

			Assert.assertEquals(currentTime, selectedTime);

		}
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test
	public void verifyTimeSettingWithOverviewPage()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		ProgramShowCard					psc				=	new ProgramShowCard();
		GlobalInputData					gid				=	new GlobalInputData();

		try
		{	

			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());

			//setting time
			String selectedTime = ip.jumpToTimeOption(driver, testCaseName, gid.getJumpToTime());

			//click on any random show card
			ip.openRandomShowOverviewPage(driver, testCaseName);

			//clicking on back to grid
			psc.backToGrid(driver, testCaseName);

			//getting currently displayed time
			String currentTime	= ip.getCurrentTime(driver, testCaseName);

			Assert.assertEquals(currentTime, selectedTime);


		}
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test
	public void verifyTimeSettingWithFavouriteChannels()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();
		SignupLogin						sl				=	new SignupLogin();
		GlobalInputData					gid				=	new GlobalInputData();
		GenereFilters					gf				=	new GenereFilters();		
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();

		try
		{	

			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());

			//login
			ip.ClickOnSignupLogin(driver, testCaseName);
			gid.setUserEmail("testuser@testuser.com");
			gid.setPassword("testuser");
			sl.login(driver, testCaseName, gid);

			//setting time
			String selectedTime = ip.jumpToTimeOption(driver, testCaseName, gid.getJumpToTime());

			//loading starred channels only
			gf.showStarredChannels(driver, testCaseName);

			//getting currently displayed time
			String currentTime	= ip.getCurrentTime(driver, testCaseName);

			Assert.assertEquals(currentTime, selectedTime);

		}
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}


	@Test
	public void verifyTimeSettingWithGenreFilter()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();
		GlobalInputData					gid				=	new GlobalInputData();
		GenereFilters					gf				=	new GenereFilters();		
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		String 							currentTime		=	"";
		try
		{	

			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());

			//setting time
			String selectedTime = ip.jumpToTimeOption(driver, testCaseName, gid.getJumpToTime());

			//loading family channels only
			gf.showFamilyChannels(driver, testCaseName, gid);

			//getting currently displayed time
			currentTime	= ip.getCurrentTime(driver, testCaseName);

			Assert.assertEquals(currentTime, selectedTime , "After family filter");

			//loading movies channels only
			gf.showMoviesChannels(driver, testCaseName, gid);

			//getting currently displayed time
			currentTime	= ip.getCurrentTime(driver, testCaseName);

			Assert.assertEquals(currentTime, selectedTime, "After movies filter");

			//loading news channels only
			gf.showNewsChannels(driver, testCaseName, gid);

			//getting currently displayed time
			currentTime	= ip.getCurrentTime(driver, testCaseName);

			Assert.assertEquals(currentTime, selectedTime, "After news filter");

			//loading sport channels only
			gf.showSportsChannels(driver, testCaseName, gid);

			//getting currently displayed time
			currentTime	= ip.getCurrentTime(driver, testCaseName);

			Assert.assertEquals(currentTime, selectedTime , "After sports filter");

			//loading sport channels only
			gf.showTalkChannels(driver, testCaseName, gid);

			//getting currently displayed time
			currentTime	= ip.getCurrentTime(driver, testCaseName);

			Assert.assertEquals(currentTime, selectedTime, "After talk filter");

		}
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	@Test
	public void verifyTimeSettingWithShowcard()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();		
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		GlobalInputData					gid				=	new GlobalInputData();

		try
		{	

			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());

			//setting time
			String selectedTime = ip.jumpToTimeOption(driver, testCaseName, gid.getJumpToTime());

			//opening and closing a random show card
			ip.openAndCloseRandomShowcard(driver, testCaseName);

			//getting currently displayed time
			String currentTime	= ip.getCurrentTime(driver, testCaseName);

			Assert.assertEquals(currentTime, selectedTime);

		}
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}

	//verify movie title with year

	@Test
	public void verifyMovieTitle()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		GlobalInputData					gid				=	new GlobalInputData();
		GenereFilters					gf				=	new GenereFilters();
		IndexPage						ip				=	new IndexPage();

		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();

		try
		{	

			//url invoked
			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());

			//filtering movies 
			gf.showMoviesChannels(driver, testCaseName, gid);

			//list of all movies displayed
			List<WebElement> movies	= ip.getAllMovieTitles(driver, testCaseName);
		
			
			logger.info("Total number of Movies:"+movies.size());
			
			for(WebElement movie : movies)
			
			{ 
				
				String	movieTitle	=	movie.getText();

				//getting year from movie title
				String	year	=	movieTitle.substring(movieTitle.indexOf("(")+1).replace(")", "");

				if(year.length() == 4)
				{
					//if its not number then throw exception 
					try {
						Integer.parseInt(year);
						logger.info("Year present in movie title '"+movieTitle+"'");
					}
					catch(Exception e){
						logger.error("Year is not present in movie title shown for movie '"+movieTitle+"'");
						Screenshot.captureScreenshot(driver, testCaseName, "Invalid year persent for title");
						Assert.fail("Invalid year present in title shown for movie '"+movieTitle+"'");
					}
				}
				else
				{
					logger.error("Invalid year present in title shown for movie '"+movieTitle+"'");
					Screenshot.captureScreenshot(driver, testCaseName, "Invalid year persent for title");
					Assert.fail("Invalid year present in title shown for movie '"+movieTitle+"'");
				}
				
				
			
				
			}
			
			
			
		}
			
		
		catch (Exception e)
		{
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}

		finally
		{
			ds.quitDriver(driver, testCaseName);
		}
	}
	//end here
	
	@Test
	public void singleStationLinking()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		GlobalInputData					gid				=	new GlobalInputData();
		GenereFilters					gf				=	new GenereFilters();
		IndexPage						ip				=	new IndexPage();

		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();

		try
		{	

			//url invoked
			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());
			
			//save preferences for Date and Time
			String selectedDate=ip.jumpToDateOption(driver, testCaseName, gid.getJumpToDate());
			String selectedTime=ip.jumpToTimeOption(driver, testCaseName, gid.getJumpToTime());
			logger.info("selectedTime"+selectedTime);
			
			if(selectedTime.contains("Now"))
			{
				selectedDate = "Today";
				logger.info("SelectedDate"+selectedDate);
			}
			
			//click on image icon 
			ip.clickOnIcon(driver, testCaseName);
			
			//validate user is on single station page
			String expectedURL=gid.getSingleStaionURL();
			String actualURL  = driver.getCurrentUrl();
			logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
			
		//	Assert.assertEquals(actualURL, expectedURL);
			
			if(actualURL.contains(expectedURL))
			{
				logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
			}
			else
			{
				logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
				Assert.fail("user is not on single station");
			}
			
			
			//validate grid selected date on single station page
			String actualDate=ip.getSingleStationDate(driver, testCaseName);
		//	Assert.assertEquals(actualDate, selectedDate);
			
			if(actualDate.contains(selectedDate))
			{	
				logger.info("Date is correct in single station"+actualDate+" "+selectedDate);
			}
			else
			{
				logger.info("actualDate"+ actualDate+"selectedDate"+ selectedDate);
				Assert.fail("Date is incorrect in single station");
			}
			
			
			
			
		}
		
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
		
	}
	
	
	@Test
	public void standardGridLinking()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		GlobalInputData					gid				=	new GlobalInputData();
		GenereFilters					gf				=	new GenereFilters();
		IndexPage						ip				=	new IndexPage();

		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();

		try
		{	

			//url invoked
			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());
			
			//save preferences for Date and Time
			String selectedDate=ip.jumpToDateOption(driver, testCaseName, gid.getJumpToDate());
			String selectedTime=ip.jumpToTimeOption(driver, testCaseName, gid.getJumpToTime());
			logger.info("selectedTime"+selectedTime);
			
			//journey from Std grid -> single station -> program overview
			
			if(selectedTime.contains("Now"))
			{
				selectedDate = "Today";
				logger.info("SelectedDate"+selectedDate);
			}
			
			//click on image icon 
			ip.clickOnIcon(driver, testCaseName);
			
			//validate user is on single station page
			Thread.sleep(4000);
			String expectedURL=gid.getSingleStaionURL();
			String actualURL  = driver.getCurrentUrl();
			logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
			
	    	//	Assert.assertEquals(actualURL, expectedURL);
			 if(actualURL.contains(expectedURL))
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
			 }
			 else
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
				 Assert.fail("user is not on single station");
			 }
			 
			 
			
			//validate grid selected date on single station page
			String actualDate=ip.getSingleStationDate(driver, testCaseName);
			if(actualDate.contains(selectedDate))
			{
				logger.info("Date is correct in single station");
			}
			else
			{
				logger.info("actualDate"+ actualDate+"selectedDate"+ selectedDate);
				Assert.fail("Date is incorrect in single station");
			}
			
			
			//Click on anyone show from the list
			ip.clickonSingleStationShow(driver, testCaseName);
			
			//validate user is on overview page 
			String overViewDetails=ip.overviewPageValidate(driver, testCaseName);
			 if(overViewDetails == null)
			 {
				 Assert.fail("user is not on over view page");
				 
			 }
			 else
			 {
				 logger.info("user is on over view page");
			 }
			 
			 
			 
			 
			 //journey from Program overview -> Single station(click on back to Grid)
			 ip.backToStationBtn(driver, testCaseName);
			 
			//validate user is on single station page
			 expectedURL = gid.getSingleStaionURL();
			 actualURL   = driver.getCurrentUrl();
			 
			 if(actualURL.contains(expectedURL))
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
			 }
			 else
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
				 Assert.fail("user is not on single station");
			 }
			 
			 
			
			 //journey from Program overview -> Std Grid

			//Go to single station lineups and  click on anyone show from the list
			ip.clickonSingleStationShow(driver, testCaseName);
			
			//validate user is on overview page 
			 overViewDetails=ip.overviewPageValidate(driver, testCaseName);
			 if(overViewDetails == null)
			 {
				 Assert.fail("user is not on over view page");
				 
			 }
			 else
			 {
				 logger.info("user is on over view page");
			 }
			 
			 
			 
			 //journey from Program overview ->std grid(click on back to home)
			 ip.backToHomeBtn(driver, testCaseName);
			 
			 if(driver.findElement(By.xpath("//button[@class='btn btn-default change-provider-btn']")) != null)
			 {
				 logger.info("user is on standard grid page");
			 }
			 
			 else
			 {
				 Assert.fail("user is not on standard grid page");
			 }
			 
			 
			 
			 
			 
			 //journey from Single station -> Std Grid
			 
			//click on image icon 
			ip.clickOnIcon(driver, testCaseName);
				
			//validate user is on single station page
		    expectedURL=gid.getSingleStaionURL();
			actualURL  = driver.getCurrentUrl();
			logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
			
			if(actualURL.contains(expectedURL))
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
			 }
			 else
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
				 Assert.fail("user is not on single station");
			 }
			
				
		   //Click on anyone show from the list
			ip.clickonSingleStationShow(driver, testCaseName);
				
			//validate user is on overview page 
			 overViewDetails=ip.overviewPageValidate(driver, testCaseName);
			 if(overViewDetails == null)
			 {
				 Assert.fail("user is not on over view page");
					 
			 }
			 else
			 {
				 logger.info("user is on over view page");
			 }
				 
			 //program overview to single station journey(click on station btn)
			 ip.backToStationBtn(driver, testCaseName);
			 
			 //Go to standard grid page 
			 ip.backToHomeBtn(driver, testCaseName);
			 
			 
			 //validate user is on std grid 
			 if(driver.findElement(By.xpath("//button[@class='btn btn-default change-provider-btn']")) !=  null)
			 {
				 logger.info("user is on standard grid product"); 
			 }
			 else
			 {
				 Assert.fail("user is not on standard grid page");
			 }
			 
			 
			
		}
		
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
		
	}
	
	
	
	
	
	
	@Test
	public void singleProviderLinking()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		GlobalInputData					gid				=	new GlobalInputData();
		GenereFilters					gf				=	new GenereFilters();
		IndexPage						ip				=	new IndexPage();

		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();

		try
		{	

			//url invoked
			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getSingleProviderAffiliate());
			
			//save preferences for Date and Time
			String selectedDate=ip.jumpToDateOption(driver, testCaseName, gid.getJumpToDate());
			String selectedTime=ip.jumpToTimeOption(driver, testCaseName, gid.getJumpToTime());
			logger.info("selectedTime"+selectedTime);
			
			//journey from Std grid -> single station -> program overview
			
			if(selectedTime.contains("Now"))
			{
				selectedDate = "Today";
				logger.info("SelectedDate"+selectedDate);
			}
			
			//click on image icon 
			ip.clickOnIcon(driver, testCaseName);
			
			//validate user is on single station page
			Thread.sleep(4000);
			String expectedURL=gid.getSingleStaionURL();
			String actualURL  = driver.getCurrentUrl();
			logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
			
	    	//	Assert.assertEquals(actualURL, expectedURL);
			 if(actualURL.contains(expectedURL))
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
				 
			 }
			 else
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
				 Assert.fail("user is not on single station");
			 }
			 
			 
			
			//validate grid selected date on single station page
			String actualDate=ip.getSingleStationDate(driver, testCaseName);
			if(actualDate.contains(selectedDate))
			{
				logger.info("Date is correct in single station");
			}
			else
			{
				logger.info("actualDate"+ actualDate+"selectedDate"+ selectedDate);
				Assert.fail("Date is incorrect in single station");
			}
			
			
			//Click on anyone show from the list
			ip.clickonSingleStationShow(driver, testCaseName);
			
			//validate user is on overview page 
			String overViewDetails=ip.overviewPageValidate(driver, testCaseName);
			 if(overViewDetails == null)
			 {
				 Assert.fail("user is not on over view page");
				 
			 }
			 else
			 {
				 logger.info("user is on over view page");
			 }
			 
			 
			 
			 
			 //journey from Program overview -> Single station(click on back to Grid)
			 ip.backToStationBtn(driver, testCaseName);
			 
			//validate user is on single station page
			 expectedURL = gid.getSingleStaionURL();
			 actualURL   = driver.getCurrentUrl();
			 
			 if(actualURL.contains(expectedURL))
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
			 }
			 else
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
				 Assert.fail("user is not on single station");
			 }
			 
			 
			
			 //journey from Program overview -> Single provider

			//Go to single station lineups and  click on anyone show from the list
			ip.clickonSingleStationShow(driver, testCaseName);
			
			//validate user is on overview page 
			 overViewDetails=ip.overviewPageValidate(driver, testCaseName);
			 if(overViewDetails == null)
			 {
				 Assert.fail("user is not on over view page");
				 
			 }
			 else
			 {
				 logger.info("user is on over view page");
			 }
			 
			 
			 
			 //journey from Program overview ->single provider(click on back to home)
			 ip.backToHomeBtn(driver, testCaseName);
			 
			 if(driver.findElement(By.xpath("//button[@class='btn btn-default single-change-provider-btn']")) != null)
			 {
				 logger.info("user is on single provider page");
			 }
			 
			 else
			 {
				 Assert.fail("user is not on single provider page");
			 }
			 
			 
			 
			 //journey from Single station -> single provider
			 
			//click on image icon 
			ip.clickOnIcon(driver, testCaseName);
				
			//validate user is on single station page
		    expectedURL=gid.getSingleStaionURL();
			actualURL  = driver.getCurrentUrl();
			logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
			
			if(actualURL.contains(expectedURL))
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
			 }
			 else
			 {
				 logger.info("expectedURL:"+expectedURL+" "+"actualURL"+actualURL);
				 Assert.fail("user is not on single station");
			 }
			
				
		   //Click on anyone show from the list
			ip.clickonSingleStationShow(driver, testCaseName);
				
			//validate user is on overview page 
			 overViewDetails=ip.overviewPageValidate(driver, testCaseName);
			 if(overViewDetails == null)
			 {
				 Assert.fail("user is not on over view page");
					 
			 }
			 else
			 {
				 logger.info("user is on over view page");
			 }
				 
			 //program overview to single station journey(click on back to station)
			 ip.backToStationBtn(driver, testCaseName);
			 
			 //Go to standard grid page 
			 ip.backToHomeBtn(driver, testCaseName);
			 
			 
			 //validate user is on single provider page  
			 if(driver.findElement(By.xpath("//button[@class='btn btn-default single-change-provider-btn']")) != null)
			 {
				 logger.info("user is on single provider page");
			 }
			 
			 else
			 {
				 Assert.fail("user is not on single provider page");
			 }
			 
			 
			 
			
		}
		
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
		
	}
	
	
	@Test
	public void verifyLastJumpToDateAndTime()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		GlobalInputData					gid				=	new GlobalInputData();
		GridPreferences          		gp				=   new GridPreferences();

		try
		{	

			driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());


			//changing the date
			String selectedDate = ip.jumpToLastDateOption(driver, testCaseName, gid.getJumpToLastDate());
			
			//setting time
			String selectedTime = ip.jumpToLastTimeOption(driver, testCaseName, gid.getJumpToLastTime());

			//getting currently displayed time
			String currentTime	= ip.getCurrentTime(driver, testCaseName);

			//getting current date
			String currentDate	= ip.getCurrentDate(driver, testCaseName);
			
			
			//check for 3hrs grid 
			
			//verifying time
			System.out.println("currentTime"+currentTime+"selectedTime"+selectedTime);
			if(currentTime.contains("9:00 PM"))
			{
			logger.info("Current time is in 3hrs grid format");	
			}
			else
			{
			Assert.fail("Current time is incorrect");
			}
			
			//verifying date
			System.out.print("selectedDate"+selectedDate+"currentDate"+currentDate);
			Assert.assertEquals(selectedDate, currentDate);
			
			//verify grid start time
			String time= gp.getGridStartTime(driver, testCaseName);
			logger.info("Gridtime"+time+"selectedTime"+selectedTime);
			if(time.contains("9:00 PM"))
			{
			logger.info("Current time is in 3hrs grid format");	
			}
			else
			{
			Assert.fail("Grid Current time is incorrect");
			}
			
			//check for 6hrs grid 
			gp.openGridPreference(driver, testCaseName);
			gp.selectSixHoursdisplay(driver, testCaseName);
			gp.SaveGridPrefernce(driver, testCaseName);

			//changing the date
			selectedDate = ip.jumpToLastDateOption(driver, testCaseName, gid.getJumpToLastDate());
			
			//setting time
		    selectedTime = ip.jumpToLastTimeOption(driver, testCaseName, gid.getJumpToLastTime());

			//getting currently displayed time
			currentTime	= ip.getCurrentTime(driver, testCaseName);

			//getting current date
			currentDate	= ip.getCurrentDate(driver, testCaseName);
			
			//verifying time
			System.out.println("currentTime"+currentTime+"selectedTime"+selectedTime);
			if(currentTime.contains("6:00 PM"))
			{
			logger.info("Current time is in 6hrs grid format");	
			}
			else
			{
			Assert.fail("Current time is incorrect");
			}
			
			//verifying date
			System.out.print("selectedDate"+selectedDate+"currentDate"+currentDate);
			Assert.assertEquals(selectedDate, currentDate);
			
			//verify grid start time
			time= gp.getGridStartTime(driver, testCaseName);
			logger.info("Gridtime"+time+"selectedTime"+selectedTime);
			if(time.contains("6:00 PM"))
			{
			logger.info("Grid Current time is in 6hrs grid format");	
			}
			else
			{
			Assert.fail("Grid Current time is incorrect");
			}

		}
		catch (AssertionError e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e) {
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} finally {
			ds.quitDriver(driver, testCaseName);
		}
	}
	
	
	
	@Test
	public void verifyStarredChannelFilters()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		GlobalInputData					gid				=	new GlobalInputData();
		GridPreferences          		gp				=   new GridPreferences();
		SignupLogin						sl				=	new SignupLogin();

		try
		{	

		driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());
		
		//login with valid user
		sl.StarredUserlogin(driver, testCaseName, gid);
		
		//check starred channel checkbox is selected in grid preferences 
		gp.openGridPreference(driver, testCaseName);
		gp.selectstarredChannelCheckbox(driver, testCaseName);
		
		//verify starred filter is applied
		WebElement starredelement= driver.findElement(IndexPageLocators.starredFilterElement);
		if(starredelement!=null)
		{
		logger.info("starred filter is applied successfully");
		}
		else
		{
		Assert.fail("Starred filter is not applied successfully");
		}
		
		//logout of application
		// User logout action
		ip.clickUserPorfile(driver, testCaseName);
		ip.logOut(driver, testCaseName);
		
		//login again and validate starred channel filter 
		//login with valid user
		sl.StarredUserlogin(driver, testCaseName, gid);
		//verify starred filter is applied
		starredelement= driver.findElement(IndexPageLocators.starredFilterElement);
		if(starredelement!=null)
		{
		logger.info("starred filter is applied successfully");
		}
		else
		{
		Assert.fail("Starred filter is not applied successfully");
		}
		
		}
		catch (AssertionError e)
		{
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e)
		{
			TestCaseExecutionStatus.markAsFail(testCaseName, e);

		}
		finally 
		{
			ds.quitDriver(driver, testCaseName);
		}
		
	}
	
	
	
	@Test
	public void validateScrollPosition()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		GlobalInputData					gid				=	new GlobalInputData();
		GridPreferences          		gp				=   new GridPreferences();
		SignupLogin						sl				=	new SignupLogin();

		try
		{	

		driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());
		
		//scroll to location
		ip.ScrollToLocation(driver, testCaseName, gid.getScrollPos());
		
		//click on nxt button
		ip.clickNextButton(driver, testCaseName);
		
		//verify scroll position
		Long pos=ip.verifyScrollPosition(driver, testCaseName);
		if(pos==gid.getScrollPos())
		{
		logger.info("Scroll position is"+ pos);
		}
		else
		{
		Assert.fail("Scroll position is not maintained"+pos);
		}
			
		}
		catch (AssertionError e)
		{
		TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e)
		{
		TestCaseExecutionStatus.markAsFail(testCaseName, e);

		}
		finally 
		{
			ds.quitDriver(driver, testCaseName);
		}
		
	}
	
	
	
	
	@Test
	public void validateGridHours()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		GlobalInputData					gid				=	new GlobalInputData();
		GridPreferences          		gp				=   new GridPreferences();


		try
		{	

		driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());
		
		//set grid hours to 6 hrs
		gp.openGridPreference(driver, testCaseName);
		gp.selectSixHoursdisplay(driver, testCaseName);
		gp.SaveGridPrefernce(driver, testCaseName);
		
		//resize browsers to 600*600
		ip.reSizeBrowserWindow(driver, testCaseName, 600, 600);
		
		//validate grid hours diplay
		List<WebElement> gridHoursItem1 = gp.getGridHeaderTotalItems(driver, testCaseName);
		int numberOfGrid=gridHoursItem1.size();
		
		//verify number of grid for 1 hr display
		Assert.assertEquals(numberOfGrid, 2);
		
		//resize browsers to 800*700
		ip.reSizeBrowserWindow(driver, testCaseName, 800, 700);
				
		//validate grid hours diplay
		List<WebElement> gridHoursItem2 = gp.getGridHeaderTotalItems(driver, testCaseName);
		numberOfGrid=gridHoursItem2.size();
		
		//verify number of grid for 2hrs(4 grids) display
		Assert.assertEquals(numberOfGrid, 4);
		
		//resize browser to default size
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		//validate grid hours diplay
		List<WebElement> gridHoursItem3 = gp.getGridHeaderTotalItems(driver, testCaseName);
		numberOfGrid=gridHoursItem3.size();
				
		//verify number of grid for 3hrs(6 grids) display
		Assert.assertEquals(numberOfGrid, 12);
		
			
		}
		catch (AssertionError e)
		{
		TestCaseExecutionStatus.markAsFail(testCaseName, e);

		} catch (Exception e)
		{
		TestCaseExecutionStatus.markAsFail(testCaseName, e);

		}
		finally 
		{
			ds.quitDriver(driver, testCaseName);
		}
		
	}
	
	
	
	
	@Test
	public void validateGoogleLogin()
	{
		WebDriver						driver			=	null;
		WebDriverSetup					ds				=	new WebDriverSetup();
		GeneralApplicaitonProperties	gap				=	new GeneralApplicaitonProperties();
		IndexPage						ip				=	new IndexPage();
		String							testCaseName	=	ConfigureTestCaseName.currentTestCaseName();
		GlobalInputData					gid				=	new GlobalInputData();
		GridPreferences          		gp				=   new GridPreferences();
		SignupLogin						s2				=	new SignupLogin();

		try
		{	

		driver 	= 	ds.getDriver(testCaseName, gap.getBrowser(), gap.getNewSiteURLAffiliates() + gap.getAffiliateURL() + gid.getTvGridAffiliate());
		
		//Click on SignUp dd
		
		 s2.GooglesignUp(driver, testCaseName, gid);
	   
	
	
		
			
		}
		catch (AssertionError e)
		{
		TestCaseExecutionStatus.markAsFail(testCaseName, e);

		}
		
		catch (Exception e)
		{
		TestCaseExecutionStatus.markAsFail(testCaseName, e);

		}
		
		finally 
		{
			ds.quitDriver(driver, testCaseName);
		}
		
	}
	
	
	
	
}
