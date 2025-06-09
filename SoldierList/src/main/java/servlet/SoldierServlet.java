package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import soldiers.SoldierBean;
import soldiers.SoldierLogic;

/**
 * Servlet implementation class SoldierServlet
 */
@WebServlet("/SoldierServlet")
public class SoldierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SoldierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	

		
	private void soldierDetail(HttpServletRequest request, HttpServletResponse response,HttpSession session,String id) throws ServletException, IOException{
		int intId=Integer.parseInt(id);
		SoldierLogic soldierLogic=new SoldierLogic();
		SoldierBean soldier =null;
		soldier = soldierLogic.load(intId);
		session.setAttribute("soldier", soldier);
		
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/detail.jsp").forward(request, response);
	
	}
	private void soldierUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
	    SoldierLogic soldierLogic = new SoldierLogic();	    
	    // リクエストからBeanを作成し、セッションに保存
//	    soldierLogic.setSoldierBeanFromRequestToSession(request);
	    // セッションからBeanを取得
	    SoldierBean soldier = (SoldierBean) session.getAttribute("soldier");
	    // 更新処理（戻り値がnullなら成功）
	    String errMessage = soldierLogic.update(soldier);
	    if (errMessage == null) {
	        getServletContext().getRequestDispatcher("/WEB-INF/jsp/update_success.jsp").forward(request, response);
	    } else {	
	        session.setAttribute("errMessage", errMessage);
	        getServletContext().getRequestDispatcher("/WEB-INF/jsp/update_fail.jsp").forward(request, response);
	    }
	}
	private void soldierAdd(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ServletException, IOException{
		//セッションから将兵の情報を取得し、DB追加処理後、新規登録完了(成功時)画面、または、新規登録未完了(失敗時)画面に遷移する
		String errMessage=null;
	    SoldierLogic soldierLogic = new SoldierLogic();
	    // セッションからBeanを取得（確認画面でset済み前提）
	    SoldierBean soldier = (SoldierBean) session.getAttribute("soldier");

	    if (soldier == null) {
	        // セッション切れ or 異常時は失敗扱い
	        request.setAttribute("message", "登録処理に失敗しました（セッションが無効です）。");
	        getServletContext().getRequestDispatcher("/WEB-INF/jsp/add_fail.jsp").forward(request, response);
	        return;
	    }
	    errMessage = soldierLogic.add(soldier);
	    if (errMessage == null) {		       
	        getServletContext().getRequestDispatcher("/WEB-INF/jsp/add_success.jsp").forward(request, response);
	    } else {
	        session.setAttribute("message", errMessage);
	        getServletContext().getRequestDispatcher("/WEB-INF/jsp/add_fail.jsp").forward(request, response);
	    }
	}
	private void soldierNew(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ServletException, IOException{
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/new.jsp");
		}
	private void soldierDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
		SoldierBean soldier =(SoldierBean)session.getAttribute("soldier");
	    SoldierLogic soldierLogic = new SoldierLogic();
	    String errMessage=null;
//	    String a="MESSAGE_CAN_NOT_DELETE";
	    if(errMessage==null) {
	    	getServletContext().getRequestDispatcher("WEB-INF/jsp/delete_success.jsp");
	    }else { 
	    session.setAttribute("errMessage", errMessage);
	    getServletContext().getRequestDispatcher("WEB-INF/jsp/delete_fail.jsp");
	    	    
	    }
	}
	    private void soldierDeleteConfirm(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
	    	
	    	int intId=Integer.parseInt(id);
	    	SoldierLogic soldierLogic=new SoldierLogic();
	    	SoldierBean soldier=soldierLogic.load(intId);
	    	if(soldier != null) {
	    		HttpSession session=request.getSession();
	    		session.setAttribute("soldier", soldier);
	    	getServletContext().getRequestDispatcher("/WEB-INF/jsp/delete_confirm.jsp");}
	}
	private boolean isNullOrEmpty(String str) {
	    return str == null || str.trim().isEmpty();
	}
	private void newConfirm(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
	    SoldierLogic logic = new SoldierLogic();
	    logic.setSoldierBeanFromRequestToSession(request);
	    getServletContext().getRequestDispatcher("/WEB-INF/jsp/new_confirm.jsp").forward(request, response);
	}
}