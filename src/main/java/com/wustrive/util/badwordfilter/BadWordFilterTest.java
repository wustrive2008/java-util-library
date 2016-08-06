package com.wustrive.util.badwordfilter;

public class BadWordFilterTest {
	public static void main(String[] args) {
		BadWordFilterService service = new BadWordFilterService();
		String str = service.replaceSensitiveWord("fdfd麻醉迷幻极品efdsafdsfdsfdsfdsfdsfsdfdsfdsfdsfdsfds哈沙河", 1, "*");
		System.out.println(str);
	}
}
