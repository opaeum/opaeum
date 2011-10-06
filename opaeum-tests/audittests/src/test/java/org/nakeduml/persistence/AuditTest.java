package org.opaeum.persistence;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import net.sf.opaeum.arquillian.ArquillianUtils;
import net.sf.opaeum.test.NakedUtilTestClasses;

import org.hibernate.Session;
import org.hibernate.annotations.common.util.ReflectHelper;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import audittest.org.opaeum.audit.Angel;
import audittest.org.opaeum.audit.Angel_Audit;
import audittest.org.opaeum.audit.Demon;
import audittest.org.opaeum.audit.Finger;
import audittest.org.opaeum.audit.God;
import audittest.org.opaeum.audit.God_Audit;
import audittest.org.opaeum.audit.Hand;
import audittest.org.opaeum.audit.Ring;

@RunWith(Arquillian.class)
public class AuditTest extends BaseTest {

	private static final String HORNETQ_JMS_DEPLOYMENT_CONFIG = "hornetq-jms.xml";

	@Deployment
	public static Archive<?> createTestArchive() throws IllegalArgumentException, ClassNotFoundException, IOException {
		WebArchive war = ArquillianUtils.createWarArchive(false);
		war.addWebResource("WEB-INF/beans.xml", "beans.xml");
		war.addWebResource("hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addClasses(NakedUtilTestClasses.getTestClasses());
		war.addClasses(getTestClasses());
		war.addPackage(ReflectHelper.classForName("audittest" + ".package-info").getPackage());
		war.addManifestResource(HORNETQ_JMS_DEPLOYMENT_CONFIG);
		return war;
	}

	@Inject
	Session session;

	@Inject
	AuditController auditController;

	@Test
	public void testAudit() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, InterruptedException {

		Date start = new Date();

		God god = createAndTestGod();
		updateAndTestGod(god);
		testAuditQuery(god);
		god = testAuditBetweenQuery();
		Hand hand = addAndTestHand(god);
		Finger finger = addAndTestFinger(hand);
		Ring ring = addAndTestRing(god);
		setRingOnFinger(finger, ring);

		Angel angel = createAndTestAngel(god);
		Demon demon = createAndTestDemon(god);

		setOneToOne(angel, demon);
		checkAuditOneToOne(angel, demon, start);
	}

	@SuppressWarnings("unchecked")
	private void checkAuditOneToOne(Angel angel, Demon demon, Date start) {
		auditController.updateDemon(demon, "name2");
		auditController.updateDemon(demon, "name3");
		auditController.updateDemon(demon, "name4");
		session.get(Angel.class, angel.getId());
		Date end = new Date();
		List<Angel_Audit> angelAudits = session.getNamedQuery("GetAuditsBetweenForAngelAudit").setParameter("original", angel).setParameter("start", start)
				.setParameter("end", end).list();
		Assert.assertEquals(2, angelAudits.size());
		auditController.updateAngel(angel, "name3");
		angelAudits = session.getNamedQuery("GetAuditsBetweenForAngelAudit").setParameter("original", angel).setParameter("start", end)
				.setParameter("end", new Date()).list();
		Assert.assertEquals(1, angelAudits.size());
		Assert.assertEquals("name4", angelAudits.get(0).getDemon().getName());
	}

	private void setOneToOne(Angel angel, Demon demon) throws InterruptedException {
		auditController.setAngelAndDemo(angel, demon);
		Thread.sleep(2000);
		Assert.assertEquals(Integer.valueOf(3), session.createSQLQuery("select count(1) from demon_audit").uniqueResult());
		Assert.assertEquals(Integer.valueOf(2), session.createSQLQuery("select count(1) from angel_audit").uniqueResult());
	}

	private Demon createAndTestDemon(God god) throws InterruptedException {
		Demon demon = auditController.addDemon(god, "demon1");
		auditController.updateDemon(demon, "name2");
		Thread.sleep(2000);
		Assert.assertEquals(Integer.valueOf(8), session.createSQLQuery("select count(1) from god_audit").uniqueResult());
		Assert.assertEquals(Integer.valueOf(2), session.createSQLQuery("select count(1) from demon_audit").uniqueResult());
		Assert.assertEquals(Integer.valueOf(15), session.createSQLQuery("select count(1) from revision_entity").uniqueResult());
		return demon;
	}

	private Angel createAndTestAngel(God god) throws InterruptedException {
		Angel angel = auditController.addAngel(god, "god1");
		auditController.updateAngel(angel, "name2");
		Thread.sleep(2000);
		Assert.assertEquals(Integer.valueOf(7), session.createSQLQuery("select count(1) from god_audit").uniqueResult());
		Assert.assertEquals(Integer.valueOf(2), session.createSQLQuery("select count(1) from angel_audit").uniqueResult());
		Assert.assertEquals(Integer.valueOf(13), session.createSQLQuery("select count(1) from revision_entity").uniqueResult());
		return angel;
	}

	private void setRingOnFinger(Finger finger, Ring ring) {
		auditController.setRingOnFinger(finger, ring);
		Assert.assertEquals(Integer.valueOf(3), session.createSQLQuery("select count(1) from finger_audit").uniqueResult());
		Assert.assertEquals(Integer.valueOf(3), session.createSQLQuery("select count(1) from ring_audit").uniqueResult());
		Assert.assertEquals(Integer.valueOf(11), session.createSQLQuery("select count(1) from revision_entity").uniqueResult());
	}

	private Finger addAndTestFinger(Hand hand) throws InterruptedException {
		Finger finger = auditController.addFinger(hand, "name4");
		auditController.updateFinger(finger, "name1_2");
		Thread.sleep(2000);
		Assert.assertEquals(session.createSQLQuery("select count(1) from hand_audit").uniqueResult(), Integer.valueOf(3));
		Assert.assertEquals(session.createSQLQuery("select count(1) from finger_audit").uniqueResult(), Integer.valueOf(2));
		Assert.assertEquals(session.createSQLQuery("select count(1) from revision_entity").uniqueResult(), Integer.valueOf(8));
		return finger;
	}

	private Hand addAndTestHand(God god) throws InterruptedException {
		Hand hand = auditController.addHand(god, "hand1");
		auditController.updateHand(hand, "name2");
		Thread.sleep(2000);
		Assert.assertEquals(Integer.valueOf(5), session.createSQLQuery("select count(1) from god_audit").uniqueResult());
		Assert.assertEquals(Integer.valueOf(2), session.createSQLQuery("select count(1) from hand_audit").uniqueResult());
		Assert.assertEquals(Integer.valueOf(6), session.createSQLQuery("select count(1) from revision_entity").uniqueResult());
		return hand;
	}

	private Ring addAndTestRing(God god) throws InterruptedException {
		Ring ring = auditController.addRing(god, "ring1");
		auditController.updateRing(ring, "name2");
		Thread.sleep(2000);
		Assert.assertEquals(Integer.valueOf(6), session.createSQLQuery("select count(1) from god_audit").uniqueResult());
		Assert.assertEquals(Integer.valueOf(2), session.createSQLQuery("select count(1) from ring_audit").uniqueResult());
		Assert.assertEquals(Integer.valueOf(10), session.createSQLQuery("select count(1) from revision_entity").uniqueResult());
		return ring;
	}

	@SuppressWarnings("unchecked")
	private God testAuditBetweenQuery() {
		God god;
		List<God_Audit> result;
		Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.DAY_OF_YEAR, false);
		god = (God) session.get(God.class, 1L);
		result = session.getNamedQuery("GetAuditsBetweenForGodAudit").setParameter("original", god).setParameter("start", cal.getTime())
				.setParameter("end", new Date()).list();
		Assert.assertEquals(4, result.size());
		return god;
	}

