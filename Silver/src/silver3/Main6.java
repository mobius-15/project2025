package silver3;

import java.util.ArrayList;

public class Main6 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		ArrayList<String>list=new ArrayList<>();
		list.add("A");
		list.set(0,"B");
		list.add("C");
		list.set(1,"D");
		for(String s:list) {
			System.out.println(s);
		}
	}

}
