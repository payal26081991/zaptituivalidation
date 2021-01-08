package com.gracenote.Locators;

import org.openqa.selenium.By;

public interface GridPreferencesLocators {

	public By	Button_Grid_Preference							= By.xpath(".//button[contains(@class,'grid-preferences-trigger')]");

	public By	Checkbox_Grid_Preference_Show_Music_Channels	= By.xpath("(//input[@type='checkbox'])[1]");
	public By	Checkbox_Grid_Preference_Show_PPV_Channels		= By.xpath("(//input[@type='checkbox'])[2]");
	public By	Checkbox_Grid_Preference_Show_HD_Channels		= By.xpath("(//input[@type='checkbox'])[3]");
	public By	Button_Grid_Preference_Save						= By.xpath("//div[contains(@class,'btn-save')]");
	public By	Button_Grid_Preference_Cancel					= By.xpath(".//div[contains(@class,'btn-cancel')]");
	public By	Button_Grid_Preference_Close					= By.xpath(".//button[@class='close']");

	public By	Text_Button_Grid_Preference						= By.xpath(".//button[contains(@class,'grid-preferences-trigger')]/span");

	public By	Label_Grid_Preference							= By.xpath("//h4[@class='modal-title']");
	public By	Label_Filters_Grid_Preference					= By.xpath(".//div[@class='form-section']/h4");

	public By	Text_Grid_Preference_Show_Music_Channels		= By.xpath("(.//div[@class='form-section']//label)[1]");

	public By	Text_Grid_Preference_Show_PPV_Channels			= By.xpath("(.//div[@class='form-section']//label)[2]");

	public By	Text_Grid_Preference_Show_HD_Channels			= By.xpath("(.//div[@class='form-section']//label)[3]");
	
	public By   Checkbox_Grid_Preference_Show_threehours_Display= By.xpath("//label[text()='3- hours']//input");
	
	public By   Checkbox_Grid_Preference_Show_sixhours_Display	= By.xpath("//label[text()='6- hours']//input");
	
	public By	Grid_Preferencde_Total_Items					= By.xpath("//div[@class='btn-group-last']//button[@class='grid-header__item btn btn-default']");
	
	public By  Grid_start_time									= By.xpath("(//button[@class='grid-header__item btn btn-default'])[1]");
	
	public By  Starred_Filter_checkbox							= By.xpath("//label[text()='Only show Starred channels']/child::input");
	
}
