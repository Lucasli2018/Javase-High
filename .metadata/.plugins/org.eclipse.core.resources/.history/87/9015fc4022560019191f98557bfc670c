package com.feiyangedu.sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Person {

	final Log log = LogFactory.getLog(getClass());

	private final String name;

	public Person(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name is null.");
		}
		this.name = name;
	}

	public String hello() {
		log.debug("Say hello to: " + name);
		return "Hello, " + name + "!";
	}
}
