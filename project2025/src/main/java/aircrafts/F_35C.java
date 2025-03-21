package aircrafts;

public class F_35C extends F_35B{
	public F_35C(){
		super();
		setName("F-35C");
		boolean vtol=false;
	}
	public void launch(String name) {
		System.out.println(name+"を発艦");
		System.out.println("カタパルトを使用");
	}
}