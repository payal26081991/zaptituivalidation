package com.gracenote.Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.gracenote.constants.IConstants;

/**
 * @author kunal.ashar
 * @descritpion to create testcase wise directory under output/Screenshots
 *
 */

public class Directory {

	private static Logger	logger			= Logger.getLogger(Directory.class);

	static File				tempFolderPath	= new File(IConstants.TEMP_SCREENSHOT_PATH);
	static File				passFolderPath	= new File(IConstants.PASS_SCREENSHOT_PATH);
	static File				failFolderPath	= new File(IConstants.FAIL_SCREENSHOT_PATH);

	/**
	 * @description to create a new directory
	 * @param testCaseName
	 */
	public void createTestCaseFolder(File path, String testCaseName) {
		if (path.exists()) {
			File f = new File(path.getAbsolutePath() + "/" + testCaseName);
			f.mkdir();
		}
	}

	/**
	 * @description to move testcase to passedCases folder from tempScreenshot
	 *              folder
	 * @param testCaseName
	 */
	public void copyToPass(String testCaseName) {

		try {

			File	tempPath	= new File(tempFolderPath.getAbsolutePath() + "/" + testCaseName);
			File	passPath	= new File(passFolderPath.getAbsolutePath() + "/" + testCaseName);

			createTestCaseFolder(passFolderPath, testCaseName);

			// copying file to pass directory
			FileUtils.copyDirectory(tempPath, passPath);

			// removing file from temp directory
			removeFile(tempPath);

			logger.info("Successfully moved TestCase " + testCaseName + " to PassedCases Directory");

		} catch (IOException e) {
			logger.error(
			        "Error while copying testCase folder to PassedCases. TestCaseName: " + testCaseName + " Generated Exception: " + e);
		}
	}

	/**
	 * @description to move testcase to failedCases folder from tempScreenshot
	 *              folder
	 * @param testCaseName
	 */
	public void copyToFail(String testCaseName) {

		try {

			File	tempPath	= new File(tempFolderPath.getAbsolutePath() + "/" + testCaseName);
			File	failPath	= new File(failFolderPath.getAbsolutePath() + "/" + testCaseName);

			createTestCaseFolder(failFolderPath, testCaseName);

			// copying file to fail directory
			FileUtils.copyDirectory(tempPath, failPath);

			// removing file from temp directory
			removeFile(tempPath);

			logger.info("Successfully moved TestCase " + testCaseName + "  to FailedCases Directory");

		} catch (IOException e) {
			logger.error(
			        "Error while copying testCase folder to FailedCases. TestCaseName: " + testCaseName + " Generated Exception: " + e);
		}
	}

	/**
	 * @description to clean output directory
	 * 
	 */
	public void cleanAllOutputDirectory() {

		File dir = new File(IConstants.OUTPUT_DIRECTORY_NAME);
		removeFile(dir);
	}

	/**
	 * @description to remove all files
	 * 
	 */
	public void removeFile(File dir) {

		File[] files = dir.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				logger.warn("Cleaning Directory: " + file.getPath());
				removeFile(file);
				file.delete();
			} else {
				file.delete();
			}
		}
		dir.delete();
	}

	/**
	 * @description to create output directory structure
	 * 
	 */
	public void createOutputDirectory() {
		File logs = new File(IConstants.OUTPUT_LOGS_PATH);
		logs.mkdirs();
		logger.info("Created Directory: " + logs.getPath());

		File multilingual = new File(IConstants.OUTPUT_MULTILINGUAL_PATH);
		multilingual.mkdirs();
		logger.info("Created Directory: " + multilingual.getPath());

		File passedCases = new File(passFolderPath.getPath());
		passedCases.mkdirs();
		logger.info("Created Directory: " + passFolderPath.getPath());

		File failedCases = new File(failFolderPath.getPath());
		failedCases.mkdirs();
		logger.info("Created Directory: " + failFolderPath.getPath());

		File tempScreenshot = new File(tempFolderPath.getPath());
		tempScreenshot.mkdirs();
		logger.info("Created Directory: " + tempFolderPath.getPath());

	}

	public static void main(String[] args) throws IOException {
		Directory d = new Directory();
		d.createOutputDirectory();
	}

}
