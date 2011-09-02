package org.nakeduml.eventstest;

import org.junit.Test;
import org.nakeduml.runtime.environment.Environment;
import org.nakeduml.runtime.persistence.UmtPersistence;
import org.nakeduml.testworkspace.eventstest.Context;
import org.nakeduml.testworkspace.eventstest.TheSignal;
import org.nakeduml.testworkspace.eventstest.TheSignalHandler;
import org.nakeduml.testworkspace.eventstest.context.MessageEventConsumingProcess;
import org.nakeduml.testworkspace.eventstest.context.MessageEventGeneratingProcess;

public class TestIt {
	@Test
	public void testIt() {
		UmtPersistence persistence = Environment.getInstance().getComponent(UmtPersistence.class);
		Context ctx  = new Context();
		persistence.beginTransaction();
		persistence.persist(ctx);
		MessageEventConsumingProcess mecp = ctx.MessageEventConsumingProcess();
		mecp.execute();
		System.out.println(mecp.getInnermostNonParallelStep());
		MessageEventGeneratingProcess megp = ctx.MessageEventGeneratingProcess();
		
		megp.execute();
		System.out.println(megp.getOutgoingEvents());
		System.out.println(megp.getInnermostNonParallelStep());
		persistence.commitTransaction();
		persistence.beginTransaction();
		System.out.println(mecp.getInnermostNonParallelStep());
		persistence.commitTransaction();
	}
}
