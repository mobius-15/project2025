package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex5_1
 */
@WebServlet("/Ex5_1")
public class Ex5_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ex5_1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String name=request.getParameter("name");
		String qtype=request.getParameter("qtype");
		String body=request.getParameter("body");
		
		Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{6}$");
			//正規表現
		
		String error="";
												//設定値と違う場合
		if(name==null || name.length()==0 ||!pattern.matcher(name).matches()) {
			error+="名前を正確に入れてください(6文字まで)";
		}
			String msg= name+"様を登録しました";
			if(error.length()!=0) {
				msg=error;
		}
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Ex5_1</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>"+ msg + "</p>");
		out.println("<p>"+ name + "</p>");
		out.println("<p>"+ qtype + "</p>");
		out.println("<p>"+ body + "</p>");
		out.println("</body>");
		out.println("</html>");
		}

	}

