package com.synnex.utils.md5Util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Md5Encode {
//	static MessageDigest md;
	private static Log logger = LogFactory.getLog("Md5Encode");
	public final static String getStringMD5(String s) {
		try {
			// 生成一个MD5加密计算摘要
			//TODO MessageDigest单例？
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(s.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			String pwd = new BigInteger(1, md.digest()).toString(16);
			return pwd;
		} catch (Exception e) {
			logger.error(e);
//			e.printStackTrace();
			return s;
		}
	}
}
