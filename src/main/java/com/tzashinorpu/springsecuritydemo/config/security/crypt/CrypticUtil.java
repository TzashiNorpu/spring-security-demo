package com.tzashinorpu.springsecuritydemo.config.security.crypt;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;

import java.nio.charset.StandardCharsets;

public class CrypticUtil {


	private static final String serverPrivateKey = "308193020100301306072a8648ce3d020106082a811ccf5501822d0479307702010104202e8853a19fad1764c7ff0c7f20b589a86e28655c3226c0278b8845503380c986a00a06082a811ccf5501822da14403420004237b2d345c5ca6df0db3651cd8b90e1063e367fd6b01b11de6611ccae6a8bd5bda482ef5acd7d3d1612b516584bb0ec7d195e15520c7164eb5efa17083fd5c02";
	private static final String serverPublicKey = "3059301306072a8648ce3d020106082a811ccf5501822d03420004237b2d345c5ca6df0db3651cd8b90e1063e367fd6b01b11de6611ccae6a8bd5bda482ef5acd7d3d1612b516584bb0ec7d195e15520c7164eb5efa17083fd5c02";

	private static final String clientPrivateKey = "308193020100301306072a8648ce3d020106082a811ccf5501822d047930770201010420ba8b2f2f21ef108e8660970ab8d89375258b582a470f8df297df3b6d94b5563fa00a06082a811ccf5501822da144034200042de95df1b596d0ea6468035af52a7106ff6ddef69d56fb05cfb4b7e6c90919cb0189075dfe059d7e9d36a57f1897fab1cf4d6a4d640abeeb8be22a2c854a8cd7";

	private static final String clientPublicKey = "3059301306072a8648ce3d020106082a811ccf5501822d034200042de95df1b596d0ea6468035af52a7106ff6ddef69d56fb05cfb4b7e6c90919cb0189075dfe059d7e9d36a57f1897fab1cf4d6a4d640abeeb8be22a2c854a8cd7";
	/*
	客户端发送的请求数据内容通过 clientPublicKey 加密
	服务端拿到客户端加密内容通过 clientPrivateKey 解密
	 */
	private static final SM2 clientSm2 = SmUtil.sm2(clientPrivateKey, clientPublicKey);
	/*
	服务端返回的响应数据内容通过 serverPublicKey 加密
	客户端拿到服务端加密内容通过 serverPrivateKey 解密
	 */
	private static final SM2 serverSm2 = SmUtil.sm2(serverPrivateKey, serverPublicKey);

	public static String requestDecrypt(String encryptText) {
		return clientSm2.decryptStr(encryptText, KeyType.PrivateKey, StandardCharsets.UTF_8);
	}

	public static String responseEncrypt(String plainText) {
		return serverSm2.encryptHex(plainText, StandardCharsets.UTF_8, KeyType.PublicKey);
	}
}
