package com.comcast.crm.generic.databaseutility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection conn;//global declaration-> can be accessed by other methods too.
	
	public void getDBConnection(String url, String username, String password) throws SQLException
	{	
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			conn=DriverManager.getConnection(url, username, password);
		}catch(Exception e){}
	} 
	
	public void getDBConnection() throws SQLException
	{	
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root1234");
		}catch(Exception e){}
	} 
	
	public void closeDBConnection() throws SQLException
	{	try {
			conn.close();
	}	catch(Exception e) {}
	}
	
	public ResultSet executeSelectQuery(String query) throws SQLException
	{	ResultSet result=null;
		try{
			Statement stat=conn.createStatement();
			result=stat.executeQuery(query);
			}catch(Exception e){}
		return result;
	}//select query:=>executeQuery: returns table in the form of resultSet.
	
	public int executeNonSelectQuery(String query) throws SQLException
	{	int result=0;
		try{
			Statement stat=conn.createStatement();
			result=stat.executeUpdate(query);
			}catch(Exception e){}
		return result;
	}//Non select query:=>executeUpdate: returns integer value. If insertion good: +,- value. otherwise returns; 0.
}

