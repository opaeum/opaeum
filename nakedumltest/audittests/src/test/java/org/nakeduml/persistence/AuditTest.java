package org.nakeduml.persistence;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import net.sf.nakeduml.arquillian.ArquillianUtils;
import net.sf.nakeduml.test.NakedUtilTestClasses;

import org.hibernate.Session;
import org.hibernate.annotations.common.util.ReflectHelper;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import audittest.org.nakeduml.audit.God;
import audittest.org.nakeduml.audit.God_Audit;

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
		war.addPackage(ReflectHelper.classForName( "audittest" + ".package-info" ).getPackage());
		war.addManifestResource(HORNETQ_JMS_DEPLOYMENT_CONFIG);
		return war;
	}

	@Inject
	Session session;

	@Inject
	AuditController auditController;

	@SuppressWarnings("unchecked")
	@Test
	public void testAudit() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, InterruptedException {

		session.clear();
		session.enableFilter("noDeletedObjects");
		
		Assert.assertEquals(0, God.allInstances().size());
		
		God god = auditController.captureGod("god1");

		Assert.assertEquals(1, God.allInstances().size());

		Assert.assertNotNull(god);
		Assert.assertNotNull(god.getName());
		Assert.assertNotSame("", god.getName());
		Thread.sleep(5000);
		List<God_Audit> godAudits = session.createQuery("from God_Audit h where h.name = :name").setParameter("name", "god1").list();
		Assert.assertEquals(1, godAudits.size());

		auditController.updateGodName(god, "god2");

		session.clear();
		godAudits = session.createQuery("from God_Audit h").list();
		Assert.assertEquals(2, godAudits.size());

		List<God_Audit> result = session.getNamedQuery("GetAuditsForGodAudit").setParameter("original", god).list();
		Assert.assertEquals(2, result.size());
		for (God_Audit godAudit : result) {
			if (godAudit.getId().getObjectVersion() > 0) {
				Assert.assertNotNull(godAudit.getPreviousVesion());
				Assert.assertEquals(godAudit.getPreviousVesion().getId().getObjectVersion(), godAudit.getId().getObjectVersion() - 1);
			}
		}
		
		god = auditController.captureGod("god3");
		List<God> gods = session.createQuery("from God h").list();
		Assert.assertEquals(2, gods.size());
		
		auditController.deleteGod(god);
		session.clear();
		session.createQuery("from God h").list();
		Assert.assertEquals(1, gods.size());
		
	}
	

}
