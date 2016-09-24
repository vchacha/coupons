package core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerPool implements ConnectionManager {

	private static final int CONNECTIONS_NUMBER = 1;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/test1?autoReconnect=true&useSSL=false&verifyServerCertificate=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	private static ConnectionManager connectionManager = null;
	private static Connection[] connections = new Connection[CONNECTIONS_NUMBER];

	private ConnectionManagerPool() {
	}

	public static ConnectionManager getInstance() {
		if (connectionManager == null) {
			try {
				for (int i = 0; i < CONNECTIONS_NUMBER; i++) {
					connections[i] = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				}
				connectionManager = new ConnectionManagerPool();
			} catch (SQLException e) {
				throw new RuntimeException("cannot establish connection");
			}
		}
		return connectionManager;
	}

	@Override
	public Connection returnConnection() {
		return connections[CONNECTIONS_NUMBER - 1];
	}

	@Override
	public void closeAllConnection() {
		try {
			for (int i = 0; i < CONNECTIONS_NUMBER; i++) {
				if (connections[i] != null) {
					connections[i].close();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("cannot close connection");
		}
	}

	@Override
	public void lockConnection() {
		// TODO lock connection
	}

}
