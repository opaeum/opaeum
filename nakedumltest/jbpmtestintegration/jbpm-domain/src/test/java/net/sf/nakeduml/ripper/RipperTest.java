package net.sf.nakeduml.ripper;

import jbpm.jbpm.Application;
import jbpm.jbpm.rip.network.RipProcess;
import jbpm.jbpm.rip.network.RipProcessState;
import jbpm.jbpm.rip.network.ripprocess.RipActivity;
import jbpm.jbpm.rip.network.ripprocess.RipActivityState;
import net.sf.nakeduml.jbpm.test.BaseTest;

import org.junit.Assert;
import org.junit.Test;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.ISignalDispatcher;

public class RipperTest extends BaseTest {

	@Test
	public void testRipProcess() {
		Application app = RipperInitData.createTestData();
		RipProcess ripProcess = app.getNetwork().iterator().next().RipProcess();
		ripProcess.execute();
		Assert.assertFalse(ripProcess.isStepActive(RipProcessState.ACTIVITYFINALNODE1));
		Assert.assertTrue(ripProcess.isStepActive(RipProcessState.CALLRIPACTIVITY));
		for (RipActivity ripActivity : ripProcess.getRipActivity()) {
			Assert.assertTrue(ripActivity.isStepActive(RipActivityState.NODEDEFINITIONTORIPSIGNAL));
		}
		Environment.getInstance().getComponent(ISignalDispatcher.class).deliverAllPendingSignals();
		for (RipActivity ripActivity : ripProcess.getRipActivity()) {
			Assert.assertTrue(ripActivity.isStepActive(RipActivityState.RIPACTIVITYACTIVITYFINALNODE1));
		}
		Assert.assertTrue(ripProcess.isStepActive(RipProcessState.ACTIVITYFINALNODE1));
	}

}
