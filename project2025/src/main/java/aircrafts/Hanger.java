package aircrafts;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import mission.Target;
public class Hanger extends Aircrafts{
	static String tailcode ="NF";
	Aircrafts name;
	String squadron ="VFA-102";
	
	public static TreeMap<String,FA_18F>vfa102(){
		
	TreeMap<String,FA_18F>vfa102=new TreeMap<>();
	for(int nf=0;nf<11;nf++){
		FA_18F fa18F = new FA_18F();
		fa18F.setModex(tailcode+(100+nf));
		vfa102.put(tailcode+(100+nf),fa18F);
		}

return vfa102;
	}
	public static TreeMap<String,F_35C>vfa147(){
		
		TreeMap<String,F_35C>vfa147=new TreeMap<>();
	for(int nf2=0;nf2<11;nf2++){
		F_35C f35C =new F_35C();
		f35C.setModex(tailcode+(300+nf2));
		vfa147.put(tailcode+(300+nf2)+"",f35C);
		}

return vfa147;		
	}	
	public static TreeMap<String,FA_18E>vfa27() {
		
	TreeMap<String,FA_18E>vfa27=new TreeMap<String,FA_18E>();
	for(int nf3=0;nf3<11;nf3++) {vfa27.put("NF"+(200+nf3)+"",new FA_18E());}

return vfa27;
	}
	public static TreeMap<String,FA_18E>vfa195() {
	
		TreeMap<String,FA_18E>vfa195=new TreeMap<String,FA_18E>();
	for(int nf4=0;nf4<11;nf4++) {vfa195.put("NF"+(400+nf4)+"",new FA_18E());}

return vfa195;
}
//	public static TreeMap<String,Object> getRandomModexList(TreeMap<String,Object> modexList, int count) {
//		if(count>modexList.size()) {throw new IllegalArgumentException("無効な要素数");
//		}
//		Collections.shuffle(modexList); // リストをランダムにシャッフル
//return new TreeMap<>(modexList.subList(0, count));
//	}
	public String getSquadron() {
		return this.squadron;
	}
	public void setSquadron(String squadron) {
		this.squadron=squadron;
	}
//    public static void printSquadron(TreeMap<String, FA_18> squadron) {
//        System.out.println("Squadron: " + squadron);
//        for (Map.Entry<String, FA_18> entry : squadron.entrySet()) {
//            System.out.println("Aircraft: " + entry.getKey() + " - " + entry.getValue().getName());
        
//        }
//    }
        public static Aircrafts selectRandomWingman(TreeMap<String, FA_18F> squadron, String excludeModex) {
            List<Map.Entry<String, FA_18F>> entries = new ArrayList<>(squadron.entrySet());
            Collections.shuffle(entries);
            for (Map.Entry<String, FA_18F> entry : entries) {
                if (!entry.getKey().equals(excludeModex)) {
                    return entry.getValue();
                }
            }
            return null;
        }
            public static List<Aircrafts> selectWingmen(TreeMap<String, ? extends Aircrafts> squadron, String excludeModex, int count) {
                List<Aircrafts> wingmen = new ArrayList<>();
                List<Map.Entry<String, ? extends Aircrafts>> entries = new ArrayList<>(squadron.entrySet());
                Collections.shuffle(entries);

                for (Map.Entry<String, ? extends Aircrafts> entry : entries) {
                    if (!entry.getKey().equals(excludeModex)) {
                        wingmen.add(entry.getValue());
                        if (wingmen.size() >= count) break;
                    }
                }
                return wingmen;
            }
            public static void printTargets(List<Target> targets) {
            	
                for (Target t : targets) {
                    System.out.printf("Target: %s - Lat %.3f / Lon %.3f%n", t.getName(), t.getLat(), t.getLon());
                }
            
           	}
    	
    }