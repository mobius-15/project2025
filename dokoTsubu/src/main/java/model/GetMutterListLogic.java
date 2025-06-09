package model;

import java.util.List;

import dao.MuttersDAO;

public class GetMutterListLogic {
	public List<Mutter>execute(){
		MuttersDAO dao= new MuttersDAO();	//DAOインスタンスを生成し
		List<Mutter>mutterList=dao.findAll(); //DAO内のメソッドでリストを取得
		return mutterList;
	}
}
