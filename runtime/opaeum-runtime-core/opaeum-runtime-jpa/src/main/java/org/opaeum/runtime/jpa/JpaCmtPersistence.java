package org.opaeum.runtime.jpa;

import javax.transaction.Synchronization;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.transaction.spi.LocalStatus;
import org.opaeum.hibernate.domain.AbstractHibernatePersistence;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.CmtPersistence;

public class JpaCmtPersistence extends AbstractHibernatePersistence implements CmtPersistence{
	private SessionFactory factory;
	// ThreadLocal<JpaCmtPersistence> currentPersistence;
	public JpaCmtPersistence(SessionFactory session,Environment e,final ThreadLocal<JpaCmtPersistence> currentPersistence){
		super(e);
		this.factory = session;
		// NB!! this code did not work because hibernate's transaction did not start the transaction and it would therefore be NOT_ACTIVE
		// if(getSession().getTransaction()==null || !getSession().getTransaction().isParticipating()){
		// throw new IllegalStateException("ContainerManagedPersistence can only be used within methods that require active transactions");
		// }
		//getSession() will already throw an exception if there is no active transaction: JtaSessionContext
		if(getSession().getTransaction() == null || !isInJtaEnvironment()){
			throw new IllegalStateException("ContainerManagedPersistence can only be used within methods that require active transactions");
		}
		getSession().setFlushMode(FlushMode.COMMIT);
		// CLose after commit
		getSession().getTransaction().registerSynchronization(new Synchronization(){
			@Override
			public void afterCompletion(int arg0){
				currentPersistence.set(null);// if there is more than one transaction per request, a new instanceof this class will be created if
																			// retrieved from the environment. Not an issue as they delegate to currentSession
			}
			@Override
			public void beforeCompletion(){
			}
		});
	}
	protected boolean isInJtaEnvironment(){
		return getSession().getTransaction().getClass().getName().toLowerCase().contains("jta");
	}
	@Override
	protected Session getSession(){
		return factory.getCurrentSession();
	}
	@Override
	public boolean isOpen(){
		try{
			return getSession().isOpen();
		}catch(Exception e){
			//Could be oustide a transaction 
			return false;
		}
	}
}
