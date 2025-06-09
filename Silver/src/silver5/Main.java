package silver5;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		ArrayList<Item>list=new ArrayList<Item>();
		list.add(new Item("A",100));
		list.add(new Item("B",200));
		list.add(new Item("C",300));
		list.add(new Item("A",100));
		list.remove(new Item("A",500));	/*priceは一致しないが、instanceOfで
nameが一致すれば同じものとみなすため、最初に格納されているAが削除される(B,C,A)*/
		
		for(Item item:list) {
			System.out.println(item.getName());
		}
		System.out.println("\n");
		ArrayList<String>list2=new ArrayList<>();
		list2.add("A");
		list2.add("B");
		list2.add("C");
		for(String str:list2) {
			if("B".equals(str)) {list2.remove(str);
			}else {System.out.println(str);}
		}/*"B"の削除で"C"が繰り上がるが、"C"の次の要素はないため
		 繰り返し処理が停止してしまい"A"のみが表示される
		 ArrayListは読み出しの最中に要素を削除すると例外となってしまうが、
		 * ここでは要素strを削除した後に呼び出していない*/
	}
	

}