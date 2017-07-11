package com.wustrive.util.nio.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerDemo1Handle implements IoHandler {
	private static Logger log = LoggerFactory.getLogger(ServerDemo1Handle.class);
	
	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("服务端发生异常。。。");
	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		MinaMessage minaMessage = (MinaMessage)message;
		log.info("服务端接收到的数据为：" + minaMessage.getTitle()+":"+minaMessage.getContent());
		if ("bye".equals(minaMessage.getTitle())) { // 服务端断开连接的条件
			session.close(true);	
		}
		Date date = new Date();
		session.write(date);

	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		log.info("服务端发送消息。。。");
	}

	public void sessionClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		log.info("服务端与客户端连接关闭。。。");
	}

	public void sessionCreated(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		log.info("服务端与客户端建立连接。。。");
	}

	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub
		log.info("服务端进入空闲状态。。。");
	}

	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		log.info("服务端与客户端连接打开。。。");
	}

}
