package silver3;

public class Main4 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String a="abc";
		String b=new String("a");
		
		int count=0;
		
		if(a.intern()=="abc") {
			count++;
		}
		if(b.intern()=="abc") {		
			count++;
		}
		if(a.intern()==b.intern()) {	//aとbはfalseだが,intern()で同じ参照先を指定するためtrueになる
			count++;
		}
		System.out.println(count);
		System.out.println("\n");


		System.out.println("\n");
//		Item[]items=new Item[3];
//		int total=0;
//		for(int i=0;i<items.length;i++) {
//			total+=items[i].price;
//		}
//		System.out.println(total);
		System.out.println("\n");
		String[]array2= {"A","B","C","D"};
				array2[0]=null;
				for(String str3:array2) {System.out.print(str3+",");	}
				/*nullは不変,[1]以降には挿入される*/
			System.out.println("\n");
			
			int[]K[]=new int[][] {};
			int u=0;
			for(int[] o:K) {for(int p:o)
			System.out.println(u+=p);}


			}
}