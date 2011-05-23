package org.nakeduml.environment.adaptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.nakeduml.environment.IMessageSender;

public class MessageSender implements IMessageSender{
	@Override
	public void sendObjectsToQueue(Collection<? extends Serializable> objects,String queue){
		Connection connection = null;
		Session session = null;
		try{
			//TODO get to work inside a transaction
			InitialContext initialContext = new InitialContext();
			ConnectionFactory factory = (ConnectionFactory) initialContext.lookup("/ConnectionFactory");
			connection = factory.createConnection();
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer((Queue) initialContext.lookup(queue));
			for(Serializable s:objects){
				try{
					ObjectOutputStream os = new ObjectOutputStream(new ByteArrayOutputStream());
					os.writeObject(s);
					producer.send(session.createObjectMessage(s));
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			producer.close();
		}catch(JMSException e){
			throw new RuntimeException(e);
		}catch(NamingException e){
			throw new RuntimeException(e);
		}finally{
			try{
				session.close();
			}catch(Exception ignore){
			}
			try{
				connection.close();
			}catch(Exception ignore){
			}
		}
	}

	@Override
	public void deliverMockedMessages(){
		// TODO Auto-generated method stub
		
	}
}
