package assigment1.tucn.cs.DAL.model;

import java.sql.Date;

public class Cours {

	private Long idCours;
	private String coursName;
	private Date examDate;
	private Long teacherId;

	public Long getIdCours() {
		return idCours;
	}

	public void setIdCours(Long idCours) {
		this.idCours = idCours;
	}

	public String getCoursName() {
		return coursName;
	}

	public void setCoursName(String coursName) {
		this.coursName = coursName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coursName == null) ? 0 : coursName.hashCode());
		result = prime * result + ((examDate == null) ? 0 : examDate.hashCode());
		result = prime * result + ((idCours == null) ? 0 : idCours.hashCode());
		result = prime * result + ((teacherId == null) ? 0 : teacherId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cours other = (Cours) obj;
		if (coursName == null) {
			if (other.coursName != null)
				return false;
		} else if (!coursName.equals(other.coursName))
			return false;
		if (examDate == null) {
			if (other.examDate != null)
				return false;
		} else if (!examDate.equals(other.examDate))
			return false;
		if (idCours == null) {
			if (other.idCours != null)
				return false;
		} else if (!idCours.equals(other.idCours))
			return false;
		if (teacherId == null) {
			if (other.teacherId != null)
				return false;
		} else if (!teacherId.equals(other.teacherId))
			return false;
		return true;
	}

	
}
