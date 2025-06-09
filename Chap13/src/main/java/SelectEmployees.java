import java.util.List;

import dao.EmployeeDAO;
import model.Employee;

public class SelectEmployees {
	public static void main(String[]args) {
		//Employeesテーブルの全レコード取得
		EmployeeDAO empDAO =new EmployeeDAO();
		List<Employee>empList=empDAO.findAll();
		//内容出力
		for(Employee e:empList) {
			System.out.println("ID:"+e.getId());
			System.out.println("Name:"+e.getName());
			System.out.println("Age:"+e.getAge()+"\n");
		}
	}
}
