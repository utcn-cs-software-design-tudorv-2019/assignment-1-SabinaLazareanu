package assigment1.tucn.cs.DAL;

import assigment1.tucn.cs.DAL.entities.Enrollment;

public class EnrollementBuilder {

	private Enrollment enrollement;

	public EnrollementBuilder() {
		enrollement = new Enrollment();
	}

	public EnrollementBuilder setIdStudent(Long id) {
		enrollement.setStudent_id(id);
		return this;
	}

	public EnrollementBuilder setIdCours(Long id) {
		enrollement.setCours_id(id);
		return this;
	}

	public EnrollementBuilder setGrade(float grade) {
		enrollement.setGrade(grade);
		return this;
	}

	public Enrollment build() {
		return enrollement;
	}

}
