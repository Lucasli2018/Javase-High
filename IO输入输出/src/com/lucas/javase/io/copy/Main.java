package com.lucas.javase.io.copy;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: java com.lucas.javase.io.copy.Main <src-file> <dest-file>");
			return;
		}
		String src = args[0];
		String dest = args[1];
		copy(src, dest);
	}

	static void copy(String src, String dest) throws FileNotFoundException, IOException {
		// TODO: 将src复制为dest
		System.out.println("将"+src+"复制为"+dest+"");
		try(InputStream inputStream=new FileInputStream(src)){
			try(OutputStream outputStream=new FileOutputStream(dest)){
				int n;
				byte[] buffer=new byte[1024];
				while((n = inputStream.read(buffer)) != -1){
					outputStream.write(buffer);
					outputStream.flush();
				}
			}
		}
		System.out.println("ok");
	}

}
