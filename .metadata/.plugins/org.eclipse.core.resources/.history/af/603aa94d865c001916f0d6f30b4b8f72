package com.feiyangedu.sample;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.crypto.Cipher;

public class X509 {

	private final PrivateKey privateKey;
	public final X509Certificate certificate;

	public X509(KeyStore ks, String certName, String password) {
		try {
			this.privateKey = (PrivateKey) ks.getKey(certName, password.toCharArray());
			this.certificate = (X509Certificate) ks.getCertificate(certName);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] encrypt(byte[] message) {
		try {
			Cipher cipher = Cipher.getInstance(this.privateKey.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, this.privateKey);
			return cipher.doFinal(message);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] decrypt(byte[] data) {
		try {
			PublicKey publicKey = this.certificate.getPublicKey();
			Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			return cipher.doFinal(data);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	public byte[] sign(byte[] message) {
		try {
			Signature signature = Signature.getInstance(this.certificate.getSigAlgName());
			signature.initSign(this.privateKey);
			signature.update(message);
			return signature.sign();
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean verify(byte[] message, byte[] sig) {
		try {
			Signature signature = Signature.getInstance(this.certificate.getSigAlgName());
			signature.initVerify(this.certificate);
			signature.update(message);
			return signature.verify(sig);
		} catch (GeneralSecurityException e) {
			throw new RuntimeException(e);
		}
	}

	static KeyStore loadKeyStore(String keyStoreFile, String password) {
		try (InputStream input = new BufferedInputStream(new FileInputStream(keyStoreFile))) {
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(input, password.toCharArray());
			return ks;
		} catch (GeneralSecurityException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws Exception {
		byte[] message = "Hello，使用X.509证书进行加密和签名！".getBytes("UTF-8");
		// 读取KeyStore:
		KeyStore ks = loadKeyStore("my.keystore", "456789");
		// 读取证书:
		X509 x509 = new X509(ks, "mycert", "123456");
		// 加密:
		byte[] encrypted = x509.encrypt(message);
		System.out.println("encrypted: " + Base64.getEncoder().encodeToString(encrypted));
		// 解密:
		byte[] decrypted = x509.decrypt(encrypted);
		System.out.println("decrypted: " + new String(decrypted, "UTF-8"));
		// 签名:
		byte[] sign = x509.sign(message);
		System.out.println("sign: " + Base64.getEncoder().encodeToString(sign));
		// 验证签名:
		boolean verified = x509.verify(message, sign);
		System.out.println("verify: " + verified);
	}
}
