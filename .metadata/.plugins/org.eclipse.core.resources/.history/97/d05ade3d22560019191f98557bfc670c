package com.feiyangedu.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.feiyangedu.sample.Person;

public class Main {

	static final Log log = LogFactory.getLog(Main.class);

	public static void main(String[] args) {
		log.debug("Program start...");
		String name = "Xiao Ming";
		log.info("Create person " + name);
		Person p = new Person(name);
		log.info("call hello(): " + p.hello());
		try {
			new Person(null);
		} catch (Exception e) {
			log.error("Error when create person.", e);
		}
		log.info("Program end.");
	}

}
