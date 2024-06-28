package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

//inserindo dados
public class Program2 {
	public static void main(String[] args){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		try {
			conn = DB.getConnection();
			
			preparedStatement = conn.prepareStatement(
					  "INSERT INTO seller"
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, "Anderson");
			preparedStatement.setString(2, "anderson@email.com");
			preparedStatement.setDate(3, new java.sql.Date(dateFormat.parse("05/11/1998").getTime()));
			preparedStatement.setDouble(4, 3000.0);
			preparedStatement.setInt(5, 4);
			
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet resultStatement = preparedStatement.getGeneratedKeys();
				while(resultStatement.next()) {
					int id = resultStatement.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			}else {
				System.out.println("No rows affected!");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeConnection();
		}
		
	}
}
