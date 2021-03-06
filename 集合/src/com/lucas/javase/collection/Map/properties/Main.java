package com.lucas.javase.collection.Map.properties;


import java.util.Properties;

public class Main {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.load(Main.class.getResourceAsStream("/com/lucas/javase/collection/Map/properties/setting.properties"));
		String url = props.getProperty("url");
		String lang = props.getProperty("language");
		String title = props.getProperty("course.title");
		String description = props.getProperty("course.description","default value");
		System.out.println(url);
		System.out.println(lang);
		System.out.println(title);
		System.out.println(description);
	}

}
