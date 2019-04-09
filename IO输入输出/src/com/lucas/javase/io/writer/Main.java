package com.lucas.javase.io.writer;


import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Main {

	public static void main(String[] args) throws IOException {
		try (Writer writer =new OutputStreamWriter(new FileOutputStream("output.txt"),"UTF-8")) {
			writer.write("Hello");
			writer.write("你好");
			writer.write("java");
		}
	}

}
