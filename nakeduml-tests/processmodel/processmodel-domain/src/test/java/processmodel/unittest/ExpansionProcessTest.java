package processmodel.unittest;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.drools.runtime.process.NodeInstance;
import org.jbpm.workflow.instance.node.CompositeNodeInstance;
import org.jbpm.workflow.instance.node.SubProcessNodeInstance;
import org.testng.annotations.Test;

import processmodel.processes.Target;
import processmodel.processes.target.ProcessExpansionActivity;
import processmodel.processes.target.ProcessExpansionActivityState;
import processmodel.processes.target.ShortProcess;
import processmodel.processes.target.ShortProcessState;

public class ExpansionProcessTest {
	@Test
	public void testIt() throws Exception {
		Target target = new Target();
		target.setName("asdf");
		ProcessExpansionActivity process = target.ProcessExpansionActivity();
		process.execute();
		assert process.isStepActive(ProcessExpansionActivityState.WAITFOREVENTOPERATION);
		process.eventOperationOnProcessExpansionActivity(new HashSet<String>(Arrays.asList("1", "2", "3")));
		assert process.isStepActive(ProcessExpansionActivityState.FOREACHSTRING);
		Collection<NodeInstance> nodeInstances = process.getProcessInstance().getNodeInstances();
		Set<SubProcessNodeInstance> subProcessNodeInstances = new HashSet<SubProcessNodeInstance>();
		getSubProcessNodeInstances(nodeInstances, subProcessNodeInstances);
		assert subProcessNodeInstances.size()==3;
		for (SubProcessNodeInstance spni : subProcessNodeInstances) {
			ShortProcess sp= (ShortProcess) spni.getVariable("callShortProcess");
			assert sp.isStepActive(ShortProcessState.ACCEPTEVENTACTION1);
			sp.onEmptySignal();
			assert sp.isStepActive(ShortProcessState.ACTIVITYFINALNODE1);
		}
		assert process.getInnermostNonParallelStep()==ProcessExpansionActivityState.ACTIVITYFINALNODE1;
		assert process.isStepActive(ProcessExpansionActivityState.ACTIVITYFINALNODE1);
	}

	private void getSubProcessNodeInstances(Collection<NodeInstance> nodeInstances, Set<SubProcessNodeInstance> subProcessNodeInstances) {
		for (NodeInstance nodeInstance : nodeInstances) {
			if (nodeInstance instanceof CompositeNodeInstance) {
				getSubProcessNodeInstances(((CompositeNodeInstance)nodeInstance).getNodeInstances(), subProcessNodeInstances);
			}else if(nodeInstance instanceof SubProcessNodeInstance){
				subProcessNodeInstances.add((SubProcessNodeInstance) nodeInstance);
			}
		}
	}
}
