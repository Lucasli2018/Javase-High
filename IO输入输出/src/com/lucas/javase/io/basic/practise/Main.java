package com.lucas.javase.io.basic.practise;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String path = ".";
		if (args.length >= 1) {
			path = args[0];
		}
		File dir = new File(path).getCanonicalFile();
		// TODO: 按层级打印dir目录下所有子目录和文件
		System.out.println(dir.getCanonicalPath());
		getFileName(dir,0);
	}

	public static void getFileName(File file, int count) {
		for (File f : file.listFiles()) { // 遍历目录
			String preStr = "";
			for (int i = 0; i <= count; i++) { // 所在层级的输出格式
				preStr += " ";
			}
			
			if (f.isDirectory()) { // 是否为目录
				System.out.println(preStr + f.getName());
				count++;
				getFileName(f, count); // 递归调用
			}else{
				System.out.println(preStr + f.getName());
			}

		}
	}
}
