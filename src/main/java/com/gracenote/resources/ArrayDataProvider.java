package com.gracenote.resources;

import org.testng.annotations.DataProvider;

public class ArrayDataProvider {

	@DataProvider(name = "TestArrayDataProvider") // , parallel = true
	public static Object[][] testArrayDataProvider() {
		return new Object[][] { { "USA", "Central", "7:00 PM" }, { "USA", "Mountain", "7:00 PM" }, { "USA", "Alaskan", "8:00 PM" },
		        { "USA", "Hawaiian", "8:00 PM" }, { "USA", "Eastern", "8:00 PM" }, { "USA", "Pacific", "8:00 PM" },

		        { "CAN", "Eastern", "8:00 PM" }, { "CAN", "Pacific", "8:00 PM" }, { "CAN", "Central", "7:00 PM" },
		        { "CAN", "Mountain", "7:00 PM" } };
	}

}
