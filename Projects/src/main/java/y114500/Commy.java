package y114500;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Command
 */
@WebServlet("/commy")
public class Commy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Commy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int[]numbers= {1,4,6,9,13,16};
		int evenSum=0;
		int oddSum=0;
	for(int number:numbers ){
		System.out.println(number);
		if(number % 2 == 0) {
			evenSum += number;
		}else {
			oddSum += number;
		}
	}
		System.out.println("偶数の和は"+evenSum );
		System.out.println("奇数の和は"+oddSum);
	int[]numbers2= {2,5,7,10,12,15};
		int evenSum2 =0;
		int oddSum2 =0;
	for(int n = 0;n < numbers2.length;n++) {
		if(numbers2[n]% 2 == 1) {
//	for(int number2:numbers2) {
//		System.out.println(number2);
//		if(number2%2==1) {
//			oddSum2 += number2;
//		}else {
//			evenSum2 += number2;
			oddSum2 += numbers2[n];
		}else {
			evenSum2 += numbers2[n];
		}
	}
		System.out.println("奇数計"+oddSum2+" "+"偶数計"+evenSum2);
		
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out=response.getWriter();
	out.println("<! DOCTYPE html>");
	out.println("<html>");
	out.println("<head>");
	out.println("<meta charset=\"UTF-8\"/>");
	out.println("<title>Calculate</title>");
	out.println("</head>");
	out.println("<body>");
	out.println("<p>偶数の和は"+evenSum+"<br>");
	out.println("奇数の和は"+oddSum+"</p><br>");
	out.println("<p>奇数計"+oddSum2+"<br>偶数計"+evenSum2+"</p>");
	out.println("</body>");
	out.println("</html>");
}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
