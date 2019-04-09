package com.feiyangedu.sample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {

	public static void main(String[] args) throws IOException {
		try (OutputStream output = new FileOutputStream("output.txt")) {
			byte[] b1 = "Hello".getBytes("UTF-8");
			output.write(b1);
			byte[] b2 = "你好".getBytes("UTF-8");
			output.write(b2);
		}
	}

}
