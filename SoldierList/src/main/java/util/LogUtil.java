package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil{
private static final boolean isDebug =true;
private LogUtil(){}
	public static void println(String log) {
		if(isDebug) {LocalDateTime now=LocalDateTime.now();
					DateTimeFormatter dt=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");	
					System.out.println(dt.format(now)+" "+log);}
	}
}
