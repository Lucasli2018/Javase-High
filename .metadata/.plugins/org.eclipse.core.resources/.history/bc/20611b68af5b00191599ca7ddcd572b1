package com.feiyangedu.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Local2Zoned {

	public static void main(String[] args) throws Exception {
		// 把LocalDateTime转换为ZonedDateTime:
		LocalDateTime ldt = LocalDateTime.of(2016, 11, 30, 8, 15, 59);
		// 关联到当前默认时区：
		ZonedDateTime bj = ldt.atZone(ZoneId.systemDefault());
		System.out.println(bj);
		// 关联到纽约时区:
		ZonedDateTime ny = ldt.atZone(ZoneId.of("America/New_York"));
		System.out.println(ny);
	}

}
