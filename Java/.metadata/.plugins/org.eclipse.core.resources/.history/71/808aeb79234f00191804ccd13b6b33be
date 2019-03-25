package assigment1.tucn.cs.DAL;

import static assigment1.tucn.cs.BLL.utils.SqlQueries.INSERT_USER;
import static assigment1.tucn.cs.BLL.utils.SqlQueries.UPDATE_USER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import assigment1.tucn.cs.database.config.JDBConnectionConfig;

public class UserRepository extends Repository {

	public UserRepository(JDBConnectionConfig dbConnectionWrapper) {
		super(dbConnectionWrapper);
	}

	public User insertStudent(User user) throws ExecutionException {
		Connection connection = getConnectionWrapper().getConnection();
		User inseredUser = null;
		try (PreparedStatement statement = connection.prepareStatement(INSERT_USER);) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getAddress());
			statement.setString(3, user.getPNC());
			statement.setString(3, user.getICN());
			try (ResultSet resultSet = statement.executeQuery();) {
				if (resultSet.next()) {
					user = getUserFromResultSet(resultSet);
				}
			}
		} catch (Exception e) {
			throw new ExecutionException(e.getMessage());
		}
		return inseredUser;
	}

	public void update(User user) throws ExecutionException {

		Connection connection = getConnectionWrapper().getConnection();
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER);) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getAddress());
			statement.setString(3, user.getPNC());
			statement.setString(4, user.getICN());
			statement.setLong(5, user.getIdUser());
			statement.execute();
		} catch (Exception e) {
			throw new ExecutionException(e.getMessage());
		}
	}

}
