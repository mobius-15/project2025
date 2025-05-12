package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import aircrafts.Aircrafts;
import aircrafts.FA_18;
import aircrafts.FA_18F;
import aircrafts.Loadout;
import flightplan.FlightPlan2;
import logic.WeaponsLogic;
import mission.MissionContext;
import weapons.WeaponFactory;
import weapons.Weapons;

/**
 * Servlet implementation class LoadoutServlet
 */
@WebServlet("/LoadoutServlet")
public class LoadoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoadoutServlet() {
        super();       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");        
        if ("submit_loadout".equals(action)) {
        	HttpSession session = request.getSession();
            FlightPlan2 plan = (FlightPlan2) session.getAttribute("flightplan");
            Aircrafts fa18f = (FA_18F) session.getAttribute("fa18f");
            if (plan == null || fa18f == null) {
            	response.sendRedirect("WEB-INF/aircraft/Planning.jsp");
            	return;
            	}
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/weapon/LoadoutConfig.jsp");
            dispatcher.forward(request, response);
        }
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
       
        FA_18 fa18f = (FA_18F) session.getAttribute("fa18f");
        if (fa18f == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/aircraft/FlightPlanner.jsp");
            dispatcher.forward(request, response);          
            return;  
         	}
        fa18f.initializeStations(11);
        List<Weapons> weaponList = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            String weaponName = request.getParameter("station" + i);
            if (weaponName != null && !weaponName.isEmpty()) {
                Weapons weapon = WeaponFactory.getByName(weaponName);
                if (weapon != null) {
                    fa18f.assignWeaponsToStation(i, weapon);
                    weaponList.add(weapon);

                }
            }
        }
        double weaponWeight = WeaponsLogic.getWeaponsWeight(weaponList);
        fa18f.setLoadoutWeight(weaponWeight);
        fa18f.setWeaponList(weaponList);

        // 総重量を再計算
        double totalWeight = fa18f.getEmptyWeight() + fa18f.getIntFuel()
                           + (fa18f.getExtFuel() * 2)
                           + WeaponsLogic.getWeaponsWeight(weaponList);
        fa18f.setFullWeight((int)totalWeight);
    	int adjustedSpeed = WeaponsLogic.calculateAdjustedCruiseSpeed(
    		    fa18f.getCruiseSpeed(), fa18f.getIntFuel(), fa18f.getFullWeight(), weaponList
    		);
    	fa18f.setCruiseSpeed(adjustedSpeed);
        
        session.setAttribute("fa18f", fa18f);
        session.setAttribute("weaponList", weaponList);
        MissionContext ctx=(MissionContext)session.getAttribute("ctx");
        if(ctx !=null) {
        	ctx.setAircraft(fa18f);
            List<Loadout> loadoutList = new ArrayList<>();
            Map<Integer, Loadout> stationMap = fa18f.getStations();

            for (Map.Entry<Integer, Loadout> entry : stationMap.entrySet()) {
                if (entry.getValue().getWeapons() != null) {
                    loadoutList.add(entry.getValue());
                }
            }
            ctx.setLoadouts(loadoutList);
        	session.setAttribute("ctx",ctx);
        	
        	
        	 System.out.println("Updated MissionContext with loadouts, count: " + loadoutList.size());
        

	}
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/aircraft/FlightPlanner.jsp");
        dispatcher.forward(request, response);
	}

}
