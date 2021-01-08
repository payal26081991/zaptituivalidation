package com.gracenote.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.DataProvider;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.gracenote.BO.GlobalInputData;
import com.gracenote.constants.IConstants;

public class ExcelReader {

	private static Logger			logger						= Logger.getLogger(ExcelReader.class);

	static List<Object[]>			GlobalData					= new ArrayList<Object[]>();
	static List<Object[]>			CastCrewData				= new ArrayList<Object[]>();
	static List<Object[]>			EpisodeGuideData			= new ArrayList<Object[]>();
	static List<Object[]>			StandardTVGridData			= new ArrayList<Object[]>();
	static List<Object[]>			SingleProviderTVGridData	= new ArrayList<Object[]>();
	static List<Object[]>			SingleStationTVGridData		= new ArrayList<Object[]>();
	public static List<Object[]>	MultilingualData			= new ArrayList<Object[]>();

	public static List<Object[]> ExcelFileReader() {
		Iterator	rowsIterator, cellsIterator;

		String		InputExcelFile	= IConstants.HEADEND_GRID_EXCEL_TESTDATA_FILE;

		try {
			logger.debug("Reading the Input file :: " + InputExcelFile);
			InputStream		ExcelIns	= new FileInputStream(InputExcelFile);

			HSSFWorkbook	wb			= new HSSFWorkbook(ExcelIns);

			HSSFSheet		sheet		= wb.getSheetAt(0);
			HSSFRow			row;
			HSSFCell		cell;
			DataFormatter	formatter	= new DataFormatter();
			rowsIterator	= sheet.rowIterator();
			row				= (HSSFRow) rowsIterator.next();
			while (rowsIterator.hasNext()) {
				logger.debug("Iterating the Row ");
				int CellCount = 0;
				row				= (HSSFRow) rowsIterator.next();

				cellsIterator	= row.cellIterator();

				GlobalInputData gid = new GlobalInputData();
				while (cellsIterator.hasNext()) {

					cell = (HSSFCell) cellsIterator.next();
					switch (CellCount) {
						case 0: {
							gid.setCountryName(formatter.formatCellValue(cell));
							break;
						}
						case 1: {
							gid.setZipCode(formatter.formatCellValue(cell));
							break;
						}
						case 2: {
							gid.setProviderList(new ArrayList<String>(Arrays.asList(formatter.formatCellValue(cell).split(","))));
							break;
						}
						case 3: {
							gid.setProviderTab(formatter.formatCellValue(cell));
							break;
						}
						case 4: {
							gid.setSelectedProvider(formatter.formatCellValue(cell));
							break;
						}
						case 5: {
							gid.setTMSID(formatter.formatCellValue(cell));
							break;
						}
						case 6: {
							gid.setUserEmail(formatter.formatCellValue(cell));
							break;
						}
						case 7: {
							gid.setPassword(formatter.formatCellValue(cell));
							break;
						}
					}
					CellCount++;
				}

				GlobalData.add(new Object[] { gid });

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

		logger.info("Returning the iterator object of the GlobalData class to invoking method.");
		return GlobalData;
	}

	private static List<Object[]> ExcelFileReaderCastCrew() {
		Iterator	rowsIterator, cellsIterator;

		String		InputExcelFile	= IConstants.CAST_CREW_EXCEL_TESTDATA_FILE;

		try {
			logger.info("Reading the Input file :: " + InputExcelFile);
			InputStream		ExcelIns	= new FileInputStream(InputExcelFile);

			HSSFWorkbook	wb			= new HSSFWorkbook(ExcelIns);

			HSSFSheet		sheet		= wb.getSheetAt(0);
			HSSFRow			row;
			HSSFCell		cell;
			DataFormatter	formatter	= new DataFormatter();
			rowsIterator	= sheet.rowIterator();
			row				= (HSSFRow) rowsIterator.next();
			while (rowsIterator.hasNext()) {
				logger.info("Iterating the Row ");
				int CellCount = 0;
				row				= (HSSFRow) rowsIterator.next();

				cellsIterator	= row.cellIterator();

				GlobalInputData gid = new GlobalInputData();
				while (cellsIterator.hasNext()) {

					cell = (HSSFCell) cellsIterator.next();
					switch (CellCount) {
						case 0: {
							gid.setProgramSeriesID(formatter.formatCellValue(cell));
							break;
						}
						case 1: {
							gid.setTMSID(formatter.formatCellValue(cell));
							break;
						}
					}
					CellCount++;
				}

				CastCrewData.add(new Object[] { gid });

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

		logger.info("Returning the iterator object of the GlobalData class to invoking method.");
		return CastCrewData;
	}

	private static List<Object[]> ExcelFileReaderEpisodeGuide() {
		Iterator	rowsIterator, cellsIterator;

		String		InputExcelFile	= IConstants.EPISODE_GUIDE_EXCEL_TESTDATA_FILE;

		try {
			logger.info("Reading the Input file :: " + InputExcelFile);
			InputStream		ExcelIns	= new FileInputStream(InputExcelFile);

			HSSFWorkbook	wb			= new HSSFWorkbook(ExcelIns);

			HSSFSheet		sheet		= wb.getSheetAt(0);
			HSSFRow			row;
			HSSFCell		cell;
			DataFormatter	formatter	= new DataFormatter();
			rowsIterator	= sheet.rowIterator();
			row				= (HSSFRow) rowsIterator.next();
			while (rowsIterator.hasNext()) {
				logger.info("Iterating the Row ");
				int CellCount = 0;
				row				= (HSSFRow) rowsIterator.next();

				cellsIterator	= row.cellIterator();

				GlobalInputData gid = new GlobalInputData();
				while (cellsIterator.hasNext()) {

					cell = (HSSFCell) cellsIterator.next();
					switch (CellCount) {
						case 0: {
							gid.setProgramSeriesID(formatter.formatCellValue(cell));
							break;
						}
						case 1: {
							gid.setTMSID(formatter.formatCellValue(cell));
							break;
						}
					}
					CellCount++;
				}

				EpisodeGuideData.add(new Object[] { gid });

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning the iterator object of the GlobalData class to invoking method.");
		return EpisodeGuideData;
	}

	private static List<Object[]> ExcelFileReaderAffiliateStandardTVGrid() {
		Iterator	rowsIterator, cellsIterator;

		String		InputExcelFile	= IConstants.AFFILIATE_MAPPING_EXCEL_TESTDATA_FILE;

		try {
			logger.info("Reading the Input file :: " + InputExcelFile);
			InputStream		ExcelIns	= new FileInputStream(InputExcelFile);

			HSSFWorkbook	wb			= new HSSFWorkbook(ExcelIns);

			HSSFSheet		sheet		= wb.getSheetAt(0);
			HSSFRow			row;
			HSSFCell		cell;
			DataFormatter	formatter	= new DataFormatter();
			rowsIterator	= sheet.rowIterator();
			row				= (HSSFRow) rowsIterator.next();
			while (rowsIterator.hasNext()) {
				logger.info("Iterating the Row: " + row.getRowNum());
				int CellCount = 0;
				row				= (HSSFRow) rowsIterator.next();

				cellsIterator	= row.cellIterator();

				GlobalInputData gid = new GlobalInputData();
				while (cellsIterator.hasNext()) {

					cell = (HSSFCell) cellsIterator.next();
					switch (CellCount) {
						case 0: {
							gid.setAffiliateTag(formatter.formatCellValue(cell));
							break;
						}
						case 1: {
							gid.setSelectedProvider(formatter.formatCellValue(cell));
							break;
						}
						case 2: {
							gid.setZipCode(formatter.formatCellValue(cell));
							break;
						}
					}
					CellCount++;
				}

				StandardTVGridData.add(new Object[] { gid });

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return StandardTVGridData;
	}

	private static List<Object[]> ExcelFileReaderAffiliateSingleProviderTVGrid() {
		Iterator	rowsIterator, cellsIterator;

		String		InputExcelFile	= IConstants.AFFILIATE_MAPPING_EXCEL_TESTDATA_FILE;

		try {
			logger.info("Reading the Input file :: " + InputExcelFile);
			InputStream		ExcelIns	= new FileInputStream(InputExcelFile);

			HSSFWorkbook	wb			= new HSSFWorkbook(ExcelIns);

			HSSFSheet		sheet		= wb.getSheetAt(1);
			HSSFRow			row;
			HSSFCell		cell;
			DataFormatter	formatter	= new DataFormatter();
			rowsIterator	= sheet.rowIterator();
			row				= (HSSFRow) rowsIterator.next();
			while (rowsIterator.hasNext()) {
				logger.info("Iterating the Row ");
				int CellCount = 0;
				row				= (HSSFRow) rowsIterator.next();

				cellsIterator	= row.cellIterator();

				GlobalInputData gid = new GlobalInputData();
				while (cellsIterator.hasNext()) {

					cell = (HSSFCell) cellsIterator.next();
					switch (CellCount) {
						case 0: {
							gid.setAffiliateTag(formatter.formatCellValue(cell));
							break;
						}
						case 1: {
							gid.setSelectedProvider(formatter.formatCellValue(cell));
							break;
						}
						case 2: {
							gid.setZipCode(formatter.formatCellValue(cell));
							break;
						}
					}
					CellCount++;
				}

				SingleProviderTVGridData.add(new Object[] { gid });

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return SingleProviderTVGridData;
	}

	private static List<Object[]> ExcelFileReaderAffiliateSingleStationTVGrid() {
		Iterator	rowsIterator, cellsIterator;

		String		InputExcelFile	= IConstants.AFFILIATE_MAPPING_EXCEL_TESTDATA_FILE;

		try {
			logger.info("Reading the Input file :: " + InputExcelFile);
			InputStream		ExcelIns	= new FileInputStream(InputExcelFile);

			HSSFWorkbook	wb			= new HSSFWorkbook(ExcelIns);

			HSSFSheet		sheet		= wb.getSheetAt(2);
			HSSFRow			row;
			HSSFCell		cell;
			DataFormatter	formatter	= new DataFormatter();
			rowsIterator	= sheet.rowIterator();
			row				= (HSSFRow) rowsIterator.next();
			while (rowsIterator.hasNext()) {
				logger.info("Iterating the Row ");

				row				= (HSSFRow) rowsIterator.next();

				cellsIterator	= row.cellIterator();

				GlobalInputData gid = new GlobalInputData();
				while (cellsIterator.hasNext()) {
					cell = (HSSFCell) cellsIterator.next();
					gid.setAffiliateTag(formatter.formatCellValue(cell));
					break;
				}

				SingleStationTVGridData.add(new Object[] { gid });

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return SingleStationTVGridData;
	}

	private static List<Object[]> ExcelFileReaderMultilingual() {

		Iterator	rowsIterator, cellsIterator;

		String		InputExcelFile	= IConstants.MULTILINGUAL_EXCEL_TESTDATA_FILE;

		try {
			logger.info("Reading the Input file :: " + InputExcelFile);
			InputStream		ExcelIns	= new FileInputStream(InputExcelFile);

			HSSFWorkbook	wb			= new HSSFWorkbook(ExcelIns);

			HSSFSheet		sheet		= wb.getSheet("Multilingual");
			HSSFRow			row;
			HSSFCell		cell;
			DataFormatter	formatter	= new DataFormatter();
			rowsIterator	= sheet.rowIterator();
			row				= (HSSFRow) rowsIterator.next();
			while (rowsIterator.hasNext()) {
				logger.info("Iterating the Row ");
				int CellCount = 0;

				row				= (HSSFRow) rowsIterator.next();

				cellsIterator	= row.cellIterator();

				GlobalInputData gid = new GlobalInputData();
				while (cellsIterator.hasNext()) {

					cell = (HSSFCell) cellsIterator.next();
					switch (CellCount) {
						case 0: {
							gid.setLangCode(formatter.formatCellValue(cell));
							break;
						}
						case 1: {
							gid.setLangID(formatter.formatCellValue(cell));
							break;
						}
						case 2: {
							gid.setMultiLingualAffiliateTag(formatter.formatCellValue(cell));
							break;
						}
						case 3: {
							gid.setSingleProviderAffiliateTag(formatter.formatCellValue(cell));
							break;
						}
						case 4: {
							gid.setSingleStationAffiliateTag(formatter.formatCellValue(cell));
							break;
						}
					}
					CellCount++;
				}

				MultilingualData.add(new Object[] { gid });

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

		return MultilingualData;
	}

	public Map<String, LinkedList<String>> readGeneratedMultilingualFile(String testCaseName) {

		Map<String, LinkedList<String>>	multilingualMap	= new HashMap<String, LinkedList<String>>();

		String							filePath		= IConstants.OUTPUT_MULTILINGUAL_PATH + "/" + testCaseName + ".xls";
		HSSFWorkbook					wb				= null;

		try {
			logger.info("Reading Multilingual Validation file :: " + filePath);
			InputStream ExcelIns = new FileInputStream(filePath);

			wb = new HSSFWorkbook(ExcelIns);

			HSSFSheet	sheet		= wb.getSheetAt(0);

			int			rowCount	= sheet.getPhysicalNumberOfRows();
			int			colCount	= sheet.getRow(0).getPhysicalNumberOfCells();

			String		key			= null;

			for (int i = 0; i < colCount; i++) {

				key = sheet.getRow(0).getCell(i).getStringCellValue();
				LinkedList<String> values = new LinkedList<String>();

				for (int j = 1; j < rowCount; j++) {

					try {

						if (sheet.getRow(j).getCell(i).getStringCellValue() != null) {
							String cellValue = sheet.getRow(j).getCell(i).getStringCellValue();
							values.add(cellValue);
						}
					} catch (NullPointerException e) {
						logger.error("Exception occured while reading data from Multilingual Excel at Row: " + j + " Cell: " + i);
					}
				}

				multilingualMap.put(key, values);
			}

			wb.close();

			logger.info("Excel Reading Completed for Multilingual Validation file");

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return multilingualMap;

	}

	@DataProvider(name = "TestDataProviders") // , parallel = true
	public static synchronized Iterator<Object[]> testDataProvider() {
		if (GlobalData.size() > 0) {
			logger.info("Size of our Data Provider List is :: " + GlobalData.size());
			return GlobalData.iterator();
		} else {
			logger.info("Size of our Data Provider List is :: " + GlobalData.size());
			return ExcelFileReader().iterator();
		}
	}

	@DataProvider(name = "TestDataProvidersCastCrew") // , parallel = true
	public static synchronized Iterator<Object[]> testDataProviderCastCrew() {
		logger.warn("Size of our Data Provider List is :: " + CastCrewData.size());
		if (CastCrewData.size() > 0)
			return CastCrewData.iterator();
		else
			return ExcelFileReaderCastCrew().iterator();
	}

	@DataProvider(name = "TestDataProvidersEpisodeGuideSeasons") // , parallel = true
	public static synchronized Iterator<Object[]> TestDataProvidersEpisodeGuideSeasons() {
		logger.warn("Size of our Data Provider List is :: " + EpisodeGuideData.size());
		if (EpisodeGuideData.size() > 0)
			return EpisodeGuideData.iterator();
		else
			return ExcelFileReaderEpisodeGuide().iterator();
	}

	@DataProvider(name = "TestDataProvidersStandardAffiliate") // , parallel = true
	public static synchronized Iterator<Object[]> TestDataProvidersStandardAffiliate() {
		logger.warn("Size of our Data Provider List is :: " + StandardTVGridData.size());
		if (StandardTVGridData.size() > 0)
			return StandardTVGridData.iterator();
		else
			return ExcelFileReaderAffiliateStandardTVGrid().iterator();
	}

	@DataProvider(name = "TestDataProvidersSingleProvider") // , parallel = true
	public static synchronized Iterator<Object[]> TestDataProvidersSingleProvider() {
		logger.warn("Size of our Data Provider List is :: " + SingleProviderTVGridData.size());
		if (SingleProviderTVGridData.size() > 0)
			return SingleProviderTVGridData.iterator();
		else
			return ExcelFileReaderAffiliateSingleProviderTVGrid().iterator();
	}

	@DataProvider(name = "TestDataProvidersSingleStation") // , parallel = true
	public static synchronized Iterator<Object[]> TestDataProvidersSingleStation() {
		logger.warn("Size of our Data Provider List is :: " + SingleStationTVGridData.size());
		if (SingleStationTVGridData.size() > 0)
			return SingleStationTVGridData.iterator();
		else
			return ExcelFileReaderAffiliateSingleStationTVGrid().iterator();
	}

	@DataProvider(name = "TestDataProvidersMultilingual") // , parallel = true
	public static synchronized Iterator<Object[]> TestDataProvidersMultilingual() {
		logger.warn("Size of our Data Provider List is :: " + MultilingualData.size());
		if (MultilingualData.size() > 0)
			return MultilingualData.iterator();
		else
			return ExcelFileReaderMultilingual().iterator();
	}

	public static void main(String[] args) {
		ExcelReader e = new ExcelReader();
		// e.readGeneratedMultilingualFile();
	}
}
