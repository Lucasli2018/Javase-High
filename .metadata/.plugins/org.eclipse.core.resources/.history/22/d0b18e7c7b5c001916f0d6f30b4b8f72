package com.feiyangedu.sample;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.Base64;
import java.util.Scanner;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Main {

	public static void main(String[] args) throws Exception {
		// 把BouncyCastle作为Provider添加到java.security:
		Security.addProvider(new BouncyCastleProvider());
		// 原文:
		String message = "Hello, world! encrypted using password and USB key!";
		// 加密口令:
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.print("Input password to encrypt: ");
		String password = in.nextLine();
		// USBKey:
		USBKey ukey = USBKey.createUSBKey("usbkey.txt");
		// 加密:
		byte[] data = message.getBytes(StandardCharsets.UTF_8);
		byte[] encrypted = ukey.encrypt(password, data);
		System.out.println("encrypted: " + Base64.getEncoder().encodeToString(encrypted));
		// 解密:
		System.out.println("Input password to decrypt: ");
		String password2 = in.nextLine();
		try {
			byte[] decrypted = ukey.decrypt(password2, encrypted);
			System.out.print(new String(decrypted, "UTF-8"));
		} catch (GeneralSecurityException e) {
			System.out.println("Failed decrypt: bad password!");
		}
	}

}
