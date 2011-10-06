package org.opeum.environment.seam2;

import javax.persistence.EntityManager;

import org.drools.RuntimeDroolsException;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.transaction.UserTransaction;
import org.opeum.runtime.jpa.AbstractJpaUmtPersistence;
import org.opeum.runtime.persistence.UmtPersistence;
@Name("umtsPersistence")
public class Seam2UmtPersistence extends AbstractJpaUmtPersistence implements UmtPersistence {
	@In
	UserTransaction userTransaction;
	@In
	EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public void beginTransaction() {
		try {
			userTransaction.begin();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeDroolsException(e);
		}
	}

	@Override
	public boolean isActive() {
		try {
			return userTransaction.isActive();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeDroolsException(e);
		}
	}

	@Override
	public void rollbackTransaction() {
		try {
			userTransaction.rollback();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeDroolsException(e);
		}
	}

	@Override
	public void commitTransaction() {
		try {
			userTransaction.commit();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeDroolsException(e);
		}
	}

	@Override
	public boolean isRolledBack() {
		try {
			return userTransaction.isRolledBackOrMarkedRollback();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeDroolsException(e);
		}
	}

	@Override
	public void setTransactionTimeout(int i) {
		try {
			userTransaction.setTransactionTimeout(i);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeDroolsException(e);
		}
	}
}
