package com.gracenote.resources;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.Alert;

import com.gracenote.Utilities.Screenshot;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverSetup {

	public WebDriver					driver;

	private static DesiredCapabilities	capabilities;
	private static Logger				logger	= Logger.getLogger(WebDriverSetup.class);

	@SuppressWarnings("deprecation")
	private WebDriver setWebDriver(String BrowserType) {
		WebDriver driverInstance = null;
		System.out.println("BrowserType"+BrowserType);

		if (BrowserType.equalsIgnoreCase("firefox") || BrowserType.equalsIgnoreCase("mozilla"))
		{	
			
			System.setProperty("webdriver.gecko.driver","WebDriversExecutalbes/geckodriver_23_32.exe");
			File pathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			
			FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
			DesiredCapabilities desired = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
			driverInstance = new FirefoxDriver(options);
		
			    

		} else if (BrowserType.equalsIgnoreCase("chrome")) {
			

			WebDriverManager.chromedriver().arch32().setup();

			logger.info("Launching chrome driver");
			driverInstance = new ChromeDriver();

		} else if (BrowserType.equalsIgnoreCase("ie") || BrowserType.equalsIgnoreCase("internetexplorer")) {
        
		//	WebDriverManager.iedriver().arch32().setup();
			System.setProperty("webdriver.ie.driver","WebDriversExecutalbes\\IEDriverServer.exe");
		//	System.setProperty("webdriver.ie.driver","C:\\Users\\shkh9002\\Downloads\\IEDriverServer_Win32_2.53.0\\IEDriverServer_2_53.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			
//			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//			capabilities.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR,"ignore");
//		    capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
//    		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driverInstance = new InternetExplorerDriver(capabilities);       
		

		} else if (BrowserType.equalsIgnoreCase("chrome_mac")) {
			System.setProperty("webdriver.chrome.driver", "WebDriversExecutalbes/chromedriver_mac");
			logger.info("Launching chrome driver for Mac");
			capabilities = DesiredCapabilities.chrome();
			capabilities.setPlatform(Platform.MAC);
			logger.info("Safari capabilities are as follows :: " + capabilities);

			driverInstance = new ChromeDriver(capabilities);

		} else if (BrowserType.equalsIgnoreCase("chrome_linux")) {
			System.setProperty("webdriver.chrome.driver", "WebDriversExecutalbes/chromedriver_linux");
			logger.info("Launching chrome driver for Linux");
			capabilities = DesiredCapabilities.chrome();
			capabilities.setPlatform(Platform.LINUX);
			logger.info("Safari capabilities are as follows :: " + capabilities);

			driverInstance = new ChromeDriver(capabilities);

		} else if (BrowserType.equalsIgnoreCase("safari")) {

			logger.info("Launching Safari driver");

			driverInstance = new SafariDriver();

		} 
		
		else if (BrowserType.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().arch32().setup();
			
		//	System.setProperty("webdriver.edge.driver", "C:\\Users\\shkh9002\\Pictures\\MicrosoftWebDriver14.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.edge();
			driverInstance = new EdgeDriver(capabilities);
			
		}
		
		else if (BrowserType.equalsIgnoreCase("opera"))
		{	
			 	
				OperaOptions options = new OperaOptions();
		    //  options.setBinary("C:\\Users\\shkh9002\\AppData\\Local\\Programs\\Opera\\65.0.3467.72\\opera.exe");
		        options.setBinary("C:\\Program Files\\Opera\\65.0.3467.78\\opera.exe");
		        System.setProperty("webdriver.opera.driver","WebDriversExecutalbes/operadriver.exe");	
				driverInstance = new OperaDriver(options);   
		        
			
		}
		

		driverInstance.manage().window().maximize();

		return driverInstance;
	}

	public WebDriver getDriver(String testCaseName, String driverString, String url) {

		try {
			driver = setWebDriver(driverString.trim());
			driver.get(url);
			Thread.sleep(4000);
			driver.manage().window().maximize();
			logger.info("Navigating to " + url);

		} catch (InterruptedException e) {
			logger.error("Exception caught: " + e);

		} catch (Exception e) {
			logger.error("Exception caught: " + e);

		}

		Screenshot.captureScreenshot(driver, testCaseName, "After Launching Browser");

		return driver;
	}

	public void quitDriver(WebDriver driver, String testCaseName) {

		try {
			if (driver != null) {
				Screenshot.captureScreenshot(driver, testCaseName, "Before Closing Browser");

				driver.quit();
				logger.info("Driver Closed.");
			}
		} catch (Exception e) {
			logger.error("Error while closing driver.");
		}
	}
}
