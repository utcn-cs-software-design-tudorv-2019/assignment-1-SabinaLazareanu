package assigment1.tucn.cs.BLL;

import static assigment1.tucn.cs.BLL.utils.ETables.COURS;
import static assigment1.tucn.cs.BLL.utils.ETables.STUDENT;
import static assigment1.tucn.cs.BLL.utils.ETables.TEACHER;
import static assigment1.tucn.cs.BLL.utils.ETables.USER;

import java.util.ArrayList;
import java.util.List;

import assigment1.tucn.cs.DAL.ExecutionException;
import assigment1.tucn.cs.DAL.model.Cours;
import assigment1.tucn.cs.DAL.model.Enrollement;
import assigment1.tucn.cs.DAL.model.Student;
import assigment1.tucn.cs.DAL.model.Teacher;
import assigment1.tucn.cs.DAL.model.User;
import assigment1.tucn.cs.DAL.repository.CoursRepository;
import assigment1.tucn.cs.DAL.repository.EnrollementRepository;
import assigment1.tucn.cs.DAL.repository.StudentRepository;
import assigment1.tucn.cs.DAL.repository.TeacherRepository;
import assigment1.tucn.cs.DAL.repository.UserRepository;
import assigment1.tucn.cs.UI.Row;

public class StudentService {

	private static final String ADMIN_USER = "admin";
	private static final String ADMIN_PASSWORD = "admin";

	private Long currentUser;

	public Long getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Long currentUser) {
		this.currentUser = currentUser;
	}

	private StudentRepository studentRepo;
	private UserRepository userRepo;
	private TeacherRepository teacherRepo;
	private EnrollementRepository enrolementRepo;
	private CoursRepository coursRepo;

	public StudentService(StudentRepository studentRepo, UserRepository userRepo, TeacherRepository teacherRepo,
			EnrollementRepository enrollementRepo, CoursRepository coursRepo) {
		this.studentRepo = studentRepo;
		this.userRepo = userRepo;
		this.teacherRepo = teacherRepo;
		this.enrolementRepo = enrollementRepo;
		this.coursRepo = coursRepo;
	}

	public User login(String userName, String password) throws ExecutionException {
		User currentUser = null;
		if (password.equalsIgnoreCase(ADMIN_USER)) {
			if (userName.equalsIgnoreCase(ADMIN_PASSWORD)) {
				currentUser = new Teacher();
			}
		} else {
			List<Student> students = (List<Student>) studentRepo.findAll(STUDENT);
			for (Student student : students) {
				if (student.getUserName().equals(userName) && student.getPassword().equals(password)) {
					currentUser = student;
					User studentUser;
					studentUser = (User) userRepo.getById(student._getIdUser(), USER);
					currentUser.setIdUser(studentUser.getIdUser());
					currentUser.setName(studentUser.getName());
					currentUser.setPNC(studentUser.getPNC());
					currentUser.setAddress(studentUser.getAddress());
					currentUser.setICN(studentUser.getICN());
					// System.out.println(currentUser.toString());
				}
			}
		}

		return currentUser;
	}

	public void updateStudent(User student) throws ExecutionException {
		studentRepo.update((Student) student);
		userRepo.update(student);
	}

	public User getStudentWithId(Long id) throws ExecutionException {
		Student student = (Student) (studentRepo.getById(id, STUDENT));
		User studentUser = (User) userRepo.getById(student._getIdUser(), USER);
		student.setIdUser(studentUser.getIdUser());
		student.setName(studentUser.getName());
		student.setPNC(studentUser.getPNC());
		student.setAddress(studentUser.getAddress());
		student.setICN(studentUser.getICN());
		return student;
	}

	public User getTeacherWithId(Long id) throws ExecutionException {
		Teacher teacher = (Teacher) (teacherRepo.getById(id, TEACHER));
		User teacherUser = (User) userRepo.getById(teacher._getIdUser(), USER);
		teacher.setIdUser(teacherUser.getIdUser());
		teacher.setName(teacherUser.getName());
		teacher.setPNC(teacherUser.getPNC());
		teacher.setAddress(teacherUser.getAddress());
		teacher.setICN(teacherUser.getICN());
		return teacher;
	}

	public List<Row> getStudentEnrollements(Long id) throws ExecutionException {

		List<Row> rows = new ArrayList<Row>();
		List<Enrollement> enrollements = enrolementRepo.getEnrollementsBySrtudentId(id);
		Cours cours = null;
		Teacher teacher = null;
		for (int i = 0; i < enrollements.size(); i++) {

			long coursIdFromEnrollement = enrollements.get(i).getCours_id();
			cours = (Cours) coursRepo.getById(coursIdFromEnrollement, COURS);
			teacher = (Teacher) getTeacherWithId(cours.getTeacherId());
			String coursName = cours.getCoursName();
			String teacherName = teacher.getName();
			// TODO
			String examDate = "maine";// cours.getExamDate().toString();
			String grade = "" + enrollements.get(i).getGrade();
			Row row = new Row(coursName, teacherName, examDate, grade);
			rows.add(row);
		}
		return rows;
	}

	public ArrayList<Cours> getCoursesForStudent(Long idStudent) throws ExecutionException {
		List<Enrollement> enrollements = enrolementRepo.getEnrollementsBySrtudentId(idStudent);
		ArrayList<Cours> courses = new ArrayList<Cours>();
		for (int i = 0; i < enrollements.size(); i++) {
			long coursIdFromEnrollement = enrollements.get(i).getCours_id();
			courses.add((Cours) coursRepo.getById(coursIdFromEnrollement, COURS));
		}
		return courses;
	}

	public ArrayList<Cours> getAllPossibleCourses() throws ExecutionException {
		ArrayList<Cours> courses = (ArrayList<Cours>) coursRepo.findAll(COURS);
		return courses;
	}

	public ArrayList<Cours> getPossibleOptionsForCoursForStudent(Long idStudent) throws ExecutionException {
		ArrayList<Cours> possibleCourses = new ArrayList<Cours>();
		ArrayList<Cours> allCourses = getAllPossibleCourses();
		ArrayList<Cours> takenCourses = getCoursesForStudent(idStudent);

		for (Cours possibleCours : allCourses) {
			for (Cours existentCours : takenCourses) {
				if (!possibleCours.equals(existentCours)) {
					possibleCourses.add(possibleCours);
				}
			}
		}
		return possibleCourses;

	}

}
