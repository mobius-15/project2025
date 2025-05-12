package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.MissionDataDAO;
import mission.MissionContext;
import mission.Target;

/**
 * Servlet implementation class TargetServlet
 */
@WebServlet("/TargetServlet")
public class TargetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TargetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    MissionContext ctx = (MissionContext) session.getAttribute("ctx");
	    if (ctx == null) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "MissionContext not found");
	        return;
	    }
	    if (ctx.getFlightPlan() == null || ctx.getFlightPlan().getId() == null) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "FlightPlan or its ID not found in context");
	        return;
	    }
	    String json = request.getReader().lines().collect(Collectors.joining());
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	    Target newTarget =mapper.readValue(json,Target.class);
	    System.out.println("Received target: " + newTarget.getName());

//	    List<Target> targetList = ctx.getTargetPoints();
        if (ctx.getTargetPoints()== null) {
        	ctx.setTargetPoints(new ArrayList<>());
        }
//	    Target dummy = new Target("Test Target", 25.0, 135.0);
//	    dummy.setRadius(0.5);
//	    ctx.getTargetPoints().add(dummy);
	    
	    ctx.getTargetPoints().add(newTarget);
//	    ctx.setTargetPoints(targetList);  

	    
	    MissionDataDAO dao = new MissionDataDAO();
	    dao.saveTargets(ctx.getFlightPlan().getId(),ctx.getTargetPoints());		//test
	    session.setAttribute("ctx", ctx);
	    System.out.println("Saving targets for mission ID: " + ctx.getFlightPlan().getId());	    
	    System.out.println("Session updated with targets: " +ctx.getTargetPoints().size() );	//test
	    // 応答返却
	   
	        // 保存処理
	    } catch (Exception e) {
	    	e.printStackTrace();
	        response.setContentType("application/json");
	        response.getWriter().write("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
	    }
	    response.sendRedirect("CarrierInfo?action=review");
	}
	
}

