package assigment1.tucn.cs.DAL;

import assigment1.tucn.cs.DAL.entities.Student;

public class StudentBuilder {

	private Student student;

	public StudentBuilder() {
		student = new Student();
	}

	public StudentBuilder setId(Long id) {
		student.setIdStudent(id);
		return this;
	}

	public StudentBuilder setIdUser(Long id) {
		student._setIdUser(id);
		return this;
	}

	public StudentBuilder setGroup(String group) {
		student.setGroup(group);
		return this;
	}

	public StudentBuilder setUserName(String userName) {
		student.setUserName(userName);
		return this;
	}

	public StudentBuilder setPassword(String password) {
		student.setPassword(password);
		return this;
	}

	public Student build() {
		return student;
	}

}
