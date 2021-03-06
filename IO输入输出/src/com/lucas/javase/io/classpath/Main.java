package com.lucas.javase.io.classpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws IOException {
		//从classpath读取配置文件.properties
		String pros="/conf.properties";
		try(InputStream input=Main.class.getResourceAsStream(pros)){
			if(input!=null){
				System.out.println("Read /conf.properties...");
				Properties props=new Properties();
				props.load(input);
				System.out.println("name="+props.getProperty("name"));
			}else{
				System.out.println("Resource not found: "+pros);
			}
		}
		
		//从classpath读取txt文件
		String data="/com/lucas/javase/io/classpath/data.txt";
		try (InputStream input = Main.class.getResourceAsStream(data)) {
			if(input!=null){
				System.out.println("Read "+data+"...");
				BufferedReader reader=new BufferedReader(new InputStreamReader(input));
				System.out.println(reader.readLine());
			}else{
				System.out.println("Resource not found: "+pros);
			}
		}
	}

}
