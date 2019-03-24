package assigment1.tucn.cs.database.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBConnectionConfig {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static int timeout;

	private static final String PROPERTIES_FILE = "database.properties";

	private Connection connection;

	public JDBConnectionConfig(String schema) {
		try {
			setProperties();
			Class.forName(driver);
			connection = DriverManager.getConnection(url + schema, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO
			e.printStackTrace();
			// throw new JDBConnectionException(e.getMessage());
		} catch (JDBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void setProperties() throws JDBConnectionException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties propertiesFile = new Properties();
		try (InputStream resourceStream = loader.getResourceAsStream(PROPERTIES_FILE);) {
			propertiesFile.load(resourceStream);

			url = propertiesFile.getProperty("url");
			username = propertiesFile.getProperty("username");
			password = propertiesFile.getProperty("password");
			driver = propertiesFile.getProperty("driver");
			timeout = Integer.parseInt(propertiesFile.getProperty("timeout"));

		} catch (IOException e) {
			throw new JDBConnectionException("En error occured on reading properties file! " + e.getMessage());
		}
	}

	public boolean testConnection() throws SQLException {
		return connection.isValid(timeout);
	}

	public Connection getConnection() {
		return connection;
	}

}
