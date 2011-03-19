package net.sf.nakeduml.ripper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jbpm.jbpm.Department;
import jbpm.jbpm.nodedefinition.EISConnection;
import jbpm.jbpm.nodedefinition.NodeDefinitionFactory;
import jbpm.jbpm.nodedefinition.interaction.EISInteractionSpec;
import jbpm.jbpm.nodedefinition.pool.EisPool;
import jbpm.jbpm.rip.NodeDefinition;
import jbpm.util.Stdlib;
import net.sf.nakeduml.jbpm.test.SimpleAsyncWebIntegrationTest;
import net.sf.nakeduml.jbpm.test.SimpleSyncWebIntegrationTest;

import org.hibernate.annotations.common.util.ReflectHelper;
import org.jboss.arquillian.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.nakeduml.jbpmtestintegration.util.jbpm.adaptor.JbpmKnowledgeBase;
import org.nakeduml.test.NakedUtilTestClasses;
import org.nakeduml.test.adaptor.ArquillianUtils;
import org.nakeduml.test.adaptor.ArtifactNames;
import org.nakeduml.test.adaptor.MavenArtifactResolver;

public class JbpmWebTest {
	private static final String HORNETQ_JMS_DEPLOYMENT_CONFIG = "hornetq-jms.xml";

	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		WebArchive war = ArquillianUtils.createWarArchive(false);
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.SEAM_SERVLET_API));
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.SEAM_SERVLET_IMPL));
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.SEAM_SCHEDULING));
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.COMMONS_NET));
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.COMMONS_POOL));
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.JCRAFT_JSCH));
		war.addWebResource("WEB-INF/beans.xml", "beans.xml");
		war.addWebResource("jbpmtestintegration-hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addWebResource("nakeduml.env.properties", "nakeduml.env.properties");
		war.addWebResource("jbpm/jbpm/application/SimpleSync1.rf", "jbpm/jbpm/application/SimpleSync1.rf");
		war.addWebResource("jbpm/jbpm/dispatch/SimpleAsyncShipping.rf", "jbpm/jbpm/dispatch/SimpleAsyncShipping.rf");
		war.addWebResource("jbpm/jbpm/rip/networksoftwareversion/RipProcess.rf", "jbpm/jbpm/rip/networksoftwareversion/RipProcess.rf");
		war.addWebResource("jbpm/jbpm/rip/networksoftwareversion/ripprocess/RipActivity.rf", "jbpm/jbpm/rip/networksoftwareversion/ripprocess/RipActivity.rf");
		war.addPackages(true, NakedUtilTestClasses.getTestPackages());
		List<Package> packages = getTestPackages();
		packages.add(EISConnection.class.getPackage());
		packages.add(NodeDefinitionFactory.class.getPackage());
		packages.add(EisPool.class.getPackage());
		packages.add(EISInteractionSpec.class.getPackage());
		Package[] result = new Package[packages.size()];
		packages.toArray(result);
		war.addPackages(true, result);
		war.addManifestResource(HORNETQ_JMS_DEPLOYMENT_CONFIG);
		return war;
	}

	static public List<Package> getTestPackages() throws IOException, ClassNotFoundException {
		List<Package> packages = new ArrayList<Package>();
		packages.add(NodeDefinition.class.getPackage());
		packages.add(Department.class.getPackage());
		packages.add(Stdlib.class.getPackage());
		packages.add(SimpleSyncWebIntegrationTest.class.getPackage());
		packages.add(SimpleAsyncWebIntegrationTest.class.getPackage());
		packages.add(JbpmWebTest.class.getPackage());
		packages.add(JbpmKnowledgeBase.class.getPackage());
		packages.add(ReflectHelper.classForName("org.nakeduml.jbpmtestintegration.util.hibernate.adaptor.package-info").getPackage());
		return packages;
	}

}