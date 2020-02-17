package space.nyuki.qclient.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public static String md5crypt(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException("String to encript cannot be null or zero length");
		}
		StringBuilder hexString = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] hash = md.digest();
			for (byte b : hash) {
				if ((0xff & b) < 0x10) {
					hexString.append("0").append(Integer.toHexString((0xFF & b)));
				} else {
					hexString.append(Integer.toHexString(0xFF & b));
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

	public static String getHalfMD5(String s){
		return MD5Util.md5crypt(s).substring(0, 16);
	}
}