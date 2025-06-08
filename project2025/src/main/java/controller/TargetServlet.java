package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.MissionDataDAO;
import logic.MissionContextLogic;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		MissionContext ctx = (MissionContext) session.getAttribute("ctx");
		MissionContextLogic logic = new MissionContextLogic(ctx);
		
		Map<String, Object> result = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		
        System.out.println("Session ID: " + session.getId());
        System.out.println("Context exists: " + (ctx != null));

		if (ctx == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			result.put("status:","error");
			result.put("message:","Context is missing");
			out.print(result);
			out.flush();
			return;
		}

		if (ctx.getFlightPlan() == null || ctx.getFlightPlan().getId() == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			result.put("status:","error");
			result.put("message:","Context or FlightPlan missing");
			out.print(result);
			out.flush();
			return;
		}

		try {
			String json = request.getReader().lines().collect(Collectors.joining());
			System.out.println("Received JSON: " + json);
			Target newTarget = mapper.readValue(json, Target.class);
			System.out.println("Received target: " + newTarget.getName());

			if (newTarget.getName() == null || newTarget.getName().trim().isEmpty()) {
				System.out.println("ERROR: Target name is empty");
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				result.put("status:","error");
				result.put("message:","Target missing");
				out.print(result);
				out.flush();
				return;		
			}

			if (ctx.getTargetPoints() == null) {
				ctx.setTargetPoints(new ArrayList<>());
			}

            System.out.println("Current target count: " + ctx.getTargetPoints().size());
            
            // 新しいTargetを追加
            logic.addTarget(newTarget);            
            System.out.println("Target added. New count: " + ctx.getTargetPoints().size());


			try {
				MissionDataDAO dao = new MissionDataDAO();
				dao.saveTargets(ctx.getFlightPlan().getId(), ctx.getTargetPoints()); //test
				System.out.println("Session updated with targets: " + ctx.getTargetPoints().size());

				System.out.println("Session updated");
				session.setAttribute("ctx", ctx);
				result.put("status", "ok");
				result.put("message", "Target added successfully");
				result.put("count", logic.getTargetCount());
				json = mapper.writeValueAsString(result);
				out.print(json);
	            out.flush();


            } catch (Exception dbException) {
                System.err.println("Database error: " + dbException.getMessage());
                dbException.printStackTrace();
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				result.put("status:","error");
				result.put("message:",dbException.getMessage());
				out.print(result);				
				out.flush();
                session.setAttribute("ctx", ctx);
                System.out.println("Session updated despite DB error");
			}

			
		} catch (JsonProcessingException jsonException) {
			System.err.println("JSON parsing error: " + jsonException.getMessage());
			jsonException.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			result.put("status:","error");
			result.put("message:","Invalid JSON format");
			out.print(result);				
			out.flush();

		} catch (IOException ioException) {
			System.err.println("IO error: " + ioException.getMessage());
			ioException.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);;
			result.put("status:","error");
			result.put("message:","IO error occurred");
			out.print(result);				
			out.flush();

		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			result.put("status:","error");
			result.put("message:","Internal server error");
			out.print(result);				
			out.flush();
		}
	}
}
