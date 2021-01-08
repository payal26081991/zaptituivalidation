package com.gracenote.Locators;

import org.openqa.selenium.By;

public interface AccountSettingLocators {

	public By	Label_Account_Setting		= By.xpath("//div[contains(@class,'account-settings-text')]");

	public By	Label_Your_Details			= By.xpath("(//form[@class='form-horizontal']//legend)[1]");

	public By	Label_Change_Password		= By.xpath("(//form[@class='form-horizontal']//legend)[2]");

	public By	Label_Name					= By.xpath("(//label[@for='name'])[1]");

	public By	Label_Email					= By.xpath("(//label[@for='email'])[1]");

	public By	Label_New_Password			= By.xpath("(//label[@for='name'])[2]");

	public By	Label_Confirm_Password		= By.xpath("(//label[@for='email'])[2]");

	public By	Textbox_New_Password		= By.id("new_password");

	public By	Textbox_Confirm_Password	= By.id("new_password_confirm");

	public By	Button_Save_Changes			= By.xpath("//button[@type='submit']");

	public By	Success_Message				= By.xpath("//div[@class='alert alert-success']");

	public By	Error_Message				= By.xpath("//div[@class='alert alert-danger']");

}
