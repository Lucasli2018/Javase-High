package com.lucas.javase.io.zip;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

	public static void main(String[] args) throws Exception {
		try(ZipInputStream zip=new ZipInputStream(new BufferedInputStream(new FileInputStream("test.jar")))){
			ZipEntry entry=null;
			while((entry=zip.getNextEntry())!=null){
				if(entry.isDirectory()){
					System.out.println("D "+entry.getName());
				}else{
					System.out.println("F "+entry.getName()+" "+entry.getSize());
					printFileContent(zip);
				}
			}
		}
	}

	private static void printFileContent(ZipInputStream zip) throws IOException {
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		byte[] buffer=new byte[1024];
		int n;
		while((n=zip.read(buffer))!=-1){
			output.write(buffer,0,n);
		}
		byte[] data=output.toByteArray();
		System.out.println(" size: "+data.length);
	}

}
