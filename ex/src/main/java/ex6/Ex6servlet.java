package ex6;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex6servlet
 */
@WebServlet("/ex62")
public class Ex6servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ex6servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Random random =new Random();
			   //(int)(Math.random()*10);
		int a=random.nextInt(10);
	
		
		
		if(a % 2 == 1) {
			
			response.sendRedirect("redirected.jsp");
			
		}else {
			RequestDispatcher dispatcher
			=request.getRequestDispatcher("forwarded.jsp");
			
			dispatcher.forward(request,response);
		}
		
	}

}
