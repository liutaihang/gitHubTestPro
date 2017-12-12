package com.java1234.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 3DES数据处理
 * @version 1.0
 * @author
 */
public abstract class DesSensitiveData {
	private static final String DES_KEY = "OJsySbUQFop2/ccTyw3sqJ2e5W5kGsdw";
	//加密 
	public static String enSensitiveData(String data) throws Exception{
		BASE64Encoder encoder = new BASE64Encoder();
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b3DesKey=decoder.decodeBuffer(DES_KEY);
		String encryptSerial = encoder.encode(DesUtil.encrypt(data.toString().getBytes("utf-8"),b3DesKey));
		return encryptSerial;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(deSensitiveData("VnOc3bcbuGCcfQh0+b4Ba7ydK1gAgdDT"));
	}
	//解密
	public static String deSensitiveData(String data) throws Exception{
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b3DesKey=decoder.decodeBuffer(DES_KEY);
		byte[] b = DesUtil.decrypt(decoder.decodeBuffer(data),b3DesKey);
		String decryptSerial = new String(b, "utf-8");
		return decryptSerial;
	}	
}
