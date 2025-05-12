package logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginLogic {
	public boolean execute(User user) {
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");		
		/*         if (isDebug) {            
            System.out.println( dtf.format(now) + " " + log);
		 *        UserDao userDao = new UserDao();
        UserBean user = userDao.getUser(login, pwd);
        return user;*/
		
		if(user.getPass().equals("password")) {
			return true;
		}else{
			return false;
		}
	}
}
