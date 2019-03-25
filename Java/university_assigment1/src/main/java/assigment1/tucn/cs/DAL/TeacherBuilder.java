package assigment1.tucn.cs.DAL;

import assigment1.tucn.cs.DAL.model.Teacher;

public class TeacherBuilder {

	private Teacher teacher;

	public TeacherBuilder() {
		teacher = new Teacher();
	}

	public TeacherBuilder setId(Long id) {
		teacher.setIdTeacher(id);
		return this;
	}

	public TeacherBuilder setIdUser(Long id) {
		teacher._setIdUser(id);
		return this;
	}


	public TeacherBuilder setUserName(String userName) {
		teacher.setUserName(userName);
		return this;
	}

	public TeacherBuilder setPassword(String password) {
		teacher.setPassword(password);
		return this;
	}

	public Teacher build() {
		return teacher;
	}

}
