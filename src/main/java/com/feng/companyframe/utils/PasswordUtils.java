package com.feng.companyframe.utils;

import java.util.UUID;

/**
 * @ClassName: PasswordUtils
 * @Description： 密码工具类
 * @createTime: 2020/2/4 14:44
 * @Author: 冯凡利
 * @UpdateUser: 冯凡利
 * @Version: 0.0.1
 */
public class PasswordUtils {

	/**
	 * 匹配密码
	 * @param salt 盐
	 * @param rawPass 明文 
	 * @param encPass 密文
	 * @return
	 */
	public static boolean matches(String salt, String rawPass, String encPass) {
		return new PasswordEncoder(salt).matches(encPass, rawPass);
	}
	
	/**
	 * 明文密码加密
	 * @param rawPass 明文
	 * @param salt
	 * @return
	 */
	public static String encode(String rawPass, String salt) {
		return new PasswordEncoder(salt).encode(rawPass);
	}

	/**
	 * 获取加密盐
	 * @return
	 */
	public static String getSalt() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
	}

	public static void main(String[] args) {
		// 给密码 和 salt，看密码密文
		String encode = encode("123456", "d3709b4efe9d47fcaba0");
		System.out.println(encode);
	}
}
