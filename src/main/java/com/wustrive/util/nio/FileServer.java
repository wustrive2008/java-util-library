package com.wustrive.util.nio;

import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class FileServer {
	private int port = 8000;
	/* 发送数据缓冲区 */
	private static ByteBuffer revBuffer = ByteBuffer.allocate(1024);
	private static Selector selector;
	private static FileOutputStream fout;
	private static FileChannel ch;
	public FileServer(){
		try{
			init();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private void init() throws Exception{
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(port));
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("server start on port:" + port);
		while (true) {
			try {
				selector.select();// 返回值为本次触发的事件数
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				
				for (SelectionKey key : selectionKeys) {
					ServerSocketChannel server = null;
					SocketChannel client = null;
					int count = 0;
					if (key.isAcceptable()) {
						server = (ServerSocketChannel) key.channel();
						System.out.println("有客户端连接进入=============)");
						client = server.accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ);
						fout = new FileOutputStream("I:\\server\\" + client.hashCode() + ".jpg");
						ch = fout.getChannel();
					} else if (key.isReadable()) {
						client = (SocketChannel) key.channel();
						revBuffer.clear();
						count = client.read(revBuffer);
						int k = 0;
						// 循环读取缓存区的数据，
						while(count > 0){
							System.out.println("k=" + (k++) + " 读取到数据量:" + count);
							revBuffer.flip();
							ch.write(revBuffer);
							fout.flush();
							revBuffer.clear();
							count = client.read(revBuffer);
						}
						if(count == -1){
							client.close();
							ch.close();
							fout.close();
						}
					}
					else if (key.isWritable()) {
						System.out.println("selectionKey.isWritable()");
					
					}
				}
				System.out.println("=======selectionKeys.clear()");
				selectionKeys.clear();
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}

		}
	}
	public static void main(String[] args){
		new FileServer();
	}
}
