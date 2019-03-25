package assigment1.tucn.cs.DAL.repository;

import static assigment1.tucn.cs.BLL.utils.SqlQueries.INSERT_STUDENT;
import static assigment1.tucn.cs.BLL.utils.SqlQueries.UPDATE_STUDENT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import assigment1.tucn.cs.DAL.ExecutionException;
import assigment1.tucn.cs.DAL.model.Student;
import assigment1.tucn.cs.database.config.JDBConnectionConfig;

public class StudentRepository extends Repository {

	public StudentRepository(JDBConnectionConfig dbConnectionWrapper) {
		super(dbConnectionWrapper);
	}

	public Student insertStudent(Student student) throws ExecutionException {
		Connection connection = getConnectionWrapper().getConnection();
		Student inseredStudent = null;
		try (PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT);) {
			statement.setLong(1, student._getIdUser());
			statement.setString(2, student.getUserName());
			statement.setString(3, student.getPassword());
			statement.setString(4, student.getGroup());
			try (ResultSet resultSet = statement.executeQuery();) {
				if (resultSet.next()) {
					student = getStudentFromResultSet(resultSet);
				}
			}
		} catch (Exception e) {
			throw new ExecutionException(e.getMessage());
		}
		return inseredStudent;

	}

	public void update(Student student) throws ExecutionException {

		Connection connection = getConnectionWrapper().getConnection();
		try (PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT);) {
			statement.setString(1, student.getGroup());
			statement.setLong(2, student.getIdStudent());
			statement.execute();
		} catch (Exception e) {
			//throw new ExecutionException(e.getMessage());
			e.printStackTrace();
		}
	}
}