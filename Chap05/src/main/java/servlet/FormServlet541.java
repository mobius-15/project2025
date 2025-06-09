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
@WebServlet("/FormServlet541")
public class FormServlet541 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {

		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");

		//リクエストパラメータをチェック
		String errorMsg = "";
		if(name == null || name.length()==0){
			errorMsg += "名前が入力されていません<br>";
		}
		if(gender == null || gender.length()==0){
			errorMsg += "性別が選択されていません<br>";
		}else {
			if(gender.equals("0")) {gender="男性";}
			else if(gender.equals("1")) {gender="女性";}
		}

		//表示するメッセージを設定
		String msg = name + "さん("+ gender +")住所："+address;
		if(errorMsg.length() != 0) {
			msg = errorMsg;
		}

		//HTMLを出力
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>ユーザー登録結果</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>"+ msg +"</p>");
		out.println("</body>");
		out.println("</html>");

	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String hoge = request.getParameter("hoge");
		
		//HTMLを出力
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>154ページサンプル</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>"+ hoge +"</p>");
		out.println("</body>");
		out.println("</html>");
	}


}
