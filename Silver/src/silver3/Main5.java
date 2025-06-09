package silver3;

import java.util.ArrayList;

public class Main5 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
//		String [][]array= {{"A","B"},null,{"C","D","E"}};
//		int total=0;
//		for(String[]tmp:array) {total+=tmp.length;
//	}
//		System.out.println(total);		//nullのため例外
		System.out.println("\n");
		
		ArrayList list = new ArrayList<>();
		list.add("A");
		list.add(10);
		list.add('B');	//Object型のListとなるため、エラーとならない
		for(Object o:list) {
			System.out.println(o);

			}
	System.out.println("\n");
	ArrayList<String>list2=new ArrayList<>();
	list2.add("A");
	list2.add(2,"B");//index[0],[1]しか存在しない状態で[2]を指定しているので例外となる
	list2.add("C");
	list2.add("D");
	for(String str:list2) {
		System.out.print(str+",");
	}
}
}