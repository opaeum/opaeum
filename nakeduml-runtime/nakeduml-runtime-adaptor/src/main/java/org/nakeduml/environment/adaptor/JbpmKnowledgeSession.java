package org.nakeduml.environment.adaptor;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Synchronization;

import org.nakeduml.runtime.domain.ExceptionAnalyser;

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@RequestScoped
@Stateful
public class JbpmKnowledgeSession extends AbstractJbpmKnowledgeSession implements SessionSynchronization{
	public void clear(){
		// For mocking purposes only
		this.knowledgeSession = null;
	}
	@Override
	public void afterBegin() throws EJBException,RemoteException{
	}
	@Override
	public void beforeCompletion() throws EJBException,RemoteException{
	}
	@Override
	public void afterCompletion(boolean committed) throws EJBException,RemoteException{
		knowledgeSession.dispose();
		knowledgeSession = null;
	}
}
