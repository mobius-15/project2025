package test;
import model.Login;
import model.LoginLogic;
public class LoginLogicTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		testExecuteOK();
		testExecuteNG();
	}
	public static void testExecuteOK() {
		Login login =new Login("minato","1234");
		LoginLogic bo =new LoginLogic();
		boolean result = bo.execute(login);
		if(result) {
			System.out.println("LogintestExecuteOK:Successed");
		}else {
			System.out.println("LogintestExecuteNG:Failed");
		}
	}
	public static void testExecuteNG() {
		Login login =new Login("minato","12345");
		LoginLogic bo =new LoginLogic();
		boolean result = bo.execute(login);
		if(!result) {
			System.out.println("LogintestExecuteNG:Successed");
		}else {
			System.out.println("LogintestExecuteNG:Failed");
		}
	}
}
