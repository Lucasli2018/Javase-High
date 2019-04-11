package com.feiyangedu.sample;

import java.math.BigInteger;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class Hmac {

	public static byte[] hmac(String hmacAlgorithm, SecretKey skey, byte[] input) throws Exception {
		Mac mac = Mac.getInstance(hmacAlgorithm);
		mac.init(skey);
		mac.update(input);
		return mac.doFinal();
	}

	public static void main(String[] args) throws Exception {
		// http://docs.oracle.com/javase/6/docs/technotes/guides/security/StandardNames.html#Mac
		String algorithm = "HmacSHA1";
		// 原始数据:
		String data = "helloworld";
		// 随机生成一个Key:
		KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
		SecretKey skey = keyGen.generateKey();
		// 打印Key:
		byte[] key = skey.getEncoded();
		System.out.println(String.format("Key: %0" + (key.length * 2) + "x", new BigInteger(1, key)));
		// 用这个Key计算HmacSHA1:
		byte[] result = hmac(algorithm, skey, data.getBytes("UTF-8"));
		System.out.println(String.format("Hash: %0" + (result.length * 2) + "x", new BigInteger(1, result)));
	}

}
