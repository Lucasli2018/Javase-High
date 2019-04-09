package com.feiyangedu.sample;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {

	public static void main(String[] args) throws IOException {
		try (Reader reader = new FileReader("readme.txt")) {
			int n;
			while ((n = reader.read()) != -1) {
				System.out.println((char) n);
			}
		}
	}

}
