package project90;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Flight0
 */
@WebServlet("/Commander")
public class Commander extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Airbase base1=new Airbase("DDH-184",20,"035");
		base1.baseType(2);
		
		Jet jet1=new Jet("F-35B","Ground_Strike");
		jet1.elements=2;
		
		jet1.setEWeight(29300);

		Weapons weapon1=new Weapons("A/A","",2);
		Weapons weapon2=new Weapons("A/G","",2);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\" />");
		out.println("<title>Uranai</title>");
		out.println("</head>");
		out.println("<body>");
//		out.println("<p>出撃拠点："+base1.baseType(2)+"</p>");
		out.println("<p>機体と兵装の重量は"+jet1.fullWeight(weapon1,weapon2)+"lb</p>");
		out.println("</body>");
		out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
