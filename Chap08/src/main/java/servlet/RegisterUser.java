package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegisterUserLogic;
import model.User;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String forwardPath=null;		//フォワード先
		String action = request.getParameter("action");
		//servletクラスの動きを決定するaction値をリクエストパラメータから取得
		
		//登録開始をリクエストされた処理(A)
		if(action==null) {	
			forwardPath="WEB-INF/jsp/registerForm.jsp";	//(A)のフォワード先を設定
		}else if(action.equals("done")) {
			/*else ifで登録確認画面から登録実行がリクエストされた時の処理*/
			
			/*セッションスコープに保存された登録ユーザを取得 工程5*/ 
			HttpSession session =request.getSession();
			User registerUser =(User)session.getAttribute("registerUser");
			
			//登録処理を呼び出す 工程6
			RegisterUserLogic logic =new RegisterUserLogic();
			logic.execute(registerUser);	/*trueを返す*/
			
			//不要となったセッションスコープ内のインスタンスは削除 工程7
			session.removeAttribute("registerUser");
			
			forwardPath= "WEB-INF/jsp/registerDone.jsp";
		}
		//設定されたforwardpath（登録完了画面）にフォワード 工程8
		RequestDispatcher dispatcher=request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストパラメータを取得 工程1
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		
			//登録するユーザ 工程2
		User registerUser = new User(id,name,pass);
		
			//セッションスコープに登録ユーザを保存 工程3
		HttpSession session =request.getSession();
		session.setAttribute("registerUser", registerUser);
		
			//確認画面にフォワード  工程4
		RequestDispatcher dispatcher 
		= request.getRequestDispatcher("WEB-INF/jsp/registerConfirm.jsp");
		dispatcher.forward(request, response);
	}

}
