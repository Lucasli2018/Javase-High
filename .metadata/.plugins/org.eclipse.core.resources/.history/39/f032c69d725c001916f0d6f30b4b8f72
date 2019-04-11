package com.feiyangedu.sample;

import java.security.MessageDigest;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

public class Digest {

	public static byte[] digest(String hashAlgorithm, byte[] input) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(hashAlgorithm);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		md.update(input);
		return md.digest();
	}

	public static void main(String[] args) throws Exception {
		// 把BouncyCastle作为Provider添加到java.security:
		// Security.addProvider(new BouncyCastleProvider());
		String s = "Java摘要算法测试";
		byte[] input = s.getBytes("UTF-8");
		byte[] r1 = digest("MD5", input);
		System.out.println(r1.length + ": " + ByteUtils.toHexString(r1));
		byte[] r2 = digest("SHA-1", input);
		System.out.println(r2.length + ": " + ByteUtils.toHexString(r2));
		byte[] r3 = digest("SHA-256", input);
		System.out.println(r3.length + ": " + ByteUtils.toHexString(r3));
		byte[] r4 = digest("RipeMD160", input);
		System.out.println(r4.length + ": " + ByteUtils.toHexString(r4));
	}

}
