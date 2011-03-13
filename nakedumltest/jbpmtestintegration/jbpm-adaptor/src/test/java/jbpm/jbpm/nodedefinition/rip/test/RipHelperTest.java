package jbpm.jbpm.nodedefinition.rip.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jbpm.jbpm.Application;
import jbpm.jbpm.nodedefinition.RipHelperImpl;
import jbpm.jbpm.nodedefinition.pool.EisPool;
import jbpm.jbpm.nodedefinition.pool.SshTunnelKeyedConnectionFactory;
import jbpm.jbpm.rip.MMLCommand;
import jbpm.jbpm.rip.Network;
import jbpm.jbpm.rip.NetworkSoftwareVersion;
import jbpm.jbpm.rip.NodeDefinition;
import jbpm.jbpm.rip.NodeType;
import jbpm.jbpm.rip.SoftwareVersion;
import jbpm.jbpm.rip.SshTunnelSpec;
import junit.framework.Assert;

import org.junit.Test;

public class RipHelperTest {

	@Test
	public void testRipPass() {
		Application application = createTestData();
		rip(application);
		for (Network network : application.getNetwork()) {
			for (NetworkSoftwareVersion networkSoftwareVersion : network.getNetworkSoftwareVersion()) {
				for (NodeDefinition nodedDefinition : networkSoftwareVersion.getNodeDefinition()) {
					Assert.assertNotNull(nodedDefinition.getLastSuccesfulRip());
					Assert.assertNull(nodedDefinition.getLastUnsuccesfulRip());
				}
			}
		}
	}

	@Test
	public void testRipFail() {
		Application application = createBadTestData();
		rip(application);
		for (Network network : application.getNetwork()) {
			for (NetworkSoftwareVersion networkSoftwareVersion : network.getNetworkSoftwareVersion()) {
				for (NodeDefinition nodedDefinition : networkSoftwareVersion.getNodeDefinition()) {
					Assert.assertNull(nodedDefinition.getLastSuccesfulRip());
					Assert.assertNotNull(nodedDefinition.getLastUnsuccesfulRip());
				}
			}
		}
	}

	private void rip(Application application) {
		List<NodeDefinition> nodeDefinitions = new ArrayList<NodeDefinition>();
		for (Network network : application.getNetwork()) {
			for (NetworkSoftwareVersion networkSoftwareVersion : network.getNetworkSoftwareVersion()) {
				nodeDefinitions.addAll(networkSoftwareVersion.getNodeDefinition());
			}
		}
		Set<MMLCommand> mmlCommands = new HashSet<MMLCommand>();
		for (Network network : application.getNetwork()) {
			mmlCommands.addAll(network.getMMLCommand());
		}
		SshTunnelKeyedConnectionFactory sshTunnelKeyedConnectionFactory = new SshTunnelKeyedConnectionFactory();
		EisPool eisPool = new EisPool(nodeDefinitions, sshTunnelKeyedConnectionFactory);
		RipHelperImpl ripHelperImpl = new RipHelperImpl();
		ripHelperImpl.setPool(eisPool.getPool());
		for (NodeDefinition nodeDefinition : nodeDefinitions) {
			ripHelperImpl.rip(nodeDefinition, mmlCommands);
		}
	}

	public static Application createTestData() {
		Application application = createApplication();
		Network network = createNetwork(application);
		NetworkSoftwareVersion networkSoftwareVersion = createNetworkSoftwareVersion(network);
		NodeDefinition nodeDefinition = createNodeDefinition(networkSoftwareVersion);
		SshTunnelSpec sshTunnelSpec = createSshTunnel(network);
		nodeDefinition.setSshTunnelSpec(sshTunnelSpec);
		createMMLCommand(network);
		return application;
	}

	private static Application createBadTestData() {
		Application application = createApplication();
		Network network = createNetwork(application);
		NetworkSoftwareVersion networkSoftwareVersion = createNetworkSoftwareVersion(network);
		NodeDefinition nodeDefinition = createNodeDefinition(networkSoftwareVersion);
		SshTunnelSpec sshTunnelSpec = createSshTunnel(network);
		sshTunnelSpec.setHost("x.x.x.x");
		nodeDefinition.setSshTunnelSpec(sshTunnelSpec);
		createMMLCommand(network);
		return application;
	}

	private static void createMMLCommand(Network network) {
		MMLCommand mmlCommand = new MMLCommand(network);
		mmlCommand.setSoftwareVersion(SoftwareVersion.R12);
		mmlCommand.setCommand("RLCP");
		mmlCommand.setSuffix(":CELL=ALL;");
	}

	private static SshTunnelSpec createSshTunnel(Network network) {
		SshTunnelSpec sshTunnelSpec = new SshTunnelSpec(network);
		sshTunnelSpec.setName("Botswana_machine1");
		sshTunnelSpec.setHost("1.bw.mtn");
		sshTunnelSpec.setUsername("rorotika");
		sshTunnelSpec.setPassword("ror0t1ka1");
		sshTunnelSpec.setActive(true);
		sshTunnelSpec.setId(100L);
		sshTunnelSpec.setLport(7777);
		sshTunnelSpec.setTimeout(20000);
		return sshTunnelSpec;
	}

	private static NodeDefinition createNodeDefinition(NetworkSoftwareVersion networkSoftwareVersion) {
		NodeDefinition nodeDefinition = new NodeDefinition(networkSoftwareVersion);
		nodeDefinition.setName("FTMSS01");
		nodeDefinition.setActive(true);
		nodeDefinition.setId(1000L);
		nodeDefinition.setHost("10.32.41.6");
		nodeDefinition.setPort(23);
		nodeDefinition.setSoTimeout(20000);
		nodeDefinition.setTimeout(10000);
		nodeDefinition.setUsername("rorotika");
		nodeDefinition.setPassword("Ror0t1ka321");
		nodeDefinition.setNodeType(NodeType.BSC);
		return nodeDefinition;
	}

	private static NetworkSoftwareVersion createNetworkSoftwareVersion(Network network) {
		NetworkSoftwareVersion networkSoftwareVersion = new NetworkSoftwareVersion(network);
		networkSoftwareVersion.setName("BotswanaR12");
		networkSoftwareVersion.setSoftwareVersion(SoftwareVersion.R12);
		return networkSoftwareVersion;
	}

	private static Network createNetwork(Application application) {
		Network network = new Network(application);
		network.setName("Botswana");
		return network;
	}

	private static Application createApplication() {
		Application application = new Application();
		application.setName("cmApplication");
		return application;
	}

}
