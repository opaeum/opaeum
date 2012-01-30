package org.activitytest.signaltest;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Test;
import org.nakeduml.runtime.domain.TinkerClassifierBehaviorExecutorService;
import org.nakeduml.runtime.domain.activity.AbstractNode;
import org.nakeduml.runtime.domain.activity.NodeStatus;
import org.opaeum.signaltest.Application;
import org.opaeum.signaltest.OpenWindowSignal;
import org.opaeum.signaltest.TemperatureController;
import org.opaeum.signaltest.Window;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class SignalTest extends BaseLocalDbTest {

	@Test
	public void testSendSignalAction() throws InterruptedException, ExecutionException {
		db.startTransaction();
		Application app = new Application(true);
		app.setName("application1");
		Window window = new Window(app);
		window.setName("window1");
		TemperatureController temperatureController = new TemperatureController(app);
		temperatureController.setName("temperatureController");
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		temperatureController.getClassifierBehavior().execute();
		db.stopTransaction(Conclusion.SUCCESS);
		TinkerClassifierBehaviorExecutorService.INSTANCE.shutdown();
		TinkerClassifierBehaviorExecutorService.INSTANCE.waitForCompletion(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testAcceptEventAction() throws InterruptedException, ExecutionException {
		db.startTransaction();
		Application app = new Application(true);
		app.setName("application1");
		Window window = new Window(app);
		window.setName("window1");
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		OpenWindowSignal openWindowSignal = new OpenWindowSignal(true);
		window.receiveSignal(openWindowSignal);
		db.stopTransaction(Conclusion.SUCCESS);
		Future<Boolean> future = TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		future.get();

		Set<AbstractNode> activeNodes = window.getClassifierBehavior().getActiveNodes();
		Assert.assertEquals(0, activeNodes.size());
		
		AbstractNode acceptEventActionNode = window.getClassifierBehavior().getNodeForName("OpenWindowAcceptEventAction");
		Assert.assertEquals(NodeStatus.ENABLED, acceptEventActionNode.getNodeStatus());
		Set<AbstractNode> enabledNodes = window.getClassifierBehavior().getEnabledNodes();
		Assert.assertEquals(1, enabledNodes.size());
		
		AbstractNode finalNode = window.getClassifierBehavior().getNodeForName("FlowFinalNode1");
		Assert.assertEquals(1, finalNode.getNodeStat().getExecuteCount());

		db.startTransaction();
		openWindowSignal = new OpenWindowSignal(true);
		window.receiveSignal(openWindowSignal);
		db.stopTransaction(Conclusion.SUCCESS);
		future = TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		future.get();
		acceptEventActionNode = window.getClassifierBehavior().getNodeForName("OpenWindowAcceptEventAction");
		Assert.assertEquals(NodeStatus.ENABLED, acceptEventActionNode.getNodeStatus());
		finalNode = window.getClassifierBehavior().getNodeForName("FlowFinalNode1");
		Assert.assertEquals(2, finalNode.getNodeStat().getExecuteCount());
	}
	
}
