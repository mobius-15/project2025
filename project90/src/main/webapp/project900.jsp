<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="project90.Airbase,project90.Jet,project90.Weapons,java.util.*"%>
<%
Airbase base1 = new Airbase("DDH-184", 20, "035");
base1.baseType(2);
%>

<%
Jet jet1 = new Jet("F-35B", "Ground_Strike");
jet1.elements = 2;
%>

<%
jet1.setEWeight(29300);
jet1.base = base1;
base1.jet = jet1;
%>
<%
int count = 0;
int wd = 0;
%>

<%
base1.bInformation(jet1, 14);

Weapons weapon1 = new Weapons("A/A", "", 2);
Weapons weapon2 = new Weapons("A/G", "", 2);
%>

<%
jet1.information(base1);
%>

<%--//{5,15,85,85,85,85,35,10,100,85,85,85,20}; --%>
<%
int[] WP = jet1.fly(13);
for (int wp : WP) {
	count++;
	System.out.print("第" + count + "WP" + wp + "nm ");
	System.out.println("方位を入力");
	wd = new Scanner(System.in).nextInt();
	System.out.println(wp + "nm," + wd + ",");
}
%>
<%--	System.out.println("\n");
	System.out.println("燃料搭載量は"+jet1.fullFuel(0)+"lb");
	System.out.println("飛行距離は"+jet1.distance+"nm");
	System.out.println("巡航速度で飛行できると考えられるのはWP[2]から[6]および[8]から[11]とする");
	System.out.println(jet1.distance-(WP[0]+WP[1]+WP[7]+WP[12]+WP[13]));	--%>
<%
jet1.cruising(0.81);
base1.baseDistance(2.0);
jet1.land(base1);
%>
<%-- //	兵装重量:gbu31=2160; gbu12=610; aim120=350;

//	Jet[]wphading=new Jet[13];
	
	System.out.println("-----------------");--%>

<%
Airbase base2 = new Airbase("CVN-73", 30, "270");
base2.baseType(1);
%>

<%
Jet jet2 = new Jet("F/A-18E", "SEAD");
jet2.elements = 3;
%>

<%
jet2.setEWeight(32100);
jet2.base = base2;
base2.jet = jet2;
%>

<%
base2.bInformation(jet2, 40);
weapon1 = new Weapons("A/A", "", 1);
weapon2 = new Weapons("A/G", "", 3);
%>

<%
jet2.information(base2);
%>


<%
WP = jet2.fly(13);
count = 0;
for (int wp2 : WP) {
	count++;
	System.out.print("第" + count + "WP" + wp2 + "nm ");
	System.out.println("方位を入力");
	wd = new Scanner(System.in).nextInt();
	System.out.println(wp2 + "nm," + wd + ",");
}
%>

<%--	//{5,15,100,100,100,100,35,10,100,100,100,100,25};
	System.out.println("\n");
	System.out.println("飛行距離は"+jet2.distance+"nm");
	System.out.println("燃料搭載量は"+jet2.fullFuel(2)+" lb");
	base2.baseDistance(2.5);
	jet2.land(base2);
	base2.landing(jet2,3);
//	wphading=new Jet[13];
	System.out.println("-----------------"); 
 System.out.println(jet1.base1.name+"を発艦");  --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		機体と兵装の重量は<%=jet1.fullWeight(weapon1, weapon2)%>lb
	</p>
</body>
</html>