package com.gracenote.resources;

import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.gracenote.BO.GeneralApplicaitonProperties;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MYSqlDBConnections {

	// Properties props = new Properties();
	GeneralApplicaitonProperties	gap			= new GeneralApplicaitonProperties();
	Connection						conn		= null;
	String							mysqlDriver	= "com.mysql.jdbc.Driver";
	private static Logger			logger		= Logger.getLogger(MYSqlDBConnections.class);
	String							Host, UserName, Password, Schema, IPPort;
	Statement						stmt		= null;

	/*
	 * public MYSqlDBConnections () { try { propertyLoader(); } catch (IOException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 */

	public Connection getNewDBConnection() {
		IPPort = gap.getDBHost();
		UserName = gap.getDBUserName();
		Password = gap.getDBPassword();
		Schema = gap.getDBSchema();
		Host = "jdbc:mysql://" + IPPort + "/" + Schema;

		logger.info(
		        "Ipport is :: " + IPPort + " UserName is :: " + UserName + " Scehma is ::" + Schema + " complete Host URL is :: " + Host);

		try {
			Class.forName(mysqlDriver);
			conn = (Connection) DriverManager.getConnection(Host, UserName, Password);
		} catch (Exception e) {

			logger.error(e);
		}
		return conn;
	}

	/*
	 * public Connection getOldDBConnection() { IPPort=props.getProperty("OldHost");
	 * UserName=props.getProperty("OldUserName");
	 * Password=props.getProperty("OldPassword"); Schema =
	 * props.getProperty("OldSchema"); Host= "jdbc:mysql://"+IPPort+"/"+Schema;
	 * 
	 * logger.info("Ipport is :: "+IPPort +" UserName is :: "+UserName
	 * +" Scehma is ::"+Schema+" complete Host URL is :: "+Host);
	 * 
	 * try{ Class.forName(mysqlDriver); conn = (Connection)
	 * DriverManager.getConnection(Host, UserName, Password); } catch (Exception e)
	 * {
	 * 
	 * logger.error(e); }
	 * 
	 * return conn; }
	 * 
	 * public void propertyLoader() throws IOException { FileInputStream fis=null;
	 * try { File propsfile = new File("application.properties"); fis = new
	 * FileInputStream(propsfile); props.load(fis); } catch (Exception e) {
	 * e.printStackTrace(); } finally { fis.close(); } }
	 */

	/*
	 * public void CleanConnections() throws SQLException { stmt.close();
	 * conn.close(); }
	 */
}
