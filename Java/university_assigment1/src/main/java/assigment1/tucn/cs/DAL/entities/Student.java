package assigment1.tucn.cs.DAL.entities;

public class Student extends User {

	private Long idStudent;
	private String userName;
	private String password;
	private String group;
	private Long user_idUser;

	public Long getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Long idStudent) {
		this.idStudent = idStudent;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Long _getIdUser() {
		return user_idUser;
	}

	public void _setIdUser(Long idUser) {
		this.user_idUser = idUser;
	}

	@Override
	public String toString() {
		System.out.println(super.toString());
		return "Student [idStudent=" + idStudent + ", userName=" + userName + ", password=" + password + ", group="
				+ group + ", user_idUser=" + user_idUser + "]";
	}

}
