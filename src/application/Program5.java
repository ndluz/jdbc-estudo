package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

// transactions
public class Program5 {
	public static void main(String[] args){
		Connection conn = null;
		Statement statement = null;
		
		try {
			conn = DB.getConnection();
			statement = conn.createStatement();
			
			conn.setAutoCommit(false);
			
			int affectedRowsQuery1 = statement.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			int x = 1;
			if (x < 2) {
				throw new SQLException("Test: some error message");
			}
			
			int affectedRowsQuery2 = statement.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit();
			
			System.out.println("rows1 : " + affectedRowsQuery1);
			System.out.println("rows2 : " + affectedRowsQuery2);
			
		} catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("rollback transaction, cause: " + e.getMessage());
			} catch (SQLException ex) {
				throw new DbException("rollback error: " + e.getMessage());
			}
		} finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}
}
