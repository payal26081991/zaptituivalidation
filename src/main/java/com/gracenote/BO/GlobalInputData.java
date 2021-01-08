package com.gracenote.BO;

import java.util.ArrayList;
import java.util.List;

public class GlobalInputData {

	private String			countryName		= "", ZipCode = "", SelectedProvider = "", ProviderTab = "", TMSID = "";
	private List<String>	ProviderList	= new ArrayList<String>();
	private String			UserEmail		= "asmann@gracenote.com", Password = "012345", UserID = "",
	        signupUserEmail = "GNIndiaQA@gracenote.com";
	private String			ProgramSeriesID	= "";
	private String			HeadendId		= "", DeviceType = "", AffiliateTag = "",multiLingualAffiliateTag	= "" , singleProviderAffiliateTag = "" , singleStationAffiliateTag = "";
	private String			jumpToTime 		= "1" , jumpToDate = "1";
	private String			jumpToLastTime 		= "27" , jumpToLastDate = "14";
	
	private String			tvGridAffiliate	= "newsdaytv";
	private String			singleProviderAffiliate	= "eastlink";
	private String			timeZone		= "Mountain";
//	private String 			singleStationURL= "https://tvlistings.preprod.gracenote.com/ss-list-affiliates.html?aid=newsdaytv";
	private String 			singleStationURL= "/ss-list-affiliates.html?";
	private String			StarredUserEmail		= "gapzaptest@gmail" , StarreduserPassword = "123" ;
	private long            scrollPosition			= 2200;
	private String 			googleUserEmail = "Rockygapzap@gmail.com" , googleUserPassword="Trade@66";
			
	       
	
	public long getScrollPos() {
		return scrollPosition;
	}

	public void setScrollPos(long scrollPosition) {
		this.scrollPosition = scrollPosition;
	}
	
	
	public String getSingleStaionURL()
	{
		return singleStationURL;
	}
	
	public String getTvGridAffiliate() {
		return tvGridAffiliate;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public void setTvGridAffiliate(String tvGridAffiliate) {
		this.tvGridAffiliate = tvGridAffiliate;
	}

	public String getJumpToTime() {
		return jumpToTime;
	}

	public void setJumpToTime(String jumpToTime) {
		this.jumpToTime = jumpToTime;
	}
	
	public String getJumpToLastTime() {
		return jumpToLastTime;
	}

	public void setJumpToLastTime(String jumpToLastTime) {
		this.jumpToLastTime = jumpToLastTime;
	}
	

	public String getJumpToDate() {
		return jumpToDate;
	}

	public void setJumpToDate(String jumpToDate) {
		this.jumpToDate = jumpToDate;
	}
	
	

	public String getJumpToLastDate() {
		return jumpToLastDate;
	}

	public void setJumpToLastDate(String jumpToLastDate) {
		this.jumpToLastDate = jumpToLastDate;
	}
	
	

	public String getSingleStationAffiliateTag() {
		return singleStationAffiliateTag;
	}

	public void setSingleStationAffiliateTag(String singleStationAffiliateTag) {
		this.singleStationAffiliateTag = singleStationAffiliateTag;
	}

	public String getSingleProviderAffiliateTag() {
		return singleProviderAffiliateTag;
	}

	public void setSingleProviderAffiliateTag(String singleProviderAffiliateTag) {
		this.singleProviderAffiliateTag = singleProviderAffiliateTag;
	}

	public String getMultiLingualAffiliateTag() {
		return multiLingualAffiliateTag;
	}

	public void setMultiLingualAffiliateTag(String multiLingualAffiliateTag) {
		this.multiLingualAffiliateTag = multiLingualAffiliateTag;
	}

	private String			ProgramName		= "";
	private String			langCode		= "", langID = "";
	 			
	
	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getLangID() {
		return langID;
	}

	public void setLangID(String langID) {
		this.langID = langID;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		this.ZipCode = zipCode;
	}

	public void setProviderList(List<String> incomingList) {
		this.ProviderList = incomingList;
	}

	public List<String> getProivderList() {
		return ProviderList;
	}

	public String getSelectedProvider() {
		return SelectedProvider;
	}

	public void setSelectedProvider(String selectedProvider) {
		SelectedProvider = selectedProvider;
	}

	public String getProviderTab() {
		return ProviderTab;
	}

	public void setProviderTab(String providerTab) {
		ProviderTab = providerTab;
	}

	public String getTMSID() {
		return TMSID;
	}

	public void setTMSID(String tMSID) {
		TMSID = tMSID;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	
	public String getStarredUserEmail() {
		return StarredUserEmail;
	}

	public void setStarredUserEmail(String userEmail) {
		StarredUserEmail = userEmail;
	}

	public String getStarredPassword() {
		return StarreduserPassword;
	}

	public void setStarredPassword(String password) {
		StarreduserPassword = password;
	}
	

	public String getHeadendId() {
		return HeadendId;
	}

	public void setHeadendId(String headendId) {
		HeadendId = headendId;
	}

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getProgramSeriesID() {
		return ProgramSeriesID;
	}

	public void setProgramSeriesID(String ProgSeriesID) {
		ProgramSeriesID = ProgSeriesID;
	}

	public String getProgramName() {
		return ProgramName;
	}

	public void setProgramName(String programName) {
		ProgramName = programName;
	}

	public String getSignupUserEmail() {
		return signupUserEmail;
	}

	public void setSignupUserEmail(String signupUserEmail) {
		this.signupUserEmail = signupUserEmail;
	}

	public String getAffiliateTag() {
		return AffiliateTag;
	}

	public void setAffiliateTag(String affiliateTag) {
		AffiliateTag = affiliateTag;
	}
	
	public String getSingleProviderAffiliate() {
		return singleProviderAffiliate;
	}

	public void setSingleProviderAffiliate(String singleProviderAffiliate) {
		this.singleProviderAffiliate = singleProviderAffiliateTag;
	}
	
	public String getGoogleUserEmail() {
		return googleUserEmail;
	}

	public void setGoogleUserEmail(String googleUserEmailID) {
		this.googleUserEmail = googleUserEmailID;
	}
	
	
	public String getGoogleUserPassword() {
		return googleUserPassword;
	}

	public void setGoogleUserPassword(String googleUserPwd) {
		this.googleUserPassword = googleUserPwd;
	}


	@Override
	public String toString() {
		return "GlobalInputData [countryName=" + countryName + ", ZipCode=" + ZipCode + ", SelectedProvider=" + SelectedProvider
		        + ", ProviderTab=" + ProviderTab + ", TMSID=" + TMSID + ", ProviderList=" + ProviderList + ", UserEmail=" + UserEmail
		        + ", Password=" + Password + ", UserID=" + UserID + ", signupUserEmail=" + signupUserEmail + ", ProgramSeriesID="
		        + ProgramSeriesID + ", HeadendId=" + HeadendId + ", DeviceType=" + DeviceType + ", AffiliateTag=" + AffiliateTag
		        + ", googleUserEmail=" + googleUserEmail + ",googleUserPassword=" + googleUserPassword+ ", ProgramName=" + ProgramName + ", langCode=" + langCode + ", langID=" + langID + "]";
	}

}
