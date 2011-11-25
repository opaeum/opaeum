package org.opaeum.audit;

import java.util.Date;
import java.util.SortedSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.opaeum.runtime.domain.IntrospectionUtil;

public class AuditTest extends TestCase {
	private EntityManagerFactory emf;
	public void testSimpleTypes() throws Exception {
		testPropertyHistory("asdf", "stringProperty");
		testPropertyHistory(Boolean.TRUE, "booleanProperty");
		testPropertyHistory(300, "integerProperty");
		testPropertyHistory(1.23456789, "doubleProperty");
		testPropertyHistory(new Date(), "dateTimeProperty");
	}

	private void testPropertyHistory(Object expectedValue, String propertyName) throws Exception {
		EntityManager em = emf.createEntityManager();
		ParentAuditedObject pao = new ParentAuditedObject();
		em.getTransaction().begin();
		em.persist(pao);
		Date updatedDate = pao.getUpdatedOn();
		em.getTransaction().commit();
		AuditHistory ph = new AuditHistory(em, pao, "updatedOn");
		SortedSet<PropertyChange<?>> history = ph.getPropertyHistory(propertyName, updatedDate, new Date());
		Assert.assertEquals(1, history.size());
		Assert.assertEquals(AuditedAction.CREATE, history.first().auditEntry.getAction());
		NullPropertyChange npc = (NullPropertyChange) history.first();
		IntrospectionUtil.getProperty(propertyName, ParentAuditedObject.class).getWriteMethod().invoke(pao, expectedValue);
		em.getTransaction().begin();
		em.getTransaction().commit();
		history = ph.getPropertyHistory(propertyName, updatedDate, new Date());
		Assert.assertEquals(2, history.size());
		assertEquals(expectedValue, history);
		Assert.assertEquals(AuditedAction.UPDATE, history.last().auditEntry.getAction());
		pao.setDeletedOn(new Date());
		em.getTransaction().begin();
		em.getTransaction().commit();
		history = ph.getPropertyHistory("deletedOn", updatedDate, new Date());
		Assert.assertEquals(2, history.size());//Initial Null insert and now update
		Assert.assertEquals(AuditedAction.DELETE, history.last().auditEntry.getAction());
		em.close();
	}

	public void setUp() {
		this.emf = Persistence.createEntityManagerFactory("AuditPU");
	}

	private void assertEquals(Object expectedValue, SortedSet<PropertyChange<?>> history) {
		if (expectedValue instanceof Number) {
			Assert.assertEquals(((Number) expectedValue).doubleValue(), ((Number) history.last().getValue()).doubleValue(), 0.001);
		} else {
			Assert.assertEquals(expectedValue, history.last().getValue());
		}
	}

