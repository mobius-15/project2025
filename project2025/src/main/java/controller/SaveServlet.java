package controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.MissionDataDAO;
import mission.MissionContext;

/**
 * Servlet implementation class SaveServlet
 */
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getParameter("action");
	       MissionContext ctx = (MissionContext) request.getSession().getAttribute("ctx");
	        if (ctx == null) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mission data not found.");
	            return;
	        }
	        switch (action) {
             case "exportJSON":
                exportJSON(ctx, response);
                break;
            case "saveDB":
                saveToDatabase(ctx, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
	        }
		}
	        private void exportJSON(MissionContext ctx, HttpServletResponse response) throws IOException {
	            response.setContentType("application/json");
	            String json = new ObjectMapper().writeValueAsString(ctx);
	            response.setHeader("Content-Disposition", "attachment; filename=mission_data.json");
	            response.getWriter().write(json);
	        }

	        private void saveToDatabase(MissionContext ctx, HttpServletResponse response) throws IOException {
	            MissionDataDAO dao = new MissionDataDAO();
	            try {
	                dao.saveMissionContext(ctx);  // 統一ポイント
	                response.setContentType("application/json");
	                response.getWriter().write("{\"status\": \"saved\"}");
	            } catch (SQLException e) {
	                e.printStackTrace();
	                response.setContentType("application/json");
	                response.getWriter().write("{\"status\": \"failed\"}");
	        }
	  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String action = request.getParameter("action");
        MissionContext ctx = (MissionContext) request.getSession().getAttribute("ctx");
        if (ctx == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "MissionContext not found.");
            return;
        }
        System.out.println("Targets before saving to DB: " + ctx.getTargetPoints().size());


        try {
            if ("saveDatabase".equals(action)) {
                MissionDataDAO dao = new MissionDataDAO();
                System.out.println("Saving MissionContext: Targets count = " + ctx.getTargetPoints().size());
                
                dao.saveMissionContext(ctx);
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"Database save successful\"}");

            } else if ("exportJSON".equals(action)) {
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().write(mapper.writeValueAsString(ctx));

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                return;
            }
            System.out.println("Saving loadouts, count: " + ctx.getLoadouts().size());
            System.out.println("Saving targets for mission ID: " + ctx.getFlightPlan().getId());
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        }

    }
}

