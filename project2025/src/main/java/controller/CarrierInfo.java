package controller;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import aircrafts.Aircrafts;
import carrierOps.Carrier;
import carrierOps.Fleet;
import logic.MissionContextLogic;
import mission.MissionContext;

/**
 * Servlet implementation class CarrierInfo
 */
@WebServlet("/CarrierInfo")
public class CarrierInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrierInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TreeMap<Integer,String>carriers=Fleet.cvn();
        request.setAttribute("carriers", carriers);
        HttpSession session = request.getSession();
        MissionContext ctx=(MissionContext)session.getAttribute("ctx");

        String action=request.getParameter("action");
        if("map".equals(action)){
        	if(ctx != null) {
        		request.setAttribute("carrier", ctx.getCarrier());
        	}
            request.getRequestDispatcher("WEB-INF/mission/MissionMAP.jsp").forward(request, response);
          }else if ("review".equals(action)) {
            request.getRequestDispatcher("WEB-INF/mission/MissionReview.jsp").forward(request, response);
        }else {
        	request.getRequestDispatcher("WEB-INF/vessel/carrierConfig.jsp").forward(request, response);
        }
    }
    /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	String action=request.getParameter("action");
     
        if("confirmMission".equals(action)){
        	   String name = request.getParameter("carrierName");
            if (name == null || name.trim().isEmpty()) {
                request.setAttribute("inputError", "All carrier fields must be filled in.");
                request.getRequestDispatcher("WEB-INF/vessel/carrierConfig.jsp").forward(request, response);
                return;
            }
        	
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lon = Double.parseDouble(request.getParameter("lon"));
        int speed = Integer.parseInt(request.getParameter("speed"));
        int disp = Integer.parseInt(request.getParameter("displacement"));
       
        Carrier carrier = new Carrier(name, lat, lon, speed, disp);

        
        MissionContext ctx = (MissionContext) session.getAttribute("ctx");
        
        Aircrafts wingman = (Aircrafts) session.getAttribute("wingman");

        if (ctx != null && ctx.getFlightPlan() != null) {
            ctx.getFlightPlan().updateWaypointCoordinates(lat,lon);
            MissionContextLogic logic = new MissionContextLogic(ctx);
            ctx.setCarrier(carrier);
            logic.setCarrier(carrier); // WP0自動更新あり
            logic.setWingmen(List.of(wingman));
            logic.initializeFromServlet(carrier, List.of(wingman));
            double landingWeight = logic.calculateLandingWeight();
            int adjustedCruiseSpeed
            	= logic.calculateAdjustedCruiseSpeed();
            
            // 必要なコンテキスト情報を再セット（JSP表示用）
            session.setAttribute("ctx", ctx);
            session.setAttribute("carrier", ctx.getCarrier());
            session.setAttribute("flightplan", ctx.getFlightPlan());
            session.setAttribute("aircraft", ctx.getAircraft());
            session.setAttribute("segmentFuelList", ctx.getSegmentFuelList());
            session.setAttribute("capIndex", ctx.getCapIndex());
            session.setAttribute("capDuration", ctx.getCapDuration());
            session.setAttribute("totalFlightTime", ctx.getTotalFlightTime());
            session.setAttribute("landingWeight", landingWeight);
            session.setAttribute("adjustedCruiseSpeed", adjustedCruiseSpeed);
 
            request.getRequestDispatcher("WEB-INF/mission/MissionReview.jsp").forward(request, response);
            System.out.println("Carrier in ctx: " + ctx.getCarrier().getName());
        	}
        }
    }
}