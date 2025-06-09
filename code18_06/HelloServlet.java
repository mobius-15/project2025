package code18_06;

import java.io.PrintWriter;
import jakarta.servlet.http.HttpServlet;


@WebServlet("/HelloServlet");
public class HelloServlet extends HttpServlet{
	protected void doGet(HttpServletRequestvlet req,
			HttpServletResponse res)throws IOException {
		Date d= new Date();
		PrintWriter w=res.getWriter();
		res.setContentType("text/html");
		w.write("<html><body>");
		w.write("Today is"+d.toString());
		w.write("</body></html>");
	}
}
