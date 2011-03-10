package net.sf.nakeduml.ripper;

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

import org.junit.Assert;
import org.junit.Test;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.ISignalDispatcher;

public class RipperTest {

	@Test
	public void testRipProcess() {
		Application application = new Application();
		application.setName("cmApplication");
		Network network = new Network();
		network.setName("Botswana");
		NetworkSoftwareVersion networkSoftwareVersion = new NetworkSoftwareVersion(network);
		networkSoftwareVersion.setName("BotswanaR12");
		networkSoftwareVersion.setSoftwareVersion(SoftwareVersion.R12);
		NodeDefinition nodeDefinition = new NodeDefinition(network);
		nodeDefinition.setName("Botswana_BSC1");
		nodeDefinition.setSoftwareVersion(SoftwareVersion.R12);
		nodeDefinition.setActive(true);
		SshTunnelSpec sshTunnelSpec = new SshTunnelSpec(network);
		sshTunnelSpec.setName("Botswana_machine1");
		nodeDefinition.setSshTunnelSpec(sshTunnelSpec);
		MMLCommand mmlCommand = new MMLCommand(network);
		mmlCommand.setSoftwareVersion(SoftwareVersion.R12);
		mmlCommand.setNodeType(NodeType.BSC);
		RipProcess ripProcess = network.RipProcess();
		ripProcess.execute();
		Assert.assertFalse(ripProcess.isStepActive(RipProcessState.ACTIVITYFINALNODE1));
		Assert.assertTrue(ripProcess.isStepActive(RipProcessState.CALLRIPACTIVITY));
		for (RipActivity ripActivity : ripProcess.getRipActivity()) {
			Assert.assertTrue(ripActivity.isStepActive(RipActivityState.MMLCOMMANDTORIPSENDSIGNALACTION));
		}
		Environment.getInstance().getComponent(ISignalDispatcher.class).deliverAllPendingSignals();
		for (RipActivity ripActivity : ripProcess.getRipActivity()) {
			Assert.assertTrue(ripActivity.isStepActive(RipActivityState.RIPACTIVITYACTIVITYFINALNODE1));
		}
		Assert.assertTrue(ripProcess.isStepActive(RipProcessState.ACTIVITYFINALNODE1));
	}
}
