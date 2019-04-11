package com.feiyangedu.sample;

import static org.junit.Assert.*;

import java.security.GeneralSecurityException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.BeforeClass;
import org.junit.Test;

public class USBKeyTest {

	@BeforeClass
	public static void beforeClass() {
		Security.addProvider(new BouncyCastleProvider());
	}

	@Test
	public void testEncryptWithUSBKeyAndDecryptWithUSBKeyOk() throws Exception {
		byte[] message = "Hello, using USBKey to encrypt!".getBytes();
		USBKey usbkey = USBKey.createUSBKey("test-usbkey.txt");
		byte[] encrypted = usbkey.encrypt("hello", message);
		// decrypt:
		byte[] decrypted = usbkey.decrypt("hello", encrypted);
		assertArrayEquals(message, decrypted);
	}

	@Test(expected = GeneralSecurityException.class)
	public void testEncryptWithUSBKeyOkAndDecryptFailedForBadPassword() throws Exception {
		byte[] message = "Hello, using USBKey to encrypt!".getBytes();
		USBKey usbkey = USBKey.createUSBKey("test-usbkey.txt");
		byte[] encrypted = usbkey.encrypt("hello", message);
		// decrypt:
		byte[] decrypted = usbkey.decrypt("badpassword", encrypted);
		assertArrayEquals(message, decrypted);
	}

	@Test(expected = GeneralSecurityException.class)
	public void testEncryptWithUSBKeyOkAndDecryptFailedForBadUSBKey() throws Exception {
		byte[] message = "Hello, using USBKey to encrypt!".getBytes();
		USBKey usbkey = USBKey.createUSBKey("test-usbkey.txt");
		USBKey usbkey2 = USBKey.createUSBKey("test-bad-usbkey.txt");
		byte[] encrypted = usbkey.encrypt("hello", message);
		// decrypt:
		byte[] decrypted = usbkey2.decrypt("hello", encrypted);
		assertArrayEquals(message, decrypted);
	}

}
