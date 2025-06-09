package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDAO {	//接続情報はprivate finalで定数に
	private final String JDBC_URL="jdbc:h2:tcp://localhost/~/example";
	private final String DB_USER="sa";
	private final String DB_PASS="";
	
public List<Employee>findAll(){
	List<Employee>empList=new ArrayList<>();
	try {Class.forName("org.h2.Driver");	//JDBCドライバ
	}catch(ClassNotFoundException e) {throw new IllegalStateException("読込不可");}
		//接続
	try(Connection conn =DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
		String sql="SELECT ID,NAME,AGE FROM EMPLOYEES";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		//結果取得
		ResultSet rs=pstmt.executeQuery();
//結果表に格納されたレコードをEmployeeインスタンスに設定し、ArrayListに追加
		while(rs.next()) {
			String id =rs.getString("ID");
			String name=rs.getString("NAME");
			int age=rs.getInt("AGE");
			Employee emp =new Employee(id,name,age);
			empList.add(emp);
		}
	}catch(SQLException e) {
		e.printStackTrace();
		return null;
			
	}return empList;		//Mainクラスへ
}
	public boolean remove(String id) {//引数はidのみ
		
		try (Connection conn=DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			String sql="DELETE FROM EMPLOYEES WHERE ID = ?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,id);
			int result=pstmt.executeUpdate();
			if(result!=1) {
				return false;
				}
			}catch(SQLException e) {
				e.printStackTrace();
				return false;		
			}
		return true;
			
	}
}

