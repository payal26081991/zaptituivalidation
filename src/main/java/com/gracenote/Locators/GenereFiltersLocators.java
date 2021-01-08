package com.gracenote.Locators;

import org.openqa.selenium.By;

public interface GenereFiltersLocators {

	public By	Main_Button_All_Channels_Filter	= By.id("jump-to-filters");

	public By	Filter_Button_All_Channels		= By.xpath("//li[@class='grid-filter grid-filter-controls__all']/a");

	public By	Filter_Button_Starred_Channels	= By.xpath("//li[@class='grid-filter grid-filter-controls__starred']/a");

	public By	Filter_Button_Movies_Channels	= By.xpath("//li[@class='grid-filter grid-filter-controls__movie']/a");
	// locator updated from movies to movie. Observed on 2nd Jan 19

	public By	Filter_Button_Sports_Channels	= By.xpath("//li[@class='grid-filter grid-filter-controls__sports']/a");

	public By	Filter_Button_Family_Channels	= By.xpath("//li[@class='grid-filter grid-filter-controls__family']/a");

	public By	Filter_Button_News_Channels		= By.xpath("//li[@class='grid-filter grid-filter-controls__news']/a");

	public By	Filter_Button_Talk_Channels		= By.xpath("//li[@class='grid-filter grid-filter-controls__talk']/a");

}
