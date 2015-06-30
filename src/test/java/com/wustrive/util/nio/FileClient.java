package com.wustrive.util.nio;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class FileClient {
	private int port = 8000;
	/* 发送数据缓冲区 */
	private static ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
	/* 接受数据缓冲区 */
	private static ByteBuffer revBuffer = ByteBuffer.allocate(1024);
	private InetSocketAddress SERVER;
	private static Selector selector;
	private static SocketChannel client;
	
	public FileClient(){
		try{
			SERVER = new InetSocketAddress("localhost", port);
			init();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	private void init(){
		try {
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(SERVER);
			while (true) {
				selector.select();
				Set<SelectionKey> keySet = selector.selectedKeys();
				for (final SelectionKey key : keySet) {
					if(key.isConnectable()){
						client = (SocketChannel)key.channel();
						client.finishConnect();
						client.register(selector, SelectionKey.OP_WRITE);

					}
					else if(key.isWritable()){
						sendFile(client);
					}
				}
				keySet.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendFile(SocketChannel client) {
		FileInputStream fis = null;
		FileChannel channel = null;
		try {
//			fis = new FileInputStream("E:\\1.txt");
//			fis = new FileInputStream("E:\\1.rar");
			fis = new FileInputStream("I:\\Koala.jpg");
			channel = fis.getChannel();
			int i = 1;
			int count = 0;
			while((count = channel.read(sendBuffer)) != -1) {
				sendBuffer.flip(); 
				int send = client.write(sendBuffer);
				System.out.println("i===========" + (i++) + "   count:" + count + " send:" + send);
				// 服务器端可能因为缓存区满，而导致数据传输失败，需要重新发送
				while(send == 0){
					Thread.sleep(10);
					send = client.write(sendBuffer);
					System.out.println("i重新传输====" + i + "   count:" + count + " send:" + send);
				}
				sendBuffer.clear(); 
           }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				channel.close();
				fis.close();
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	
	public static void main(String[] args){
		new FileClient();
	}
}
