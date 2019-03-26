package assigment1.tucn.cs.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import assigment1.tucn.cs.BLL.StudentService;
import assigment1.tucn.cs.BLL.Validator;
import assigment1.tucn.cs.DAL.ExecutionException;
import assigment1.tucn.cs.DAL.StudentBuilder;
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
import assigment1.tucn.cs.database.config.JDBConnectionConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	private static final float INITIAL_GRADE = -1.0f;
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
	private Button registerButton;

	@FXML
	private Button registerButtonRegister;

	@FXML
	private Button refreshButton;

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

	@FXML
	private ComboBox<String> comboBox;

	@FXML
	private TextField pncRegister;

	@FXML
	private Label registerMessage;

	@FXML
	private TextField icnRegister;

	@FXML
	private TextField nameRegister;

	@FXML
	private TextField groupRegister;

	@FXML
	private TextField addressRegister;

	@FXML
	private TextField passwordRegister;

	@FXML
	private TextField userNameRegister;

	@FXML
	private TextField cPasswordRegister;

	Alert alert = new Alert(AlertType.INFORMATION);

	// TODO fix this instantiation
	private TableColumn coursColumn = new TableColumn("Cours");
	private TableColumn gradeColumn = new TableColumn("Teacher");
	private TableColumn teacherColumn = new TableColumn("Exam Date");
	private TableColumn examColumn = new TableColumn("Grade");

	@FXML
	void enroleButton(ActionEvent event) throws ExecutionException {
		String selectedCours = comboBox.getValue();
		Cours cours = loginService.getSelectedCours(selectedCours);
		Enrollement enrollement = new Enrollement();
		enrollement.setStudent_id(((Student) currentStudent).getIdStudent());
		enrollement.setCours_id(cours.getIdCours());
		enrollement.setGrade(INITIAL_GRADE);
		loginService.addEnrollement(enrollement);
		refreshButton.fire();
		showAllertMessage("Enrollement with success!");
	}

	@FXML
	void register(ActionEvent event) {
		Student newUser = new Student();
		newUser.setName(nameRegister.getText());
		newUser.setAddress(addressRegister.getText());
		newUser.setPNC(pncRegister.getText());
		newUser.setICN(icnRegister.getText());
		newUser.setGroup(groupRegister.getText());
		newUser.setUserName(userNameRegister.getText());
		newUser.setPassword(passwordRegister.getText());

		Validator validator = new Validator();
		if (validator.validatePassword(passwordRegister.getText(), cPasswordRegister.getText())) {
			try {
				currentStudent = loginService.addUser(newUser);
				Stage stage = (Stage) registerButtonRegister.getScene().getWindow();
				stage.close();
				studentPage();
			} catch (ExecutionException e) {
				showAllertMessage(e.getMessage());
			} catch (IOException e) {
				showAllertMessage(e.getMessage());
			}
		} else {
			registerMessage.setText("Confirmed password is incorrect!");
		}

	}

	@FXML
	void refresh(ActionEvent event) {
		Student student;
		try {
			student = (Student) loginService.getStudentWithId(((Student) currentStudent).getIdStudent());
			fillStudentFields(student);
			table();
			comboBox();
		} catch (ExecutionException e) {
			showAllertMessage(e.getMessage());
		}

	}

	@FXML
	void updateStudentButtonClicked(ActionEvent event) {
		try {
			Student studentToBeUpdated = updateCurrentUserInfo();
			loginService.updateStudent(studentToBeUpdated);
			refreshButton.fire();
			showAllertMessage("Update done with succes!");
		} catch (ExecutionException e) {
			showAllertMessage(e.getMessage());
		}
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
			showAllertMessage(e.getMessage());
		}

	}

	@FXML
	void registerFromLoginButtonClicked(ActionEvent event) {
		try {
			Stage stage = (Stage) registerButton.getScene().getWindow();
			stage.close();
			registerPage();
		} catch (IOException e) {
			showAllertMessage(e.getMessage());
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

	private void table() throws ExecutionException {

		tabelView.getColumns().clear();
		tabelView.getColumns().addAll(coursColumn, teacherColumn, examColumn, gradeColumn);
		ObservableList<CoursEnrollement> obs = FXCollections.observableArrayList();
		List<CoursEnrollement> rows = null;
		try {
			rows = loginService.getStudentEnrollements(((Student) currentStudent).getIdStudent());
		} catch (ExecutionException e) {
			showAllertMessage(e.getMessage());
		}
		for (CoursEnrollement row : rows) {
			obs.add(row);
		}

		coursColumn.setCellValueFactory(new PropertyValueFactory<ArrayList<String>, String>("cours"));
		teacherColumn.setCellValueFactory(new PropertyValueFactory<ArrayList<String>, String>("examDate"));
		examColumn.setCellValueFactory(new PropertyValueFactory<ArrayList<String>, String>("grade"));
		gradeColumn.setCellValueFactory(new PropertyValueFactory<ArrayList<String>, String>("teacher"));

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

	private void fillStudentFields(Student user) {
		this.nameField.setText(user.getName());
		this.addressField.setText(user.getAddress());
		this.pncField.setText(user.getPNC());
		this.icnField.setText(user.getICN());
		this.idField.setText("" + user.getIdStudent());
		this.groupField.setText(user.getGroup());
	}

	private void registerPage() throws IOException {
		Stage registertStage = new Stage();
		registertStage.setTitle("Register:");
		Pane pane = FXMLLoader.load(getClass().getResource("register.fxml"));
		registertStage.setScene(new Scene(pane, 540, 480));
		registertStage.show();

	}

	private void studentPage() throws IOException {
		Stage studentStage = new Stage();
		studentStage.setTitle("Student:");
		Pane pane = FXMLLoader.load(getClass().getResource("student.fxml"));
		studentStage.setScene(new Scene(pane, 1011, 540));
		studentStage.show();

	}

	private void showAllertMessage(String message) {
		alert.close();
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
