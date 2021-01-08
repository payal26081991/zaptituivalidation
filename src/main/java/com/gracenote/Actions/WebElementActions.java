package com.gracenote.Actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebElementActions {
	
	JavascriptExecutor executor;
	
	private static WebElementActions wea = null;

	private WebElementActions() {

	}

	public static WebElementActions getWebElementActionsObject() {
		if (wea == null) {
			wea = new WebElementActions();
		}
		return wea;
	}

	public void click(WebElement element) {
		element.click();
	}
	
	public void clickJs(WebDriver driver, WebElement element)
	{
		executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public String getTextJs(WebDriver driver, WebElement element)
	{
		executor = (JavascriptExecutor)driver;
		String text = (String) executor.executeScript("return arguments[0].text;", element);
		return text;
	}
	
	
	
	public void clear(WebElement element) {
		element.clear();
	}

	public void setText(WebElement element, String InputText) {
		element.clear();
		element.sendKeys(InputText);
	}

	public void selectDropDown(WebElement element, String country) {
		new Select(element).selectByVisibleText(country);
	}

	public void scrollPageBottom(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}

	public void scrollPageTop(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, 0)");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void scrollPageUpByOffset(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, -250)");
	}

	public void scrollPageDownByOffset(WebDriver driver) throws InterruptedException {
		JavascriptExecutor js = ((JavascriptExecutor) driver);

		js.executeScript("window.scrollTo(0, 250)");
		Thread.sleep(2000);
	}

	public void JavaScriptClick(WebElement elem, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", elem);
		System.out.println("Clicked the Web Element");
	}

	public String getJavaScriptResult(WebDriver driver, String script) {
		String				ScriptResult	= "";

		JavascriptExecutor	js				= (JavascriptExecutor) driver;
		ScriptResult = (String) js.executeScript(script);
		return ScriptResult;
	}

	public boolean isElementDisplayed(WebElement element) {

		return element.isDisplayed();
	}
}
