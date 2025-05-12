	package weapons;
import java.util.ArrayList;
import java.util.List;

public class WeaponFactory {
	
	private static final List<Weapons> masterList = new ArrayList<>();

    static {
        masterList.add(new AIM120());
        masterList.add(new AIM9());
        masterList.add(new AGM88());
        masterList.add(new AGM154());
        masterList.add(new GBU31());
        masterList.add(new FuelTank());
    }

    public static List<Weapons> getAllWeapons() {
        return masterList;
    }

    public static Weapons getByName(String name) {
        for (Weapons w : masterList) {
            if (w.getName().equalsIgnoreCase(name)) {
                return w;
            }
        }
        return null;
    }
}

