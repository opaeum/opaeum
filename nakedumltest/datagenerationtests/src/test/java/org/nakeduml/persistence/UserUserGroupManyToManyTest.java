package org.nakeduml.persistence;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nakeduml.arquillian.ArquillianUtils;
import org.nakeduml.arquillian.ArtifactNames;
import org.nakeduml.arquillian.MavenArtifactResolver;

import datagenerationtest.org.nakeduml.God;
import datagenerationtest.org.nakeduml.User;
import datagenerationtest.org.nakeduml.UserGroup;


@RunWith(Arquillian.class)
public class UserUserGroupManyToManyTest extends BaseTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		WebArchive war = ArquillianUtils.createWarArchive(true);
		war.addWebResource("hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.NAKED_UML_UTIL));
		war.addClasses(getTestClasses());
		return war;
	}

	@Inject
	@DefaultTransaction
	SeamTransaction transaction;

	@Inject
	Session session;

	@SuppressWarnings("unchecked")
	@Test
	public void testDataGeneration() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException {

		List<God> gods = session.createQuery("select h from God h").list();
		for (God god : gods) {
			Assert.assertNotNull(god.getName());
			Assert.assertNotSame("", god.getName());
		}
		// The startup creates 3 objects
		Assert.assertEquals(3, gods.size());
		
		List<User> users = session.createQuery("select h from User h").list();
		Assert.assertEquals(9, users.size());
		List<UserGroup> userGroups = session.createQuery("select h from UserGroup h").list();
		Assert.assertEquals(9, userGroups.size());

		for (User user : users) {
			Assert.assertTrue(!user.getUserGroup().isEmpty());
		}

		for (UserGroup userGroup : userGroups) {
			Assert.assertTrue(!userGroup.getUser().isEmpty());
			Assert.assertTrue(!userGroup.getUserGroupModulePermission().isEmpty());
		}

	}

}
