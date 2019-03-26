package assigment1.tucn.cs.DAL.repository;

import static assigment1.tucn.cs.BLL.utils.SqlQueries.INSERT_USER;
import static assigment1.tucn.cs.BLL.utils.SqlQueries.UPDATE_USER;

import java.sql.Connection;
import java.sql.PreparedStatement;

import assigment1.tucn.cs.DAL.ExecutionException;
import assigment1.tucn.cs.DAL.model.User;
import assigment1.tucn.cs.database.config.JDBConnectionConfig;

public class UserRepository extends Repository {

	public UserRepository(JDBConnectionConfig dbConnectionWrapper) {
		super(dbConnectionWrapper);
	}

	public void insertUser(User user) throws ExecutionException {
		Connection connection = getConnectionWrapper().getConnection();
		try (PreparedStatement statement = connection.prepareStatement(INSERT_USER);) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getAddress());
			statement.setString(3, user.getPNC());
			statement.setString(4, user.getICN());
			statement.execute();
		} catch (Exception e) {
			throw new ExecutionException(e.getMessage());
		}
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
