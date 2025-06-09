package models;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/enter");
		 dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type=request.getParameter("type");

		String armament1 = request.getParameter("AA");
		
		Weapons weapon1 =new Weapons(armament1,"",2);
		
		if(armament1.equals("AIM-120")){}
		
		int nTank=Integer.parseInt(request.getParameter("ntank"));
		Jet sortie1= new Jet();
		
		
	sortie1.setFullWeight(sortie1.fullFuel(nTank)+sortie1.geteWeight()+weapon1.wWeight());
		Emulator emulator1=new Emulator();
		emulator1.fuelConsumption(sortie1);
		
		request.setAttribute("sortie1",sortie1);
		RequestDispatcher dispatcher 
		=request.getRequestDispatcher("WEB-INF/export.jsp");
		dispatcher.forward(request, response);
	}

}
