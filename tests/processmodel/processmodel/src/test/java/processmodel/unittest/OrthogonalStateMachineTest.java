package processmodel.unittest;

import java.util.Collection;

import org.drools.runtime.process.ProcessInstance;
import org.jbpm.workflow.instance.NodeInstance;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.testng.annotations.Test;

import processmodel.statemachines.OrthogonalStateMachine;

public class OrthogonalStateMachineTest {
	@Test
	public void test(){
		OrthogonalStateMachine process = new OrthogonalStateMachine();
		process.setId(12345l);
		process.execute();
		System.out.println(process.getInnermostNonParallelStep());
		assert process.getState1();
		assert process.getState2();
		assert process.getState1_1();
		assert process.getState1_2();
		process.orthogonalOperation();
		assert process.getState1();
		assert process.getState2();
		assert !process.getState1_1();
		assert process.getState1_2();
		process.orthogonalOperation();
		System.out.println(process.getProcessInstance().getState()==ProcessInstance.STATE_COMPLETED);
		assert process.getFinalInRegion1();
		assert process.getState2();
		assert !process.getState1_1();
		assert !process.getState1_2();
		process.orthogonalOperation();
		Collection<NodeInstanceImpl> nds = process.getNodeInstancesRecursively();
		for (NodeInstance ni : nds) {
			System.out.println(ni.getNode().getNodeContainer().getClass().getSimpleName() + "::" + ni.getNodeName());
		}
		assert process.getFinalInRegion2();
		assert process.getFinalInRegion1();
		
	}
}
