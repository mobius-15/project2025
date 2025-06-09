package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;
public class MuttersDAO {
	private final String JDBC_URL="jdbc:mysql://localhost:3306/standard_atmosphere?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
			/*"jdbc:h2:tcp://localhost/~/dokoTsubu";*/
	private final String DB_USER="root";
			//"sa"
	private final String DB_PASS="qq3g9yp";
		//"";
	public List<Mutter>findAll(){
		List<Mutter>mutterList=new ArrayList<>();
		
		try {Class.forName("org.h2.Driver");
			}catch(ClassNotFoundException e) {
				throw new IllegalStateException("Can't load JDBC driver");
			}
		
		try(Connection conn =DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql="SELECT ID,NAME,TEXT FROM MUTTERS ORDER BY ID DESC";
		PreparedStatement pstmt=conn.prepareStatement(sql);			
			ResultSet rs=pstmt.executeQuery();			
			while(rs.next()) {		//投稿データが存在する限り
				int id=rs.getInt("ID");
				String userName=rs.getString("NAME");
				String text=rs.getString("TEXT");	//ID,NAME,TEXTを取得しMUTTERインスタンスを生成し
				Mutter mutter =new Mutter(id,userName,text);	
				mutterList.add(mutter);		//mutterListに追加、最後までエラーなければListを返す
			}
		}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		return mutterList;
	}
	public boolean create(Mutter mutter) {
		try {Class.forName("com.mysql.cj.jdbc.Driver");/*"org.h2.Driver"*/
		}catch(ClassNotFoundException e) {throw new IllegalStateException("Can't load JDBC driver");
		}try(Connection conn =DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
				//name,textをINSERT、idは自動連番としてDB作成
			String sql="INSERT INTO MUTTERS(NAME,TEXT)VALUES(?,?)";	 //?は可変データ
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mutter.getUserName());	//可変データ1
			pstmt.setString(2, mutter.getText());		//2
			
			int result =pstmt.executeUpdate();
			if(result != 1) {
				return false;	//投稿できなければfalse
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;	//投稿出来ればtrue
	}
}
