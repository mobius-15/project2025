package aircrafts;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import mission.MissionContext;

@WebServlet("/TestPlan")
public class TestPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestPlan() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<String>missionTypes = Missions.getMissions();
    	request.setAttribute("missionTypes", missionTypes);
    	
    	RequestDispatcher dispatcher=request.getRequestDispatcher("/Command.jsp");
    	dispatcher.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		HttpSession session=request.getSession();
		
		if("input_wp".equals(action)) {
				
		String missionType=request.getParameter("missionType");		
		int wayPoint=Integer.parseInt(request.getParameter("wayPoint"));
		
		session.setAttribute("missionType", missionType);
		session.setAttribute("wayPoint", wayPoint);
		
		RequestDispatcher dispatcher
		 =request.getRequestDispatcher("WEB-INF/jsp/Planning.jsp");
		 dispatcher.forward(request, response);
		 return;
		}
		if("submit_wp".equals(action)) {
	
			int wayPoint=Integer.parseInt(request.getParameter("wayPoint"));
		
		 FlightPlan2 plan= new FlightPlan2();
		 List<Waypoint>waypoints=new ArrayList<>();
		 	double totalFlightTime = 0;

		for(int i=0;i<wayPoint;i++) {				
		    int altitude = Integer.parseInt(request.getParameter("altitude" + i));
		    int cas = Integer.parseInt(request.getParameter("cas" + i));
		    double distance = Double.parseDouble(request.getParameter("distance" + i));
		    int heading = Integer.parseInt(request.getParameter("heading" + i));
				
		    double tas=plan.convertTAS(altitude,cas);  
			double segmentTime=(distance*3600)/tas;
				
			FlightPlan2.AtmosphereData data = null;
			try {data = plan.getDataBase(altitude);
			} catch (SQLException e) {
					e.printStackTrace();
			}
				
			double sonicSpeed=data.sonicSpeed;
			double mach=(tas*0.514444)/sonicSpeed;
							
			waypoints.add(new Waypoint(0,0,altitude,(int)tas,distance,heading,segmentTime, mach, sonicSpeed));
			totalFlightTime += segmentTime;
			}
			plan.setWaypoints(waypoints);
			
			TreeMap<String,FA_18F>vfa102 = Hanger.vfa102();
			FA_18F fa18f= vfa102.get("NF103");

			if(fa18f != null) {
			fa18f.setExtFuel(3300);
			fa18f.calculateFuelUsage(waypoints,2);
			}
			List<Double> segmentFuelList = fa18f.calculateSegmentFuel(waypoints);
			String missionType = (String) session.getAttribute("missionType");
			if ("CAP".equalsIgnoreCase(missionType) && waypoints.size() >= 6) {
			    double capFuel = fa18f.cap(waypoints, 5, 15); // WP6 で 15分 CAP
			    double oldFuel = segmentFuelList.get(5);
			    segmentFuelList.set(5, oldFuel + capFuel); // 追加燃料反映
			    totalFlightTime += 45 * 60; // 15分（900秒）追加

			}
			session.setAttribute("segmentFuelList", segmentFuelList);
			session.setAttribute("flightplan",plan);
			session.setAttribute("fa18f",fa18f);
			session.setAttribute("squadron", vfa102);
			request.setAttribute("fa18f",fa18f);
			request.setAttribute("flightplan",plan);
			session.setAttribute("totalFlightTime", totalFlightTime);
			request.setAttribute("totalFlightTime", totalFlightTime);
			session.setAttribute("waypoints", waypoints);
			request.setAttribute("waypoints", waypoints);
			request.setAttribute("squadron", vfa102);
			request.setAttribute("segmentFuelList", segmentFuelList);
		    request.setAttribute("capIndex", 5); // JSP側表示用
		    request.setAttribute("capDuration", 15);
		    
		    MissionContext context = new MissionContext(
		    	    missionType,
		    	    plan,
		    	    fa18f,
		    	    null, // Carrier はまだ設定されていない場合
		    	    segmentFuelList,
		    	    totalFlightTime
		    	);
		    	session.setAttribute("missionContext", context);
		 RequestDispatcher dispatcher
		 =request.getRequestDispatcher("WEB-INF/jsp/FlightPlanner.jsp");
		 dispatcher.forward(request, response);
		 
			 
		}
	}
}