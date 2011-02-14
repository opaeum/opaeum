package org.nakeduml.persistence;

import java.io.File;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import net.sf.nakeduml.util.DataGeneratorProperty;

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
import datagenerationtest.org.nakeduml.GodDataGenerator;


@RunWith(Arquillian.class)
public class DataExportTest extends BaseTest {

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

	@Inject
	GodDataGenerator godDataGenerator;
	@Inject
	DataGeneratorProperty dataGeneratorProperty;

	@SuppressWarnings("unchecked")
	@Test
	public void testDataExport() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException {

		List<God> gods = session.createQuery("select h from God h").list();
		godDataGenerator.exportGod(gods);

		Properties exportProperties = dataGeneratorProperty.getExportProperties();
		Properties dataGenerationProperties = dataGeneratorProperty.getProperties();

		dataGeneratorProperty.exportPropertiesToFile(new File("/tmp/exportProperties"));
		
		Enumeration<?> e = dataGenerationProperties.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			Assert.assertNotNull(exportProperties.get(key));
		}
		
		e = exportProperties.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			if (dataGenerationProperties.get(key)==null && !key.endsWith("size")) {
				Assert.fail();
			}
		}		

	}
}
