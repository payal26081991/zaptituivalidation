package com.gracenote.Locators;

import org.openqa.selenium.By;

public interface MyShowsLocators {

	public By	Text_Next_on_TV						= By.xpath(".//div[contains(@class,'my-shows__header')]//h3[@class='pull-left']");
	public By	Button_Airing_First					= By.xpath("(.//ul[contains(@class,'airingAlphabetical ')]//a)[1]");
	public By	Button_Alphabetical					= By.xpath("(.//ul[contains(@class,'airingAlphabetical ')]//a)[2]");
	public By	Text_No_Favourite_Show				= By.xpath("(//div[@class='my-shows__listings--empty']//div)[2]");
	public By	Button_Add_To_My_Shows				= By.xpath(".//button[contains(@class,'add-to-my-shows-btn')]");
	public By	Button_See_All_Upcoming_Episodes	= By
	        .xpath(".//div[contains(@class,'my-shows-listing__header')]//button[@class='btn btn-primary']");

	public By	Label_On_Show_Card_Details			= By.xpath("//div[@class='show-detail-card__meta']/span[2]");

}
