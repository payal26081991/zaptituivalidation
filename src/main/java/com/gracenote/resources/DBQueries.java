package com.gracenote.resources;

public class DBQueries {

	public String	MusicChannels				= "select callsign,tier from  Source so join HeadendLineups hl on hl.ProgSvcId =so.PrgSvcID "
	        + "WHERE hl.HeadendId=(select HeadendId from Headends where HeadendName like '<OPERATOR>%' and HeadendId in "
	        + "(select HeadendId from HeadendsPostalCodes where PostalCode='<POSTALCODE>')) and Device='<DEVICETYPE>' and"
	        + " CallSign in (<CALLSIGN>)";

	public String	UserGridPreference			= "select * from user_property_values where "
	        + "USER_ID=(select USER_ID from user where email like '<EMAILID>')" + " and PROP_ID=1318";

	/*
	 * public String ZipcodeCountryList =
	 * "select distinct hpc.PostalCode,he.Country from " +
	 * "HeadendsPostalCodes hpc join Headends he on he.HeadendId=hpc.HeadendId " +
	 * "where he.Country in ('USA','CAN')";
	 */

	public String	ZipcodeCountryList			= "select distinct hpc.PostalCode,he.Country from headendspostalcodes hpc join headends  he on hpc.HeadendId=he.HeadendId where he.Country in ('USA','CAN')";

	public String	CountryHeadendIDDeviceType	= "select distinct top 1000 h.Country,hl.HeadendId,ISNULL(hl.Device,'-') as DeviceType"
	        + " from Headends h join HeadendLineups hl on h.HeadendId=hl.HeadendId " + "where Country in ('USA')";
	// ,'CAN'

	public String	UserIDsFavShows				= "select distinct USER_ID from fav_shows";

	public String	ProgramSeriesID				= "select distinct s.TMSID, p.ProgramSeriesID from Schedules s join Program p on s.TMSID=p.TMSID where s.StartTime >= CURRENT_TIMESTAMP";

	public String	HeadedGridTest				= "select top 1 [type],name + ' - '+     "
	        + "(    CASE WHEN [type]='Satellite' THEN 'Satellite'         ELSE         "
	        + "(CASE WHEN ISNULL(ll.Device,'')='' then 'Cable'         WHEN ISNULL(ll.Device,'')='L' then 'Digital Rebuild'   "
	        + " ELSE 'Digital' end        )    end    ) as name,Country,PostalCode,ll.Device  from  (         "
	        + "select inn.[type],inn.HeadendId as headendId,inn.HeadendName as name,inn.location,inn.Country,inn.MSOId,         "
	        + "inn.MSOName,inn.timezone,'BLANK' as isDefaultProvider,inn.PostalCode         from          "
	        + "(   select h.HeadendId,h.HeadendName, ISNULL(h.Location,h.Country) as location, h.Country,ISNULL(hp.PostalCode,'All') as PostalCode,"
	        + "h.LineupType as [type], h.MSOId,h.MSOName,h.Timezone as timezone   from Headends h   left join HeadendsPostalCodes hp on hp.HeadendId=h.HeadendId    "
	        + "where h.LineupType='cable'    UNION ALL     select h.HeadendId,h.HeadendName, ISNULL(h.Location,h.Country) as location, h.Country,ISNULL(hp.PostalCode,'All') as PostalCode,"
	        + "h.LineupType as [type], h.MSOId,h.MSOName,h.Timezone as timezone   from Headends h   left join HeadendsPostalCodes hp on hp.HeadendId=h.HeadendId   "
	        + "where h.LineupType='satellite'          ) inn         where inn.Country in ('CAN','USA')   )abc  outer apply   (        "
	        + " select distinct device          from HeadendLineups hl         where abc.HeadendId=hl.HeadendId   )ll          "
	        + " ORDER BY NEWID()";

	// List of TMSid's shared by PM's to exclude from search
	public String	ProgramSearchAPIList		= "select distinct TMSID,AttributeValue from ProgramTitleMapping "
	        + "where TMSID in ('SH016734440000','SH022385100000','SH023816250000','SH012857740000','SH012925070000','SH017880990000','SH016777420000','SH022227750000','SH016734410000','SH018900530000','SH021358300000','SH021142050000','SH021142170000','SH022944880000','SH016819790000','SH016734380000','SH022644520000','SH016734420000','SH018900540000','SH020258500000','SH022803050000','SH016772960000','SH017881020000','SH018065790000','SH021494580000','SH023498170000','SH020196880000','SH014804530000','SH022644490000','SH018269830000','SH019293830000','SH016185690000','SH022644500000','SH022644510000','SH023011590000','SH025280740000','SH021142060000','SH021142160000','SH023752720000','SH023752730000','SH017155680000','SH017994030000','SH019242820000','SH017155690000','SH021142040000','SH016734340000','SH022770760000','SH013145300000','SH018709380000','SH021792740000','SH024995750000','SH016777400000','SH022632390000','SH015442700000','SH016734310000','SH016819780000','SH025139600000','SH016734290000','SH020721280000','SH016734320000','SH017130400000','SH014031120000','SH015798330000','SH014479940000','SH016938330000','SH018837560000','SH018999540000','SH016185670000','SH017155700000','SH020400810000','SH017155710000','SH019643770000','SH021797350000','SH016734540000','SH013684890000','SH017686670000','SH016777440000','SH016734520000','SH016819820000','SH016734490000','SH016734530000','SH016562450000','SH024152730000','SH025265470000','SH016185700000','SH017155730000','SH017155740000','SH019211600000','SH019227510000','SH019568840000','SH019677340000','SH023922660000','SH019220800000','SH019762020000','SH019657290000','SH021764870000','SH020847200000','SH020847310000','SH019843120000','SH019834120000','SH019176710000','SH019993080000','SH022814630000','SH021409940000','SH021409950000','SH021469990000','SH022814620000','SH019151700000','SH025072150000','SH025072160000','SH019158310000','SH020194030000','SH022903610000','SH023919810000','SH017684310000','SH025692770000','SH022649680000','SH020847190000','SH020847210000','SH024634450000','SH024578860000','SH019806680000','SH020332400000','SH020646030000')";

	public String	SeasonsCount				= "SELECT COUNT(DISTINCT IFNULL(ped.SeasonNumber,0)) as SeasonCount FROM program p JOIN programepisodedetails ped ON p.TMSID=ped.TMSID WHERE p.ProgramSeriesID=";

	public String	HeadendPostalWebAPI			= "select distinct he.HeadendId,IFNULL(hl.Device,'-') as DeviceType,he.Country from headends he join headendlineups hl on he.headendid=hl.headendid";

}
