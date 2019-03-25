package assigment1.tucn.cs.DAL;

import assigment1.tucn.cs.DAL.model.Enrollement;

public class EnrollementBuilder {

	private Enrollement enrollement;

	public EnrollementBuilder() {
		enrollement = new Enrollement();
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

	public Enrollement build() {
		return enrollement;
	}

}
