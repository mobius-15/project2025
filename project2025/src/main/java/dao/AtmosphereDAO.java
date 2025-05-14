package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import flightplan.Atmosphere;

public class AtmosphereDAO {

	    private static final String URL = "jdbc:mysql://localhost:3306/standard_atmosphere?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";

	    public static Atmosphere fetchDataByAltitude(int altitude) {
	    	try {Class.forName("com.mysql.cj.jdbc.Driver");
	    		}catch (ClassNotFoundException e) {
	    			System.err.println("JDBC Driver not found: " + e.getMessage());
	    		}
	        Atmosphere data = new Atmosphere(1.0, 340.29); // fallback default

	        String sql="SELECT density_ratio,sonicspeed FROM altitude "
	        		+ "LEFT JOIN density ON altitude.id=density.id "
	        		+ "LEFT JOIN physicalproperties ON altitude.id = physicalproperties.id "
	        		+ "WHERE feet >= ? ORDER BY feet ASC LIMIT 1";

	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setInt(1, altitude);
	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                double densityRatio = rs.getDouble("density_ratio");
	                double sonicSpeed = rs.getDouble("sonicspeed");
	                data=new Atmosphere(densityRatio,sonicSpeed);
	            } else {
	                System.err.println("[DAO] NO DATA (altitude >= " + altitude + ")");
	            }
	        } catch (SQLException e) {
	            System.err.println("[DAO] SQL Error: " + e.getMessage());
	        }
	        return data;
	    }
}


