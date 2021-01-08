package com.gracenote.Locators;

import org.openqa.selenium.By;

public interface ProgramShowCardLocators {

	public By	Button_Back_To_Grid							= By.className("backToGrid");

	public By	Textbox_Search								= By.xpath("//div[@class='show-movie-search']//input");

	public By	Button_SignUp_Or_Login						= By.xpath("//button[@class='btn btn-default']/span");

	public By	Button_Add_To_My_Shows						= By.xpath("//button[contains(@class,'add-to-my-shows-btn')]");

	public By	Label_Genere_Type							= By
	        .xpath("//div[contains(@class,'banner-info')]//span[@class='meta-data-text']");

	public By	Program_Image								= By.xpath("//div[@class='row banner-info']/img");

	public By	Label_Program_Name							= By.xpath("//div[@class='row banner-info']//h1/span");

	public By	Cast_List									= By
	        .xpath("//section[@class='cast']//div[contains(@class,'cast-crew-list')]/div");

	public By	Crew_List									= By
	        .xpath("//section[@class='crew']//div[contains(@class,'cast-crew-list')]/div");

	public By	Tab_Overview								= By.xpath("//a[@data-id='Overview']");

	public By	Tab_Upcoming_Episodes						= By.xpath("//a[@data-id='Upcoming Episodes']");

	public By	Tab_Upcoming_Airings						= By.xpath("//a[@data-id='Upcoming Airings']");

	public By	Tab_Episode_Guide							= By.xpath("//a[@data-id='Episode Guide']");

	public By	Tab_Cast_And_Crew							= By.xpath("//a[@data-id='Cast & Crew']");

	public By	Button_See_All_Upcoming_Episode_Airings		= By
	        .xpath("//section[@class='overview']//button[contains(@class,'btn-overview')]");

	public By	Dropdown_Watch_Online_Overview_Tab			= By.xpath("//section[@class='overview']//button[@type='button']");

	public By	Dropdown_Watch_Online_Upcoming_Episode_Tab	= By.xpath("//section[@class='upcoming-episodes']//button[@type='button']");

	public By	Label_Cast_And_Crew_Overview_Tab			= By.xpath("//section[@class='cast-crew']//div[@class='header-divider']/span");

	public By	Button_See_All_Cast_And_Crew				= By.xpath("//span[@class='cast']");

	public By	Label_Similar_Shows							= By.xpath("//h1[@class='widget-title']");

	public By	Label_Find_It_On_Overview_Tab				= By.xpath("//section[@class='overview']//span[@class='epi_ch']/h2");

	public By	Label_Find_It_On_Upcoming_Episode_Tab		= By.xpath("//section[@class='upcoming-episodes']//span[@class='epi_ch']/h2");

	public By	Button_All_UpComing_Episodes				= By.xpath("//button[contains(@class,'btn-all-episodes')]");

	public By	Button_Only_New_Episodes					= By.xpath("//button[contains(@class,'btn-new-episodes')]");

	public By	Button_Upcoming_Airings						= By.xpath("//button[contains(@class,'episode-upcoming-btn')]");

	public By	Button_See_More								= By.xpath("//button[contains(@class,'episodes-listings-expander')]");

	public By	Header_Label_Cast							= By.xpath("//section[@class='cast']//div[@class='header-divider']");

	public By	Header_label_Crew							= By.xpath("//section[@class='crew']//div[@class='header-divider']");

	public By	List_Cast_Crew_Type_Cast_Crew_Tab			= By
	        .xpath(".//div[contains(@class,'cast-crew-tab')]//div[@class='cast-crew-list row']//h1");

	public By	List_Cast_Crew_Type_Overview_Tab			= By
	        .xpath(".//div[contains(@class,'overview-tab')]//div[@class='cast-crew-list row']//h1");

	public By	Label_Airing_Upcoming_Episodes				= By
	        .xpath("//section[@class='upcoming-episodes']//span[@class='meta-data-text'][2]/span[1]");

	public By	Dropdwon_Seasons							= By.xpath("//div[contains(@class,'jump-to-season')]/button");

	public By	Label_Season_Dropdown_Title					= By.xpath("//div[contains(@class,'jump-to-season')]/button/span[1]");

	public By	Label_Season_Index_Dropdown_Title			= By.xpath("//div[contains(@class,'jump-to-season')]/button/span[2]");

	public By	Label_Seasons_From_Dropdown					= By
	        .xpath("//div[@class='header-divider']//ul[@class='dropdown-menu']//span[1]");

	public By	Dropdown_Watch_Online_Episode_Guide_Tab		= By.xpath("//div[contains(@class,'ovd-drop-down')]/button");

	public By	Label_Season_Overview_Tab					= By
	        .xpath("//section[@class='overview']//div[@class='episodes_info']//span[@class='meta-data-text']/span[1]/span[1]");

	public By	Label_Episode_Overview_Tab					= By
	        .xpath("//section[@class='overview']//div[@class='episodes_info']//span[@class='meta-data-text']/span[2]/span[1]");

	public By	Label_Airing_Overview_Tab					= By
	        .xpath("//section[@class='overview']//span[@class='meta-data-text'][2]/span[1]");

	public By	Label_Program_Timimgs_Overview_Tab			= By.xpath("//section[@class='overview']//span[@class='epi_time']");

	public By	Label_Season_Upcoming_Episodes_Tab			= By
	        .xpath("//section[@class='upcoming-episodes']//div[@class='episodes_info']//span[@class='meta-data-text']/span[1]/span[1]");

	public By	Label_Episode_Upcoming_Episodes_Tab			= By
	        .xpath("//section[@class='upcoming-episodes']//div[@class='episodes_info']//span[@class='meta-data-text']/span[2]/span[1]");

	public By	Label_Program_Timimgs_Upcoming_Episode_Tab	= By.xpath("//section[@class='upcoming-episodes']//span[@class='epi_time']");

	public By	Label_Upcoming_Episode_Airings				= By.xpath("//section[@class='overview']//div[@class='header-divider']/span");

	public By	Label_Season_Episodes_Guide_Tab				= By.xpath(
	        "//section[@class='episode-guide-episodes']//div[@class='episodes_info']//span[@class='meta-data-text']/span[1]/span[1]");

	public By	Label_Episode_Episodes_Guide_Tab			= By.xpath(
	        "//section[@class='episode-guide-episodes']//div[@class='episodes_info']//span[@class='meta-data-text']/span[2]/span[1]");

	public By	Label_Airing_Episodes_Guide_Tab				= By
	        .xpath("//section[@class='episode-guide-episodes']//span[@class='meta-data-text'][2]/span[1]");

}
