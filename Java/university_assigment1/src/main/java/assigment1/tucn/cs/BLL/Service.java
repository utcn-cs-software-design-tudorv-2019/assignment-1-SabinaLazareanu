package assigment1.tucn.cs.BLL;

import static assigment1.tucn.cs.BLL.utils.ETables.COURS;
import static assigment1.tucn.cs.BLL.utils.ETables.STUDENT;
import static assigment1.tucn.cs.BLL.utils.ETables.TEACHER;
import static assigment1.tucn.cs.BLL.utils.ETables.USER;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import assigment1.tucn.cs.DAL.ExecutionException;
import assigment1.tucn.cs.DAL.StudentBuilder;
import assigment1.tucn.cs.DAL.entities.Cours;
import assigment1.tucn.cs.DAL.entities.Enrollment;
import assigment1.tucn.cs.DAL.entities.Student;
import assigment1.tucn.cs.DAL.entities.Teacher;
import assigment1.tucn.cs.DAL.entities.User;
import assigment1.tucn.cs.DAL.repository.CoursRepository;
import assigment1.tucn.cs.DAL.repository.EnrollementRepository;
import assigment1.tucn.cs.DAL.repository.StudentRepository;
import assigment1.tucn.cs.DAL.repository.TeacherRepository;
import assigment1.tucn.cs.DAL.repository.UserRepository;
import assigment1.tucn.cs.UI.CoursEnrollementEntity;

public class Service {

	private static final String ADMIN_USER = "admin";
	private static final String ADMIN_PASSWORD = "admin";

	private StudentRepository studentRepo;
	private UserRepository userRepo;
	private TeacherRepository teacherRepo;
	private EnrollementRepository enrolementRepo;
	private CoursRepository coursRepo;

	public Service(StudentRepository studentRepo, UserRepository userRepo, TeacherRepository teacherRepo,
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
				}
			}
		}

		return currentUser;
	}

	public void updateStudent(User student) throws ExecutionException {
		studentRepo.update((Student) student);
		userRepo.update(student);
	}

	public void updateGrade(Long studentId, Long coursId, float grade) throws ExecutionException {
		enrolementRepo.updateGradeAtCours(studentId, coursId, grade);
	}

	public void updateStudentGroup(Long idStudent, String group) throws ExecutionException {
		Student studentWithNewGroup = new StudentBuilder().setGroup(group).setId(idStudent).build();
		studentRepo.update(studentWithNewGroup);

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

	public List<CoursEnrollementEntity> getStudentEnrollements(Long id) throws ExecutionException {

		List<CoursEnrollementEntity> rows = new ArrayList<CoursEnrollementEntity>();
		List<Enrollment> enrollements = enrolementRepo.getEnrollementsBySrtudentId(id);
		Cours cours = null;
		Teacher teacher = null;
		for (int i = 0; i < enrollements.size(); i++) {

			long coursIdFromEnrollement = enrollements.get(i).getCours_id();
			cours = (Cours) coursRepo.getById(coursIdFromEnrollement, COURS);
			teacher = (Teacher) getTeacherWithId(cours.getTeacherId());
			String coursName = cours.getCoursName();
			String teacherName = teacher.getName();
			String examDate = Objects.nonNull(cours.getExamDate()) ? cours.getExamDate().toString() : "";
			String grade = "" + enrollements.get(i).getGrade();
			CoursEnrollementEntity row = new CoursEnrollementEntity(coursName, teacherName, examDate, grade);
			rows.add(row);
		}
		return rows;
	}

	public List<Student> getAllStudents() throws ExecutionException {
		List<Student> students = (List<Student>) studentRepo.findAll(STUDENT);
		User studentUser;
		for (Student student : students) {
			studentUser = (User) userRepo.getById(student._getIdUser(), USER);
			student.setIdUser(studentUser.getIdUser());
			student.setName(studentUser.getName());
			student.setAddress(studentUser.getAddress());
			student.setPNC(studentUser.getPNC());
			student.setICN(studentUser.getICN());
		}
		return students;
	}

	public ArrayList<Cours> getCoursesForStudent(Long idStudent) throws ExecutionException {
		List<Enrollment> enrollements = enrolementRepo.getEnrollementsBySrtudentId(idStudent);
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
		ArrayList<Cours> possibleCourses = null;
		ArrayList<Cours> allCourses = getAllPossibleCourses();
		ArrayList<Cours> takenCourses = getCoursesForStudent(idStudent);

		if (!(takenCourses.size() == 0)) {
			possibleCourses = new ArrayList<>();
			for (Cours possibleCours : allCourses) {
				if (!takenCourses.contains(possibleCours)) {
					possibleCourses.add(possibleCours);
				}
			}
		} else {
			possibleCourses = new ArrayList<Cours>(allCourses);
		}
		return possibleCourses;

	}

	public User addUser(User newUser) throws ExecutionException {
		userRepo.insertUser(newUser);
		User addedUser = (User) userRepo.getObjectWithMaxID(USER);
		((Student) newUser)._setIdUser(addedUser.getIdUser());
		studentRepo.insertStudent((Student) newUser);
		Student addedStudent = (Student) studentRepo.getObjectWithMaxID(STUDENT);

		addedStudent.setAddress(addedUser.getAddress());
		addedStudent.setName(addedUser.getName());
		addedStudent.setPNC(addedUser.getPNC());
		addedStudent.setICN(addedUser.getICN());
		addedStudent.setIdUser(addedUser.getIdUser());
		return addedStudent;
	}

	public Cours getSelectedCours(String selectedCours) throws ExecutionException {
		List<Cours> courses = (List<Cours>) coursRepo.findAll(COURS);
		for (Cours cours : courses) {
			if (cours.getCoursName().equals(selectedCours)) {
				return cours;
			}
		}
		return null;

	}

	public void addEnrollement(Enrollment enrollement) throws ExecutionException {
		enrolementRepo.insertEnrollement(enrollement);
	}

	public void deleteAcount(User currentUser) throws ExecutionException {
		enrolementRepo.deleteByStudentId(((Student) currentUser).getIdStudent());
		studentRepo.delete(((Student) currentUser).getIdStudent(), STUDENT);
		userRepo.delete(currentUser.getIdUser(), USER);
	}

	public void unenrollStudent(Enrollment enrollment) throws ExecutionException {
		enrolementRepo.deleteByStudentIdAndCoursId(enrollment.getStudent_id(), enrollment.getCours_id());
	}

}
