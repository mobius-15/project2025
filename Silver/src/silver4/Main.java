package silver4;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		A[]array= {new C(),null,new D()};
		Object[]aryo=array;
		System.out.println("\n");
	}
/*コンパイルエラーにならない：継承関係にある場合、スーパークラス型の配列変数で
 *サブクラスのインスタンスの集合を扱える(ex:Object型,String型) インターフェイスと
 *それを実装するクラスに於いても同様*/
	/*(互換性がない型でキャスト使用時に例外発生)
	 * このコードで、変数array,aryoを使う箇所がある場合はnullで例外となる*/
	
}
