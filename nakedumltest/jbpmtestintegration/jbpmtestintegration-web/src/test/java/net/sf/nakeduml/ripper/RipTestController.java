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
import jbpm.jbpm.rip.network.RipProcess;
import jbpm.jbpm.rip.network.RipProcessState;
import jbpm.jbpm.rip.network.ripprocess.RipActivity;
import jbpm.jbpm.rip.network.ripprocess.RipActivityState;

import org.hibernate.Session;

public class RipTestController {

	@Inject
	Session session;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Application createTestData() {
		Application application = new Application();
		application.setName("cmApplication");
		Network network = new Network(application);
		network.setName("Botswana");
		NetworkSoftwareVersion networkSoftwareVersion = new NetworkSoftwareVersion(network);
		networkSoftwareVersion.setName("BotswanaR12");
		networkSoftwareVersion.setSoftwareVersion(SoftwareVersion.R12);
		NodeDefinition nodeDefinition = new NodeDefinition(networkSoftwareVersion);
		nodeDefinition.setName("Botswana_BSC1");
		nodeDefinition.setActive(true);
		nodeDefinition.setNodeType(NodeType.BSC);
		SshTunnelSpec sshTunnelSpec = new SshTunnelSpec(network);
		sshTunnelSpec.setName("Botswana_machine1");
		nodeDefinition.setSshTunnelSpec(sshTunnelSpec);
		MMLCommand mmlCommand = new MMLCommand(network);
		mmlCommand.setSoftwareVersion(SoftwareVersion.R12);
		session.persist(application);
		session.flush();
		return application;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void testRipNetwork(Network network) {
		Network networkToRip = (Network) session.get(Network.class, network.getId());
		RipProcess ripProcess = networkToRip.RipProcess();
		ripProcess.execute();
		session.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean assertProcessCompleted(Network network) {
		session.clear();
		Network networkRipped = (Network) session.get(Network.class, network.getId());
		for (RipProcess ripProcess : networkRipped.getRipProcess()) {
			for (RipActivity ripActivity : ripProcess.getRipActivity()) {
				if (!ripActivity.isStepActive(RipActivityState.RIPACTIVITYACTIVITYFINALNODE1)) {
					return false;
				}
			}
			if (!ripProcess.isStepActive(RipProcessState.ACTIVITYFINALNODE1)) {
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

}
