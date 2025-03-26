package aircrafts;

import java.io.IOException;
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

		request.setCharacterEncoding("UTF-8");
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
				totalFlightTime += segmentTime;
							
				waypoints.add(new Waypoint(0,0,altitude,(int)tas,distance,heading,segmentTime));
			}
			plan.setWaypoints(waypoints);
			
			TreeMap<String,FA_18F>vfa102 = Hanger.vfa102();
			FA_18F fa18f= vfa102.get("NF103");
			
//			double segmentTime=(distance*3600)/tas;
//			double fuelUsed=fa18f.fuel() * (segmentTime / 3600);
//			double remainingFuel;
//			remainingFuel -= fuelUsed;
//			segmentFuelList.add(fuelUsed);

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
			    totalFlightTime += 15 * 60; // 15分（900秒）追加

			}
			request.setAttribute("segmentFuelList", segmentFuelList);
			session.setAttribute("flightplan",plan);
			session.setAttribute("fa18f",fa18f);
			session.setAttribute("squadron", vfa102);
			request.setAttribute("fa18f",fa18f);
			request.setAttribute("totalFlightTime", totalFlightTime);
			session.setAttribute("waypoints", waypoints);
			request.setAttribute("waypoints", waypoints);
			request.setAttribute("squadron", vfa102);
			request.setAttribute("segmentFuelList", segmentFuelList);
		    request.setAttribute("capIndex", 5); // JSP側表示用
		    request.setAttribute("capDuration", 15);
		 RequestDispatcher dispatcher
		 =request.getRequestDispatcher("WEB-INF/jsp/FlightPlanner.jsp");
		 dispatcher.forward(request, response);
		 	
			
		 
		}

	}
}