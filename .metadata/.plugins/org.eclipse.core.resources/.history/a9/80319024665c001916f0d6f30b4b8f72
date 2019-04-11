package com.feiyangedu.sample;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class SecURL {

	public static void main(String[] args) throws Exception {
		String original = "URL 参数";
		String encoded = URLEncoder.encode(original, "UTF-8");
		System.out.println(encoded);
		String ori = new String(URLDecoder.decode(encoded, "UTF-8"));
		System.out.println(ori);
	}
}
