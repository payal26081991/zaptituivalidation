package com.gracenote.pages;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.Locators.IndexPageLocators;
import com.gracenote.Locators.SignupLoginLocators;
import com.gracenote.Utilities.Screenshot;
import com.gracenote.Utilities.TestCaseExecutionStatus;
import com.gracenote.constants.IConstants;

public class SignupLogin {

	private static Logger	logger	= Logger.getLogger(SignupLogin.class);

	WebElementActions		wea		= WebElementActions.getWebElementActionsObject();
	IndexPage				ip		= new IndexPage();
	ChannelListOnPincode	clp		= new ChannelListOnPincode();
	GridPreferences			gp		= new GridPreferences();

	private WebElement loginField(WebDriver driver, String testCaseName) {
		WebElement logintextbox = driver.findElement(SignupLoginLocators.Textbox_Email);
		return logintextbox;
	}

	private WebElement passwordField(WebDriver driver, String testCaseName) {
		WebElement passwordtextbox = driver.findElement(SignupLoginLocators.Textbox_Password);
		return passwordtextbox;
	}

	private WebElement submitBtn(WebDriver driver, String testCaseName) {
		WebElement SubmitBtn = driver.findElement(SignupLoginLocators.Button_Submit);
		return SubmitBtn;
	}

	private WebElement signUp(WebDriver driver, String testCaseName) {
		new WebDriverWait(driver, 180).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SignupLoginLocators.Button_Sign_Up));
		new WebDriverWait(driver, 180).until(ExpectedConditions.elementToBeClickable(SignupLoginLocators.Button_Sign_Up));
		WebElement signUp = driver.findElement(SignupLoginLocators.Button_Sign_Up);
		return signUp;
	}

	private WebElement forgotPassword(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60)
		        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SignupLoginLocators.Link_Forgot_Your_Password_Login));
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(SignupLoginLocators.Link_Forgot_Your_Password_Login));
		WebElement forgotPassword = driver.findElement(SignupLoginLocators.Link_Forgot_Your_Password_Login);
		return forgotPassword;
	}

	private WebElement signUpLink(WebDriver driver, String testCaseName) {
		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SignupLoginLocators.Link_SignUp));
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(SignupLoginLocators.Link_SignUp));
		WebElement SignupLink = driver.findElement(SignupLoginLocators.Link_SignUp);
		return SignupLink;
	}

	public WebElement getSignedUpUserName(WebDriver driver, String testCaseName) {
		WebElement signedUpUsername = null;

		try {
			new WebDriverWait(driver, 30)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Text_Logged_In_UserName));
			signedUpUsername = driver.findElement(IndexPageLocators.Text_Logged_In_UserName);
		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, "Exception Occured");
		}
		return signedUpUsername;
	}

	public WebElement getUserActionTvListing(WebDriver driver, String testCaseName) {
		WebElement tvListingUserAction = null;

		try {
			new WebDriverWait(driver, 30)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Link_TV_Listing_User_Action));
			tvListingUserAction = driver.findElement(IndexPageLocators.Link_TV_Listing_User_Action);
		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, "Exception Occured");
		}
		return tvListingUserAction;
	}

	public WebElement getUserActionMyShows(WebDriver driver, String testCaseName) {
		WebElement myShowsUserAction = null;

		try {
			new WebDriverWait(driver, 30)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Link_My_Shows_User_Action));
			myShowsUserAction = driver.findElement(IndexPageLocators.Link_My_Shows_User_Action);
		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, "Exception Occured");
		}
		return myShowsUserAction;
	}

	public WebElement getUserActionAccountSettings(WebDriver driver, String testCaseName) {
		WebElement accountSettingsUserAction = null;

		try {
			new WebDriverWait(driver, 30)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(IndexPageLocators.Link_Account_Settings_User_Action));
			accountSettingsUserAction = driver.findElement(IndexPageLocators.Link_Account_Settings_User_Action);
		} catch (Exception e) {

			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, "Exception Occured");
		}
		return accountSettingsUserAction;
	}

	public void login(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {

			wea.clear(loginField(driver, testCaseName));
			wea.setText(loginField(driver, testCaseName), gid.getUserEmail());

			wea.clear(passwordField(driver, testCaseName));
			wea.setText(passwordField(driver, testCaseName), gid.getPassword());

			Screenshot.captureScreenshot(driver, testCaseName, "Before clicking Submit Button");

			wea.click(submitBtn(driver, testCaseName));

			clp.gridLoadingTimeWait(driver, testCaseName);

			Screenshot.captureScreenshot(driver, testCaseName, "After clicking Submit Button");
			// Thread.sleep(5000);

		} catch (Exception e) {
			logger.error("Error while performing Login", e);
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
	}
	
	

	public void StarredUserlogin(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {
			
			wea.click(signUp(driver, testCaseName));
			wea.clear(loginField(driver, testCaseName));
			wea.setText(loginField(driver, testCaseName), gid.getStarredUserEmail());

			wea.clear(passwordField(driver, testCaseName));
			wea.setText(passwordField(driver, testCaseName), gid.getStarredPassword());

			Screenshot.captureScreenshot(driver, testCaseName, "Before clicking Submit Button");

			wea.click(submitBtn(driver, testCaseName));

			clp.gridLoadingTimeWait(driver, testCaseName);

			Screenshot.captureScreenshot(driver, testCaseName, "After clicking Submit Button");
			// Thread.sleep(5000);

		} catch (Exception e) {
			logger.error("Error while performing Login", e);
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
	}

	
	
	public void signUp(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {
			// Thread.sleep(5000);

			wea.click(signUp(driver, testCaseName));

			String	registeredUserEmail	= gid.getSignupUserEmail();
			Date	date				= new Date();
			// wea.click(signUpLink(driver));

			registeredUserEmail.replace("GNIndiaQA", "GNIndiaQA" + date.getTime());

			wea.setText(loginField(driver, testCaseName), registeredUserEmail);
			wea.setText(passwordField(driver, testCaseName), gid.getPassword());

			Screenshot.captureScreenshot(driver, testCaseName, "Before Clicking Login Button");

			wea.click(submitBtn(driver, testCaseName));

			Screenshot.captureScreenshot(driver, testCaseName, "After Clicking Login Button");

			// Thread.sleep(5000);
			logger.info("Our destination URL is :: " + driver.getCurrentUrl());

		} catch (Exception e) {
			logger.error("Error while performing Login", e);
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
	}

	public void forgotPassword(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {
			wea.click(forgotPassword(driver, testCaseName));
			wea.setText(loginField(driver, testCaseName), gid.getSignupUserEmail());
			wea.click(submitBtn(driver, testCaseName));

			// Thread.sleep(5000);

		} catch (Exception e) {
			logger.error("Error While re-setting the password using forgot password ", e);
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
	}

	public String getLoginLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Label_Login).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getConnectWithFacebookButtonText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Button_Connect_With_Facebook).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getOrLabelText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Label_SignUp_Or_Option).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getEmailPlaceholderText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Textbox_Email).getAttribute("placeholder");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getPasswordPlaceholderText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Textbox_Password).getAttribute("placeholder");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getSubmitButtonText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Button_Submit).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getForgotYourPasswordLoginLinkText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Link_Forgot_Your_Password_Login).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getForgotYourPasswordSignupLinkText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Link_Forgot_Your_Password_Signup).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getDontHaveAccountLabelLinkText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Text_Dont_Have_Account).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getSignUpLinkText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Link_SignUp).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getLoginLinkText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Link_LogIn).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getLoginLinkTextPassowrdResetPopup(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Link_LogIn_Password_Reset_Popup).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getAlreadyHaveAccountLabelLinkTextSignupPOpup(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Text_Already_Have_Account_SignUp_Popup).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public String getAlreadyHaveAccountLabelLinkTextPasswordResetPopup(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Text_Already_Have_Account_Password_Reset).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public void closeSignUpPopup(WebDriver driver, String testCaseName) {
		try {
			wea.click(driver.findElement(SignupLoginLocators.Button_Close_SignUp_Popup));
			Thread.sleep(2000);
			logger.info("After Closing SignUp Popup");
			Screenshot.captureScreenshot(driver, testCaseName, "SignUp Popup Closed");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public void openSignUpPopup(WebDriver driver, String testCaseName) {
		try {
			wea.scrollPageTop(driver);
			wea.click(signUp(driver, testCaseName));
			logger.info("After Opening SignUp Popup");
			Screenshot.captureScreenshot(driver, testCaseName, "SignUp Popup Opened");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
	}

	public String getPasswordResetLabelLinkText(WebDriver driver, String testCaseName) {
		String s = null;
		try {
			s = driver.findElement(SignupLoginLocators.Label_Password_Reset).getText();

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	public boolean isLoginLinkDisplayed(WebDriver driver, String testCaseName) {
		boolean link = false;
		try {
			link = wea.isElementDisplayed(driver.findElement(SignupLoginLocators.Link_LogIn));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return link;
	}

	public boolean isSignUpLinkDisplayed(WebDriver driver, String testCaseName) {
		boolean link = false;
		try {
			link = wea.isElementDisplayed(driver.findElement(SignupLoginLocators.Link_SignUp));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return link;
	}

	public boolean isErrorMessageDisplayed(WebDriver driver, String testCaseName) {
		boolean link = false;
		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SignupLoginLocators.Text_Error_Message));
			link = wea.isElementDisplayed(driver.findElement(SignupLoginLocators.Text_Error_Message));

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
				s = driver.findElement(SignupLoginLocators.Text_Error_Message).getText();
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

	public boolean isInfoMessageDisplayed(WebDriver driver, String testCaseName) {
		boolean link = false;
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(SignupLoginLocators.Text_Info_Message));
			link = wea.isElementDisplayed(driver.findElement(SignupLoginLocators.Text_Info_Message));

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return link;
	}

	public String getInfoMessageText(WebDriver driver, String testCaseName) {
		String s = null;
		try {

			Thread.sleep(2000);
			if (isInfoMessageDisplayed(driver, testCaseName)) {
				s = driver.findElement(SignupLoginLocators.Text_Info_Message).getText();
				Screenshot.captureScreenshot(driver, testCaseName, "After Capturing Info Message");
			} else {
				logger.error("Unable to fetch SignUp or Login Info Message");
				Screenshot.captureScreenshot(driver, testCaseName, "Info Message Not Displayed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return s;
	}

	/**
	 * @description to fetch text of entire Signup Module for Multilingual
	 *              validation
	 * @param driver
	 * @param testCaseName
	 * @return List
	 */
	public List<String> getSignUpText(WebDriver driver, String testCaseName) {

		List<String> signUpPopupText = new LinkedList<String>();
		try {

			logger.info("Reading texts from SignUp PopUp");

			String validEmail = null;

			// to open signup popup
			openSignUpPopup(driver, testCaseName);

			// to fetch all text from Login Popuop
			signUpPopupText.addAll(getCommonText(driver, testCaseName));

			if (isSignUpLinkDisplayed(driver, testCaseName)) {
				signUpPopupText.add(getDontHaveAccountLabelLinkText(driver, testCaseName));
				signUpPopupText.add(getSignUpLinkText(driver, testCaseName));
				signUpPopupText.add(getForgotYourPasswordLoginLinkText(driver, testCaseName));
			}

			// to get error message validation text from login popup
			signUpPopupText.addAll(getLoginPopupErrorMessageText(driver, testCaseName));

			// to navigate to SignUp Popup
			wea.click(driver.findElement(SignupLoginLocators.Link_SignUp));
			
			Thread.sleep(2000);

			// to fetch all text from Login Popuop
			signUpPopupText.addAll(getCommonText(driver, testCaseName));

			// to fetch Email already exists Error message.
			if (isLoginLinkDisplayed(driver, testCaseName)) {

				GlobalInputData g3 = new GlobalInputData();
				g3.setUserEmail(RandomStringUtils.random(5, true, true) + "@gmail.com");
				g3.setPassword(RandomStringUtils.random(5, true, true));
				validEmail = g3.getUserEmail();

				login(driver, testCaseName, g3);
				logger.info("New User signedup successfully");

				// to close grid preference popup
	//			gp.closeGridPreferencePopup(driver, testCaseName);

				// User logout action
				ip.clickUserPorfile(driver, testCaseName);
				ip.logOut(driver, testCaseName);

				// trying to sign up with same email
				userSignUp(driver, testCaseName, g3);

				signUpPopupText.add(getErrorMessageText(driver, testCaseName));
				logger.info("Error message fetched for : Email already exists");

				if (isLoginLinkDisplayed(driver, testCaseName)) {
					signUpPopupText.add(getAlreadyHaveAccountLabelLinkTextSignupPOpup(driver, testCaseName));
					signUpPopupText.add(getLoginLinkText(driver, testCaseName));
					signUpPopupText.add(getForgotYourPasswordSignupLinkText(driver, testCaseName));
				}

				// closing Signup popup
				closeSignUpPopup(driver, testCaseName);
			}

			// to open signup popup
			openSignUpPopup(driver, testCaseName);

			// to fetch text from forgot passowrd popup
			signUpPopupText.addAll(getForgotPasswordPopupText(driver, testCaseName));

			// to reset password for valid user

			// to fetch A new password has been emailed to you Error Message
			wea.setText(loginField(driver, testCaseName), validEmail);
			wea.click(submitBtn(driver, testCaseName));
			signUpPopupText.add(getInfoMessageText(driver, testCaseName));
			wea.click(driver.findElement(SignupLoginLocators.Button_Close_Error_Message));
			logger.info("Error message fetched for : A new password has been emailed to you");

			// closing Signup popup
			closeSignUpPopup(driver, testCaseName);

			logger.info("SignUp Popup text reading Completed.");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return signUpPopupText;
	}

	/**
	 * @Description to signup new user
	 * @param driver
	 * @param testCaseName
	 * @param gid
	 */
	public void userSignUp(WebDriver driver, String testCaseName, GlobalInputData gid) {
		openSignUpPopup(driver, testCaseName);

		wea.click(driver.findElement(SignupLoginLocators.Link_SignUp));

		login(driver, testCaseName, gid);
	}

	/**
	 * @description to fetch all validation message text from login popup
	 * @param driver
	 * @param testCaseName
	 * @return
	 */
	private List<String> getLoginPopupErrorMessageText(WebDriver driver, String testCaseName) {

		List<String> errorText = new LinkedList<String>();

		try {

			/** to fetch Both fields are required Error messages **/

			wea.click(submitBtn(driver, testCaseName));
			errorText.add(getErrorMessageText(driver, testCaseName));
			logger.info("Error message fetched for : Both fields are required");

			/** to fetch Email must be a valid email address Error message **/

			GlobalInputData g1 = new GlobalInputData();
			g1.setUserEmail(RandomStringUtils.random(5, true, true));
			g1.setPassword(RandomStringUtils.random(5, true, true));

			login(driver, testCaseName, g1);

			errorText.add(getErrorMessageText(driver, testCaseName));
			logger.info("Error message fetched for : Email must be a valid email address");

			/** to fetch Incorrect username and/or password Error message **/

			GlobalInputData g2 = new GlobalInputData();
			g2.setUserEmail(RandomStringUtils.random(5, true, true) + "@gmail.com");
			g2.setPassword(RandomStringUtils.random(5, true, true));

			login(driver, testCaseName, g2);
			errorText.add(getErrorMessageText(driver, testCaseName));

			logger.info("Error message fetched for : Incorrect username and/or password");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}

		return errorText;
	}

	/**
	 * @description to fetch text from Forgot password popup
	 * @param driver
	 * @param testCaseName
	 * @return
	 */
	private List<String> getForgotPasswordPopupText(WebDriver driver, String testCaseName) {

		List<String> popupText = new LinkedList<String>();
		try {

			// Verifying Forgot Password Popup
			wea.click(forgotPassword(driver, testCaseName));

			popupText.add(getPasswordResetLabelLinkText(driver, testCaseName));
			popupText.add(getEmailPlaceholderText(driver, testCaseName));
			popupText.add(getSubmitButtonText(driver, testCaseName));
			popupText.add(getAlreadyHaveAccountLabelLinkTextPasswordResetPopup(driver, testCaseName));
			popupText.add(getLoginLinkTextPassowrdResetPopup(driver, testCaseName));

			// to fetch Email cannot be blank Error message
			wea.click(submitBtn(driver, testCaseName));
			popupText.add(getErrorMessageText(driver, testCaseName));
			logger.info("Error message fetched for : Email cannot be blank");

			// to fetch Email must be a valid email address Error Message
			wea.setText(loginField(driver, testCaseName), RandomStringUtils.random(5, true, true));
			wea.click(submitBtn(driver, testCaseName));
			popupText.add(getErrorMessageText(driver, testCaseName));
			logger.info("Error message fetched for : Email must be a valid email address");

			// to fetch user User doesn't exists ! Error Message
			wea.setText(loginField(driver, testCaseName), RandomStringUtils.random(5, true, true) + "@gmail.com");
			wea.click(submitBtn(driver, testCaseName));
			popupText.add(getErrorMessageText(driver, testCaseName));
			wea.click(driver.findElement(SignupLoginLocators.Button_Close_Error_Message));

			logger.info("Error message fetched for : User doesn't exists !");

		} catch (Exception e) {
			e.printStackTrace();
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
		}
		return popupText;
	}

	/**
	 * @description used to fetch common page text for multilingual validation
	 * @param driver
	 * @param testCaseName
	 * @return
	 */
	public List<String> getCommonText(WebDriver driver, String testCaseName) {

		List<String> popupText = new LinkedList<String>();

		popupText.add(getLoginLabelText(driver, testCaseName));
		popupText.add(getConnectWithFacebookButtonText(driver, testCaseName));
		popupText.add(getOrLabelText(driver, testCaseName));
		popupText.add(getEmailPlaceholderText(driver, testCaseName));
		popupText.add(getPasswordPlaceholderText(driver, testCaseName));
		popupText.add(getSubmitButtonText(driver, testCaseName));

		return popupText;
	}

	/**
	 * @description to fetch user actions text post signup
	 * @param driver
	 * @param testCaseName
	 * @return
	 */
	public List<String> getUserActionsText(WebDriver driver, String testCaseName) {

		logger.info("Reading texts from User Actions");

		List<String>	userActionText	= new LinkedList<String>();

		// to perform signup activity

		GlobalInputData	g				= new GlobalInputData();
		g.setUserEmail(RandomStringUtils.random(8, true, true) + "@gmail.com");
		g.setPassword(RandomStringUtils.random(5, true, true));

		userSignUp(driver, testCaseName, g);

		// to close channel list popup
		gp.closeGridPreferencePopup(driver, testCaseName);

		// to fetch user action text

		userActionText.add(getSignedUpUserName(driver, testCaseName).getText().split(" ")[0]);

		wea.click(getSignedUpUserName(driver, testCaseName));

		userActionText.add(getUserActionTvListing(driver, testCaseName).getText());
		userActionText.add(getUserActionMyShows(driver, testCaseName).getText());
		userActionText.add(getUserActionAccountSettings(driver, testCaseName).getText());
		userActionText.add(ip.logoutBtn(driver, testCaseName).getText());

		logger.info("User Actions text reading Completed.");
		return userActionText;

	}
	
	
	public void GooglesignUp(WebDriver driver, String testCaseName, GlobalInputData gid) {
		try {

		wea.click(signUp(driver, testCaseName));
			
		//switch to google login window
			
		String parentWindow = driver.getWindowHandle();     
		System.out.println("Parent Window ID is : " + parentWindow);
			 
		//click on connect with google
		wea.click(connectWithGoogle(driver, testCaseName));
				
		WebDriverWait wait = new WebDriverWait(driver,5);
	    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	    Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while(i1.hasNext())
		{
			String next_tab = i1.next();
			if (!parentWindow.equalsIgnoreCase(next_tab))
			{
				driver.switchTo().window(next_tab);
				System.out.println("Working on Google Login Box");
				String	registeredUserEmail	= gid.getGoogleUserEmail();
				Thread.sleep(4000);
				wea.setText(enterGoogleEmail(driver, testCaseName), registeredUserEmail);
					
				//click on next button 
				wea.click(nextButton(driver, testCaseName));
				Thread.sleep(4000);
					
				wea.setText(enterGooglePassword(driver, testCaseName), gid.getGoogleUserPassword());
				Screenshot.captureScreenshot(driver, testCaseName, "Before Clicking Login Button");

				//	wea.click(submitBtn(driver, testCaseName));
				Thread.sleep(3000);
				//click on next button 
				wea.click(nextButton(driver, testCaseName));
				Thread.sleep(3000);

				Screenshot.captureScreenshot(driver, testCaseName, "After Clicking Login Button");
			}
		}
			
		driver.switchTo().window(parentWindow);
			
		logger.info("Our destination URL is :: " + driver.getCurrentUrl());

		} catch (Exception e) {
			logger.error("Error while performing Login", e);
			Screenshot.captureScreenshot(driver, testCaseName, IConstants.EXCEPTION_SCREENSHOT_NAME);
			TestCaseExecutionStatus.markAsFail(testCaseName, e);
		}
	}
	
	
	
	private WebElement connectWithGoogle(WebDriver driver, String testCaseName) throws InterruptedException {
		Thread.sleep(4000);
		WebElement connect_with_google = driver.findElement(SignupLoginLocators.connect_with_google);
		return connect_with_google;
	}
	
	private WebElement enterGoogleEmail(WebDriver driver, String testCaseName) {
		WebElement google_email = driver.findElement(SignupLoginLocators.google_email);
		return google_email;
	}
	
	private WebElement enterGooglePassword(WebDriver driver, String testCaseName) {
		WebElement google_password = driver.findElement(SignupLoginLocators.google_password);
		return google_password;
	}
	
	private WebElement nextButton(WebDriver driver, String testCaseName) {
		
		WebElement google_next_btn = driver.findElement(SignupLoginLocators.google_next_btn);
		return google_next_btn;
	}
	
	

}
