package com.gracenote.Miscellaneous;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeCompare {
	WebDriver		driver;
	WebDriverWait	wait;

	// Locators
	By				NewSite_SkipTourButton				= By.xpath("//button[text()='Skip Tour']");
	By				NewSite_ChangeProvider				= By.xpath("//button/span[text()='Change provider or Time Zone']/..");
	By				NewSite_ChangeProviderDialog		= By.xpath("//h4[text()='Change Your Provider Or Time Zone']");
	By				NewSite_ProviderCountryDropdown		= By.xpath("//div[@class='form-group form-group-lg country-dropdown']/select");
	By				NewSite_ProviderPostalCode			= By.xpath("//div[@class='form-group form-group-lg postal-code']/input");
	By				NewSite_FindProvidersButton			= By.xpath("//button[text()='Find Providers']");
	By				NewSite_LoadingSpinner				= By.xpath("//div[@class='spinner']");
	By				NewSite_ChannelNames				= By.xpath("//span[@class='channel-card__callSign']");
	By				NewSite_ChannelListing				= By
	        .xpath(".//div[@class='channel-row']/div/div[contains(@class,'channel-listings')]");										// Row
	                                                                                                                                    // Wise
	By				NewSite_ShowCardDetail				= By.xpath("//div[@class='show-detail-card']");
	By				NewSite_ShowCardDetail_Title		= By.xpath("//h3[@class='show-detail-card__title']/a");
	By				NewSite_ShowCardDetail_CloseButton	= By.xpath("//div[@class='close-show-detail']");

	public TimeCompare() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 60);
	}

	public void verifyShowsInTimeFrame(String countryName, String postalCode, String tabName, String providerName) throws ParseException {
		driver.get("http://tvschedule.whatsonindia.com/new/gapzap_zap2it_ui/?aid=gapzap");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.findElement(NewSite_SkipTourButton).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(NewSite_SkipTourButton));
		wait.until(ExpectedConditions.elementToBeClickable(NewSite_ChangeProvider));
		driver.findElement(NewSite_ChangeProvider).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(NewSite_ChangeProviderDialog));
		new Select(driver.findElement(NewSite_ProviderCountryDropdown)).selectByVisibleText(countryName);
		driver.findElement(NewSite_ProviderPostalCode).sendKeys(postalCode);

		driver.findElement(NewSite_FindProvidersButton).click();
		driver.findElement(By.xpath("//a[@role='tab' and text()='" + tabName + "']")).click();

		wait.until(ExpectedConditions
		        .visibilityOfAllElementsLocatedBy(By.xpath("//div[@aria-hidden='false']/ul[@class='provider-results__list']/li")));
		driver.findElement(By.xpath(".//li[@class='provider-results__item' and text()='" + providerName + "']")).click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(NewSite_LoadingSpinner));

		List<WebElement>	channelList		= driver.findElements(NewSite_ChannelNames);
		List<WebElement>	showCardRows	= driver.findElements(NewSite_ChannelListing);

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(NewSite_ChannelNames));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(NewSite_ChannelListing));

		System.out.println(channelList.size() + " " + showCardRows.size());

		System.out.println(driver.findElement(By.xpath("//div[@class='timebarStandEnd']")).getAttribute("innerHTML"));
		String	gridStartTime	= driver.findElement(By.xpath("//div[@class='timebarStandEnd']")).getAttribute("innerHTML").split("--")[0];
		String	gridEndTime		= driver.findElement(By.xpath("//div[@class='timebarStandEnd']")).getAttribute("innerHTML").split("--")[1];

		System.out.println(gridStartTime + " " + gridEndTime);

		for (int i = 0; i < channelList.size(); i++) {

			try {
				wait.until(ExpectedConditions
				        .visibilityOfElementLocated(By.xpath("(//div[contains(@class,'channel-listings')])[" + (i + 1) + "]/div")));
				List<WebElement> showCards = driver
				        .findElements(By.xpath("(//div[contains(@class,'channel-listings')])[" + (i + 1) + "]/div"));

				showCards.get(0).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='show-detail-card_timeStartEnd']")));
				String firstShowCardStartTime = driver.findElement(By.xpath("//div[@class='show-detail-card_timeStartEnd']"))
				        .getAttribute("innerHTML").split("--")[0];

				if (showCards.size() > 1) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(NewSite_ShowCardDetail_CloseButton));
					wait.until(ExpectedConditions.elementToBeClickable(NewSite_ShowCardDetail_CloseButton));
					driver.findElement(NewSite_ShowCardDetail_CloseButton).click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(NewSite_ShowCardDetail));
					showCards.get(showCards.size() - 1).click();
				}

				String LastShowCardEndTime = driver.findElement(By.xpath("//div[@class='show-detail-card_timeStartEnd']"))
				        .getAttribute("innerHTML").split("--")[1];
				wait.until(ExpectedConditions.visibilityOfElementLocated(NewSite_ShowCardDetail_CloseButton));
				wait.until(ExpectedConditions.elementToBeClickable(NewSite_ShowCardDetail_CloseButton));
				driver.findElement(NewSite_ShowCardDetail_CloseButton).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(NewSite_ShowCardDetail));

				if (compareTime(firstShowCardStartTime, gridStartTime) <= 0 && compareTime(LastShowCardEndTime, gridEndTime) >= 0) {
					System.out.println("Row Passed: " + i + " : " + channelList.get(i).getText());

				} else {
					System.out.println("Row Failed: " + i + " : " + channelList.get(i).getText() + " " + firstShowCardStartTime + "<="
					        + gridStartTime + " :: " + LastShowCardEndTime + ">=" + gridEndTime);
					System.out.println(
					        "compareTimes(firstShowCardStartTime,gridStartTime)=" + compareTimes(firstShowCardStartTime, gridStartTime));
					System.out.println("compareTimes(LastShowCardEndTime,gridEndTime)=" + compareTimes(LastShowCardEndTime, gridEndTime));
				}

			} catch (TimeoutException e) {
				System.out.println("Row Failed: " + i + " : " + channelList.get(i).getText() + " Show Card Timings are Missing");
			}
		}
	}

	public ArrayList<String> getTimes(String expression) {
		String[]			split1	= expression.split("-->");
		ArrayList<String>	retVal	= new ArrayList<String>();

		for (String str : split1) {
			String val = str.split("<!--")[0].replace("\n", "");
			if (val.contains(":")) {
				retVal.add(val);
			}
		}
		return retVal;
	}

	public static int compareTime(String date1, String date2) throws ParseException {
		SimpleDateFormat	format	= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date				d1		= format.parse(date1);
		Date				d2		= format.parse(date2);

		System.out.println(d1.toString() + " compare to " + d2.toString());
		return d1.compareTo(d2);
	}

	public int compareTimes(String date1, String date2) throws ParseException {
		SimpleDateFormat	format		= new SimpleDateFormat("dd/MM/YYYY HH:mm a");
		int					date1Hours	= Integer.parseInt(date1.split(":")[0]);
		int					date1Mins	= Integer.parseInt(date1.split(":")[1].split(" ")[0]);

		int					date2Hours	= Integer.parseInt(date2.split(":")[0]);
		int					date2Mins	= Integer.parseInt(date2.split(":")[1].split(" ")[0]);

		if (date1.contains("PM") && date1Hours < 12) {
			date1Hours = date1Hours + 12;
		}

		if (date2.contains("PM") && date2Hours < 12) {
			date2Hours = date2Hours + 12;
		}

		if (date1.contains("AM") && date1Hours == 12) {
			date1Hours = date1Hours - 12;
		}

		if (date2.contains("AM") && date2Hours == 12) {
			date2Hours = date2Hours - 12;
		}

		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DATE), date1Hours, date1Mins, 00);

		if (date1.contains("PM") && date2.contains("AM") && (Math.abs(date2Hours - date1Hours)) > 12) {
			calendar1.add(Calendar.DATE, -1);
		}

		// System.out.println("Calendar 1 Time: " + calendar1.getTime());
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DATE), date2Hours, date2Mins, 00);

		// System.out.println("Calendar 2 Time: " + calendar2.getTime());
		// System.out.println(calendar1.getTimeInMillis() + " " +
		// calendar2.getTimeInMillis());

		if (date1.equals(date2)) {
			Date	d1	= format.parse(format.format(calendar1.getTime()));
			Date	d2	= format.parse(format.format(calendar2.getTime()));

			// System.out.println("Date 1 Time: " + d1.toString());
			// System.out.println("Date 2 Time: " + d2.toString());

			return d1.compareTo(d2);
		} else {
			return calendar1.compareTo(calendar2);
		}
	}

	public void closeDriver() {
		driver.quit();
	}

	public static void main(String[] args) throws InterruptedException, IOException, ParseException {
		/*
		 * String t1="2017-04-13 22:30"; String t2="2017-04-13 22:30";
		 * 
		 * System.out.println(compareTime(t1,t2));
		 */

		TimeCompare t = new TimeCompare();
		try {
			t.verifyShowsInTimeFrame("USA", "90002", "Cable", "Time Warner Cable - Cable");
			t.closeDriver();
		} catch (Exception e) {
			e.printStackTrace();
			t.closeDriver();
		}

	}

}