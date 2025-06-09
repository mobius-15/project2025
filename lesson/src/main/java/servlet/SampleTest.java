package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;
/**
 * Servlet implementation class SampleTest
 */
@WebServlet("/SampleTest")
public class SampleTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       /*
        10month, 200process
	 	①	②	③	④
process:16%	33%	42%	9%
/per man:32	66	84	18

sched: 20%	30%	42%	10%
month:	2	3	4	1
		
maxpeople:16 22	21	18
     
     */
    public SampleTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String shain_code =request.getParameter("shain_code");
		String password= request.getParameter("password");
		User user= new User(shain_code,password);
		LoginLogic bo= new LoginLogic();
		boolean result =bo.execute(user);
		
		if(result){HttpSession session=request.getSession();
		session.setAttribute("shain_code",shain_code);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/list.jsp");
		dispatcher.forward(request, response);
		}else {
			request.setAttribute("error", "NOT FOUND");
	RequestDispatcher dispatcher=request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);			
//			response.sendRedirect("Login");
		}
	}

}
