package org.nakeduml.environment.adaptor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.Synchronization;
import javax.transaction.TransactionManager;

import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.nakeduml.runtime.domain.ExceptionAnalyser;
@RequestScoped
public class JbpmKnowledgeSession extends AbstractJbpmKnowledgeSession implements Synchronization{
	private TransactionManager txManager;
	@Inject
	private SessionFactory sessionFactory;
	public void clear(){
		// For mocking purposes only
		this.knowledgeSession = null;
	}
	@Override
	public void afterCompletion(int status){
		knowledgeSession.dispose();
		knowledgeSession = null;
	}
	@Override
	public void beforeCompletion(){
		
	}
	private TransactionManager getTxManager() throws NamingException{
		if(txManager==null){
			txManager=(TransactionManager) new InitialContext().lookup("java:/TransactionManager");
		}
		return txManager;
	}
	@Override
	public StatefulKnowledgeSession getKnowledgeSession(){
		if(this.knowledgeSession == null){
			try{
				getTxManager().getTransaction().registerSynchronization(this);
			}catch(Exception e){
				throw new ExceptionAnalyser(e).wrapRootCauseIfNecessary();
			}
			this.knowledgeSession = createKnowledgeSession();
		}
		return this.knowledgeSession;
	}
	public Session getHibernateSession(){
		final Session session = sessionFactory.getCurrentSession();
		return session;
	}

}
