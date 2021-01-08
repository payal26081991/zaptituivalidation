package com.gracenote.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.gracenote.BO.GeneralApplicaitonProperties;

public class MSSQLDBConnections {

	Connection						conn		= null;
	String							msSqlDriver	= "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static Logger			logger		= Logger.getLogger(MSSQLDBConnections.class);
	String							Host, UserName, Password, Schema, DBHost, DBInstance;
	Statement						stmt		= null;
	GeneralApplicaitonProperties	gap			= new GeneralApplicaitonProperties();

	public Connection getDBConnection() {
		DBHost = gap.getDBHost();
		UserName = gap.getDBUserName();
		Password = gap.getDBPassword();
		Schema = gap.getDBSchema();
		DBInstance = gap.getDBInstanceName();

		Host = "jdbc:sqlserver://" + DBHost + ";instanceName=" + DBInstance + ";databaseName=" + Schema;

		logger.info(
		        "DBHost is :: " + DBHost + " UserName is :: " + UserName + " Scehma is ::" + Schema + " complete Host URL is :: " + Host);

		try {
			Class.forName(msSqlDriver);
			conn = DriverManager.getConnection(Host, UserName, Password);
		} catch (Exception e) {

			logger.error(e);
		}
		return conn;
	}

}
