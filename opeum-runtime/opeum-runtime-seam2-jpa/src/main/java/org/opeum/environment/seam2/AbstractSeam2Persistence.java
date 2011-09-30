package org.opeum.environment.seam2;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.opeum.runtime.jpa.AbstractJpaPersistence;

public class AbstractSeam2Persistence extends AbstractJpaPersistence{
	@In
	EntityManager entityManager;
	@Override
	protected EntityManager getEntityManager(){
		return entityManager;
	}
}
