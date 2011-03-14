package jbpm.jbpm.nodedefinition.rip.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jbpm.jbpm.Application;
import jbpm.jbpm.nodedefinition.EISConnection;
import jbpm.jbpm.nodedefinition.MockNodeDefinitionFactory;
import jbpm.jbpm.nodedefinition.interaction.EISInteractionSpec;
import jbpm.jbpm.nodedefinition.pool.EisPool;
import jbpm.jbpm.nodedefinition.pool.jmx.EisPoolService;
import jbpm.jbpm.rip.MMLCommand;
import jbpm.jbpm.rip.Network;
import jbpm.jbpm.rip.NetworkSoftwareVersion;
import jbpm.jbpm.rip.NodeDefinition;
import jbpm.jbpm.rip.RipHelper;

import org.jboss.seam.scheduling.util.WebBeansManagerUtils;
import org.junit.Assert;
import org.junit.Test;
import org.nakeduml.test.adaptor.AbstractCDITest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RipHelperWithCDITest extends AbstractCDITest {

	private Logger log = LoggerFactory.getLogger(RipHelperWithCDITest.class);

	@Override
	protected List<Class<? extends Object>> getAdditionalWebBeans() {
		List<Class<? extends Object>> list = new ArrayList<Class<? extends Object>>(1);
		list.add(RipHelperWithCDITest.class);
		list.addAll(getClasses(EISConnection.class.getPackage().getName()));
		list.addAll(getClasses(EISInteractionSpec.class.getPackage().getName()));
		list.addAll(getClasses(EisPool.class.getPackage().getName()));
		list.addAll(getClasses(EisPoolService.class.getPackage().getName()));
		list.addAll(getClasses(MockNodeDefinitionFactory.class.getPackage().getName()));
		return list;
	}

	protected Collection<URL> getBeansXmlFiles() {
		URL u = Thread.currentThread().getContextClassLoader().getResource("META-INF/beans.xml");
		return Arrays.asList(u);
	}

	@Test
	public void testRipping() {
		log.info("Testing shedule observer receiving events");
		RipHelperWithCDITest ripHelperTestBean = WebBeansManagerUtils.getInstanceByType(manager, RipHelperWithCDITest.class);
		Assert.assertNotNull(ripHelperTestBean);
		RipHelper ripHelper = WebBeansManagerUtils.getInstanceByType(manager, RipHelper.class);
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
	}

}
