/*
 * package com.gracenote.Miscellaneous;
 * 
 * import java.io.IOException;
 * 
 * import org.apache.log4j.Logger; import org.json.JSONArray; import
 * org.json.JSONObject; import org.testng.annotations.AfterSuite; import
 * org.testng.annotations.Test;
 * 
 * import com.gracenote.BO.GlobalInputData; import
 * com.mashape.unirest.http.HttpResponse; import
 * com.mashape.unirest.http.Unirest; import
 * com.mashape.unirest.http.exceptions.UnirestException;
 * 
 * public class ImagePresenceVerification {
 * 
 * private static Logger logger =
 * Logger.getLogger(ImagePresenceVerification.class); long CurrentTime =
 * System.currentTimeMillis()/1000;
 * 
 * static long counter=0,RecordCount=0;;
 * 
 * @Test(dataProvider="TestDataProviderCountryHeadendIdsDeviceTypes",
 * dataProviderClass=com.gracenote.resources.DBDataProvider.class,threadPoolSize
 * =20) public void verifyProgramDropCellImages(GlobalInputData gid) { String
 * hostURL =
 * "http://35.154.17.145:4040/api/grid?lineupId="+gid.getCountryName()+"-"+gid.
 * getHeadendId()+"-"+gid.getDeviceType()+
 * "&timespan=3&excludeChannels=m%2Cp%2Ch" +
 * "&headendId="+gid.getHeadendId()+"&country="+gid.getCountryName()+"&device="+
 * gid.getDeviceType()+"&postalCode=0&time="+CurrentTime+
 * "&pref=-&userId=-&chunk=1";
 * 
 * logger.info(hostURL); try { RecordCount++; HttpResponse<String> GridPrograms
 * = Unirest.get(hostURL).asString();
 * 
 * JSONObject responseObj = new JSONObject(GridPrograms.getBody().toString());
 * 
 * JSONArray Channels = responseObj.getJSONArray("channels");
 * 
 * logger.info("Size of channels count is : " +Channels.length());
 * 
 * for(int i =1; i < Channels.length();i++) {
 * 
 * JSONObject channelJson = Channels.getJSONObject(i);
 * 
 * JSONArray Events = channelJson.getJSONArray("events");
 * 
 * for (int j =1 ; j < Events.length(); j++) { logger.info("value of i is : " +i
 * + " and value of j is : "+ j); JSONObject ChannelEvent =
 * Events.getJSONObject(j); logger.info("Event Array is :"+ ChannelEvent);
 * String ProgramImage = ChannelEvent.getString("thumbnail");
 * 
 * JSONObject ProgramJson = ChannelEvent.getJSONObject("program"); counter++; if
 * (ProgramImage.length() <1)
 * logger.error("image value not found for Country: "+gid.getCountryName()
 * +" HeadendID is : "+gid.getHeadendId() +
 * " and Device Type is :"+gid.getDeviceType() +
 * " ohhTMS id is : "+ProgramJson.getString("tmsId")); } }
 * 
 * 
 * } catch (UnirestException e) { // TODO Auto-generated catch block
 * logger.error(e); e.printStackTrace(); } catch (Exception e) {
 * logger.error(e); e.printStackTrace(); }
 * 
 * if((RecordCount%5000)==0)
 * logger.error("Total Records Processed are :: "+RecordCount); }
 * 
 * @Test(dataProvider="TestDataProviderUserIDsFavShows",dataProviderClass=com.
 * gracenote.resources.DBDataProvider.class,threadPoolSize=20) public void
 * verifyUserFavShowImages(GlobalInputData gid) { String hostURL =
 * "http://35.154.17.145:4040/api/user/myShowsAiring?headendId=lineupId&countryCode=CAN&postalCode=00000&device=&userId="
 * +gid.getUserID(); RecordCount++;
 * 
 * logger.info(hostURL); try { HttpResponse<String> UserFavShows =
 * Unirest.get(hostURL).asString();
 * 
 * JSONObject responseObj = new JSONObject(UserFavShows.getBody().toString());
 * 
 * JSONArray FavShowsList = responseObj.getJSONArray("myFavouriteShows");
 * 
 * logger.info("Size of User Fav show count is : " +FavShowsList.length()
 * +" : userID is :"+gid.getUserID());
 * 
 * for(int i =0; i < FavShowsList.length();i++) {
 * 
 * counter++; JSONObject ProgramJson = FavShowsList.getJSONObject(i); String
 * SeriesImageName = ProgramJson.getString("seriesImage"); String TMSID =
 * ProgramJson.getString("tmsID"); if(SeriesImageName.length() < 1)
 * logger.error("Image name is found for TMS ID : "+TMSID
 * +" : userid is : "+gid.getUserID()); }
 * 
 * if((RecordCount%5000)==0)
 * logger.error("Total Records Processed are :: "+RecordCount);
 * 
 * 
 * 
 * } catch (UnirestException e) { // TODO Auto-generated catch block
 * logger.error(e); e.printStackTrace(); } catch (Exception e) {
 * logger.error(e); e.printStackTrace(); } }
 * 
 * 
 * @Test(dataProvider="TestDataProviderProgramShowcardImages",dataProviderClass=
 * com.gracenote.resources.DBDataProvider.class,threadPoolSize=20) public void
 * verifyProgramShowcardImages(GlobalInputData gid) { String hostURL =
 * "http://35.154.17.145:4040/api/program/overviewDetails?headendId=CA04960&countryCode=USA&postalCode=00000&device=X&programSeriesID="
 * +gid.getProgramSeriesID(); RecordCount++;
 * 
 * logger.info(hostURL); try { HttpResponse<String> UserFavShows =
 * Unirest.get(hostURL).asString();
 * 
 * JSONObject responseObj = new JSONObject(UserFavShows.getBody().toString());
 * 
 * String SeriesImage = responseObj.getString("seriesImage"); String BannerImage
 * = responseObj.getString("backgroundImage");
 * 
 * if(SeriesImage.length() <1)
 * logger.error("Program Series image not found : "+gid.getProgramSeriesID() +
 * " : with TMS id as :"+gid.getTMSID());
 * 
 * if(BannerImage.length() <1)
 * logger.error("Program Banner image not found : "+gid.getProgramSeriesID() +
 * " : with TMS id as :"+gid.getTMSID());
 * 
 * if((RecordCount%5000)==0)
 * logger.error("Total Records Processed are :: "+RecordCount);
 * 
 * 
 * 
 * } catch (UnirestException e) { // TODO Auto-generated catch block
 * logger.error(e); e.printStackTrace(); } catch (Exception e) {
 * logger.error(e); e.printStackTrace(); } }
 * 
 * @AfterSuite public void ClearingConnections() {
 * logger.error("Total parsed programs are : "+counter); try {
 * Unirest.shutdown(); } catch (IOException e) { // TODO Auto-generated catch
 * block logger.error(e); e.printStackTrace(); } }
 * 
 * }
 */