package net.sf.nakeduml.ripper;

import jbpm.jbpm.Application;
import jbpm.jbpm.rip.RipHelper;
import jbpm.jbpm.rip.networksoftwareversion.RipProcess;
import jbpm.jbpm.rip.networksoftwareversion.RipProcessState;
import jbpm.jbpm.rip.networksoftwareversion.ripprocess.RipActivity;
import jbpm.jbpm.rip.networksoftwareversion.ripprocess.RipActivityState;
import net.sf.nakeduml.jbpm.test.BaseTest;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.environment.domain.DomainEnvironment;

public class RipperTest extends BaseTest {

	@Test
	public void testRipProcess() {
		Application app = RipperInitData.createTestData();
		
		
		Mockery mockery = new Mockery();
		final RipHelper ripHelper = mockery.mock(RipHelper.class);
		mockery.checking(new Expectations() {{
		    atLeast(1).of(ripHelper);
		}});
		
		DomainEnvironment domainEnvironment = (DomainEnvironment) Environment.getInstance();
		domainEnvironment.mockComponent(RipHelper.class, ripHelper);
		
		RipProcess ripProcess = app.getNetwork().iterator().next().getNetworkSoftwareVersion().iterator().next().RipProcess();
		ripProcess.execute();
		Assert.assertFalse(ripProcess.isStepActive(RipProcessState.ACTIVITYFINALNODE1));
		Assert.assertTrue(ripProcess.isStepActive(RipProcessState.NODEDEFINITIONEXPANSIONREGION));
		for (RipActivity ripActivity : ripProcess.getRipActivity()) {
			Assert.assertTrue(ripActivity.isStepActive(RipActivityState.NODEDEFINITIONTORIPSIGNAL));
		}
		Environment.getInstance().getComponent(ISignalDispatcher.class).deliverAllPendingSignals();
		for (RipActivity ripActivity : ripProcess.getRipActivity()) {
			Assert.assertTrue(ripActivity.isStepActive(RipActivityState.RIPPEDSUCCESSFULLY));
		}
		Assert.assertTrue(ripProcess.isStepActive(RipProcessState.ACTIVITYFINALNODE1));
	}

}
