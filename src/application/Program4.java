package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegretyException;

//apagando dados
public class Program4 {
	public static void main(String[] args){
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		try {
			conn = DB.getConnection();
			preparedStatement = conn.prepareStatement(
					 "DELETE FROM department "
					+"WHERE Id = ?");
			
			preparedStatement.setInt(1, 5);
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			System.out.println("Done! Rows Affected: " + rowsAffected);
		} catch(SQLException e) {
			throw new DbIntegretyException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeConnection();
		}
	}
}
