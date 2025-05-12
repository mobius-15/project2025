package logic;

import java.util.List;

import weapons.Weapons;

public class WeaponsLogic {

    /** 指定リストに兵装を追加 */
    public static void addWeapon(List<Weapons> list, Weapons weapon) {
        list.add(weapon);
    }

    /** 指定リストの総重量を計算 */
    public static double getWeaponsWeight(List<Weapons> list) {
        return list.stream().mapToDouble(Weapons::getWeightlb).sum();
    }

    /** 想定：巡航速度調整（仮に重量で10%低下） */
    public static int calculateAdjustedCruiseSpeed(int baseSpeed, int intFuel, double fullWeight, List<Weapons> list) {
        double weightFactor = (getWeaponsWeight(list) + intFuel) / fullWeight;
        return (int) (baseSpeed * (1 - 0.1 * weightFactor));
    }
    public static long countByType(List<Weapons> list, Class<?> type) {
        return list.stream().filter(type::isInstance).count();
    }
}
