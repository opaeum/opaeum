package org.nakeduml.environment.adaptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;

import org.jboss.ejb3.annotation.Pool;
import org.nakeduml.runtime.domain.ExceptionAnalyser;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Pool(value = "StrictMaxPool",maxSize = 100)
public class MessageRetryer{
	@Resource
	TimerService timerService;
	public void testDeployment(){
	}
	public void retryMessage(String destination,Serializable payload){
		long delay = Math.round(Math.random() * 20000);// Randomly spread delay to minimise risk of concurrency
		timerService.createTimer(new Date(System.currentTimeMillis() + delay), new PayloadToResend(destination, payload));
	}
	public static void main(String[] args){
		System.out.println(Math.random());
	}
	@Timeout
	public void redeliver(Timer t){
		PayloadToResend p = (PayloadToResend) t.getInfo();
		String destination = p.getDestination();
		Serializable payload = p.getPayload();
		sendMessage(destination, payload);
	}
	public static void sendMessage(String destination,Serializable payload){
		ConnectionFactory factory=null;
		Connection conn = null;
		Session sess = null;
		try{
			InitialContext ctx = new InitialContext();
			factory=(ConnectionFactory) ctx.lookup("java:/XAConnectionFactory");
			Queue q = (Queue) ctx.lookup(destination);
			conn = factory.createConnection();
			sess = conn.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			try{
				ObjectOutputStream os = new ObjectOutputStream(new ByteArrayOutputStream());
				os.writeObject(payload);
			}catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MessageProducer prod = sess.createProducer(q);
			prod.send(sess.createObjectMessage(payload));
			prod.close();
		}catch(RuntimeException e){
			throw e;
		}catch(Exception e){
			new ExceptionAnalyser(e).throwRootCause();
		}finally{
			if(sess != null){
				try{
					sess.close();
				}catch(Exception e){
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){
				}
			}
		}
	}
}
