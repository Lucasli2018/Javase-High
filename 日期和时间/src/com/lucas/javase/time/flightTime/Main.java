package com.lucas.javase.time.flightTime;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		// 输入：
		//Scanner scanner = new Scanner(System.in);
		//System.out.print("Departure date (yyyy-MM-dd): ");
		String departureDate = "2019-08-01";//scanner.nextLine();
		//System.out.print("Departure time (HH:mm): ");
		String departureTime ="08:30"; //scanner.nextLine();
		
		// TODO: 飞行12小时15分钟，计算到达的当地时间：
		LocalDate d=LocalDate.now();//LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalTime t=LocalTime.now();//LocalTime.parse(departureTime, DateTimeFormatter.ofPattern("HH:mm"));
		System.out.println("departureDate: " +d);
		System.out.println("departureTime: " +t);
		
		LocalDateTime ldt=LocalDateTime.of(d, t);
		System.out.println("departureDateTime: " +ldt);
		System.out.println("flight...");
		LocalDateTime ldt2=ldt.plusHours(12).plusMinutes(15);
		System.out.println("ArrivalDateTime: " +ldt2);
		System.out.println("zoned to New_York:注意夏令时2019-08-01差12h，2019-01-01差13h");
		System.out.println("ArrivalDateTime: " +ldt2.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")));
		
		
		System.out.println();
		LocalDate arrivalDate = ldt2.toLocalDate();
		LocalTime arrivalTime = ldt2.toLocalTime();
		// 输出：
		System.out.println("Arrival date: " + arrivalDate);
		System.out.println("Arrival time: " + arrivalTime);
	}

}
