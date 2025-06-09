package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.LogUtil;

public class BaseDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/customerSystem?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "qq3g9yp";
    protected Connection conn=null;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}	
    protected void open()throws ClassNotFoundException,SQLException{
    	LogUtil.println(this.getClass().getSimpleName()+"#open");
    	Class.forName(PASSWORD);
    	conn=DriverManager.getConnection(URL,USER,PASSWORD);
    	conn.setAutoCommit(true);
    }
	protected void close()throws SQLException{
		LogUtil.println(this.getClass().getSimpleName()+"#close");
	
		if(conn !=null) {conn.close();conn=null;}

	}
	public PreparedStatement getPstmt() {
		return pstmt;
	}
	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public static String getUrl() {
		return URL;
	}
	public static String getUser() {
		return USER;
	}
	public static String getPassword() {
		return PASSWORD;
	}
}

