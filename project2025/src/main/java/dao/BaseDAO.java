package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public class BaseDao {

	    private static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	    private static final String URL = "jdbc:mysql://localhost:3306/customersystem?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";
	    protected Connection conn = null;
	    
	    public void open() throws ClassNotFoundException, SQLException {	       

	        Class.forName(JDBC_DRIVER_NAME);
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        conn.setAutoCommit(true);
	    }
	    public void close() throws SQLException {

	    	if (conn != null) {
	            conn.close();
	            conn = null;
	        }
	    }
	}
}
