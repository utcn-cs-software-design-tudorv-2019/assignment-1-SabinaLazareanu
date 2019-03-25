package assigment1.tucn.cs.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import assigment1.tucn.cs.BLL.StudentService;
import assigment1.tucn.cs.DAL.ExecutionException;
import assigment1.tucn.cs.DAL.StudentBuilder;
import assigment1.tucn.cs.DAL.model.Cours;
import assigment1.tucn.cs.DAL.model.Student;
import assigment1.tucn.cs.DAL.model.Teacher;
import assigment1.tucn.cs.DAL.model.User;
import assigment1.tucn.cs.DAL.repository.CoursRepository;
import assigment1.tucn.cs.DAL.repository.EnrollementRepository;
import assigment1.tucn.cs.DAL.repository.StudentRepository;
import assigment1.tucn.cs.DAL.repository.TeacherRepository;
import assigment1.tucn.cs.DAL.repository.UserRepository;
import assigment1.tucn.cs.database.config.JDBConnectionConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {

	private static final String SCHEMA = "university_db";
	private String checkUser;
	private String checkPassword;
	private static User currentStudent;
	private JDBConnectionConfig dbConnectionWrapper = new JDBConnectionConfig(SCHEMA);
	private StudentRepository studentRepo = new StudentRepository(dbConnectionWrapper);
	private TeacherRepository teacherRepo = new TeacherRepository(dbConnectionWrapper);
	private UserRepository userRepo = new UserRepository(dbConnectionWrapper);
	private EnrollementRepository enrolementRepo = new EnrollementRepository(dbConnectionWrapper);
	private CoursRepository coursRepo = new CoursRepository(dbConnectionWrapper);
	private StudentService loginService = new StudentService(studentRepo, userRepo, teacherRepo, enrolementRepo,
			coursRepo);

	@FXML
	private Button loginButton;

	@FXML
	private Label messageField;

	@FXML
	private TextField password;

	@FXML
	private TextField userName;

	@FXML
	private TextField addressField;

	@FXML
	private TextField groupField;

	@FXML
	private TextField idField;

	@FXML
	private TextField pncField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField icnField;

	@FXML
	private TableView tabelView;

	
	//TODO: FIX This!
	private TableColumn coursColumn = new TableColumn("Cours");
	private TableColumn gradeColumn = new TableColumn("Teacher");
	private TableColumn teacherColumn = new TableColumn("Exam Date");
	private TableColumn examColumn = new TableColumn("Grade");
//	@FXML
//	private TableColumn coursColumn;
//
//	@FXML
//	private TableColumn gradeColumn;
//
//	@FXML
//	private TableColumn teacherColumn;
//
//	@FXML
//	private TableColumn examColumn;

	@FXML
	private ComboBox<String> comboBox;

	@FXML
	void refresh(ActionEvent event) {
		Student student;
		try {
			student = (Student) loginService.getStudentWithId(((Student) currentStudent).getIdStudent());
			fillStudentFields(student);
			table();
			comboBox();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void updateStudentButtonClicked(ActionEvent event) {
		try {
			Student studentToBeUpdated = updateCurrentUserInfo();
			loginService.updateStudent(studentToBeUpdated);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Student updateCurrentUserInfo() {
		Student studentToBeUpdated = new StudentBuilder().setGroup(groupField.getText()).build();
		studentToBeUpdated.setIdStudent(((Student) currentStudent).getIdStudent());
		studentToBeUpdated._setIdUser(((Student) currentStudent)._getIdUser());
		studentToBeUpdated.setUserName(((Student) currentStudent).getUserName());
		studentToBeUpdated.setPassword(((Student) currentStudent).getPassword());
		studentToBeUpdated.setIdUser(currentStudent.getIdUser());
		studentToBeUpdated.setName(nameField.getText());
		studentToBeUpdated.setAddress(addressField.getText());
		studentToBeUpdated.setPNC(pncField.getText());
		studentToBeUpdated.setICN(icnField.getText());
		return studentToBeUpdated;
	}

	@FXML
	void loginButtonClicked(ActionEvent event) {
		checkUser = userName.getText().toString();
		checkPassword = password.getText().toString();
		try {
			currentStudent = loginService.login(checkUser, checkPassword);
			if (currentStudent instanceof Teacher) {
			} else if (currentStudent instanceof Student) {

				Stage stage = (Stage) loginButton.getScene().getWindow();
				stage.close();
				studentPage();
			} else if (Objects.isNull(currentStudent)) {
				messageField.setText("Username or password invalid!!");
			}
		} catch (ExecutionException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void studentPage() throws IOException {
		Stage studentStage = new Stage();
		studentStage.setTitle("Student:");
		Pane pane = FXMLLoader.load(getClass().getResource("student.fxml"));
		studentStage.setScene(new Scene(pane, 1011, 540));
		studentStage.show();

	}

	private void fillStudentFields(Student user) {
		this.nameField.setText(user.getName());
		this.addressField.setText(user.getAddress());
		this.pncField.setText(user.getPNC());
		this.icnField.setText(user.getICN());
		this.idField.setText("" + user.getIdStudent());
		this.groupField.setText(user.getGroup());
	}

	private void table() throws ExecutionException {

		tabelView.getColumns().addAll(coursColumn, teacherColumn, examColumn, gradeColumn);
		ObservableList<Row> obs = FXCollections.observableArrayList();
		List<Row> rows = null;
		try {
			rows = loginService.getStudentEnrollements(currentStudent.getIdUser());

		} catch (ExecutionException e) {
			// TODO Auto-genrated catch block
			e.printStackTrace();
		}
		for (Row row : rows) {
			obs.add(row);
			System.out.println(row);
		}

		coursColumn.setCellValueFactory(new PropertyValueFactory<ArrayList<String>, String>("cours"));
		teacherColumn.setCellValueFactory(new PropertyValueFactory<ArrayList<String>, String>("teacher"));
		examColumn.setCellValueFactory(new PropertyValueFactory<ArrayList<String>, String>("examDate"));
		gradeColumn.setCellValueFactory(new PropertyValueFactory<ArrayList<String>, String>("grade"));

		tabelView.setItems(obs);
	}

	private void comboBox() throws ExecutionException {
		ArrayList<Cours> courses = new ArrayList<>();
		courses = loginService.getPossibleOptionsForCoursForStudent(((Student) currentStudent).getIdStudent());
		ObservableList<String> data = FXCollections.observableArrayList();
		for (Cours cours : courses) {
			data.add(cours.getCoursName());
		}
		comboBox.setItems(data);
	}

}
