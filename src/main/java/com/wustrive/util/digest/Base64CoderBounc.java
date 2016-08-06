package com.wustrive.util.digest;

import org.bouncycastle.util.encoders.Base64;

public class Base64CoderBounc {
	 /**
     * 字符编码
     */
    public final static String ENCODING = "UTF-8";

    /**
     * Base64编码
     *
     * @param data 待编码数据
     * @return String 编码数据
     * @throws Exception
     */
    public static String encode(String data) throws Exception {

          // 执行编码
          byte[] b = Base64.encode(data.getBytes(ENCODING));

          return new String(b, ENCODING);
    }

    /**
     * Base64解码
     *
     * @param data 待解码数据
     * @return String 解码数据
     * @throws Exception
     */
    public static String decode(String data) throws Exception {

          // 执行解码
          byte[] b = Base64.decode(data.getBytes(ENCODING));

          return new String(b, ENCODING);
    }

}
