package code9_01;		//パッケージに所属

import java.io.Serializable;

public class Human implements Serializable {	//直列化
	private String name;
	private int age;
	
	public Human() {}		//引数を持たないコンストラクタ(public)
	public Human(String name,int age) {		//持つコンストラクタも定義可
		this.name=name;
		this.age=age;	
	}
	public String getName() {	//カプセル化されているフィールド
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age=age;
	}
}
