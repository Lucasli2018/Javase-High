package com.lucas.javase.calendar.basic;


import java.util.Calendar;
import java.util.TimeZone;

public class Zone {

	public static void main(String[] args) throws Exception {
		// 获取当前时间:
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTime());
		// 获取纽约时间:
		c.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) + 1;
		int d = c.get(Calendar.DAY_OF_MONTH);
		int hh = c.get(Calendar.HOUR_OF_DAY);
		int mm = c.get(Calendar.MINUTE);
		int ss = c.get(Calendar.SECOND);
		System.out.println(y + "-" + m + "-" + d + " " + hh + ":" + mm + ":" + ss);
		System.out.println("所有时区：");
		String[] s=TimeZone.getAvailableIDs();
		for (int i = 0; i < s.length; i++) {
			String string = s[i];
			System.out.println(string);
			if(i>5){
				System.out.println("...");
				break;
			}
		}
	}

}
