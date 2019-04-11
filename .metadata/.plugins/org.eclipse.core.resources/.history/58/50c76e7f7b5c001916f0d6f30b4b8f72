package com.feiyangedu.sample;

import java.security.GeneralSecurityException;

public class USBKey {

	static final String CIPHER_NAME = "PBEwithSHA1and128bitAES-CBC-BC"; // "PBEwithSHA1andDESede";

	private byte[] salt;

	public USBKey(String filename) {
		// TODO: 从文件中读取salt:
		this.salt = null;
	}

	USBKey(byte[] salt) {
		this.salt = salt;
	}

	public static USBKey createUSBKey(String filename) {
		// TODO: 生成随机Salt并保存到文件:
		byte[] salt = null;
		return new USBKey(salt);
	}

	// 加密:
	public byte[] encrypt(String password, byte[] input) throws GeneralSecurityException {
		// TODO: 根据password和salt加密input:
		byte[] encrypted = null;
		return encrypted;
	}

	// 解密:
	public byte[] decrypt(String password, byte[] encrypted) throws GeneralSecurityException {
		// TODO: 根据password和salt解密encrypted:
		byte[] decrypted = null;
		return decrypted;
	}

}
