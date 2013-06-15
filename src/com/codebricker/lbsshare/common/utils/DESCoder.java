package com.codebricker.lbsshare.common.utils;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* 
 * @Author xingqisheng
 * 对字符串提供Des加密和解密
 * 对Xml文件内容进行加密和解密（对属性无效）
 */

public class DESCoder {
	private static final String ALGORITHM = "DES";
	private static final String ALGORITHM_TYPE = "DES/CBC/PKCS5Padding";
	private byte[] key = null;
	private byte[] paramvector = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public DESCoder(byte[] key) {
		this.key = key;
		paramvector = Arrays.copyOfRange(key, 0, 8);
	}

	public DESCoder() {
		this.key = this.getPrivateKey();
		paramvector = Arrays.copyOfRange(key, 0, 8);
	}


	public static void main(String[] args) {
		String src = "AQvuy8CIF-.Nq2GJ39NfgmJERPO3AyOkZVRET-hB5rYJIHVnCA7kAUAR";
		System.out.println("原文是:" + src);
		byte[] key ="12345678".getBytes();
		DESCoder coder = new DESCoder(key);
		String cypher = coder.encrypt(src.getBytes());
		System.out.println("加密后:" + new String(cypher));
		byte[] plain = coder.decrypt(cypher);
		System.out.println("解密后:" + new String(plain));

		// System.out.println("======测试xml文件加密=======");
		// String testElName="username";
		// coder.encryptXml("C:\\Documents and Settings\\Administrator\\v3\\conf\\v3-taobao-config.xml");
		// System.out.println("xml原文是:"+XmlUtil.getDocument("d:\\xx.xml").getRootElement().elementText(testElName));
		// Document doc = coder.decryptXml("d:\\xx_new.xml");
		// System.out.println("xml解密后:"+doc.getRootElement().elementText(testElName));
	}

	public String encrypt(byte[] src) {
		return Base64Coder.encode(this.desEncrypt(src)).replace("\n", "");
	}

	public byte[] decrypt(String src) {
		return this.desDecrypt(Base64Coder.decode(src));
	}

	/********** public functions ends ******************/
	private byte[] getPrivateKey() {
		byte[] result = null;
		try {
			byte[] bArr = { 100, 101, 118, 105, 108, 105, 118, 101, 100 };
			SecureRandom secureRandom = new SecureRandom(bArr);
			KeyGenerator kg = null;
			kg = KeyGenerator.getInstance(ALGORITHM);
			kg.init(secureRandom);

			SecretKey secretKey = kg.generateKey();
			result = secretKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 因为不同的加密算法密钥的位数是不同的，因此通过spec进行初步整理，进行取舍，得到符合规范的key。
	 */
	private Key toKey(byte[] key) throws Exception {
		// DESKeySpec spec = new DESKeySpec(key);
		// SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
		// SecretKeyFactory keyFactory =
		// SecretKeyFactory.getInstance(ALGORITHM);
		// SecretKey secretKey = keyFactory.generateSecret(spec);
		// return secretKey;

		SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
		return keySpec;
	}

	private byte[] desDecrypt(byte[] cipherText) {
		byte[] key = this.key;
		byte[] plainText = null;
		try {
			Key k = this.toKey(key);
			IvParameterSpec zeroIv = new IvParameterSpec(paramvector);
			Cipher cipher = Cipher.getInstance(ALGORITHM_TYPE);
			cipher.init(Cipher.DECRYPT_MODE, k, zeroIv);
			plainText = cipher.doFinal(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return plainText;
	}

	private byte[] desEncrypt(byte[] plainText) {
		byte[] key = this.key;
		byte[] cipherText = null;
		try {
			Key k = this.toKey(key);
			IvParameterSpec zeroIv = new IvParameterSpec(paramvector);
			Cipher cipher = Cipher.getInstance(ALGORITHM_TYPE);
			cipher.init(Cipher.ENCRYPT_MODE, k, zeroIv);
			cipherText = cipher.doFinal(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cipherText;
	}
}
