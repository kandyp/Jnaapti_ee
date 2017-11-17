package pack1;

import java.sql.*;
import pack1.Connect;

public class Init {

	private static final String dbName = "TASK_KANDARP";
	ResultSet rs = null;
	Connection connection = null;
	Statement statement = null;
	Init(){
		try {
			
			connection = Connect.getConnection();
			ResultSet resultSet = connection.createStatement().executeQuery("SHOW DATABASES LIKE '"+dbName+"';");

			
			  
			  if(!resultSet.next()){
				  
				  Statement s = connection.createStatement();
				  s.executeUpdate("CREATE DATABASE IF NOT EXISTS "+dbName);
				  s.execute("use " + dbName );
				  s.executeUpdate("CREATE TABLE LIST"
				  		+ "(ID int NOT NULL AUTO_INCREMENT,"
				  		+ "NAME varchar(255) NOT NULL,"
				  		+ "CT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
				  		+ "MT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
				  		+ "PRIMARY KEY (ID) )");
				  String query = "INSERT INTO LIST (NAME) VALUES(?)";
				  
				  PreparedStatement preparedStmt = connection.prepareStatement(query);
			      preparedStmt.setString (1, "sample data, press delete or edit to execute");
			      preparedStmt.execute();
				  s.close();
				  System.out.println("database created");

			  }
			  else {
				  System.out.println("database connected");
			  }
			
			resultSet.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error in init");
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.print("error while closing init");
				}
			}
		}
	}
}
