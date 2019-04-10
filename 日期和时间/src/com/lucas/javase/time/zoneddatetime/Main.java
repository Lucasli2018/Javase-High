package com.lucas.javase.time.zoneddatetime;


import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {

	public static void main(String[] args) throws Exception {
		// 获取当前默认时区的日期和时间:
		ZonedDateTime now = ZonedDateTime.now();
		System.out.println(now);//2019-04-11T00:44:14.547+08:00[Asia/Shanghai]
		// 打印时区:
		System.out.println(now.getZone());//Asia/Shanghai
		// 获取Instant:
		Instant ins = now.toInstant();
		System.out.println(ins.getEpochSecond());//1554914654
		// 按指定时区获取当前日期和时间:
		ZonedDateTime london = ZonedDateTime.now(ZoneId.of("Europe/London")); // 伦敦时间
		System.out.println(london);//2019-04-10T17:45:14.464+01:00[Europe/London]
		// 把伦敦时间转换到纽约时间:
		ZonedDateTime newyork = london.withZoneSameInstant(ZoneId.of("America/New_York")); // 纽约时间
		System.out.println(newyork);//2019-04-10T12:45:14.464-04:00[America/New_York]
	}

}
