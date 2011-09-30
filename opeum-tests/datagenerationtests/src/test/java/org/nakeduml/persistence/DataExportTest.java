package org.opeum.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import net.sf.opeum.arquillian.ArquillianUtils;
import net.sf.opeum.test.NakedUtilTestClasses;
import net.sf.opeum.util.DataGeneratorProperty;

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

import datagenerationtest.org.opeum.God;
import datagenerationtest.org.opeum.GodDataGenerator;


@RunWith(Arquillian.class)
public class DataExportTest extends BaseTest {

	@Deployment
	public static Archive<?> createTestArchive() throws IllegalArgumentException, ClassNotFoundException, IOException {
		WebArchive war = ArquillianUtils.createWarArchive(true);
		war.addWebResource("hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addClasses(NakedUtilTestClasses.getTestClasses()); 
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
			if (exportProperties.get(key)==null) {
				System.out.println(key + " ::: " + dataGenerationProperties.getProperty(key));
			}
//			Assert.assertNotNull(exportProperties.get(key));
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
