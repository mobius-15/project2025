package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class JObServlet
 */
@WebServlet("/JobServlet")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		request.getRequestDispatcher("/WEB-INF/jsp/job.jsp");
		
		String job=request.getParameter("job");
		String message=null;
		if(job==null) {message+=" フォーム画面から入力";}
		else if(job.equals("")) {message +=" 何も入力されていません";}
		if(message==null){
		session =request.getSession();
				session.setAttribute("job", job);
				session.setAttribute("message", message);
request.getRequestDispatcher("/WEB-INF/jsp/confirm.jsp").forward(request, response);
	}else{request.setAttribute("message", message);
	request.getRequestDispatcher("/WEB-INF/jsp/job.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
