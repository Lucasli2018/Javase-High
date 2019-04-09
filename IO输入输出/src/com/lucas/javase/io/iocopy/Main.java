package com.lucas.javase.io.iocopy;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class Main {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: java com.lucas.javase.io.iocopy.Main <gbk-file.txt> <utf8-file.txt>");
			return;
		}
		String src = args[0];
		String dest = args[1];
		copyGbkToUtf8(src, dest);
	}

	static void copyGbkToUtf8(String src, String dest) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		// TODO: 将GBK编码的src复制为UTF-8编码的dest
		System.out.println("将GBK编码的"+src+"复制为UTF-8编码的"+dest+"");
		try (Reader reader =new InputStreamReader( new FileInputStream(src),"GBK")) {
			try (Writer writer =new OutputStreamWriter(new FileOutputStream(dest),"UTF-8")) {
				int n;
				char[] buffer=new char[1024];
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			}
			
		}
		System.out.println("ok");
	}

}
