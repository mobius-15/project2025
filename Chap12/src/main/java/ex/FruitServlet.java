package ex;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FruitServlet
 */
@WebServlet("/FruitServlet")
public class FruitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FruitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Fruit f1= new Fruit("Expensive_Strawbery",3000);

		
		ServletContext application= this.getServletContext();//ServletContextの取得
		application.setAttribute("fruit",f1);//アプリケーションスコープに保存
		
		ArrayList<Human>humanList=new ArrayList<>();
		humanList.add(new Human("Minato",25));
		humanList.add(new Human("Asaka",26));
		humanList.add(new Human("Sugawara",33));
		
		application.setAttribute("humanList",humanList);
		
		RequestDispatcher dispatcher =request.getRequestDispatcher("WEB-INF/ex/fruit.jsp");
		dispatcher.forward(request, response);//fruit.jspにフォワード
	
		
	}

}
