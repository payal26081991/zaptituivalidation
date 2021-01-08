package com.gracenote.Locators;

import org.openqa.selenium.By;

public interface FindChannelLocators {

	public By	Text_Button_Find_Channels				= By.xpath(".//button[contains(@class,'find-channels')]/span");
	public By	Label_Find_Channels						= By.xpath("//h4[@class='modal-title']");
	public By	Text_Find_Channels_Placeholder_Text		= By.xpath(".//div[@class='find-channels']//input");

	public By	Text_Button_Add_To_Starred_Channel		= By
	        .xpath(".//div[@class='find-channels']//button[contains(@class,'fav-channel')]//span");

	public By	Text_Button_Go_To_My_Starred_Channel	= By.xpath(".//div[@class='find-channels__footer']/a");

	public By	Button_Close_Find_Channel				= By.xpath(".//button[@class='close']");

	public By	Button_Find_Channels					= By.xpath(".//button[contains(@class,'find-channels')]");
}
