package com.lucas.javase.exception.CommonsLogging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {

	//实例化静态字段log
	static final Log log=LogFactory.getLog(Main.class);
	
	public static void main(String[] args) {
		Person p = new Person("Xiao Ming");
		log.info(p.hello());
		try {
			new Person(null);
		} catch (Exception e) {
			log.error("Exception",e);
		}
	}

}
