package silver3;

public class Main3 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String str="Hello,".concat("Java");	//.concatはString用
		System.out.println(str);
		System.out.println("\n");
		StringBuilder sb=new StringBuilder("abcde");
		System.out.println(sb.capacity());	/*StringBuilder:変更可能な文字列
		capacity:文字数+16バッファ*/
		sb.append("abcdef");		//.appendはStringBuilder用
		sb.reverse();
		sb.replace(2, 3, "a");
		System.out.println(sb);
		System.out.println("\n");
		StringBuilder sc=new StringBuilder("abcd");
		System.out.println(sc.indexOf("cd"));
		System.out.println("\n");
		String str3="""
							A
								B
									C
								""";
		System.out.println(str3);
		System.out.println("\n");
		String a="qeaaga";
		String b="qeaaga";
		String c=new String("qeaaga");
		System.out.print(a==b);
		System.out.print(",");
		System.out.println(a.equals(b));
		System.out.print(a==c);
		System.out.print(",");
		System.out.println(a.equals(c));
		
	}

}
