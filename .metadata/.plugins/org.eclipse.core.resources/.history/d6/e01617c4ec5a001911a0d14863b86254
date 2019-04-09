package com.feiyangedu.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Parse {

	public static void main(String[] args) throws Exception {
		// 按系统Locale解析日期时间：
		String s1 = "2016-11-20 12:15:59";
		Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s1);
		System.out.println(date1);
		// 解析MMM时默认按照系统Locale:
		String s2 = "Nov/20/2016 12:15:59";
		Date date2 = new SimpleDateFormat("MMM/dd/yyyy HH:mm:ss").parse(s2);
		System.out.println(date2);
		// 按ISO 8601标准格式解析：
		String iso = "2016-11-20T12:15:59";
		Date date3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(iso);
		System.out.println(date3);
	}

}
