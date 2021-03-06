package com.lucas.javase.io.basic;


import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		File win = new File("C:\\Windows");//创建File对象本身不涉及IO操作
		System.out.println("win.isDirectory-->"+win.isDirectory()); // true
		File notepad = new File("C:\\Windows\\notepad.exe");
		System.out.println("notepad.isFile-->"+notepad.isFile()); // true
		System.out.println("notepad.canExecute-->"+notepad.canExecute());
		File dir = new File("C:\\abc\\xyz");
		System.out.println("mkdirs-->"+dir.mkdirs()); // -> mkdirs 如果路径不存在，自动创建一个
		File readme = new File("../src/readme.txt");
		System.out.println("readme.isFile-->"+readme.isFile()); // false
		System.out.println(readme.getAbsolutePath());//D:\github\Javase-High\IO输入输出\.\src\readme.txt
		System.out.println(readme.getCanonicalPath());//D:\github\Javase-High\IO输入输出\src\readme.txt
		
		File tempFile=File.createTempFile("temp-", ".txt");
		System.out.println("创建临时文件。。。");
		Thread.sleep(10000);
		tempFile.deleteOnExit();
		System.out.println("退出");
	}

}
