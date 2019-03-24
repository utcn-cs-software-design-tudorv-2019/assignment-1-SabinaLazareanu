package assigment1.tucn.cs.BLL;

import static assigment1.tucn.cs.BLL.utils.ETables.STUDENT;
import static assigment1.tucn.cs.BLL.utils.ETables.USER;

import java.util.List;

import assigment1.tucn.cs.DAL.ExecutionException;
import assigment1.tucn.cs.DAL.Student;
import assigment1.tucn.cs.DAL.StudentRepository;
import assigment1.tucn.cs.DAL.Teacher;
import assigment1.tucn.cs.DAL.User;
import assigment1.tucn.cs.DAL.UserRepository;

public class LoginService {

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

	public LoginService(StudentRepository studentRepo, UserRepository userRepo) {
		this.studentRepo = studentRepo;
		this.userRepo = userRepo;
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
					System.out.println(currentUser.toString());
				}
			}
		}

		return currentUser;
	}

	public void updateStudent(User student) throws ExecutionException {
		studentRepo.update((Student) student);
		userRepo.update(student);
	}

	public User getUserWithId(Long id) throws ExecutionException {
		return (Student) (studentRepo.getById(id, STUDENT));
	}

}