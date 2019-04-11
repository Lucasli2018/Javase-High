package com.feiyangedu.sample;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SecRSASignature {

	PrivateKey sk;
	PublicKey pk;

	public SecRSASignature() throws GeneralSecurityException {
		// generate key pair:
		KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
		kpGen.initialize(1024);
		KeyPair kp = kpGen.generateKeyPair();
		this.sk = kp.getPrivate();
		this.pk = kp.getPublic();
	}

	public SecRSASignature(byte[] pk, byte[] sk) throws GeneralSecurityException {
		// create from bytes:
		KeyFactory kf = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec pkSpec = new X509EncodedKeySpec(pk);
		this.pk = kf.generatePublic(pkSpec);
		PKCS8EncodedKeySpec skSpec = new PKCS8EncodedKeySpec(sk);
		this.sk = kf.generatePrivate(skSpec);
	}

	public byte[] getPrivateKey() {
		return this.sk.getEncoded();
	}

	public byte[] getPublicKey() {
		return this.pk.getEncoded();
	}

	public byte[] sign(byte[] message) throws GeneralSecurityException {
		// sign by sk:
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initSign(this.sk);
		signature.update(message);
		return signature.sign();
	}

	public boolean verify(byte[] message, byte[] sign) throws GeneralSecurityException {
		// verify by pk:
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initVerify(this.pk);
		signature.update(message);
		return signature.verify(sign);
	}

	public static void main(String[] args) throws Exception {
		byte[] message = "Hello，使用SHA1withRSA算法进行数字签名！".getBytes("UTF-8");
		SecRSASignature rsas = new SecRSASignature();
		byte[] sign = rsas.sign(message);
		System.out.println("sign: " + Base64.getEncoder().encodeToString(sign));
		boolean verified = rsas.verify(message, sign);
		System.out.println("verify: " + verified);
		// 用另一个公钥验证:
		boolean verified2 = new SecRSASignature().verify(message, sign);
		System.out.println("verify with another public key: " + verified2);
		// 修改原始信息:
		message[0] = 100;
		boolean verified3 = rsas.verify(message, sign);
		System.out.println("verify changed message: " + verified3);
	}
}
