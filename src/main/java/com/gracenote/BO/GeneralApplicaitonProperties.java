package com.gracenote.BO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GeneralApplicaitonProperties {

	Properties		props			= new Properties();
	InputStream		input			= null;
	private String	OldSiteURL		= "", NewSiteURL = "",NewSiteURLAffiliates="", ExcelFileName = "", Browser = "", HubURL = "", GoogleAnalyticsTag = "",
	        ComscoreAnalyticsTag = "", AdsFileText = "";
	private String	DBHost			= "", DBUserName = "", DBPassword = "", DBSchema = "", DBInstanceName = "", GracenoteURL = "",
	        AffiliateURL = "", SingleStaionURL = "", LanguageEncodingURL = "", gapzapAffiliateURL = "";
	private long	gridStartTime	= 0, gridEndTime = 0;
	static int		i				= 0;

	public GeneralApplicaitonProperties() {
		try {

			input = new FileInputStream("application.properties");

			// load a properties file
			props.load(input);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (System.getProperty("EndPointURL") != null) {
			String Endpoint = System.getProperty("EndPointURL");
			setNewSiteURL(Endpoint);
		} else {
			setNewSiteURL(props.getProperty("NewSiteURL"));
		}
		
		setOldSiteURL(props.getProperty("OldSiteURL"));
		setExcelFileName(props.getProperty("ExcelFileName"));

		if (System.getProperty("Browser") != null) {
			setBrowser(System.getProperty("Browser"));
		} else {
			setBrowser(props.getProperty("Browser"));
		}

		// setHubURL(props.getProperty("HubURL"));

		if (System.getProperty("env") != null) {
			if (System.getProperty("env").equalsIgnoreCase("uat"))
				setDBHost(props.getProperty("UATDBHost"));
			else if (System.getProperty("env").equalsIgnoreCase("preprod"))
				setDBHost(props.getProperty("preprodDBHost"));
			else if (System.getProperty("env").equalsIgnoreCase("prod"))
				setDBHost(props.getProperty("prodDBHost"));
			else if (System.getProperty("env").equalsIgnoreCase("dev"))
				setDBHost(props.getProperty("devDBHost"));
		} else {
			setDBHost(props.getProperty("UATDBHost"));
		}

		setDBUserName(props.getProperty("DBUserName"));
		setDBPassword(props.getProperty("DBPassword"));
		setDBSchema(props.getProperty("DBSchema"));
		setDBInstanceName(props.getProperty("DBInstanceName"));
		setGracenoteURL(props.getProperty("GracenoteURL"));
		setAffiliateURL(props.getProperty("StandardTVGridAffiliateURL"));
		setSingleStaionURL(props.getProperty("SingleStationAffiliateURL"));
		setGoogleAnalyticsTag(props.getProperty("GoogleAnalyticsApp"));
		setComscoreAnalyticsTag(props.getProperty("ComscoreApp"));
		setAdsFileText(props.getProperty("AdsTextFile"));
		setLanguageEncodingURL(props.getProperty("LanguageEncodingURL"));
		setGapzapAffiliateURL(props.getProperty("GapZapAffiliateURL"));
		
		// for changes as per redirections in zap2it.com and gracenote.com
		if (System.getProperty("EndPointAffiliateURL") != null) {
			String Endpoint = System.getProperty("EndPointAffiliateURL");
			setNewSiteURLAffiliates(Endpoint);
		} else {
			setNewSiteURLAffiliates(props.getProperty("NewSiteURLAffiliates"));
		}
	}

	public String getLanguageEncodingURL() {
		return LanguageEncodingURL;
	}

	public void setLanguageEncodingURL(String languageEncodingURL) {
		LanguageEncodingURL = languageEncodingURL;
	}

	public String getOldSiteURL() {
		return OldSiteURL;
	}

	public void setOldSiteURL(String oldSiteURL) {
		OldSiteURL = oldSiteURL;
	}

	public String getNewSiteURL() {
		return NewSiteURL;
	}

	public void setNewSiteURL(String newSiteURL) {
		NewSiteURL = newSiteURL;
	}

	public String getExcelFileName() {
		return ExcelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		ExcelFileName = excelFileName;
	}

	public String getBrowser() {
		return Browser;
	}

	public void setBrowser(String browser) {
		Browser = browser;
	}

	public String getHubURL() {
		return HubURL;
	}

	public void setHubURL(String hubURL) {
		HubURL = hubURL;
	}

	public String getDBHost() {
		return DBHost;
	}

	public void setDBHost(String dBHost) {
		DBHost = dBHost;
	}

	public String getDBUserName() {
		return DBUserName;
	}

	public void setDBUserName(String dBUserName) {
		DBUserName = dBUserName;
	}

	public String getDBPassword() {
		return DBPassword;
	}

	public void setDBPassword(String dBPassword) {
		DBPassword = dBPassword;
	}

	public String getDBSchema() {
		return DBSchema;
	}

	public void setDBSchema(String dBSchema) {
		DBSchema = dBSchema;
	}

	public String getDBInstanceName() {
		return DBInstanceName;
	}

	public void setDBInstanceName(String dBInstanceName) {
		DBInstanceName = dBInstanceName;
	}

	public long getGridStartTime() {
		return gridStartTime;
	}

	public void setGridStartTime(long gridStartTime) {
		this.gridStartTime = gridStartTime;
	}

	public long getGridEndTime() {
		return gridEndTime;
	}

	public void setGridEndTime(long gridEndTime) {
		this.gridEndTime = gridEndTime;
	}

	public void captureScreenShot(WebDriver driver, String FileName) {
		File	ErrorScreenshot			= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// String FileName = "Method"+ i + ".png";
		File	ErrorMethodScreenShot	= new File("target/" + FileName + ".png");
		try {
			FileUtils.copyFile(ErrorScreenshot, ErrorMethodScreenShot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i++;
	}

	public String getGracenoteURL() {
		return GracenoteURL;
	}

	public void setGracenoteURL(String gracenoteURL) {
		GracenoteURL = gracenoteURL;
	}

	public String getAffiliateURL() {
		return AffiliateURL;
	}

	public void setAffiliateURL(String affiliateURL) {
		AffiliateURL = affiliateURL;
	}

	public String getSingleStaionURL() {
		return SingleStaionURL;
	}

	public void setSingleStaionURL(String singleStaionURL) {
		SingleStaionURL = singleStaionURL;
	}

	public String getGoogleAnalyticsTag() {
		return GoogleAnalyticsTag;
	}

	public void setGoogleAnalyticsTag(String googleAnalyticsTag) {
		GoogleAnalyticsTag = googleAnalyticsTag;
	}

	public String getComscoreAnalyticsTag() {
		return ComscoreAnalyticsTag;
	}

	public void setComscoreAnalyticsTag(String comscoreAnalyticsTag) {
		ComscoreAnalyticsTag = comscoreAnalyticsTag;
	}

	public String getAdsFileText() {
		return AdsFileText;
	}

	public void setAdsFileText(String adsFileText) {
		AdsFileText = adsFileText;
	}

	public String getGapzapAffiliateURL() {
		return gapzapAffiliateURL;
	}

	public void setGapzapAffiliateURL(String gapzapAffiliateURL) {
		this.gapzapAffiliateURL = gapzapAffiliateURL;
	}

	@Override
	public String toString() {
		return "GeneralApplicaitonProperties [OldSiteURL=" + OldSiteURL + ", NewSiteURL=" + NewSiteURL + ", ExcelFileName=" + ExcelFileName
		        + ", Browser=" + Browser + ", HubURL=" + HubURL + ", GoogleAnalyticsTag=" + GoogleAnalyticsTag + ", ComscoreAnalyticsTag="
		        + ComscoreAnalyticsTag + ", AdsFileText=" + AdsFileText + ", DBHost=" + DBHost + ", DBUserName=" + DBUserName
		        + ", DBPassword=" + DBPassword + ", DBSchema=" + DBSchema + ", DBInstanceName=" + DBInstanceName + ", GracenoteURL="
		        + GracenoteURL + ", AffiliateURL=" + AffiliateURL + ", SingleStaionURL=" + SingleStaionURL + ", LanguageEncodingURL="
		        + LanguageEncodingURL + ", gapzapAffiliateURL=" + gapzapAffiliateURL + ", gridStartTime=" + gridStartTime + ", gridEndTime="
		        + gridEndTime + "]";
	}

	public String getNewSiteURLAffiliates() {
		return NewSiteURLAffiliates;
	}

	public void setNewSiteURLAffiliates(String newSiteURLAffiliates) {
		NewSiteURLAffiliates = newSiteURLAffiliates;
	}

	/*
	 * public long getGridStartTime() { return gridStartTime; }
	 * 
	 * public void setGridStartTime(long gridStartTime) { this.gridStartTime =
	 * gridStartTime; }
	 * 
	 * public long getGridEndTime() { return gridEndTime; }
	 * 
	 * public void setGridEndTime(long gridEndTime) { this.gridEndTime =
	 * gridEndTime; }
	 */
}
