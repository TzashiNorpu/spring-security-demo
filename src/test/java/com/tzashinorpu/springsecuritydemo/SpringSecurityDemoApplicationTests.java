package com.tzashinorpu.springsecuritydemo;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

//@SpringBootTest
class SpringSecurityDemoApplicationTests {


	@Test
	void testSelect() {
		KeyPair pair = SecureUtil.generateKeyPair("SM2");
		byte[] privateKey = pair.getPrivate().getEncoded();
		System.out.println("私钥为：" + HexUtil.encodeHexStr(privateKey));

		byte[] publicKey = pair.getPublic().getEncoded();
		System.out.println("公钥为：" + HexUtil.encodeHexStr(publicKey));

		// 公钥加密，私钥解密
		SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
		String text = "美丽新世纪";
//		String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
//		String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
//		String encryptStr = HexUtil.encodeHexStr(sm2.encrypt(text.getBytes(), KeyType.PublicKey));
		String encryptStr = sm2.encryptHex(text, StandardCharsets.UTF_8, KeyType.PublicKey);
		String decryptStr = sm2.decryptStr(encryptStr, KeyType.PrivateKey, StandardCharsets.UTF_8);
		System.out.println(encryptStr);
		System.out.println(decryptStr);
	}
}
