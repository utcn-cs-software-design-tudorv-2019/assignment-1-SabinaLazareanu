package assigment1.tucn.cs.DAL;

import java.sql.Date;

import assigment1.tucn.cs.DAL.entities.Cours;

public class CoursBuilder {

	private Cours cours;

	public CoursBuilder() {
		cours = new Cours();
	}

	public CoursBuilder setId(Long id) {
		cours.setIdCours(id);
		return this;
	}

	public CoursBuilder setCoursName(String coursName) {
		cours.setCoursName(coursName);
		return this;
	}

	public CoursBuilder setExamDate(Date date) {
		cours.setExamDate(date);
		return this;
	}

	public CoursBuilder setTeacherId(Long id) {
		cours.setTeacherId(id);
		return this;
	}

	public Cours build() {
		return cours;
	}

}
