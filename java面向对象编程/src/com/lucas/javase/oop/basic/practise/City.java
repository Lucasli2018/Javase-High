package com.lucas.javase.oop.basic.practise;

public class City {
	// TODO:
	// 请定义class City，该class具有如下字段:
	// name: 名称，String类型
	// latitude: 纬度，double类型
	// longitude: 经度，double类型
	private String name;
	private double latitude;
	private double longitude;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "City [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}
