package com.coqair.serframe.encrypt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
//import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

public class EncrypAES {

	
	/**
	 *  KeyGenerator 提供对称密钥生成器的功能，支持各种算法 目前没用
	    private KeyGenerator keygen;
	    //SecretKey 负责保存对称密钥
	    private SecretKey deskey;
	    Cipher负责完成加密或解密工作
	 */
	private Cipher c;
	// 该字节数组负责保存加密的结果
	private byte[] cipherByte;
	// api 中SecretKeySpec 新增
	private SecretKeySpec secretkeyspec;

	@SuppressWarnings("restriction")
	public EncrypAES() throws NoSuchAlgorithmException, NoSuchPaddingException {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		// 实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
		// keygen = KeyGenerator.getInstance("AES");
		// //生成密钥
		// deskey = keygen.generateKey();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -cal.getFirstDayOfWeek() * 2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datekey = sdf.format(cal.getTime()) + "_Core+";//"_hr_co"
		byte[] keybs = (datekey).getBytes();// 密钥要保证16位，32不行，64位不行
		secretkeyspec = new SecretKeySpec(keybs, "AES");

		// 生成Cipher对象,指定其支持的DES,AES算法
		c = Cipher.getInstance("AES");
	}


	/**
	 * // 生成Cipher对象,指定其支持的DES,AES算法
	 * @param key 自定义KEY值
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	@SuppressWarnings("restriction")
	public EncrypAES(String key) throws NoSuchAlgorithmException, NoSuchPaddingException {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		secretkeyspec = new SecretKeySpec((key).getBytes(), "AES");		
		c = Cipher.getInstance("AES");
	}

	
	/**
	 * 对字符串加密
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] Encrytor(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
		// c.init(Cipher.ENCRYPT_MODE, deskey);
		c.init(Cipher.ENCRYPT_MODE, secretkeyspec);
		byte[] src = str.getBytes();
		// 加密，结果保存进cipherByte
		cipherByte = c.doFinal(src);
		return cipherByte;
	}
	

	/**
	 * 对字符串解密
	 * 
	 * @param buff
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] Decryptor(byte[] buff) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
		// c.init(Cipher.DECRYPT_MODE, deskey);
		c.init(Cipher.DECRYPT_MODE, secretkeyspec);
		cipherByte = c.doFinal(buff);
		return cipherByte;
	}

	public static void main(String[] args) {
		try {
			EncrypAES de1 = new EncrypAES("18502075074czxue");
			
			String msg = "java:comp/env/ecs";
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("type", "hr");
//			map.put("logdate", "");
//			String msg = JsonTool.toStr(map);

			byte[] encontent = de1.Encrytor(msg);
			byte[] decontent = de1.Decryptor(encontent);
			System.out.println("明文是:" + msg);
			System.out.println("加密后:" + new String(encontent, "utf-8") + " 长度:" + encontent.length);
			System.out.println("解密后:" + new String(decontent, "utf-8") + " 长度:" + decontent.length);
			// get请求里面 特殊字符会进过特殊编码，用 下面编码 先转
			Hex hex = new Hex();
			byte[] encontentb = hex.encode(de1.Encrytor(msg));
			byte[] decontentb = de1.Decryptor(hex.decode(encontentb));
			System.out.println("加密后hex:" + new String(encontentb, "utf-8"));
			System.out.println("解密后hex:" + new String(decontentb, "utf-8"));

		} catch (Exception e) {
			System.out.println("加密异常：" + e.toString());
			e.printStackTrace();
		}

	}

}
