package com.feiyangedu.sample;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		File win = new File("C:\\Windows");
		System.out.println(win.isDirectory()); // true
		File notepad = new File("C:\\Windows\\notepad.exe");
		System.out.println(notepad.isFile()); // true
		File dir = new File("C:\\abc\\xyz");
		System.out.println(dir.mkdir()); // -> mkdirs
		File readme = new File("./src/readme.txt");
		System.out.println(readme.isFile()); // false
		System.out.println(readme.getAbsolutePath());
		System.out.println(readme.getCanonicalPath());
	}

}
