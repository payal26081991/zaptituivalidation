package com.gracenote.Locators;

import org.openqa.selenium.By;

public interface SignupLoginLocators {

	public By	Button_Sign_Up								= By.xpath("//*[@id='user-actions']/ul[@class='nav navbar-nav']/li/button");
	public By	Label_Login									= By.xpath("//h4[@class='modal-title']");
	public By	Textbox_Email								= By.xpath("//input[@type='email']");
	public By	Textbox_Password							= By.xpath("//input[@type='password']");
	public By	Button_Submit								= By.xpath("//button[@type='submit']");
	public By	Link_Forgot_Your_Password_Login				= By.className("login-forgot-password");
	public By	Link_Forgot_Your_Password_Signup			= By.className("signup-forgot-password");
	public By	Link_SignUp									= By.className("login-signup");
	public By	Link_LogIn									= By.className("signup-login");
	public By	Text_Error_Message							= By.xpath("//div[@class='alert alert-danger']/span");
	public By	Text_Info_Message							= By.xpath("//div[@class='alert alert-info']/span");
	public By	Text_Dont_Have_Account						= By.xpath(".//div[@class='modal-footer-link'][2]/span");
	public By	Button_Connect_With_Facebook				= By.xpath(".//button[contains(@class,'fb-btn')]");
	public By	Label_SignUp_Or_Option						= By.xpath(".//div[contains(@class,'hr-or')]");
	public By	Button_Close_SignUp_Popup					= By.xpath(".//button[@class='close']");
	public By	Label_Password_Reset						= By.xpath("//h4[@class='modal-title']");
	public By	Text_Already_Have_Account_SignUp_Popup		= By.xpath(".//div[@class='modal-footer-link'][2]/span");
	public By	Text_Already_Have_Account_Password_Reset	= By.xpath("//div[@class='modal-footer-link']/span");
	public By	Button_Close_Error_Message					= By.xpath(".//a[@class='close']");
	public By	Link_LogIn_Password_Reset_Popup				= By.xpath("//div[@class='modal-footer-link']/a");
	public By   connect_with_google							= By.xpath("//button[@class='google-btn btn btn-default btn-block']");
	public By   google_email								= By.xpath("//input[@type='email']");
	public By   google_password								= By.xpath("//input[@type='password']");
	public By   google_next_btn   							= By.xpath("//div[contains(@id,'Next')]");

}
