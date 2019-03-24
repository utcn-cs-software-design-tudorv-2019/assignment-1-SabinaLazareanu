package assigment1.tucn.cs.BLL.utils;

public class SqlQueries {

	public static final String SELECT_ALL_QUERY = "Select * from {};";
	public static final String SELECT_BY_ID_QUERY = "Select * from {} where id=?;";
	public static final String DELETE_BY_ID_QUERY = "Delete from {} where id= ?;";
	public static final String INSERT_STUDENT = "Insert into Student(User_id,user_name, password, group) values(?,?,?,?);";
	public static final String INSERT_TEACHER = "Insert into Teacher (User_id,user_name, password) values(?,?,?);";
	public static final String INSERT_USER = "Insert into User (name,address, PNC,ICN) values(?,?,?,?);";
	public static final String UPDATE_USER = "Update User set name=?, address=?, pnc=?,icn=? where id =?;";
	public static final String UPDATE_STUDENT = "Update Student set groupF=? where id =?;";
}
