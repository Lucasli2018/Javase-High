package com.feiyangedu.sample;

import java.util.Properties;

public class Main {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.load(Main.class.getResourceAsStream("/com/feiyangedu/sample/setting.properties"));
		String url = props.getProperty("url");
		String lang = props.getProperty("language");
		String title = props.getProperty("course.title");
		String description = props.getProperty("course.description");
		System.out.println(url);
		System.out.println(lang);
		System.out.println(title);
		System.out.println(description);
	}

}
