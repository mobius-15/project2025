package code8_01;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Human human =new Human("Yusuke Minato",23);	//インスタンス化
//		human.setName("Yusuke Minato");
//		human.setAge(23);
		
		HttpSession session = request.getSession();	//HttpSessionインスタンス取得
		session.setAttribute("human", human);	//スコープにインスタンスを保存
		
		RequestDispatcher dispatcher		//02.jspにフォワード
		=request.getRequestDispatcher("WEB-INF/jsp/code8_02.jsp");
		
		dispatcher.forward(request, response);
		
		session.removeAttribute("human");
	}

}
