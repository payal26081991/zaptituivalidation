package com.gracenote.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gracenote.constants.IConstants;

public class ExcelWriter {

	private static Logger logger = Logger.getLogger(ExcelWriter.class);

	public void writeMultilingualExcel(String langCode, String langId, List<String> langText, String testCaseName) {

		try {

			logger.info("Writing Text:" + langText);

			HSSFWorkbook	wb			= new HSSFWorkbook();
			HSSFSheet		sh			= null;
			HSSFRow			row			= null;

			// String fileName = IConstants.MULTILINGUAL_EXCEL_OUTPUT_FILE_NAME;
			String			fileName	= IConstants.OUTPUT_MULTILINGUAL_PATH + "/" + testCaseName + ".xls";

			File			file		= new File(fileName);

			if (!file.exists()) {
				sh	= wb.createSheet("Sheet1");

				row	= sh.createRow(0);
				row.createCell(0).setCellValue(langCode.trim() + "_" + langId);
				int rowCount = 1;

				for (String s : langText) {
					row = sh.createRow(rowCount);
					if (s != null) {
						row.createCell(0).setCellValue(s.trim());
					}
					rowCount++;
				}

			} else {
				FileInputStream fis = new FileInputStream(file);
				wb	= new HSSFWorkbook(fis);
				sh	= wb.getSheetAt(0);
				int rowCount = sh.getPhysicalNumberOfRows();

				row = sh.getRow(0);

				int colCount = row.getPhysicalNumberOfCells();

				row.createCell(colCount).setCellValue(langCode.trim() + "_" + langId);

				for (int i = 1; i < rowCount; i++) {

					row			= sh.getRow(i);

					colCount	= row.getPhysicalNumberOfCells();
					if (langText.get(i - 1) != null) {
						row.createCell(colCount).setCellValue(langText.get(i - 1).trim());
					} else {
						System.out.println(langText.get(i - 1));
					}
				}
			}

			FileOutputStream fout = new FileOutputStream(fileName);
			wb.write(fout);

			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExcelWriter		w	= new ExcelWriter();

		List<String>	l1	= new LinkedList<String>();
		l1.add("a1");
		l1.add("a2");
		l1.add("a3");

		List<String> l2 = new LinkedList<String>();
		l2.add("a4");
		l2.add("a5");
		l2.add("a6");

		/*w.writeMultilingualExcel("en", "41", l1);
		w.writeMultilingualExcel("es", "65", l2);*/
	}

}
