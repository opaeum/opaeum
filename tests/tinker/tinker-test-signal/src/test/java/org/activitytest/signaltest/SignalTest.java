package org.activitytest.signaltest;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import org.junit.Test;
import org.nakeduml.runtime.domain.TinkerClassifierBehaviorExecutorService;
import org.nakeduml.runtime.domain.activity.ActivityNode;
import org.nakeduml.runtime.domain.activity.NodeStatus;
import org.nakeduml.runtime.domain.activity.Token;
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
		
		//Take 2, one for sending and one for receiving
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		
		ActivityNode<?> openWindowAction = window.getClassifierBehavior().getNodeForName("OpenWindowAction");
		Assert.assertEquals(1, openWindowAction.getNodeStat().getExecuteCount());
		
		db.startTransaction();
		temperatureController.getClassifierBehavior().execute();
		db.stopTransaction(Conclusion.SUCCESS);

		//Take 2, one for sending and one for receiving
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();

		Window w = new Window(window.getVertex());
		openWindowAction = w.getClassifierBehavior().getNodeForName("OpenWindowAction");
		Assert.assertEquals(2, openWindowAction.getNodeStat().getExecuteCount());
		
		db.startTransaction();
		temperatureController.getClassifierBehavior().execute();
		db.stopTransaction(Conclusion.SUCCESS);

		//Take 2, one for sending and one for receiving
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();

		w = new Window(window.getVertex());
		openWindowAction = w.getClassifierBehavior().getNodeForName("OpenWindowAction");
		Assert.assertEquals(3, openWindowAction.getNodeStat().getExecuteCount());
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
		
		Boolean future = TinkerClassifierBehaviorExecutorService.INSTANCE.take();

		Set<ActivityNode<? extends Token>> activeNodes = window.getClassifierBehavior().getActiveNodes();
		Assert.assertEquals(0, activeNodes.size());
		
		ActivityNode<? extends Token> acceptEventActionNode = window.getClassifierBehavior().getNodeForName("OpenWindowAcceptEventAction");
		Assert.assertEquals(NodeStatus.ENABLED, acceptEventActionNode.getNodeStatus());
		Set<ActivityNode<? extends Token>> enabledNodes = window.getClassifierBehavior().getEnabledNodes();
		Assert.assertEquals(1, enabledNodes.size());
		
		ActivityNode<? extends Token> finalNode = window.getClassifierBehavior().getNodeForName("FlowFinalNode1");
		Assert.assertEquals(1, finalNode.getNodeStat().getExecuteCount());

		db.startTransaction();
		openWindowSignal = new OpenWindowSignal(true);
		window.receiveSignal(openWindowSignal);
		db.stopTransaction(Conclusion.SUCCESS);
		future = TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		acceptEventActionNode = window.getClassifierBehavior().getNodeForName("OpenWindowAcceptEventAction");
		Assert.assertEquals(NodeStatus.ENABLED, acceptEventActionNode.getNodeStatus());
		finalNode = window.getClassifierBehavior().getNodeForName("FlowFinalNode1");
		Assert.assertEquals(2, finalNode.getNodeStat().getExecuteCount());
	}
	
}
