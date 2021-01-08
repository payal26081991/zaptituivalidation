package com.gracenote.resources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.gracenote.constants.IConstants;

/**
 * @author kunal.ashar
 *
 */
public class CompareMultilingualData {

	private static Logger			logger		= Logger.getLogger(CompareMultilingualData.class);

	Map<String, LinkedList<String>>	excelData	= new HashMap<String, LinkedList<String>>();

	/**
	 * @description call this method to start comparing multilingual data
	 */
	public void startComparison(String testCaseName) {

		logger.info("Starting comparision for testcase: " + testCaseName);

		getDataFromExcel(testCaseName);
	}

	public void getDataFromExcel(String testCaseName) {

		ExcelReader er = new ExcelReader();

		excelData = er.readGeneratedMultilingualFile(testCaseName);

		// to fetch columns to be compared
		getCompareFromAndCompareTo(testCaseName);
	}

	public void getCompareFromAndCompareTo(String testCaseName) {

		String		compareFrom	= null;
		String		compareTo	= null;

		// to fetch all keys
		Set<String>	keys		= excelData.keySet();
		Set<String>	updatedKeys	= new TreeSet<String>();

		// created new set to alter data at runtime
		updatedKeys.addAll(keys);

		// to remove english language data
		for (String s : keys) {
			String key = s.split("_")[0];
			if (key.equalsIgnoreCase(IConstants.ENGLISH_LANGAUGE_CODE)) {
				updatedKeys.remove(key);
			}
		}

		// created new set to alter data at runtime
		Set<String> keys1 = new TreeSet<String>();
		keys1.addAll(updatedKeys);

		// comparing pending keys to find compareFrom and compareTo
		for (String s : updatedKeys) {

			keys1.remove(s);

			for (String s1 : keys1) {
				if (s.split("_")[1].equalsIgnoreCase(s1.split("_")[1])) {
					compareFrom	= s;
					compareTo	= s1;

					// Comparing Excel column data
					compareExcelData(compareFrom, compareTo, testCaseName);
				}
			}

		}
	}

	public void compareExcelData(String compareFrom, String compareTo, String testCaseName) {

		LinkedList<String>	compareFromList	= excelData.get(compareFrom);
		LinkedList<String>	compareToList	= excelData.get(compareTo);
		LinkedList<String>	resultList		= new LinkedList<String>();

		// verifying size of both list
		if (!(compareFromList.size() == compareToList.size())) {
			logger.error("Cannot compare list as size is different. Comparing From: " + compareFrom + " Comparing To: " + compareTo);
		} else {

			// verifying if data is matched properly
			for (int i = 0; i < compareFromList.size(); i++) {

				if (compareFromList.get(i).equalsIgnoreCase(compareToList.get(i))) {
					resultList.add(IConstants.PASS);
				} else {
					resultList.add(IConstants.FAIL);
					logger.error("String mismatch found while comparing data. Compare From: " + compareFromList.get(i) + " Compare To: "
					        + compareToList.get(i));
				}

			}

			// writing results to excel file
			writeResultToExcel(compareFrom.split("_")[1], resultList, testCaseName);
		}

	}

	/**
	 * 
	 * @param langId
	 * @param resultList
	 */
	public void writeResultToExcel(String langId, LinkedList<String> resultList, String testCaseName) {

		ExcelWriter ew = new ExcelWriter();
		ew.writeMultilingualExcel(IConstants.RESULT, langId, resultList, testCaseName);

	}

	public static void main(String[] args) {
		CompareMultilingualData cd = new CompareMultilingualData();
		// cd.startComparison();
	}

}
