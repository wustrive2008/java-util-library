package com.wustrive.util.nio.mina;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class ClientDemo1 {
	private static Logger log = Logger.getLogger(ClientDemo1.class);
	private static String HOST = "localhost";
	private static int PORT = 2005;
	
	public static void main(String[] args) {
		
		IoConnector connector = new NioSocketConnector();
		
		//connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"),LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
		
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		
		connector.setHandler((IoHandler) new ClientDemo1Handle());
		IoSession session = null;
		
		ConnectFuture future = connector.connect(new InetSocketAddress(HOST,PORT));
		
		future.awaitUninterruptibly();// 等待连接创建完成
		session = future.getSession();
		MinaMessage minaMessage = new MinaMessage();
		minaMessage.setTitle("message title");
		minaMessage.setContent("message content");
		session.write(minaMessage);
		
		session.getCloseFuture().awaitUninterruptibly();//等待连接断开
		connector.dispose();

	}

}
