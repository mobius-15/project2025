package ex4;

public class Sample {
	protected  int num=10;
//	private var value;			//varはフィールドで使用不可
//	public Sample(var value) {
//		this.value=value;
//	}
	int _$3a=0x3_2242;		//変数名に_,$のみ,実数値に_のみ可(_は数値の両端,記号の隣は不可)
	public void test(var value) {	// varはローカル変数のみ
//	public void test() {
		System.out.println(value);
	}
}
