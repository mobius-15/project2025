package model;

public class LoginLogic {
	public boolean execute(User user) {
		if(user.getPassword().equals("password")||user.getShain_code().equals("Sample")) {
			return true;
		}else{
			return false;
		}
	}
}
