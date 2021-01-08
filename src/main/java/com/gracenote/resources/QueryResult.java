package com.gracenote.resources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.mysql.jdbc.CallableStatement;

public class QueryResult {
	// MSSQLDBConnections MsSqlDatabaseConnection = new MSSQLDBConnections();
	MYSqlDBConnections		mysqlDBC		= new MYSqlDBConnections();
	Statement				stmt			= null;
	CallableStatement		cs				= null;
	Connection				dBConnection	= null;
	ResultSet				rs;
	private static Logger	logger			= Logger.getLogger(QueryResult.class);

	public void setConnectivity() {
		logger.info("Setting the connectivity to respective Databases");
		// this.dBConnection= MsSqlDatabaseConnection.getDBConnection();
		this.dBConnection = mysqlDBC.getNewDBConnection();
	}

	public ResultSet getMSSQLData(String Query) throws SQLException {
		try {
			logger.info("Query from calling method is :: " + Query);
			stmt = dBConnection.createStatement();
			rs = stmt.executeQuery(Query);

			// stmt.close();

		} catch (Exception e) {
			logger.error(e);
		}
		/*
		 * finally { if(stmt!=null) stmt.close(); }
		 */
		return rs;
	}

	public void CleanUpDBConnections() throws SQLException {
		if (dBConnection != null) {
			logger.debug("closing the Old DB connection Object");
			this.dBConnection.close();
		}
		if (stmt != null) {
			logger.debug("closing the Statement Object");
			this.stmt.close();
		}
		if (rs != null) {
			logger.debug("closing the Result Set Object");
			this.rs.close();
		}
	}

}
