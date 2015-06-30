package com.wustrive.util.nio.buffer;

import java.nio.CharBuffer;

public class CopyBuffer {
	public static void main(String[] args) {
		
		CharBuffer charBuffer = CharBuffer.allocate(100);
		charBuffer.position(3).limit(6).mark().position(5);
		charBuffer.put("c");
		System.out.println(charBuffer.get(5));
		//复制缓冲区
		CharBuffer buffer = charBuffer.duplicate();
		//生成一个只读的缓冲区视图
		CharBuffer buffer2 = charBuffer.asReadOnlyBuffer();
		buffer.clear();
		buffer2.clear();
		//分割缓冲区
		char [] myBuffer = new char[100];
		CharBuffer buffer3 = CharBuffer.wrap(myBuffer);
		buffer3.position(12).limit(21);
		//产生一个从原缓冲区12到20的子缓冲区
		/**
		 * 分割缓冲区与复制相似，但slice()创建一个从原始缓冲区的当前位置开始的新缓冲区，
		 * 并且其容量是原始缓冲区的剩余元素数量（limit-position）。
		 * 这个新缓冲区与原始缓冲区共享一段数据元素子序列。
		 * 分割出来的缓冲区也会继承只读和直接属性.
		 */
		CharBuffer buffer4 = buffer3.slice();
		
	}

}
