package com.portalsandtimemachines.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;


public class DBGameConnect {
	 void dbConnect(HashMap dbInput) {
		String url = "jdbc:mysql://localhost:3306/game";
		String username = "cse360";
		String password = "cse360";
		
		String playerName = (String) dbInput.get("pname");
		
		
		
		String insertQuery = "INSERT INTO `scorecard` (`Player_Name`, `Games_Won`, `Games_Played`, `Win_Rate`) VALUES ("+ "'"+playerName +"'"+", '0', '1', '0')";
		System.out.println(insertQuery);
		System.out.println("Loading driver...");

		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		
		System.out.println("Connecting database...");

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
		    System.out.println("Database connected!");
		    Statement stmt = conn.createStatement();
		    stmt.execute(insertQuery);
		    stmt.close();
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
}
