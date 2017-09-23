package com.wustrive.util.digest;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.net.URLEncoder;

public class AESCoderTest {
	 /**
     * 测试
     *
     * @throws Exception
     */
    @Test
    public final void test() throws Exception {
         String inputStr = "18695850831";
          byte[] inputData = inputStr.getBytes();
         System. err.println("原文:\t" + inputStr);

          // 初始化密钥
          //byte[] key = AESCoderBounc.initKey(128);
         byte[] key = "1452895565632514".getBytes();
         System. err.println("密钥:\t" + Base64.encodeBase64String(key));

          // 加密
         inputData = AESCoderBounc. encrypt(inputData, key);
         System. err.println("加密后:\t" + Base64.encodeBase64String(inputData));

         // 解密
         byte[] outputData = AESCoderBounc.decrypt(inputData, key);

         String outputStr = new String(outputData);
         System. err.println("解密后:\t" + outputStr);

          // 校验
          assertEquals(inputStr, outputStr);
    }

    @Test
    public final void testCBC() throws Exception {
        String data = AESCoderBounc.encrypt_CBC("1525336533","67b0e70749124420","86a37a98753f3175");
        System.out.println("密文:"+ URLEncoder.encode(data,"utf-8"));
        System.out.println(AESCoderBounc.decrypt_CBC(data,"67b0e70749124420","86a37a98753f3175"));
    }
}
