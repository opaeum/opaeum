package org.nakeduml.audit;

import java.util.Date;
import java.util.SortedSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AuditPerformanceChecker  {
	private EntityManagerFactory emf;

	public void setUp() {
		this.emf = Persistence.createEntityManagerFactory("AuditPU");
	}

	public void testUpdates() throws Exception {

		EntityManager em = emf.createEntityManager();
		ParentAuditedObject pao = null;
		Date fromDate = new Date();
		for (int j = 0; j < 1; j++) {
			pao = new ParentAuditedObject();
			em.getTransaction().begin();
			em.persist(pao);
			em.getTransaction().commit();
			for (int i = 0; i < 10000; i++) {
				em.getTransaction().begin();
				pao.setBooleanProperty(i % 2 == 0);
				pao.setDateTimeProperty(new Date());
				pao.setIntegerProperty(i);
				pao.setDoubleProperty(i*1.23456789);
				pao.setStringProperty("" + i);
				
				em.getTransaction().commit();
			}
			System.out.println(j);
		}
		System.out.println("auditing took " + (System.currentTimeMillis() - fromDate.getTime()));
		AuditHistory ph = new AuditHistory(em, pao, "updatedOn");
		Date newFromDate=new Date();
		SortedSet<PropertyChange<?>> history = ph.getPropertyHistory("stringProperty", fromDate, new Date());
		System.out.println("history retrieval took " + (System.currentTimeMillis() - newFromDate.getTime()));
		System.out.println(history.size());

	}

	public void testLargeInserts() throws Exception {

		EntityManager em = emf.createEntityManager();
		ParentAuditedObject pao = null;
		Date fromDate = new Date();
		em.getTransaction().begin();
		for (int i = 0; i < 10000; i++) {
			pao = new ParentAuditedObject();
			em.persist(pao);
		}
		em.getTransaction().commit();
		System.out.println("took " + (System.currentTimeMillis() - fromDate.getTime()));
		AuditHistory ph = new AuditHistory(em, pao, "updatedOn");
		ph.getPropertyHistory("stringProperty", fromDate, new Date());

	}
}
