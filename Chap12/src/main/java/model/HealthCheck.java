package model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HealthCheck
 */
@WebServlet("/HealthCheck")
public class HealthCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher dispatcher= request.getRequestDispatcher("WEB-INF/jsp/healthCheck.jsp");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
								//リクエストパラメータ
		String weight = request.getParameter("weight");
		String height = request.getParameter("height");
								//入力値をプロパティに設定
		Health health = new Health();
		health.setHeight(Double.parseDouble(height));
		health.setWeight(Double.parseDouble(weight));
							//健康診断を実行し結果を設定
		HealthCheckLogic healthCheckLogic 
						= new HealthCheckLogic();	//インスタンス化しHealthCheckLogicで処理を実行
		healthCheckLogic.execute(health);
							//リクエストスコープに保存
		request.setAttribute("health", health);
						//フォワード
		RequestDispatcher dispatcher= request.getRequestDispatcher
				("WEB-INF/jsp/healthCheckResult.jsp");
		dispatcher.forward(request, response);
		
	}

}
