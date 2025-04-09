package project.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgres {
	private static Connection conn;
	private static ConnectionPostgres instance;

	private ConnectionPostgres() {
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OOBD", "postgres", "sugretsop25");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static ConnectionPostgres getInstance(){
		if(instance == null) {
			instance = new ConnectionPostgres();
		} else
			try {
				if(instance.getConnection().isClosed()) {
					instance = new ConnectionPostgres();
				}
			}
				catch(SQLException e) {
					e.printStackTrace();
				}

		return instance;
	}

	public Connection getConnection() {
		try {
			if(conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/OOBD", "postgres", "sugretsop25");
			}
		}
			catch(SQLException e) {
				e.printStackTrace();
			}

		return conn;
	}
}
