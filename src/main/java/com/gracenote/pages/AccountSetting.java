package com.gracenote.pages;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gracenote.Actions.WebElementActions;
import com.gracenote.Locators.AccountSettingLocators;
import com.gracenote.Utilities.Screenshot;

public class AccountSetting {

	Logger				logger	= Logger.getLogger(AccountSetting.class);

	WebElementActions	wea		= WebElementActions.getWebElementActionsObject();

	public WebElement getAccountSettingLabel(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60)
		        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Label_Account_Setting));
		WebElement accountSettingLabel = driver.findElement(AccountSettingLocators.Label_Account_Setting);
		return accountSettingLabel;
	}

	public WebElement getYourDetailsLabel(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Label_Your_Details));
		WebElement yourDetailsLabel = driver.findElement(AccountSettingLocators.Label_Your_Details);
		return yourDetailsLabel;
	}

	public WebElement getChangePasswordLabel(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60)
		        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Label_Change_Password));
		WebElement changePasswordLabel = driver.findElement(AccountSettingLocators.Label_Change_Password);
		return changePasswordLabel;
	}

	public WebElement getNameLabel(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Label_Name));
		WebElement nameLabel = driver.findElement(AccountSettingLocators.Label_Name);
		return nameLabel;
	}

	public WebElement getEmailLabel(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Label_Email));
		WebElement emailLabel = driver.findElement(AccountSettingLocators.Label_Email);
		return emailLabel;
	}

	public WebElement getNewPasswordLabel(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Label_New_Password));
		WebElement newPasswordLabel = driver.findElement(AccountSettingLocators.Label_New_Password);
		return newPasswordLabel;
	}

	public WebElement getConfirmPasswordLabel(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60)
		        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Label_Confirm_Password));
		WebElement confirmPasswordLabel = driver.findElement(AccountSettingLocators.Label_Confirm_Password);
		return confirmPasswordLabel;
	}

	public WebElement getNewPasswordTextbox(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60)
		        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Textbox_New_Password));
		WebElement newPasswordTextbox = driver.findElement(AccountSettingLocators.Textbox_New_Password);
		return newPasswordTextbox;
	}

	public WebElement getConfirmPasswordTextbox(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60)
		        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Textbox_Confirm_Password));
		WebElement confirmPasswordTextbox = driver.findElement(AccountSettingLocators.Textbox_Confirm_Password);
		return confirmPasswordTextbox;
	}

	public WebElement getSaveChangesButton(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60)
		        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Button_Save_Changes));
		WebElement saveChangesButton = driver.findElement(AccountSettingLocators.Button_Save_Changes);
		return saveChangesButton;
	}

	public WebElement getSuccessMessage(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Success_Message));
		WebElement successMessage = driver.findElement(AccountSettingLocators.Success_Message);
		return successMessage;
	}

	public WebElement getErrorMessage(WebDriver driver, String testCaseName) {

		new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AccountSettingLocators.Error_Message));
		WebElement errorMessage = driver.findElement(AccountSettingLocators.Error_Message);
		return errorMessage;
	}

	/**
	 * @description to fetch all text from Account Setting Page
	 * @param driver
	 * @param testCaseName
	 * @return
	 */
	public List<String> getAccountSettingPageText(WebDriver driver, String testCaseName) {

		List<String> accountSettingPageText = new LinkedList<String>();

		accountSettingPageText.add(getAccountSettingLabel(driver, testCaseName).getText());
		accountSettingPageText.add(getYourDetailsLabel(driver, testCaseName).getText());
		accountSettingPageText.add(getChangePasswordLabel(driver, testCaseName).getText());
		accountSettingPageText.add(getNameLabel(driver, testCaseName).getText());
		accountSettingPageText.add(getEmailLabel(driver, testCaseName).getText());
		accountSettingPageText.add(getNewPasswordLabel(driver, testCaseName).getText());
		accountSettingPageText.add(getConfirmPasswordLabel(driver, testCaseName).getText());
		accountSettingPageText.add(getSaveChangesButton(driver, testCaseName).getText());

		// to get Profile Updated Successfully message.
		wea.click(getSaveChangesButton(driver, testCaseName));
		Screenshot.captureScreenshot(driver, testCaseName, "Post clicking Save button on Account Setting Page");
		accountSettingPageText.add(getSuccessMessage(driver, testCaseName).getText());

		// to get Password confirmation doesn't match! error message
		wea.setText(getNewPasswordTextbox(driver, testCaseName), RandomStringUtils.random(5, true, true));
		wea.click(getSaveChangesButton(driver, testCaseName));
		Screenshot.captureScreenshot(driver, testCaseName, "Post clicking Save button on Account Setting Page");

		accountSettingPageText.add(getErrorMessage(driver, testCaseName).getText());

		// to get Password updated successfully! message.
		String password = RandomStringUtils.random(5, true, true);
		wea.setText(getNewPasswordTextbox(driver, testCaseName), password);
		wea.setText(getConfirmPasswordTextbox(driver, testCaseName), password);

		wea.click(getSaveChangesButton(driver, testCaseName));
		Screenshot.captureScreenshot(driver, testCaseName, "Post clicking Save button on Account Setting Page");

		accountSettingPageText.add(getSuccessMessage(driver, testCaseName).getText());

		return accountSettingPageText;
	}

}
