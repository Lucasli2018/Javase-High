package com.feiyangedu.sample;

import java.util.Base64;

public class SecBase64 {

	public static void main(String[] args) throws Exception {
		String original = "Hello\u00ff编码测试";
		String b64 = Base64.getEncoder().encodeToString(original.getBytes("UTF-8"));
		System.out.println(b64);
		String ori = new String(Base64.getDecoder().decode(b64), "UTF-8");
		System.out.println(ori);
	}
}
