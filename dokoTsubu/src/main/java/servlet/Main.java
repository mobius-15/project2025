package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;
/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GetMutterListLogic getMutterListLogic=new GetMutterListLogic();
	
		List<Mutter>mutterList	=getMutterListLogic.execute();

			request.setAttribute("mutterList", mutterList);
		//ログインしているか確認する
		HttpSession session=request.getSession();
		User loginUser =(User)session.getAttribute("loginUser");
		
		if(loginUser == null) {//ログインしていなければリダイレクト
			response.sendRedirect("index.jsp");
			}else {	//ログイン済みならmainへフォワード
				RequestDispatcher dispatcher
			=request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
				dispatcher.forward(request, response);
			}
		}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text= request.getParameter("text");
				
	if(text != null && text.length() != 0){
			
	HttpSession session =request.getSession();	//User情報（セッションスコープより取得）
	User loginUser =(User)session.getAttribute("loginUser");
			
	Mutter mutter = new Mutter(loginUser.getName(),text);	//投稿インスタンス作成
	PostMutterLogic posting = new PostMutterLogic();	//ロジックインスタンス作成
	posting.execute(mutter);	//
	
	}else {
			request.setAttribute("errorMsg", "入力は必須です");
	}
		GetMutterListLogic getMutterListLogic= new GetMutterListLogic();
		List<Mutter>mutterList= getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}
