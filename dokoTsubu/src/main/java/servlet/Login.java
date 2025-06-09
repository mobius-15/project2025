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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String pass =request.getParameter("pass");
		//Userインスタンスの生成
		User user=new User(name,pass);
		LoginLogic loginlogic=new LoginLogic();
		
		boolean isLogin = loginlogic.execute(user);
		
		if(isLogin) {	//ログイン成功時は
			HttpSession session = request.getSession();
			session.setAttribute("loginUser",user);
			//ユーザーインスタンスをセッションスコープに保存
		}
		RequestDispatcher dispatcher //ログイン結果画面にフォワード
		=request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
			dispatcher.forward(request, response);
		}
	}


