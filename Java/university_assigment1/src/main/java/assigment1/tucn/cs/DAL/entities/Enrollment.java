package assigment1.tucn.cs.DAL.entities;

public class Enrollment {
	private Long Student_id;
	private Long Cours_id;
	private float grade;
	public Long getStudent_id() {
		return Student_id;
	}
	public void setStudent_id(Long student_id) {
		Student_id = student_id;
	}
	public Long getCours_id() {
		return Cours_id;
	}
	public void setCours_id(Long cours_id) {
		Cours_id = cours_id;
	}
	public float getGrade() {
		return grade;
	}
	public void setGrade(float grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Enrollement [Student_id=" + Student_id + ", Cours_id=" + Cours_id + ", grade=" + grade + "]";
	}

}
