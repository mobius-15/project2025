package aircrafts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FlightPlan3
 */
@WebServlet("/FlightPlan3")
public class FlightPlan3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightPlan3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String missionType=request.getParameter("missionType");
		
		String nwPoint=request.getParameter("wayPoint");
		
		 if (nwPoint == null) {
	RequestDispatcher dispatcher = request.getRequestDispatcher("Command.jsp");
         dispatcher.forward(request, response);
         return;
     }
		 int wayPoint =Integer.parseInt(nwPoint);
		Math.min(wayPoint,20);
		int[][]sa=new int[wayPoint][3];
		
		for(int s=0;s<sa.length;s++) {
			sa[s][0]=Math.max(100, Math.min(50000,Integer.parseInt(request.getParameter("altitude"))));
			
			
			int cas=Math.max(130, Math.min(1000,Integer.parseInt(request.getParameter("cas"))));
			
			sa[s][1]=(int)(cas*(1/Math.sqrt(Aircrafts.ρρ0)));
			
			sa[s][2]=Math.max(5, Math.min(300, Integer.parseInt(request.getParameter("distance"))));
			
			
			 List<int[]>fpList=new ArrayList<>();
			
		     for (int[] point : sa) {
		    	 fpList.add(point);
		     }
		     request.setAttribute("mission", missionType);
		     request.setAttribute("FP",fpList);	
		     
		        RequestDispatcher dispatcher = request.getRequestDispatcher("FlightPlanner.jsp");
		        dispatcher.forward(request, response);
		}
		
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out =response.getWriter();

	}
}