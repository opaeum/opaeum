package org.nakeduml.environment.cdi.test;

import java.util.Stack;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.seam.transaction.DefaultTransaction;
import org.jboss.seam.transaction.SeamTransaction;
import org.nakeduml.environment.adaptor.JbpmKnowledgeSession;

@DefaultTransaction
@RequestScoped
public class CdiTestSeamTransaction implements SeamTransaction{
	@Inject
	Session hibernateSession;
	int status = -1;
	Transaction tx;
	Stack<Object> txStack = new Stack<Object>();
	@Override
	public void begin() throws NotSupportedException,SystemException{
		if(txStack.isEmpty()){
			tx = hibernateSession.beginTransaction();
		}
		txStack.push(new Object());
		status = Status.STATUS_ACTIVE;
	}
	@Override
	public void commit() throws RollbackException,HeuristicMixedException,HeuristicRollbackException,SecurityException,IllegalStateException,SystemException{
		status = Status.STATUS_COMMITTED;
		txStack.pop();
		if(txStack.isEmpty()){
			tx.commit();
		}
		resetJbpmKnowledgeSession();
	}
	private void resetJbpmKnowledgeSession(){
		JbpmKnowledgeSession component = CdiTestEnvironment.getInstance().getComponent(JbpmKnowledgeSession.class);
		if(component != null){
			component.clear();
		}
	}
	@Override
	public void rollback() throws IllegalStateException,SecurityException,SystemException{
		resetJbpmKnowledgeSession();
		txStack.pop();
		if(txStack.isEmpty()){
			tx.rollback();
		}
		status = Status.STATUS_ROLLEDBACK;
	}
	@Override
	public void setRollbackOnly() throws IllegalStateException,SystemException{
		status = Status.STATUS_MARKED_ROLLBACK;
	}
	@Override
	public int getStatus() throws SystemException{
		return status;
	}
	@Override
	public void setTransactionTimeout(int seconds) throws SystemException{
	}
	@Override
	public boolean isActive() throws SystemException{
		return status == Status.STATUS_ACTIVE;
	}
	@Override
	public boolean isActiveOrMarkedRollback() throws SystemException{
		return isActive() || isMarkedRollback();
	}
	@Override
	public boolean isRolledBackOrMarkedRollback() throws SystemException{
		return isRolledBack() || isMarkedRollback();
	}
	@Override
	public boolean isMarkedRollback() throws SystemException{
		return status == Status.STATUS_MARKED_ROLLBACK;
	}
	@Override
	public boolean isNoTransaction() throws SystemException{
		return status == -1;
	}
	@Override
	public boolean isRolledBack() throws SystemException{
		return status == Status.STATUS_ROLLEDBACK;
	}
	@Override
	public boolean isCommitted() throws SystemException{
		return status == Status.STATUS_COMMITTED;
	}
	@Override
	public boolean isConversationContextRequired(){
		return false;
	}
	@Override
	public void registerSynchronization(Synchronization sync){
	}
	@Override
	public void enlist(EntityManager entityManager) throws SystemException{
	}
}
