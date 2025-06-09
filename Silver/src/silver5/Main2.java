package silver5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		var list0=List.of(1,2,3);
		var list4=new ArrayList<>();
		var list5=Arrays.asList(new Integer[]{1,2,3});  /*固定長は0と5*/
		var list6=new ArrayList<Integer>(3);
		
		System.out.println("\n");
		ArrayList<String>list=new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		for(String str:list) {
			if("C".equals(str)) {list.remove(str);
			}
		}
		
		for(String str:list) {
			System.out.println(str);
		}
		/*ArrayListはスレッドセーフクラスでない事から、
		 * 読み出しの最中に要素を削除すると例外となってしまう
		 * 要素strを削除した後にforで呼び出しているので*/
		
	}

}
