package assigment1.tucn.cs.DAL.repository;

import static assigment1.tucn.cs.BLL.utils.SqlQueries.DELETE_ENROLLEMENT_BY_STUDENT_ID_QUERY;
import static assigment1.tucn.cs.BLL.utils.SqlQueries.INSERT_ENROLLEMENT;
import static assigment1.tucn.cs.BLL.utils.SqlQueries.SELECT_BY_ID_STUDENT_ENROLLEMENT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import assigment1.tucn.cs.DAL.ExecutionException;
import assigment1.tucn.cs.DAL.model.Enrollement;
import assigment1.tucn.cs.database.config.JDBConnectionConfig;

public class EnrollementRepository extends Repository {

	public EnrollementRepository(JDBConnectionConfig dbConnectionWrapper) {
		super(dbConnectionWrapper);
	}

	public List<Enrollement> getEnrollementsBySrtudentId(Long id) throws ExecutionException {
		Connection connection = getConnectionWrapper().getConnection();
		List<Enrollement> enrollements = new ArrayList<>();

		try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_STUDENT_ENROLLEMENT)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					enrollements.add(getEnrolleFromResultSet(resultSet));
				}
			}
		} catch (SQLException e) {
			throw new ExecutionException(e.getMessage());
		}

		return enrollements;
	}

	public void insertEnrollement(Enrollement enrollement) throws ExecutionException {
		Connection connection = getConnectionWrapper().getConnection();
		try (PreparedStatement statement = connection.prepareStatement(INSERT_ENROLLEMENT);) {
			statement.setLong(1, enrollement.getStudent_id());
			statement.setLong(2, enrollement.getCours_id());
			statement.setFloat(3, enrollement.getGrade());
			statement.execute();
		} catch (Exception e) {
			throw new ExecutionException(e.getMessage());
		}

	}

	public void delete(Long id) throws ExecutionException {
		Connection connection = getConnectionWrapper().getConnection();

		try (PreparedStatement statement = connection.prepareStatement(DELETE_ENROLLEMENT_BY_STUDENT_ID_QUERY)) {
			statement.setLong(1, id);
			statement.execute();
		} catch (Exception e) {
			throw new ExecutionException(e.getMessage());
		}

	}

}
