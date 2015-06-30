package com.wustrive.util.nio.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;


import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class ServerDemo1 {
	private static Logger log = Logger.getLogger(ServerDemo1.class);
	private static int PORT = 2005;
	public static void main(String[] args) {
		
		IoAcceptor acceptor = null;
		acceptor = new NioSocketAcceptor();
		//acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
		//		new TextLineCodecFactory(Charset.forName("utf-8"),LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
		acceptor.setHandler(new ServerDemo1Handle());
		try {
			acceptor.bind(new InetSocketAddress(PORT));
			log.info("服务端启动成功。。。");
		} catch (IOException e) {
			log.info("服务器启动失败。。。");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
