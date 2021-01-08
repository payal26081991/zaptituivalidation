package com.gracenote.Utilities;

import org.apache.log4j.Logger;

import junit.framework.Assert;

public class TestCaseExecutionStatus {

	private static Logger logger = Logger.getLogger(TestCaseExecutionStatus.class);

	public static void markAsFail(String testCaseName, Exception e) {
		logger.error("Exception Occured: ", e);		
		Assert.fail("Execution of testcase " + testCaseName + " Failed");
	}

	public static void markAsFail(String testCaseName, AssertionError e) {
		logger.error("Assertion Error Occured: ", e);
		Assert.fail("Execution of testcase " + testCaseName + " Failed");
	}
	

}
