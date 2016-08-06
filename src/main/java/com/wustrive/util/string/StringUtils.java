package com.wustrive.util.string;

import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 去除所有空格
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		String after = m.replaceAll("");

		return after;
	}

	/**
	 * 判断字符串格式（共8位，前两位为字母，后6位为数字，如:AA100001）
	 * 
	 * @param s
	 * @return
	 */
	public static boolean checkLetterAndNumeral(String s) {
		boolean a = false;
		/*
		 * Regexp匹配模式
		 */
		String userName = "^[a-zA-Z][a-zA-Z][0-9][0-9][0-9][0-9][0-9][0-9]$";
		Pattern pattern = Pattern.compile(userName);

		Matcher matcher = pattern.matcher(s);
		a = matcher.matches();
		return a;
	}
	
	/**
	 * 判断字符串是否为整数（包括正数与负数）
	 * @param str
	 * @return
	 */
	public static boolean isInt(String str){
		String intRegex = "^-?\\d+$";
		if (null != str) {
			return str.matches(intRegex);
		} else {
			return false;
		}
	}
	
	/**
	 *  null 转换为 空
	 *  使用范围单一值{比如Interger Long类型的对象}
	 * @param {String} str
	 * @return
	 */
	public static String nullToString(Object obj){
		if(null == obj){
			return "";
		}else{
			return obj.toString();
		}
	}
	
	/**
	 * 把字符串数组转换成sql in里面的内容（类型是字符）
	 * @param strArray [a,b]
	 * @example sql in('a','b')
	 * @return  'a','b'
	 */
	public static String sqlInString(List<String> strArray){
		StringBuffer sf = new StringBuffer("");
		if(null!=strArray && strArray.size()>0){
			for (String code : strArray) {
				if ("".equals(sf.toString())) {
					sf.append("'" + code + "'");
				} else {
					sf.append("," + "'" + code + "'");
				}
			}
		}
		return sf.toString();
	}
	/**
	 * 把字符串数组转换成sql in里面的内容（类型是int）
	 * @param strArray [1,2]
	 * @example sql in(1,2)
	 * @return  1,2
	 */
	public static String sqlInInt(List<String> strArray){
		StringBuffer sf = new StringBuffer("");
		if(null!=strArray && strArray.size()>0){
			for (String code : strArray) {
				if ("".equals(sf.toString())) {
					sf.append("" + code + "");
				} else {
					sf.append("," + "" + code + "");
				}
			}
		}
		return sf.toString();
	}
	/**
	 * 把字符串数组转换成sql in里面的内容（类型是int）
	 * @param IntArray [1,2]
	 * @example sql in(1,2)
	 * @return  1,2
	 */
	public static String sqlInIntByList(List<Integer> IntArray){
		StringBuffer sf = new StringBuffer("");
		if(null!=IntArray && IntArray.size()>0){
			for (Integer code : IntArray) {
				if ("".equals(sf.toString())) {
					sf.append("" + code + "");
				} else {
					sf.append("," + "" + code + "");
				}
			}
		}
		return sf.toString();
	}
	/**
	 *  list 去重复
	 * @param list
	 * @return
	 */
	public static List distinctList(List list) { 
		List newList = new ArrayList(); 
        for (Object o : list) { 
            if (!newList .contains(o)) newList.add( o); 
        } 
        return newList; 
    } 
	/**
	 * 获取手机好的后六位号码
	 * @param phone
	 * @return String
	 */
	public static String getNumByPhone(String phone){
		if(StringUtils.isNotBlank(phone)){
			return phone.substring(5);
		}else{
			return "";
		}
		
	}
	public static void main(String[] args) throws CharacterCodingException {
		  byte[] buff = "ADC03525ADC0".getBytes();
		  byte[] newByte = new byte[buff.length+3];
		  newByte[0] = (byte)0x08;
		  newByte[1] = (byte)0xAC;
		  System.arraycopy(buff, 0, newByte, 2, 12);
		  newByte[14] = (byte)0x03;
		  
		  System.out.println(new String(newByte));
	}

}
