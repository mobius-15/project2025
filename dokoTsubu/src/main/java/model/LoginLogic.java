package model;

public class LoginLogic {
	public boolean execute(User user) {
		if(user.getPass().equals("password")) {
			return true;
		}else{
			return false;
		}
	}
}
