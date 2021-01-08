package com.gracenote.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gracenote.Actions.WebElementActions;

public class SingleStationListPage {

	private static Logger	logger	= Logger.getLogger(SingleStationListPage.class);
	WebElementActions		wea		= WebElementActions.getWebElementActionsObject();

	public List<WebElement> getScheduleAiring(WebDriver driver, String testCaseName) {
		List<WebElement> ScheduleAiring = new ArrayList<WebElement>();
		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@class='list-results']")));
			ScheduleAiring = driver.findElements(By.xpath(".//*[@class='list-results']"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ScheduleAiring;
	}

	private WebElement getGridViewSingleStationLink(WebDriver driver) {
		WebElement GridViewSingleStationLink = null;
		try {

			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@class='view_tabs']/ul/li[2]/a")));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@class='view_tabs']/ul/li[2]/a")));
			GridViewSingleStationLink = driver.findElement(By.xpath(".//*[@class='view_tabs']/ul/li[2]/a"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return GridViewSingleStationLink;
	}

	public List<WebElement> getGridTimeBar(WebDriver driver, String testCaseName) {
		List<WebElement> GridTimeBar = new ArrayList<WebElement>();
		try {
			new WebDriverWait(driver, 60)
			        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@class='btn-group']/button")));
			GridTimeBar = driver.findElements(By.xpath(".//*[@class='btn-group']/button"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return GridTimeBar;

	}

	public List<WebElement> getProgramAirings(WebDriver driver, String testCaseName) {
		List<WebElement> GridTimeBar = new ArrayList<WebElement>();
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions
			        .visibilityOfAllElementsLocatedBy(By.xpath(".//*[@id='primary']/div[contains(@class,'grid-details')]/ul")));
			GridTimeBar = driver.findElements(By.xpath(".//*[@id='primary']/div[contains(@class,'grid-details')]/ul"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return GridTimeBar;

	}

	public void goToGridView(WebDriver driver, String testCaseName) {
		wea.click(getGridViewSingleStationLink(driver));
	}
}