	public void testAuditedEntityRelationship() {		
		EntityManager em = emf.createEntityManager();
		ParentAuditedObject pao1 = new ParentAuditedObject();
		em.getTransaction().begin();
		ChildAuditedObject cao = new ChildAuditedObject();
		em.persist(pao1);
		//Persisting BEFORE adding children causes two events but one object version
		pao1.getChildren().add(cao);
		cao.setParent(pao1);
		em.persist(cao);
		Date updatedDate = pao1.getUpdatedOn();
		em.getTransaction().commit();
		AuditHistory childAuditHistory = new AuditHistory(em, cao, "updatedOn");
		SortedSet<PropertyChange<?>> propertyChanges = childAuditHistory.getPropertyHistory("parent", updatedDate, new Date());
		Assert.assertEquals(1, propertyChanges.size());
		AuditEntryPropertyChange aepc= (AuditEntryPropertyChange) propertyChanges.first();
		AuditHistory parentAuditHistory = new AuditHistory(em, pao1, "updatedOn");
		SortedSet<AuditEntry> parentAuditEntries = parentAuditHistory.getHistory(updatedDate, new Date());
		Assert.assertEquals(parentAuditEntries.first().getObjectVersion(), aepc.getValue().getObjectVersion());
		//When a manyToOne's entity was dirty, the property also needs to be logged to allow for a snapshot to be reconstructed
		em.getTransaction().begin();
		cao.setSomeProperty("s");
		pao1.setStringProperty("asdf");
		em.getTransaction().commit();
		parentAuditEntries = parentAuditHistory.getHistory(updatedDate, new Date());
		propertyChanges = childAuditHistory.getPropertyHistory("parent", updatedDate, new Date());
		Assert.assertEquals(2, propertyChanges.size());
		aepc= (AuditEntryPropertyChange) propertyChanges.last();
		Assert.assertEquals(parentAuditEntries.last().getObjectVersion(), aepc.getValue().getObjectVersion());
		em.close();
	}
	public void testPrevious() {		
		EntityManager em = emf.createEntityManager();
		ParentAuditedObject pao1 = new ParentAuditedObject();
		em.getTransaction().begin();
		em.persist(pao1);
		em.flush();
		em.getTransaction().commit();
		pao1.setStringProperty("asdf");
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();
		em.close();
		em=emf.createEntityManager();
		AuditHistory history = new AuditHistory(em, pao1, "updatedOn");
		SortedSet<PropertyChange<?>> hist = history.getPropertyHistory("stringProperty", new Date(System.currentTimeMillis()-100000), new Date());
		Assert.assertEquals(2, hist.size());
		AuditEntry firstAuditEntry = hist.first().getAuditEntry();
		Assert.assertEquals(0, firstAuditEntry.getObjectVersion());
		AuditEntry lastAuditEntry = hist.last().getAuditEntry();
		Assert.assertEquals(1, lastAuditEntry.getObjectVersion());
		em.close();
	}
	public void testUnauditedEntityRelationship(){
		EntityManager em = emf.createEntityManager();
		ParentAuditedObject pao1 = new ParentAuditedObject();
		em.getTransaction().begin();
		ChildAuditedObject cao = new ChildAuditedObject();
		pao1.getChildren().add(cao);
		cao.setParent(pao1);
		UnauditedObject uao1 = new UnauditedObject();
		em.persist(uao1);
		cao.setUnauditedObject(uao1);
		UnauditedObject uao2 = new UnauditedObject();
		em.persist(uao2);
		em.persist(pao1);
		em.flush();
		Date updatedDate = pao1.getUpdatedOn();
		em.getTransaction().commit();
		em.getTransaction().begin();
		AuditHistory ph = new AuditHistory(em, cao, "updatedOn");
		SortedSet<PropertyChange<?>> history = ph.getPropertyHistory("unauditedObject", updatedDate, new Date());
		Assert.assertEquals(1, history.size());
		EntityPropertyChange epc =(EntityPropertyChange) history.first();
		Assert.assertSame(uao1, epc.getValue());
		cao.setUnauditedObject(uao2);
		em.flush();
		em.getTransaction().commit();
		history = ph.getPropertyHistory("unauditedObject", updatedDate, new Date());
		Assert.assertEquals(2, history.size());
		epc =(EntityPropertyChange) history.last();
		Assert.assertSame(uao2, epc.getValue());
		em.close();
	}
	public void testCustomAuditEntry(){
		EntityManager em = emf.createEntityManager();
		ParentAuditedObject pao1 = new ParentAuditedObject();
		em.getTransaction().begin();
		ChildAuditedObject cao = new ChildAuditedObject();
		em.persist(pao1);
		//Persisting BEFORE adding children causes two events but one object version
		pao1.getChildren().add(cao);
		cao.setParent(pao1);
		em.persist(cao);
		Date updatedDate = pao1.getUpdatedOn();
		em.getTransaction().commit();
		AuditHistory childAuditHistory = new AuditHistory(em, cao, "updatedOn");
		SortedSet<PropertyChange<?>> propertyChanges = childAuditHistory.getPropertyHistory("parent", updatedDate, new Date());
		Assert.assertEquals(1, propertyChanges.size());
		AuditEntryPropertyChange aepc= (AuditEntryPropertyChange) propertyChanges.first();
		AuditHistory parentAuditHistory = new AuditHistory(em, pao1, "updatedOn");
		SortedSet<AuditEntry> parentAuditEntries = parentAuditHistory.getHistory(updatedDate, new Date());
		Assert.assertEquals(parentAuditEntries.first().getObjectVersion(), aepc.getValue().getObjectVersion());
		//When a manyToOne's entity was dirty, the property also needs to be logged to allow for a snapshot to be reconstructed
		em.getTransaction().begin();
		cao.setSomeProperty("s");
		pao1.setStringProperty("asdf");
		em.getTransaction().commit();
		parentAuditEntries = parentAuditHistory.getHistory(updatedDate, new Date());
		propertyChanges = childAuditHistory.getPropertyHistory("parent", updatedDate, new Date());
		Assert.assertEquals(2, propertyChanges.size());
		aepc= (AuditEntryPropertyChange) propertyChanges.last();
		Assert.assertEquals(parentAuditEntries.last().getObjectVersion(), aepc.getValue().getObjectVersion());
		em.close();
	}
	public void testNoPreviousEntry(){
		EntityManager em = emf.createEntityManager();
		ParentAuditedObject pao1 = new ParentAuditedObject();
		em.getTransaction().begin();
		em.persist(pao1);
		em.flush();
		em.getTransaction().commit();
		//Delete first audit entry
		em.getTransaction().begin();
		em.remove(em.find(AuditEntry.class, new AuditEntryId(pao1, 0)));
		em.flush();
		em.getTransaction().commit();		
		pao1.setStringProperty("asdf");
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();
		em.close();
		em=emf.createEntityManager();
		AuditHistory history = new AuditHistory(em, pao1, "updatedOn");
		SortedSet<PropertyChange<?>> hist = history.getPropertyHistory("stringProperty", new Date(System.currentTimeMillis()-100000), new Date());
		Assert.assertEquals(1, hist.size());
		AuditEntry firstAuditEntry = hist.first().getAuditEntry();
		Assert.assertEquals(1, firstAuditEntry.getObjectVersion());
		em.close();
	}
	public void tearDown(){
//		Session session=(Session) emf.createEntityManager().getDelegate();
//		List resultList = session.createCriteria(PropertyChange.class).list();
//		for (Object object : resultList) {
//			System.out.println(object);
//		}
	}
}
