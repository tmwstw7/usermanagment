package ua.nure.cs.koshurnikov.usermanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImpl implements ConnectionFactory {

	//"org.hsqldb.jdbcDriver";
	//"jdbc:hsqldb:file:dbusermanagement";
	private String driver;
	private String url;
	private String user;
	private String password;

	public ConnectionFactoryImpl(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public ConnectionFactoryImpl(Properties properties) {
		driver = properties.getProperty("connection.driver");
		url = properties.getProperty("connection.url");
		user = properties.getProperty("connection.user");
		password = properties.getProperty("connection.password");
	}
	
	@Override
	public Connection createConnection() throws DatabaseException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

}
