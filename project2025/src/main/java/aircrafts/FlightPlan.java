package aircrafts;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FlightPlan extends Aircrafts{
	int distance;
	int altitude;
	int wayPoint;
//	int wpCount;
	int[][]FP;
	private static List<int[]>fpList=new ArrayList<>();
	private final Scanner scanner = new Scanner(System.in); 
	
	public FlightPlan() {
		Scanner scanner=new Scanner(System.in);
		try {
		System.out.println("Mission(飛行目的)を入力");
		String missionType=scanner.nextLine();
		System.out.println("WP数を入力");
		wayPoint=getValidIntInput(scanner,"WP(整数):");
		if(wayPoint>25) {wayPoint=25;}
		
		FP=this.specify(wayPoint);	//
		
		}catch (Exception e) {
			System.err.println("エラー " + e.getMessage());
		}

	}
	public int[][]specify (int wayPoint){
		int[][]sa=new int[wayPoint][3];//
		for(int s=0;s<sa.length;s++){super.wpCount++;System.out.println("WP"+super.wpCount+"の高度を入力");
		sa[s][0]= getValidIntInput(scanner,"Altitude(整数):");
		if(sa[s][0]>50000) {sa[s][0]=50000;}else if(sa[s][0]<=100) {sa[s][0]=100;}
		System.out.println("WP"+super.wpCount+"の速度を入力");
		sa[s][1]= getValidIntInput(scanner,"knots(整数):");
		if(sa[s][1]>1000) {sa[s][1]=1000;}else if(sa[s][1]<=130) {sa[s][1]=130;}
		System.out.println("次WPとの距離を入力");
		sa[s][2]= getValidIntInput(scanner,"nm(整数):");
		if(sa[s][2]>300) {sa[s][2]=300;}else if(sa[s][2]<=5) {sa[s][1]=5;}
		}
		return sa;
	}
		
    private int getValidIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt + " ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("入力は整数のみ");
                scanner.nextLine(); }// バッファをクリア
        	}
        
        }
    private double getValidDoubleInput(Scanner scanner, String prompt) {
        while (true) {			//燃料用
            try {
                System.out.print(prompt + " ");
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("入力は数値のみ");
                scanner.nextLine();
            }
        }
    
//	public int[][] getFlightPlan() {
////		return FP;
	}
	public void gconvertAndAdd(int[][]FP) {
        for (int[] point : FP) {
            fpList.add(point);
        }
	}
	 public void displayFlightPlans() {
	        if (fpList == null) {
	            System.err.println("No ArrayList");
	            return;
	        }	    	
	        for (int[] point : fpList){
	        	System.out.println("Altitude (ft)\tTAS Speed (kt)\tDistance (nm)\"");
	            System.out.println(point[0] + "ft\t\t" + point[1] + "kt\t\t" + point[2] + "nm");
	        	if(point[0]>0) {int second=(point[2]*3600/point[1]);
	        	System.out.println("目標通過時間は"+second/60+"分"+second%60+"秒(nm/kt)");

	        	System.out.println("飛行距離は"+(this.distance+=point[2])+"nm");
	        	
	        	}else { System.err.println("無効");}
	    	}
		
	    }
	 
	public static List<int[]> getFpList() {
		return fpList;
	}
}	
	
//	public void displayFuelInfo() {
//	    for (int[] point : fpList) {
//	        double distanceNM = point[2];
//	        double timeHours = distanceNM / point[1]; // 時間 (時単位)
//	        double fuelUsed = (super.fuelFlow * timeHours * 3600 )*2.205;// 燃料使用量 (kg)
//	        System.out.println("WPでの燃料消費量: " + fuelUsed + " lb");
//	        System.out.println("TSFC: " +/* calculateTSFC()*/ getSfc()+ " (lb/s/N)");
//	    }
	    
	    

//	}
//}

	
