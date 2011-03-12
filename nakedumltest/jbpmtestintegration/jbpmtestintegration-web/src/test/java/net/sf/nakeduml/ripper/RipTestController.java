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
	public void testRipNetwork(NetworkSoftwareVersion networkSoftwareVersion) {
		NetworkSoftwareVersion networkSoftwareVersionToRip = (NetworkSoftwareVersion) session.get(NetworkSoftwareVersion.class, networkSoftwareVersion.getId());
		RipProcess ripProcess = networkSoftwareVersionToRip.RipProcess();
		ripProcess.execute();
		session.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
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

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean isStepActive(RipProcess ripProcess, RipProcessState ripProcessState) {
		return ripProcess.isStepActive(ripProcessState);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean isStepActive(RipActivity ripActivity, RipActivityState ripActivityState) {
		return ripActivity.isStepActive(ripActivityState);
	}

}
