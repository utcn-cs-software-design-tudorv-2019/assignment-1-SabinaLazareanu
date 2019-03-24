package assigment1.tucn.cs.BLL.utils;

public enum ETables {

	STUDENT("Student"), TEACHER("Teacher"), COURS("Cours"), USER("User"), ENROLLEMENT("Enrollement");

	private ETables(String table) {
		this.table = table;
	}

	private final String table;

	public String getValue() {
		return this.table;
	}
}
