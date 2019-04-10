package com.feiyangedu.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

public class Calculate {

	public static void main(String[] args) throws Exception {
		// 获取当前日期和时间:
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		// + 5 days:
		LocalDateTime ldt2 = ldt.plusDays(5);
		System.out.println(ldt2);
		// - 2 hours:
		LocalDateTime ldt3 = ldt2.minusHours(2);
		System.out.println(ldt3);
		// 获得当月第一天:
		LocalDate firstDay = LocalDate.now().withDayOfMonth(1);
		LocalDate firstDay2 = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		System.out.println(firstDay.equals(firstDay2));
		System.out.println(firstDay);
		// 获得当月最后一天:
		LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(lastDay);
		// 获得当月第一个星期日：
		LocalDate firstSunday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
		System.out.println(firstSunday);
		// 判断两个日期哪个在前:
		System.out.println(firstSunday.isBefore(LocalDate.now()));
		// 两个日期相差？年？月？天:
		Period p = LocalDate.now().until(LocalDate.of(2050, 1, 1));
		System.out.println(p);
		// 两个日期一共相差多少天:
		System.out.println(LocalDate.of(2050, 1, 1).toEpochDay() - LocalDate.now().toEpochDay());
	}

}
