package net.sf.nakeduml.ripper;

import java.util.Set;

import jbpm.jbpm.Application;
import jbpm.jbpm.rip.MMLCommand;
import jbpm.jbpm.rip.NodeDefinition;
import jbpm.jbpm.rip.RipHelper;
import jbpm.jbpm.rip.networksoftwareversion.RipProcess;
import jbpm.jbpm.rip.networksoftwareversion.RipProcessState;
import jbpm.jbpm.rip.networksoftwareversion.ripprocess.RipActivity;
import jbpm.jbpm.rip.networksoftwareversion.ripprocess.RipActivityState;

import org.hamcrest.core.IsAnything;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.ISignalDispatcher;
import org.nakeduml.environment.domain.DomainEnvironment;

public class RipDomainTest {
	
	@Before
	public void beforeTest() {
		Environment.getInstance().reset();
	}
	
	@Test
	public void testRipProcessSuccess() {
		DomainEnvironment.getInstance().reset();
		Application app = RipDomainInitData.createTestData();

		Mockery mockery = new Mockery();
		final RipHelper ripHelper = mockery.mock(RipHelper.class);
		mockery.checking(new Expectations() {{
		    atLeast(1).of(ripHelper).rip(with(new IsAnything<NodeDefinition>()), with(new IsAnything<Set<MMLCommand>>()));
		    will(returnValue(Boolean.TRUE));
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
		System.out.println(ripProcess.getInnermostNonParallelStep());
		//IMPORTANT STEP:
		mockery.assertIsSatisfied();
		Assert.assertTrue(ripProcess.isStepActive(RipProcessState.ACTIVITYFINALNODE1));
		Assert.assertNotNull(ripProcess.getLastSuccesfulRip());
		Assert.assertNull(ripProcess.getLastUnsuccessfulRip());
	}
	
	@Test
	public void testRipProcessFailure() {
		Application app = RipDomainInitData.createTestData();

		Mockery mockery = new Mockery();
		final RipHelper ripHelper = mockery.mock(RipHelper.class);
		mockery.checking(new Expectations() {{
		    atLeast(1).of(ripHelper).rip(with(new IsAnything<NodeDefinition>()), with(new IsAnything<Set<MMLCommand>>()));
		    will(returnValue(Boolean.FALSE));
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
		System.out.println(ripProcess.getInnermostNonParallelStep());
		//IMPORTANT STEP:
		mockery.assertIsSatisfied();
		Assert.assertTrue(ripProcess.isStepActive(RipProcessState.ACTIVITYFINALNODE1));
		Assert.assertNull(ripProcess.getLastSuccesfulRip());
		Assert.assertNotNull(ripProcess.getLastUnsuccessfulRip());
	}
	
}
