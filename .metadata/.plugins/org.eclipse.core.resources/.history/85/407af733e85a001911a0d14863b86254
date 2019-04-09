package com.feiyangedu.sample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {

	public static void main(String[] args) throws IOException {
		try (Writer writer = new FileWriter("output.txt")) {
			writer.write("Hello");
			writer.write("你好");
		}
	}

}
