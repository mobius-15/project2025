package aircrafts;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
public class Hanger extends Aircrafts{
	static String tailcode ="NF";
	Aircrafts name;
	String squadron ="VFA-102";
	
	public static HashMap<String,FA_18F>vfa102(){
		
	HashMap<String,FA_18F>vfa102=new HashMap<>();
	for(int nf=0;nf<11;nf++){
		FA_18F fa18F = new FA_18F();
		fa18F.setModex(tailcode+(100+nf));
		vfa102.put(tailcode+(100+nf),fa18F);
		}

return vfa102;
	}
	public static HashMap<String,F_35C>vfa147(){
		
		HashMap<String,F_35C>vfa147=new HashMap<>();
	for(int nf2=0;nf2<11;nf2++){
		vfa147.put("NF"+(300+nf2)+"",new F_35C());
		}

return vfa147;		
	}	
	public static List<String>vfa27() {
		
	List<String>vfa27=new ArrayList<String>();
	for(int nf3=0;nf3<11;nf3++) {vfa27.add("NF"+(200+nf3)+"");}

return vfa27;
	}
	public static List<String>vfa195() {
	
	List<String>vfa195=new ArrayList<String>();
	for(int nf4=0;nf4<11;nf4++) {vfa195.add("NF"+(400+nf4)+"");}

return vfa195;
}
	public static List<String> getRandomModexList(List<String> modexList, int count) {
		if(count>modexList.size()) {throw new IllegalArgumentException("無効な要素数");
		}
		Collections.shuffle(modexList); // リストをランダムにシャッフル
return new ArrayList<>(modexList.subList(0, count));
	}
	public String getSquadron() {
		return this.squadron;
	}
	public void setSquadron(String squadron) {
		this.squadron=squadron;
	}
    public static void printSquadron(HashMap<String, FA_18> squadron) {
//        System.out.println("Squadron: " + squadron);
//        for (Map.Entry<String, FA_18> entry : squadron.entrySet()) {
//            System.out.println("Aircraft: " + entry.getKey() + " - " + entry.getValue().getName());
        
//        }
    }
}