package jbpm.jbpm.nodedefinition.rip.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jbpm.jbpm.Application;
import jbpm.jbpm.nodedefinition.NodeDefinitionFactory;
import jbpm.jbpm.nodedefinition.RipHelperImpl;
import jbpm.jbpm.nodedefinition.mock.MockNodeDefinitionFactory;
import jbpm.jbpm.rip.MMLCommand;
import jbpm.jbpm.rip.Network;
import jbpm.jbpm.rip.NetworkSoftwareVersion;
import jbpm.jbpm.rip.NodeDefinition;
import jbpm.jbpm.rip.RipHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.cdi.test.CdiTestEnvironment;
import org.nakeduml.environment.cdi.test.CdiTestSeamTransaction;
import org.nakeduml.environment.cdi.test.MockTransactionSession;
import org.nakeduml.runtime.domain.IntrospectionUtil;

public class RipHelperWithCdiTest {
	
	@Before
	public void beforeClass() throws Throwable {
		CdiTestEnvironment.getInstance().initializeDeployment(Arrays.asList("test-beans.xml"), getAdditionalWebBeans());
	}
	
	protected List<Class<? extends Object>> getAdditionalWebBeans() {
		Set<Class<? extends Object>> list = new HashSet<Class<? extends Object>>(1);
		list.addAll(IntrospectionUtil.getClasses(MockNodeDefinitionFactory.class.getPackage().getName()));
		list.addAll(IntrospectionUtil.getClasses(MockTransactionSession.class.getPackage().getName()));
		list.add(RipHelperWithCdiTest.class);
		list.add(RipHelperImpl.class);
		list.remove(NodeDefinitionFactory.class);
		list.add(CdiTestSeamTransaction.class);
		return new ArrayList<Class<? extends Object>>(list);		
	}

	protected Collection<URL> getBeansXmlFiles() {
		URL u = Thread.currentThread().getContextClassLoader().getResource("test-beans.xml");
		return Arrays.asList(u);
	}

	@Test
	public void testRipping() {
		CdiTestEnvironment.getInstance().beforeRequest(this);
		RipHelper ripHelper = Environment.getInstance().getComponent(RipHelper.class);
		Assert.assertNotNull(ripHelper);
		
		Application application = RipHelperTest.createTestData();
		Set<MMLCommand> mmlCommands = new HashSet<MMLCommand>();
		for (Network network : application.getNetwork()) {
			mmlCommands.addAll(network.getMMLCommand());
			for (NetworkSoftwareVersion networkSoftwareVersion : network.getNetworkSoftwareVersion()) {
				for (NodeDefinition nodeDefinition : networkSoftwareVersion.getNodeDefinition()) {
					ripHelper.rip(nodeDefinition, mmlCommands);
				}
			}
			mmlCommands.clear();
		}

		for (Network network : application.getNetwork()) {
			for (NetworkSoftwareVersion networkSoftwareVersion : network.getNetworkSoftwareVersion()) {
				for (NodeDefinition nodedDefinition : networkSoftwareVersion.getNodeDefinition()) {
					Assert.assertNotNull(nodedDefinition.getLastSuccesfulRip());
					Assert.assertNull(nodedDefinition.getLastUnsuccesfulRip());
				}
			}
		}
		CdiTestEnvironment.getInstance().beforeRequest(this);
	}

}
