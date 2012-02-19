package org.activitytest.signaltest;

import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import org.junit.Test;
import org.nakeduml.runtime.domain.TinkerClassifierBehaviorExecutorService;
import org.nakeduml.runtime.domain.activity.ActivityNode;
import org.opaeum.signaltest.Application;
import org.opaeum.signaltest.Window;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class CallTest extends BaseLocalDbTest {

	@Test
	public void testCallOperation() throws InterruptedException, ExecutionException {
		db.startTransaction();
		Application app = new Application(true);
		app.setName("application1");
		Window window = new Window(app);
		window.setName("window1");
		db.stopTransaction(Conclusion.SUCCESS);

		db.startTransaction();
		window.open();
		db.stopTransaction(Conclusion.SUCCESS);
		
		//Take 2, one for sending and one for receiving
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		
		ActivityNode openWindowAction = window.getClassifierBehavior().getNodeForName("OpenWindowAction");
		Assert.assertEquals(1, openWindowAction.getNodeStat().getExecuteCount());

		db.startTransaction();
		window.open();
		db.stopTransaction(Conclusion.SUCCESS);
		
		//Take 2, one for sending and one for receiving
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		
		openWindowAction = window.getClassifierBehavior().getNodeForName("OpenWindowAction");
		Assert.assertEquals(2, openWindowAction.getNodeStat().getExecuteCount());

	}
}
