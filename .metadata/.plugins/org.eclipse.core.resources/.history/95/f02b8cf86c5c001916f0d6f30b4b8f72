package com.feiyangedu.sample;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.math.BigInteger;

import org.junit.Test;

public class MD5Test {

	@Test
	public void testToMD5WithFile1() throws Exception {
		InputStream input1 = MD5.class.getResourceAsStream("/LICENSE.txt");
		byte[] r1 = MD5.toMD5(input1);
		assertEquals("3b83ef96387f14655fc854ddc3c6bd57", String.format("%032x", new BigInteger(1, r1)));
	}

	@Test
	public void testToMD5WithFile2() throws Exception {
		InputStream input2 = MD5.class.getResourceAsStream("/commons-logging-1.2-bin.zip");
		byte[] r2 = MD5.toMD5(input2);
		assertEquals("4ae2b295bc11f00716fe4143654a3b92", String.format("%032x", new BigInteger(1, r2)));
	}

}
