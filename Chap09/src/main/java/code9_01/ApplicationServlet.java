package code9_01;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/AppricationServlet")
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Human human =new Human("Yusuke Minato",23);	//インスタンス化
		
		ServletContext application = this.getServletContext();	//ServletContextインスタンス取得
		application.setAttribute("human", human);	//スコープにインスタンスを保存
		
		RequestDispatcher dispatcher		//9_01.jspにフォワード
		=request.getRequestDispatcher("WEB-INF/jsp/code9_01.jsp");
		
		dispatcher.forward(request, response);
		
		application.removeAttribute("human");
	}

}
