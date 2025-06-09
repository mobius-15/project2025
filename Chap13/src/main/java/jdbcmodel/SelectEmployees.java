package jdbcmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectEmployees {
	public static void main(String[]args) {
		try {Class.forName("org.h2.Driver");	//ドライバ接続の有無
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバの読み込みに失敗");
		}
		try(Connection conn=DriverManager.getConnection
			("jdbc:h2:tcp://localhost/~/example","sa","")){//データベースとのConnection
			String sql="SELECT ID,NAME,AGE FROM EMPLOYEES";
			PreparedStatement pstmt =conn.prepareStatement(sql); //sqlコードを実行
			ResultSet rs =pstmt.executeQuery();	//SELECT文実行時
			
			while(rs.next()) {		//次のデータが存在する限り
				String id=rs.getString("ID");
				String name=rs.getString("NAME");
				int age=rs.getInt("AGE");
				
				System.out.println("ID:"+id);
				System.out.println("名前："+name);
				System.out.println("年齢:"+age+"\n");
				
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
