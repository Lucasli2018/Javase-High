package com.lucas.javase.io.reader;


import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {

	public static void main(String[] args) throws IOException {
		try (Reader reader =new InputStreamReader( new FileInputStream("readme.txt"),"UTF-8")) {
			int n;
			while ((n = reader.read()) != -1) {
				System.out.println((char) n);
			}
		}
	}

}
