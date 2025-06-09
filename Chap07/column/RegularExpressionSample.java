package column;

import java.util.regex.Pattern;

public class RegularExpressionSample {

	public static void main(String[] args) {
		//チェックする文字列
//		String str = "java";
		String str = "JavaEE";
		//パターンの生成（半角英数4文字）
		Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{4}$");
		//パターンと一致するか
		if(pattern.matcher(str).matches()) {
			//一致したときの処理
			System.out.println("４文字の文字列です");
		}else {
			//一致しなかったときの処理
			System.out.println("何文字ですか？");
		}
	}

}
