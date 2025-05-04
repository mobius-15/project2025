package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import aircrafts.Aircrafts;
import aircrafts.FA_18F;
import aircrafts.Hanger;
import flightplan.FlightPlan2;
import flightplan.Waypoint;
import logger.User;
import logic.AircraftsLogic;
import logic.FuelLogic;
import mission.MissionContext;
import mission.Missions;
import weapons.WeaponFactory;
import weapons.Weapons;

@WebServlet("/TestPlan")
public class TestPlan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestPlan() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		User loginUser =(User)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			response.sendRedirect("index.jsp");
			}else {
				RequestDispatcher dispatcher
			=request.getRequestDispatcher("");
		List<String> missionTypes = Missions.getMissions();
		request.setAttribute("missionTypes", missionTypes);

		 dispatcher = request.getRequestDispatcher("/Command.jsp");
		dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		HttpSession session = request.getSession();

		if ("input_wp".equals(action)) {

			String missionType = request.getParameter("missionType");
			int wayPoint = Integer.parseInt(request.getParameter("wayPoint"));

			session.setAttribute("missionType", missionType);
			session.setAttribute("wayPoint", wayPoint);

			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/aircraft/Planning.jsp");
			dispatcher.forward(request, response);
			return;
		}
		if ("submit_wp".equals(action)) {

			int wayPoint = Integer.parseInt(request.getParameter("wayPoint"));

			FlightPlan2 plan = new FlightPlan2();
			AircraftsLogic aLogic=new AircraftsLogic();
			List<Waypoint> waypoints = new ArrayList<>();
			double totalFlightTime = 0;

			for (int i = 0; i < wayPoint; i++) {
			    String alt = request.getParameter("altitude" + i);
			    String speed = request.getParameter("cas" + i);
			    String dist = request.getParameter("distance" + i);
			    String hdg = request.getParameter("heading" + i);

			    if (Stream.of(alt, speed, dist, hdg).anyMatch(s -> s == null || s.isEmpty())) {
			        request.setAttribute("inputError", "Some fields are missing. Please fill in all waypoints.");
			        request.getRequestDispatcher("WEB-INF/aircraft/Planning.jsp").forward(request, response);
			        return;
			    
				}

				int altitude = Integer.parseInt(alt);
				int cas = Integer.parseInt(speed);
				double distance = Double.parseDouble(dist);
				int heading = Integer.parseInt(hdg);

				double mach =aLogic.calculateMach(altitude,cas); 
				double tas =aLogic.calculateTAS(altitude, cas);
				double segmentTime = aLogic.calculateSegmentTime(distance,tas);

	waypoints.add(new Waypoint(0, 0, altitude, (int) tas, distance, heading, segmentTime, mach));
				totalFlightTime += segmentTime;
			}
			plan.setWaypoints(waypoints);
			
			TreeMap<String, FA_18F> vfa102 = Hanger.vfa102();
			String leaderModex = "NF103";
			FuelLogic fLogic=new FuelLogic();
			FA_18F fa18f = vfa102.get(leaderModex);

			
			Aircrafts wingman = (FA_18F)Hanger.selectRandomWingman(vfa102, leaderModex);	
				
			if (fa18f != null) {				
				fa18f.setExtFuel(3300);
				fLogic.calculateFuelUsage(waypoints, 2,fa18f);
			}
			
			List<Double> segmentFuelList = fLogic.calculateSegmentFuel(waypoints,fa18f);
			String missionType = (String) session.getAttribute("missionType");
			
			int capIndex=0;
			int capDuration=45;
			if ("CAP".equalsIgnoreCase(missionType) && waypoints.size() >= 3) {
				capIndex=waypoints.size()/2;				
				double capFuel = aLogic.cap(waypoints, capIndex,capDuration,fa18f); // WP数 で 15分 CAP
				double oldFuel = segmentFuelList.get(capIndex);
				segmentFuelList.set(capIndex, oldFuel + capFuel); // 追加燃料反映
				totalFlightTime += capDuration; 
		
			}
			MissionContext ctx 
			= new MissionContext(missionType,plan,fa18f,null, // Carrier はまだ設定されていない場合
				segmentFuelList,totalFlightTime);
			List<Weapons> weaponList = WeaponFactory.getAllWeapons();
			
			session.setAttribute("segmentFuelList", segmentFuelList);
			session.setAttribute("flightplan", plan);
			session.setAttribute("aLogic", aLogic);
			session.setAttribute("fLogic",fLogic);
			session.setAttribute("squadron", vfa102);
			session.setAttribute("fa18f", fa18f);		
			session.setAttribute("totalFlightTime", totalFlightTime);
			session.setAttribute("waypoints", waypoints);
			session.setAttribute("capIndex",capIndex); // JSP側表示用
			session.setAttribute("capDuration",capDuration);
			session.setAttribute("wingman", wingman);
			session.setAttribute("ctx", ctx);
			session.setAttribute("weaponList", weaponList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/aircraft/FlightPlanner.jsp");
			dispatcher.forward(request, response);
			
		}
	}
}
		