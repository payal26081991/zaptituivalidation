package com.gracenote.Locators;

import org.openqa.selenium.By;

public interface ChannelListOnPincodeLocators {

	public By	Button_Change_Provider_Timezone			= By.xpath(".//*[contains(@class,'change-provider-btn')]/span");

	public By	Button_Close_Change_Provider_Timezone	= By.xpath(".//button[@class='close']");

	public By	Dropdown_Country_Selection				= By.xpath(".//div[contains(@class,'country-dropdown')]/select");

	public By	TextBox_Zip_Code						= By.xpath(".//div[contains(@class,'postal-code')]/input");
	public By	Button_Find_Provider					= By.xpath("//button[@type='submit']");
	public By	Text_Channel_Name						= By.xpath("//span[@class='channel-card__callSign']");
	public By	Grid_Loading							= By.xpath("//div[@class='spinner']");
	public By	Channel_Row								= By.className("channel-row");
	public By	Button_Close_Show_Details				= By.xpath("//div[@class='close-show-detail']");

	public By	Label_Change_Provider_Or_TimeZone		= By.xpath("//h4[@class='modal-title']");
	public By	Label_Find_Provider						= By.xpath("(.//div[@class='modal-body']//h5)[1]");
	public By	Label_Select_TimeZone					= By.xpath("(.//div[@class='modal-body']//h5)[2]");

	public By	Label_No_Providers_Found				= By.xpath("(.//ul[@class='provider-results__list']/div[@class='empty'])[1]");

	public By	Text_Tab_Cable							= By.xpath("(.//ul[@role='tablist']//a)[1]");
	public By	Text_Tab_Satellite						= By.xpath("(.//ul[@role='tablist']//a)[2]");
	public By	Text_Tab_Antenna						= By.xpath("(.//ul[@role='tablist']//a)[3]");

	public By	Text_Tip								= By.xpath("//div[@class='tip']");
	public By	Label_Find_Provider_Or_Option			= By.xpath(".//div[contains(@class,'hr-or')]");
	public By	Text_Error_Message						= By.xpath("//div[@class='alert alert-danger']/span");

	public By	All_Provider_List						= By.xpath(".//*[@id='content']//ul[@class='provider-results__list']/li");

}
