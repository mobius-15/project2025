package silver3;

public class Main2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String str="abcde";
		System.out.println(str.charAt(4));
			/*.charAt()は、インデックス番号と同じく0から数えるので、
			 * [5]を指定した場合要素の範囲外となる([4]まで)*/
		System.out.println("\n");
		System.out.println(str.indexOf("de"));
			/*.indexOf()では、含まれていない文字列を指定した場合-1を返す*/
		System.out.println("\n");
		System.out.println(str.substring(2,4));/*[1]より(4-2)文字分を取得
		 			存在しない場合は例外*/
		System.out.println("\n");
		String str2="aaaa";
		System.out.println(str2.replace("aa","b"));/*2文字ずつ置き換え
		 	どちらかがchar型の場合はエラーとなる*/
		System.out.println("\n");
//		System.out.println(str.charAt(str.length()));  /*[5]になる為エラー*/
		System.out.println(str.substring(1,3).startsWith("b")); 
	}

}
