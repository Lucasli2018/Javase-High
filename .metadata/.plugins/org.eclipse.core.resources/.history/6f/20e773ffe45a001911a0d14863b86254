package com.feiyangedu.sample;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class Main {

	public static void main(String[] args) throws IOException {
		try (InputStream input = new GZIPInputStream(new BufferedInputStream(new FileInputStream("test.txt.gz")))) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int n;
			while ((n = input.read(buffer)) != -1) {
				output.write(buffer, 0, n);
			}
			byte[] data = output.toByteArray();
			String text = new String(data, "UTF-8");
			System.out.println(text);
		}
	}

}
