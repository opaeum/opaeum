package org.nakeduml.eventstest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nakeduml.events.eventstest.Context;
import org.nakeduml.events.eventstest.context.MessageEventConsumingProcess;
import org.nakeduml.events.eventstest.context.MessageEventConsumingProcessState;
import org.nakeduml.events.eventstest.context.MessageEventGeneratingProcess;
import org.nakeduml.events.eventstest.context.MessageEventGeneratingProcessState;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.jpa.StandaloneJpaEnvironment;
import org.nakeduml.runtime.persistence.UmtPersistence;

public class TestIt {

	private Context ctx;
	private MessageEventConsumingProcess mecp;
	private MessageEventGeneratingProcess megp;
	private UmtPersistence persistence;

	@Test
	public void testIt() throws InterruptedException {
		this.persistence = StandaloneJpaEnvironment.getInstance().getUmtPersistence();
		this.persistence.beginTransaction();
		this.ctx = new Context();
		persistence.persist(ctx);
		this.mecp = ctx.MessageEventConsumingProcess();
		mecp.execute();
		this.megp = ctx.MessageEventGeneratingProcess();
		megp.execute();
		Assert.assertTrue(megp.isStepActive(MessageEventGeneratingProcessState.WAITFORGOTOOPERATIONCALL));
		Assert.assertTrue(mecp.isStepActive(MessageEventConsumingProcessState.ACCEPTSIGNALACTION));
		waitForState(mecp, MessageEventConsumingProcessState.ACCEPTCALLACTION, 20000);
		megp.goToOperationCall();
		Assert.assertTrue(megp.isStepActive(MessageEventGeneratingProcessState.WAITFORGOTOOPERATIONCALL));
		Assert.assertTrue(mecp.isStepActive(MessageEventConsumingProcessState.ACCEPTCALLACTION));
		waitForState(mecp, MessageEventConsumingProcessState.ACTIVITYFINALNODE1, 20000);
		Assert.assertTrue(megp.isStepActive(MessageEventGeneratingProcessState.ACTIVITYFINALNODE1));
		Assert.assertTrue(mecp.isStepActive(MessageEventConsumingProcessState.ACTIVITYFINALNODE1));
	}

	private void waitForState(IProcessObject po, IProcessStep step, long timeout) throws InterruptedException {
		if (persistence.isActive()) {
			persistence.commitTransaction();
		}
		persistence.close();
		StandaloneJpaEnvironment.getInstance().reset();
		persistence=StandaloneJpaEnvironment.getInstance().getUmtPersistence();
		persistence.beginTransaction();
		IProcessObject reference = persistence.getReference(IntrospectionUtil.getOriginalClass(po), ((IPersistentObject) po).getId());
		if (reference.isStepActive(step)) {
			ctx=persistence.getReference(Context.class, ctx.getId());
			megp=persistence.getReference(MessageEventGeneratingProcess.class, megp.getId());
			mecp=persistence.getReference(MessageEventConsumingProcess.class, mecp.getId());
			return;
		} else if (timeout > 0) {
			Thread.sleep(100);
			waitForState(po, step, timeout - 100);
		} else {
			System.out.println(megp.getInnermostNonParallelStep());
			System.out.println(mecp.getInnermostNonParallelStep());
			throw new InterruptedException();
		}
	}
}
