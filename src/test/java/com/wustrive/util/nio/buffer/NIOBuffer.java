package com.wustrive.util.nio.buffer;

import java.nio.CharBuffer;

public class NIOBuffer {
	
	private static String[] strings = {
		"a random string value",
		"the product of an infinite number of monkeys"
	}; 
	private static int index = 0;
	public static void main(String[] args) throws Exception{
		//申请CharBuffer空间
		CharBuffer charBuffer = CharBuffer.allocate(100);
		while(fillBuffer(charBuffer)){
			//倒转CharBuffer，由写变为读
			charBuffer.flip();
			drainBuffer(charBuffer);
			//清除CharBuffer，位置重置
			charBuffer.clear();
		}
		
	}
	
	private static void drainBuffer (CharBuffer buffer) {
		while (buffer.hasRemaining( )) { 
			System.out.print (buffer.get( )); 
		} 
		System.out.println (""); 
	}
	
	private static boolean fillBuffer(CharBuffer buffer){
		if(index>=strings.length){
			return false;
		}
		String string  = strings[index++];
		for(int i=0;i<string.length();i++){
			buffer.put(string.charAt(i));
		}
		return true;
	}
}
