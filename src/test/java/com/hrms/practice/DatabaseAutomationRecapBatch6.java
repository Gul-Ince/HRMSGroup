package com.hrms.practice;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAutomationRecapBatch6 {

	public static void main(String[] args) throws SQLException {

		String dbUrl = "jdbc:mysql://18.232.148.34:3306/syntaxhrm_mysql";
		String dbUsername = "syntax_hrm";
		String dbPassword = "syntaxhrm123";

		Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

		// DataBase MetaData-------------> Info about our database

		DatabaseMetaData dbMetaData = conn.getMetaData();
		String driverName = dbMetaData.getDriverName();
		System.out.println("Driver Name= " + driverName);

		System.out.println("Product Name= " + dbMetaData.getDatabaseProductName());
		System.out.println(dbMetaData.getURL());

		// Create a query statement object

		Statement statement = conn.createStatement();

		// Execute query and save result in ResulSet object

		ResultSet resultSet = statement.executeQuery("select id, name, country_code from ohrm_location");

		ResultSetMetaData rsMetaData = resultSet.getMetaData();

		int colCount = rsMetaData.getColumnCount();
		System.out.println("Number of columns : " + colCount);

		for (int i = 1; i <= 3; i++) {
			System.out.println("Column Name: " + i + " : " + rsMetaData.getColumnName(i));
		}

		for (int i = 1; i <= 3; i++) {
			System.out.println(rsMetaData.getColumnType(i));

			// Process or results

			System.out.println("Printing results ........");

			while (resultSet.next()) {
				String id = resultSet.getString(1);
				String name = resultSet.getString(2);
				String countryCode = resultSet.getString(3);

			}

		}
	}

}
