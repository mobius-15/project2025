package silver3;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String str="hoge,world";
		hello(str);
		System.out.println(str);
	}
	private static void hello(String msg) {
		msg.replaceAll("hoge","hello");   /*String型をreplaceAll()しても変更できない
		(Immutable)*/
		
	}
}
