package assigment1.tucn.cs.DAL.model;

public class Teacher extends User {

	private Long idTeacher;
	private Long idUser;
	private String userName;
	private String password;

	public Long getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(Long idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long _getIdUser() {
		return idUser;
	}

	public void _setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "Teacher [idTeacher=" + idTeacher + ", idUser=" + idUser + ", userName=" + userName + ", password="
				+ password + "]";
	}

}
