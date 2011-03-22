package net.sf.nakeduml.ripper;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.rip.MMLCommand;
import jbpm.jbpm.rip.Network;
import jbpm.jbpm.rip.NetworkSoftwareVersion;
import jbpm.jbpm.rip.NodeDefinition;
import jbpm.jbpm.rip.NodeType;
import jbpm.jbpm.rip.SoftwareVersion;
import jbpm.jbpm.rip.SshTunnelSpec;
import jbpm.jbpm.rip.networksoftwareversion.RipProcess;
import jbpm.jbpm.rip.networksoftwareversion.RipProcessState;
import jbpm.jbpm.rip.networksoftwareversion.ripprocess.RipActivity;
import jbpm.jbpm.rip.networksoftwareversion.ripprocess.RipActivityState;

import org.hibernate.Session;

public class RipTestController {

	@Inject
	Session session;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void testRipNetwork(NetworkSoftwareVersion networkSoftwareVersion) {
		NetworkSoftwareVersion networkSoftwareVersionToRip = (NetworkSoftwareVersion) session.get(NetworkSoftwareVersion.class, networkSoftwareVersion.getId());
		RipProcess ripProcess = networkSoftwareVersionToRip.RipProcess();
		ripProcess.execute();
		session.flush();
	}

	public boolean assertProcessCompleted(NetworkSoftwareVersion networkSoftwareVersion) {
		session.clear();
		NetworkSoftwareVersion networkSoftwareVersionToRip = (NetworkSoftwareVersion) session.get(NetworkSoftwareVersion.class, networkSoftwareVersion.getId());
		for (RipProcess ripProcess : networkSoftwareVersionToRip.getRipProcess()) {
			for (RipActivity ripActivity : ripProcess.getRipActivity()) {
				if (!ripActivity.isStepActive(RipActivityState.RIPPEDSUCCESSFULLY)) {
					return false;
				}
			}
			if (!ripProcess.isStepActive(RipProcessState.ACTIVITYFINALNODE1)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean assertProcessCompletedWithFailure(NetworkSoftwareVersion networkSoftwareVersion) {
		session.clear();
		NetworkSoftwareVersion networkSoftwareVersionToRip = (NetworkSoftwareVersion) session.get(NetworkSoftwareVersion.class, networkSoftwareVersion.getId());
		for (RipProcess ripProcess : networkSoftwareVersionToRip.getRipProcess()) {
			if (ripProcess.getLastSuccesfulRip()!=null && ripProcess.getLastUnsuccessfulRip()==null) {
				return false;
			}
		}
		return true;
	}	

	public boolean assertProcessCompletedWithSuccess(NetworkSoftwareVersion networkSoftwareVersion) {
		session.clear();
		NetworkSoftwareVersion networkSoftwareVersionToRip = (NetworkSoftwareVersion) session.get(NetworkSoftwareVersion.class, networkSoftwareVersion.getId());
		for (RipProcess ripProcess : networkSoftwareVersionToRip.getRipProcess()) {
			if (ripProcess.getLastUnsuccessfulRip()!=null && ripProcess.getLastSuccesfulRip()==null) {
				return false;
			}
		}
		return true;
	}	

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean isStepActive(RipProcess ripProcess, RipProcessState ripProcessState) {
		return ripProcess.isStepActive(ripProcessState);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean isStepActive(RipActivity ripActivity, RipActivityState ripActivityState) {
		return ripActivity.isStepActive(ripActivityState);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Application createTestData() {
		Application application = createApplication();
		Network network = createNetwork(application);
		NetworkSoftwareVersion networkSoftwareVersion = createNetworkSoftwareVersion(network);
		NodeDefinition nodeDefinition = createNodeDefinition(networkSoftwareVersion);
		SshTunnelSpec sshTunnelSpec = createSshTunnel(network);
		nodeDefinition.setSshTunnelSpec(sshTunnelSpec);
		createMMLCommand(network);
		session.persist(application);
		session.flush();		
		return application;
	}

	private  Application createBadTestData() {
		Application application = createApplication();
		Network network = createNetwork(application);
		NetworkSoftwareVersion networkSoftwareVersion = createNetworkSoftwareVersion(network);
		NodeDefinition nodeDefinition = createNodeDefinition(networkSoftwareVersion);
		SshTunnelSpec sshTunnelSpec = createSshTunnel(network);
		sshTunnelSpec.setHost("x.x.x.x");
		nodeDefinition.setSshTunnelSpec(sshTunnelSpec);
		createMMLCommand(network);
		session.persist(application);
		session.flush();		
		return application;
	}

	private void createMMLCommand(Network network) {
		MMLCommand mmlCommand = new MMLCommand(network);
		mmlCommand.setSoftwareVersion(SoftwareVersion.R12);
		mmlCommand.setCommand("RLCP");
		mmlCommand.setSuffix(":CELL=ALL;");
	}

	private SshTunnelSpec createSshTunnel(Network network) {
		SshTunnelSpec sshTunnelSpec = new SshTunnelSpec(network);
		sshTunnelSpec.setName("Botswana_machine1");
		sshTunnelSpec.setHost("1.bw.mtn");
		sshTunnelSpec.setUsername("rorotika");
		sshTunnelSpec.setPassword("ror0t1ka1");
		sshTunnelSpec.setActive(true);
		sshTunnelSpec.setLport(7777);
		sshTunnelSpec.setTimeout(20000);
		return sshTunnelSpec;
	}

	private NodeDefinition createNodeDefinition(NetworkSoftwareVersion networkSoftwareVersion) {
		NodeDefinition nodeDefinition = new NodeDefinition(networkSoftwareVersion);
		nodeDefinition.setName("FTMSS01");
		nodeDefinition.setActive(true);
		nodeDefinition.setHost("10.32.41.6");
		nodeDefinition.setPort(23);
		nodeDefinition.setSoTimeout(20000);
		nodeDefinition.setTimeout(10000);
		nodeDefinition.setUsername("rorotika");
		nodeDefinition.setPassword("Ror0t1ka321");
		nodeDefinition.setNodeType(NodeType.BSC);
		return nodeDefinition;
	}

	private NetworkSoftwareVersion createNetworkSoftwareVersion(Network network) {
		NetworkSoftwareVersion networkSoftwareVersion = new NetworkSoftwareVersion(network);
		networkSoftwareVersion.setName("BotswanaR12");
		networkSoftwareVersion.setSoftwareVersion(SoftwareVersion.R12);
		return networkSoftwareVersion;
	}

	private Network createNetwork(Application application) {
		Network network = new Network(application);
		network.setName("Botswana");
		return network;
	}

	private Application createApplication() {
		Application application = new Application();
		application.setName("cmApplication");
		return application;
	}	

}
