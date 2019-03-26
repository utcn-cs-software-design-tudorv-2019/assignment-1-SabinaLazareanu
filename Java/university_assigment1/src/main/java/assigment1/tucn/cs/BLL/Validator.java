package assigment1.tucn.cs.BLL;

public class Validator {
	
	public boolean validatePassword(String password, String confirmedPassword) {
		return password.equals(confirmedPassword);
	}

}
