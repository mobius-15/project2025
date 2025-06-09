package servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		
		request.setCharacterEncoding("UTF-8");	//リクエストパラメータの文字コードを指定
		
		String name=request.getParameter("name");	//リクエストパラメータを取得
		String gender=request.getParameter("gender");
		
		String errorMsg ="";	//リクエストパラメータをチェック
		if(name==null || name.length()==0) {
			errorMsg += "名前の入力は必須です<br>";
		}
		if(gender==null ||gender.length()== 0){
			errorMsg += "性別の入力は必須です<br>";
		}else {
			if(gender.equals("0")) {gender="男性";
		}else if(gender.equals("1")) {gender="女性";}
		}
		String msg =name +"様("+gender+")を登録しました";
		if(errorMsg.length()!=0) {
			msg= errorMsg;
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Registered</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>"+ msg + "</p>");
		out.println("</body>");
		out.println("</html>");
		
		}
	}
