package org.nakeduml.environment.adaptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;
import org.nakeduml.environment.IMessageSender;
import org.nakeduml.runtime.domain.ExceptionAnalyser;

@Stateful
@RequestScoped
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class MessageSender implements IMessageSender,SessionSynchronization{
	private static final long serialVersionUID = 1L;
	@Inject
	Logger logger;
	@Resource(mappedName = "java:/XAConnectionFactory")
	ConnectionFactory factory;
	transient Connection connection = null;
	transient Map<String,MessageProducer> producers = new HashMap<String,MessageProducer>();
	transient private Session session;
	transient private InitialContext initialContext;
	@PreDestroy
	public void release(){
		try{
			if(initialContext != null){
				initialContext.close();
			}
			for(MessageProducer p:producers.values()){
				p.close();
			}
			if(session != null){
				session.close();
			}
			if(connection!=null){
				connection.close();
			}
			connection=null;
		}catch(Exception e){
			logger.debug("Error closing jms resources", e);
		}
	}
	@Override
	public void sendObjectToQueue(Serializable s,String queue){
		try{
			MessageProducer producer = getProducer(queue);
			try{
				ObjectOutputStream os = new ObjectOutputStream(new ByteArrayOutputStream());
				os.writeObject(s);
				producer.send(session.createObjectMessage(s));
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch(Exception e){
			logger.error("Error sending message", e);
			throw new ExceptionAnalyser(e).wrapRootCauseIfNecessary();
		}
	}
	private MessageProducer getProducer(String queue) throws JMSException,NamingException{
		MessageProducer producer = producers.get(queue);
		if(producer == null){
			producer = getSession().createProducer((Queue) getInitialContext().lookup(queue));
			producers.put(queue, producer);
		}
		return producer;
	}
	private InitialContext getInitialContext() throws NamingException{
		if(initialContext == null){
			initialContext = new InitialContext();
		}
		return initialContext;
	}
	private Session getSession() throws JMSException{
		if(session == null){
			connection = factory.createConnection();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		}
		return session;
	}
	@Override
	public void deliverMockedMessages(){
	}
	@Override
	public void afterBegin() throws EJBException,RemoteException{
	}
	@Override
	public void beforeCompletion() throws EJBException,RemoteException{
	}
	@Override
	public void afterCompletion(boolean committed) throws EJBException,RemoteException{
		try{
			session.commit();
			release();
		}catch(Exception e){
			throw new ExceptionAnalyser(e).wrapRootCauseIfNecessary();
		}
	}
}
