package assigment1.tucn.cs.BLL.utils;

public class SqlQueries {

	public static final String SELECT_ALL_QUERY = "Select * from {};";
	public static final String SELECT_BY_ID_QUERY = "Select * from {} where id=?;";
	public static final String DELETE_BY_ID_QUERY = "Delete from {} where id= ?;";
	public static final String DELETE_ENROLLMENT_BY_STUDENT_AND_COURS_ID = "Delete from ENROLLEMENT where Student_id= ? and Cours_id= ?;";
	public static final String DELETE_ENROLLEMENT_BY_STUDENT_ID_QUERY = "Delete from Enrollement where Student_id= ?;";
	public static final String INSERT_STUDENT = "Insert into Student(User_id,user_name, password, groupF) values(?,?,?,?);";
	public static final String INSERT_TEACHER = "Insert into Teacher (User_id,user_name, password) values(?,?,?);";
	public static final String INSERT_USER = "Insert into User (name,address, PNC,ICN) values(?,?,?,?);";
	public static final String INSERT_ENROLLEMENT = "Insert into Enrollement values(?,?,?);";
	public static final String UPDATE_USER = "Update User set name=?, address=?, pnc=?,icn=? where id =?;";
	public static final String UPDATE_STUDENT_GROUP = "Update Student set groupF=? where id =?;";
	public static final String UPDATE_ENROLLMENT_GRADE = "Update ENROLLEMENT set grade=? where Student_id =? and Cours_id = ?;";
	public static final String SELECT_BY_ID_STUDENT_ENROLLEMENT = "Select * from Enrollement where Student_id=?;";
	public static final String SELECT_OBJECT_WITH_MAX_ID = "SELECT * FROM {} WHERE id=(SELECT max(id) FROM {} )";

}
