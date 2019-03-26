package assigment1.tucn.cs.DAL.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import assigment1.tucn.cs.BLL.utils.SqlQueries;
import assigment1.tucn.cs.DAL.ExecutionException;
import assigment1.tucn.cs.DAL.model.Teacher;
import assigment1.tucn.cs.database.config.JDBConnectionConfig;

public class TeacherRepository extends Repository {

	public TeacherRepository(JDBConnectionConfig dbConnectionWrapper) {
		super(dbConnectionWrapper);
	}

	public void insertStudent(Teacher teacher) throws ExecutionException {
		Connection connection = getConnectionWrapper().getConnection();
		try (PreparedStatement statement = connection.prepareStatement(SqlQueries.INSERT_TEACHER);) {
			statement.setLong(1, teacher._getIdUser());
			statement.setString(2, teacher.getUserName());
			statement.setString(3, teacher.getPassword());
			statement.execute();
		} catch (Exception e) {
			throw new ExecutionException(e.getMessage());
		}
	}
}
