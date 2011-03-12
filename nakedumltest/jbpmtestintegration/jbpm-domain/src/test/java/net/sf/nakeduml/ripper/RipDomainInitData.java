package net.sf.nakeduml.ripper;

import jbpm.jbpm.Application;
import jbpm.jbpm.rip.MMLCommand;
import jbpm.jbpm.rip.Network;
import jbpm.jbpm.rip.NetworkSoftwareVersion;
import jbpm.jbpm.rip.NodeDefinition;
import jbpm.jbpm.rip.NodeType;
import jbpm.jbpm.rip.SoftwareVersion;
import jbpm.jbpm.rip.SshTunnelSpec;

public class RipDomainInitData {
	public static Application createTestData() {
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
		mmlCommand.setCommand("command1");
		return application;
	}
}
