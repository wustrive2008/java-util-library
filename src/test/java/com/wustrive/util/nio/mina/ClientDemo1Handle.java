package com.wustrive.util.nio.mina;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ClientDemo1Handle implements IoHandler {
	private static Logger log = Logger.getLogger(ClientDemo1Handle.class);
	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("客户端异常。。。。。");
	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		log.info("接收到的消息为："+message.toString());
	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionCreated(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
