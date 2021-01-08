package com.gracenote.Utilities;

import org.apache.log4j.MDC;

/**
 * @author kunal.ashar
 * @description Used to print testcase name in log file
 * 
 */

public class ConfigureTestCaseName {

	final static String key = "testCaseName";

	public static String currentTestCaseName() {
		String testCaseName = Thread.currentThread().getStackTrace()[2].getMethodName();
		MDC.put(key, testCaseName);

		return testCaseName;
	}

}
