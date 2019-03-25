package assigment1.tucn.cs.DAL;

import static assigment1.tucn.cs.BLL.utils.ETables.STUDENT;
import static assigment1.tucn.cs.BLL.utils.ETables.TEACHER;

import java.util.List;

import assigment1.tucn.cs.BLL.StudentService;
import assigment1.tucn.cs.DAL.repository.StudentRepository;
import assigment1.tucn.cs.DAL.repository.TeacherRepository;
import assigment1.tucn.cs.UI.LoginPage;
import assigment1.tucn.cs.database.config.JDBConnectionConfig;
import assigment1.tucn.cs.database.config.JDBConnectionException;
import assigment1.tucn.cs.database.config.JDBConnectionFactory;

public class App {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
//		// System.out.println("Hello World!");
//		JDBConnectionFactory factory = new JDBConnectionFactory();
//		@SuppressWarnings("unchecked")
//		List<Student> students = null;
//		@SuppressWarnings("unused")
//		Student student = null;
//		// List<Teacher> ts = null;
//		try {
//			Repository repo = new Repository(factory.getConnectionWrapper("university_db"));
//
//			try {
//				students = (List<Student>) repo.findAll(STUDENT);
//				repo.delete(1l, TEACHER);
//				// ts = (List<Teacher>) repo.findAll(TEACHER);
//				// student = (Student) repo.getById(2l, STUDENT);
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(students.get(0).getPassword());
//			// System.out.println(student.getUserName());
//		} catch (JDBConnectionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
		
		
		JDBConnectionConfig dbConnectionWrapper;
		try {
			dbConnectionWrapper = new JDBConnectionConfig("university_db");
			StudentRepository studentRepo = new StudentRepository(dbConnectionWrapper);
			TeacherRepository teacherRepo = new TeacherRepository(dbConnectionWrapper);

			StudentService logineService = new StudentService(studentRepo, teacherRepo);
		} catch (JDBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoginPage loginPage = new LoginPage();
		loginPage.start(ars);
		launch(args);
		
	}
}