	@SuppressWarnings("unchecked")
	private void testAuditQuery(God god) {
		List<God_Audit> result = session.getNamedQuery("GetAuditsForGodAudit").setParameter("original", god).list();
		Assert.assertEquals(4, result.size());
		for (God_Audit godAudit : result) {
			if (godAudit.getId().getObjectVersion() > 0) {
				Assert.assertNotNull(godAudit.getPreviousVesion());
				Assert.assertEquals(godAudit.getPreviousVesion().getId().getObjectVersion(), godAudit.getId().getObjectVersion() - 1);
			}
		}
	}

	private void updateAndTestGod(God god) {
		auditController.updateGodName(god, "god2");
		auditController.updateGodName(god, "god3");
		auditController.updateGodName(god, "god4");
		session.clear();
		Assert.assertEquals(session.createSQLQuery("select count(1) from god_audit").uniqueResult(), Integer.valueOf(4));
		Assert.assertEquals(session.createSQLQuery("select count(1) from revision_entity").uniqueResult(), Integer.valueOf(4));
	}

	@SuppressWarnings("unchecked")
	private God createAndTestGod() throws InterruptedException {
		God god = auditController.captureGod("god1");
		Assert.assertEquals(1, God.allInstances().size());
		Assert.assertNotNull(god);
		Assert.assertNotNull(god.getName());
		Assert.assertNotSame("", god.getName());
		Thread.sleep(2000);
		List<God_Audit> godAudits = session.createQuery("from God_Audit h where h.name = :name").setParameter("name", "god1").list();
		Assert.assertEquals(1, godAudits.size());
		return god;
	}

}
