package assigment1.tucn.cs.database.config;

public class JDBConnectionFactory {
	public JDBConnectionConfig getConnectionWrapper(String schema) throws JDBConnectionException {
		return new JDBConnectionConfig(schema);
	}
}
