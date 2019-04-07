package com.distribution.wamoli.common.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.log4j.Logger;

public class CiperTools {
	private static final Logger log = Logger.getLogger(CiperTools.class);

	public static final String KEY_ALGORITHM = "DES";
	public static final String DES_ECB_ALGORITHM = "DES/ECB/PKCS5Padding";
	public static final String DES_CBC_ALGORITHM = "DES/CBC/PKCS5Padding";
	public static final String DES_CBC_NOPADDING = "DES/CBC/NoPadding";
	public static String SECURITY_KEY = "92ba640738a413b0";

	public static final byte[] DES_CBC_IV = { 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00 };

	public static void setSECURITY_KEY(String sECURITY_KEY) {
		SECURITY_KEY = sECURITY_KEY;
	}

//
	private static Key toKey(byte[] key) {
		try {
			DESKeySpec des = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance(KEY_ALGORITHM);
			SecretKey secretKey = keyFactory.generateSecret(des);
			return secretKey;
		} catch (Exception e) {
			log.error("exception:", e);
		}
		return null;
	}
//
	public static byte[] encrypt(byte[] data, byte[] key, String algorithm) {
		try {
			Key k = toKey(key);
			Cipher cipher = Cipher.getInstance(algorithm);
			if (DES_CBC_ALGORITHM.equals(algorithm)
					|| DES_CBC_NOPADDING.equals(algorithm)) {
				IvParameterSpec spec = new IvParameterSpec(DES_CBC_IV);
				cipher.init(Cipher.ENCRYPT_MODE, k, spec);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, k);
			}
			return cipher.doFinal(data);
		} catch (Exception e) {
			log.error("exception:", e);
		}
		return null;
	}
//
	public static byte[] decrypt(byte[] data, byte[] key, String algorithm) {
		try {
			Key k = toKey(key);
			Cipher cipher = Cipher.getInstance(algorithm);
			if (DES_CBC_ALGORITHM.equals(algorithm)
					|| DES_CBC_NOPADDING.equals(algorithm)) {
				IvParameterSpec spec = new IvParameterSpec(DES_CBC_IV);
				cipher.init(Cipher.DECRYPT_MODE, k, spec);
			} else {
				cipher.init(Cipher.DECRYPT_MODE, k);
			}
			return cipher.doFinal(data);
		} catch (Exception e) {
			log.error("exception:", e);
		}
		return null;
	}
//
	public static String encrypt(String data) {
		byte[] aa = encrypt(data.getBytes(),
				InetTool.ascii2Hex(SECURITY_KEY.getBytes()), DES_ECB_ALGORITHM);
		return new String(InetTool.hex2Ascii(aa));
	}
//
	public static String decrypt(String data) {
		byte[] aa = InetTool.ascii2Hex(data.getBytes());
		return new String(decrypt(aa,
				InetTool.ascii2Hex(SECURITY_KEY.getBytes()), DES_ECB_ALGORITHM));
	}
//

	public static void main(String[] args) {
		String orign = "hello base version";
		System.out.println("初始:" + orign);
		System.out.println("加密后:" + CiperTools.encrypt(orign));
		System.out.println("解密后:"
				+ CiperTools.decrypt(CiperTools.encrypt(orign)));

	}
}
