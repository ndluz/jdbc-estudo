package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {
	public static void main(String[] args){
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn = DB.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM department");
			
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("Id") + ", " + resultSet.getString("Name"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			DB.closeStatement(statement);
			DB.closeResultSet(resultSet);
			DB.closeConnection();
		}
	}
}
