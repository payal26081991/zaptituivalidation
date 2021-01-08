package com.gracenote.Locators;

import org.openqa.selenium.By;

public interface IndexPageLocators {

	public By	Button_Skip_Tour						= By.xpath(".//*[@class='modal-content']//button[contains(@class,'skip btn')]");

	//public By	Dropdown_Jump_To_Time					= By.id("jump-to-time");
	
	public By 	Dropdown_Jump_To_Time	    			= By.xpath("(.//*[contains(@id,'jump-to-time')])[1]");
	
	public By Dropdown_Jump_To_Movie                    = By.id("jump-to-filters");
		
	public By	DropdownList_Jump_To_Time				= By
	        .xpath(".//*[@class='jump-to-filters']/div[contains(@class,'jump-to-time')]/ul[@class='dropdown-menu']/li/a");

	public By	Dropdown_Jump_To_Date					= By.xpath(".//*[contains(@id,'jump-to-date')]");

	public By	DropdownList_Jump_To_Date				= By
	        .xpath(".//*[@class='jump-to-filters']/div[contains(@class,'jump-to-date')]/ul[@class='dropdown-menu']/li/a");

	public By	Button_Grid_Start_Time					= By.xpath(".//*[@class='btn-group-last']/button[1]");

	public By	Dropdown_Option_Primetime				= By.xpath("//a[text()='Primetime']");

	public By	Text_Button_Change_Provider_Timezone	= By.xpath(".//*[contains(@class,'change-provider-btn')]/span");

	public By	Text_Menu_Tv_Listing					= By.xpath("(//li[contains(@class,'menu-item')]//a)[1]");

	public By	Text_Menu_My_Shows						= By.xpath("(//li[contains(@class,'menu-item')]//a)[2]");

	public By	Text_Menu_Print							= By.xpath("(//li[contains(@class,'pull-right')]//a)[2]");

	public By	Text_Search_Placeholder_Text			= By.xpath(".//div[@class='show-movie-search']//input");

	public By	Text_Button_SignUp_Or_Login				= By.xpath("//*[@id='user-actions']/ul[@class='nav navbar-nav']/li/button/span");

	public By	Label_Jumo_To_Filer						= By.xpath("//span[@class='jump-to-filters__label']");

	public By	Label_Scroll_To_Top						= By.xpath(".//a[@class='back-to-top']/span");

	public By	Text_Button_Add_To_My_Show				= By.xpath("//button[contains(@class,'add-to-my-shows')]");

	public By	Dropdown_User_Profile					= By.id("user-actions");

	public By	Text_Logged_In_UserName					= By.className("salutation");

	public By	Link_TV_Listing_User_Action				= By.xpath("(.//a[@class='nor-my-show'])[1]");

	public By	Link_My_Shows_User_Action				= By.xpath("(.//a[@class='nor-my-show'])[2]");

	public By	Link_Account_Settings_User_Action		= By.xpath("//ul[@class='zap-dropdown-menu']/li[4]/a");

	public By	Link_Sign_Out_User_Action				= By.xpath(".//a[contains(@class,'signout')]");

	public By	All_Show_Cards							= By.xpath(".//div[contains(@class,'channel-listings')]/div");
	
	public By   All_Movie_details                       =By.xpath("(//div[@class='show-card filter-movie']//h5)");

	public By	Label_Contact_Us						= By.xpath(".//*[@class='footerMenu']/li[1]/a");

	public By	Label_Copyright							= By.xpath(".//*[@class='footerMenu']/li[2]");

	public By	Label_PoweredBy							= By.xpath(".//*[@class='footerMenu']/li[3]");

	public By	Link_PoweredBy							= By.xpath(".//*[@class='footerMenu']/li[3]/a");

	public By	Link_Tv_Listings						= By.xpath("//li[contains(@class,'listings current-menu-item')]");

	public By	Link_Tv_By_The_Numbers					= By.xpath("//li[@class='ratings']");

	public By	Link_First_Program_Name					= By.xpath("//h3[@class='show-detail-card__title']/a");

	public By	First_Program_From_Search_List			= By.xpath("(//ul[@class='dropdown-menu search-result']//li)[1]");

	public By	Label_View_Single_Station				= By.xpath("//div[@class='view_tabs']/ul/span");

	public By	Link_List_Single_Station				= By.xpath("(//div[@class='view_tabs']//a)[1]");

	public By	Link_Grid_Single_Station				= By.xpath("(//div[@class='view_tabs']//a)[2]");

	public By	Label_Channels_Single_Station			= By.xpath("//span[@class='channelList-filters__label']");

	public By	All_Headers_Single_Station				= By.xpath("//div[@class='list-header']");
	
	public By	Opened_ShowCard							= By.xpath("//h3[@class='show-detail-card__title']/a");
	
	public By   image_icon								= By.xpath("(//div[@class='channel-card'])[1]/child::div/child::div/img");
	
	public By    show_title								= By.xpath("//h3[@class='show-detail-card__title']//a");
	
	public By    overview_details 						= By.xpath("//div[@class='row overview-preference-bar preferences-bar']");
	
	public By    backtoStationBtn	         			= By.xpath("(//div[@class='backToGrid'])[2]");
	
	public By    backtoHomeBtn							= By.xpath("(//div[@class='backToGrid'])[1]");
	
	public By    changeProviderText						= By.xpath("//button[@class='btn btn-default change-provider-btn']");
	
	public By    starredFilterElement					= By.xpath("//button[contains(@class,'__starred')]");
	
	public By    nxtButton								= By.xpath("(//button[@class='btn btn-default'])[3]");
	
		
}
