package com.gracenote.Utilities;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.gracenote.Listeners.TestNgListeners;
import com.gracenote.TestCases.UpdatedSprint6;

public class StartupAndTearDown {

	Logger		logger	= Logger.getLogger(StartupAndTearDown.class);

	static Date	startTime, endTime;

	@BeforeSuite
	public void configureWorkspace() {

		startTime = new Date();

		Directory d = new Directory();

		// to clean output directory
		d.cleanAllOutputDirectory();

		// to setup a fresh output directory
		d.createOutputDirectory();
	}

	@AfterSuite
	public void cleanUp() {
		endTime = new Date();

		long	diffTime	= endTime.getTime() - startTime.getTime();

		long	minutes		= (diffTime / 1000) / 60;
		long	seconds		= (diffTime / 1000) % 60;

		logger.info("\n\nTotal Cases Executed :: " + TestNgListeners.testCaseCount + " Cases.\n\nPass Case Count :: "
		        + TestNgListeners.passCaseCount + "\n\nFail Case Count :: " + TestNgListeners.failCaseCount + "\n\nTotal Execution Time :: "
		        + minutes + " Minutes : " + seconds + " Seconds.");
	}

	public static void main(String[] args) throws InterruptedException {

		startTime = new Date();
		System.out.println(startTime.getTime());

		Thread.sleep(5000);

		endTime = new Date();
		System.out.println(endTime.getTime());

		long diffTime = endTime.getTime() - startTime.getTime();
		System.out.println("Diff time :" + diffTime);

		long	minutes	= (diffTime / 1000) / 60;
		long	seconds	= (diffTime / 1000) % 60;

		System.out.format("%d Milliseconds = %d minutes and %d seconds.", diffTime, minutes, seconds);

	}
}
