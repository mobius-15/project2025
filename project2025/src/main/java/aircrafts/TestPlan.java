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
		
		if("input_wp".equals(action)) {
				
		String missionType=request.getParameter("missionType");		
		int wayPoint=Integer.parseInt(request.getParameter("wayPoint"));
		
		HttpSession session=request.getSession();
		
		session.setAttribute("missionType", missionType);
//		request.setAttribute("missionType", missionType);
		session.setAttribute("wayPoint", wayPoint);
//		request.setAttribute("wayPoint", wayPoint);

		
		RequestDispatcher dispatcher
		 =request.getRequestDispatcher("WEB-INF/jsp/Planning.jsp");
		 dispatcher.forward(request, response);
		 return;
		}
		if("submit_wp".equals(action)) {
			HttpSession session=request.getSession();
//			String missionType=request.getParameter("missionType");		
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
				double segmentTime=(distance*3600)/cas;
				totalFlightTime += segmentTime;
				
				waypoints.add(new Waypoint(0,0,altitude,(int)tas,distance,heading,segmentTime));
			}
			plan.setWaypoints(waypoints);
			
			TreeMap<String,FA_18F>vfa102 = Hanger.vfa102();
			FA_18F fa18f= vfa102.get("NF100");
			if(fa18f != null) {
			fa18f.setExtFuel(3300);
			fa18f.calculateFuelUsage(waypoints,2);
			}
			session.setAttribute("flightplan",plan);
			session.setAttribute("fa18f",fa18f);
			session.setAttribute("squadron", vfa102);
//			request.setAttribute("fa18f",fa18f);
//			request.setAttribute("totalFlightTime", totalFlightTime);
			session.setAttribute("waypoints", waypoints);
//			request.setAttribute("waypoints", waypoints);
//			request.setAttribute("squadron", vfa102);
		 
		 RequestDispatcher dispatcher
		 =request.getRequestDispatcher("WEB-INF/jsp/FlightPlanner.jsp");
		 dispatcher.forward(request, response);
		 
			
		 
		}

	}
}