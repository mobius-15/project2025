package soldiers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import dao.SoldierDAO;
import util.LogUtil;

public class SoldierLogic {

	public SoldierBean load(int id) {
		LogUtil.println(this.getClass().getSimpleName());
		SoldierDAO solDAO=new SoldierDAO();
		SoldierBean soldier =solDAO.load(id);
		
		return soldier;
	}
	public String delete(SoldierBean soldier) {
	    SoldierDAO dao = new SoldierDAO();
	    boolean result = dao.delete(soldier.getId())!=null;

	    if (!result) {
	        return "削除に失敗しました。";
	    }

	    return null; // 成功
	}
	public String add(SoldierBean soldier) {
		String result=null;
	    SoldierDAO dao = new SoldierDAO();
	    
	    if (soldier==null) {
	        return "追加に失敗しました。";
	    }
	    result=dao.add(soldier);
	    if(result != null) {result="追加できず"; 
	    }
	    return null;
	}
	public String update(SoldierBean soldier) {
	    SoldierDAO dao = new SoldierDAO();
	    String result = dao.update(soldier);
	    

	    if (result !=null) {
	        return "更新に失敗しました。";
	    }

	    return result;
	}
	public void setSoldierBeanFromRequestToSession(HttpServletRequest request) {
	    LogUtil.println(this.getClass().getSimpleName() + "#setSoldierFromRequestToSession");

	    // リクエストからパラメータ取得
	    String name = request.getParameter("name");
	    String zip = request.getParameter("zip");
	    String add1 = request.getParameter("add1");
	    String add2 = request.getParameter("add2");
	    String tel = request.getParameter("tel");
	    String mail = request.getParameter("mail");

	    // Beanにセット
	    SoldierBean soldier = new SoldierBean();
	    soldier.setName(name);
	    soldier.setZip(zip);
	    soldier.setAdd1(add1);
	    soldier.setAdd2(add2);
	    soldier.setTel(tel);
	    soldier.setMail(mail);

	    // セッションに保存
	    HttpSession session = request.getSession();
	    session.setAttribute("soldier", soldier);
	}
}
