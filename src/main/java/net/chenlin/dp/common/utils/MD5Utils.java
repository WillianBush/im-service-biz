package net.chenlin.dp.common.utils;


import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * MD5加密工具
 * @author wang<fangyuan.co@outlook.com>
 */
@Slf4j
public class MD5Utils {

	private static final String SALT = "1qazxsw2";


	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String byteArrayToHexString(byte[] b)
	{
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin)
	{
		String resultString = null;
		try
		{
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		}
		catch (Exception localException) {}
		return resultString;
	}

	public static String MD5EncodeUTF8(String origin)
	{
		String resultString = null;
		try
		{
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes(StandardCharsets.UTF_8)));
		}
		catch (Exception localException) {}
		return resultString;
	}
	/**
	 * 使用md5生成加密后的密码
	 * @param password
	 * @return
	 */
	public static String encrypt(String password) {
		return MD5EncodeUTF8(password + SALT);
	}
	
	/**
	 * 使用md5生成加密后的密码
	 * @param username
	 * @param password
	 * @return
	 */
	public static String encrypt(String username, String password) {
		log.info("pwd:{}",username+password + SALT);
		return MD5EncodeUTF8(username+password + SALT);
	}

	public static void main(String[] args) {
		String secret = MD5Utils.encrypt("admin","qwer1234");
		log.info(secret);
	}
	
}
