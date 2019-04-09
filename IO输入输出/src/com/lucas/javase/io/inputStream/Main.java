package com.lucas.javase.io.inputStream;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		try (InputStream input = new FileInputStream("readme.txt")) {
			int n;
			byte[] buffer=new byte[1024];
			//List<Integer> list=new ArrayList<>();
			while ((n = input.read(buffer)) != -1) {//[72, 101, 108, 108, 111, 228, 189, 160, 229, 165, 189]
				//list.add(n);
				System.out.println(n+"byte read.");
			}
			//System.out.println(list);
			System.out.println(buffer[0]);
		}
	}

}
