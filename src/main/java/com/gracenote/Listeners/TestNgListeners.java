package com.gracenote.Listeners;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.gracenote.Utilities.Directory;

public class TestNgListeners implements ITestListener {

	public static int					testCaseCount;
	public static int					passCaseCount;
	public static int					failCaseCount;

	public static Map<String, Integer>	testCaseOccurance	= new HashMap<String, Integer>();

	private static Logger				logger				= Logger.getLogger(TestNgListeners.class);

	String								logPattern			= "-------------------------------------------------------------------------------------";

	@Override
	public void onTestStart(ITestResult result) {

		String testCaseName = result.getName();

		if (testCaseOccurance.size() > 0) {

			if (testCaseOccurance.get(testCaseName) != null) {

				testCaseOccurance.put(testCaseName, testCaseOccurance.get(testCaseName) + 1);
			} else {
				testCaseOccurance.put(testCaseName, 1);
			}

		} else {
			testCaseOccurance.put(testCaseName, 1);
		}

		testCaseCount++;

		logger.info("\n" + logPattern + "\n\t\tStarting Test Case ::" + testCaseName + "\n\t\tIteration :: "
		        + testCaseOccurance.get(testCaseName) + "\n" + logPattern);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		passCaseCount++;

		Directory d = new Directory();

		d.copyToPass(result.getName());

		logger.info("\n" + logPattern + "\n\t\t TestCase " + result.getName() + " Passed.\n" + logPattern);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		failCaseCount++;

		Directory d = new Directory();

		d.copyToFail(result.getName());

		logger.error("\n" + logPattern + "\n\t\t TestCase " + result.getName() + " Failed.\n" + logPattern);
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
