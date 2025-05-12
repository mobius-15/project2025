package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logger.LoginLogic;
import logger.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String pass =request.getParameter("pass");
		User user=new User(name,pass);
		LoginLogic loginlogic=new LoginLogic();
		
		boolean isLogin = loginlogic.execute(user);
		
		if(isLogin) {	
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",user);			
		}
		RequestDispatcher dispatcher
		=request.getRequestDispatcher("WEB-INF/logging/loginResult.jsp");
			dispatcher.forward(request, response);
		}
	}


