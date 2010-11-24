package net.sf.nakeduml.jbpmstatemachine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.marshalling.impl.MarshallerReaderContext;
import org.drools.marshalling.impl.PersisterEnums;
import org.drools.runtime.process.WorkflowProcessInstance;
import org.jbpm.marshalling.impl.AbstractProcessInstanceMarshaller;
import org.jbpm.ruleflow.instance.RuleFlowProcessInstance;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.jbpm.workflow.instance.impl.WorkflowProcessInstanceImpl;
import org.jbpm.workflow.instance.node.CompositeContextNodeInstance;
import org.jbpm.workflow.instance.node.DynamicNodeInstance;
import org.jbpm.workflow.instance.node.EventNodeInstance;
import org.jbpm.workflow.instance.node.ForEachNodeInstance;
import org.jbpm.workflow.instance.node.HumanTaskNodeInstance;
import org.jbpm.workflow.instance.node.JoinInstance;
import org.jbpm.workflow.instance.node.MilestoneNodeInstance;
import org.jbpm.workflow.instance.node.RuleSetNodeInstance;
import org.jbpm.workflow.instance.node.SubProcessNodeInstance;
import org.jbpm.workflow.instance.node.TimerNodeInstance;
import org.jbpm.workflow.instance.node.WorkItemNodeInstance;

public class UmlProcessMarshaller extends AbstractProcessInstanceMarshaller {
	public UmlProcessMarshaller (){
		
	}
	protected WorkflowProcessInstanceImpl createProcessInstance() {
		return new RuleFlowProcessInstance();
	}

	protected NodeInstanceImpl readNodeInstanceContent(int nodeType, ObjectInputStream stream, MarshallerReaderContext context,
			WorkflowProcessInstance processInstance) throws IOException {
		NodeInstanceImpl nodeInstance = null;
		switch (nodeType) {
		case PersisterEnums.RULE_SET_NODE_INSTANCE:
			nodeInstance = new RuleSetNodeInstance();
			int nbTimerInstances = stream.readInt();
			if (nbTimerInstances > 0) {
				List<Long> timerInstances = new ArrayList<Long>();
				for (int i = 0; i < nbTimerInstances; i++) {
					timerInstances.add(stream.readLong());
				}
				((RuleSetNodeInstance) nodeInstance).internalSetTimerInstances(timerInstances);
			}
			break;
		case PersisterEnums.HUMAN_TASK_NODE_INSTANCE:
			nodeInstance = new HumanTaskNodeInstance();
			((HumanTaskNodeInstance) nodeInstance).internalSetWorkItemId(stream.readLong());
			nbTimerInstances = stream.readInt();
			if (nbTimerInstances > 0) {
				List<Long> timerInstances = new ArrayList<Long>();
				for (int i = 0; i < nbTimerInstances; i++) {
					timerInstances.add(stream.readLong());
				}
				((HumanTaskNodeInstance) nodeInstance).internalSetTimerInstances(timerInstances);
			}
			break;
		case PersisterEnums.WORK_ITEM_NODE_INSTANCE:
			nodeInstance = new WorkItemNodeInstance();
			((WorkItemNodeInstance) nodeInstance).internalSetWorkItemId(stream.readLong());
			nbTimerInstances = stream.readInt();
			if (nbTimerInstances > 0) {
				List<Long> timerInstances = new ArrayList<Long>();
				for (int i = 0; i < nbTimerInstances; i++) {
					timerInstances.add(stream.readLong());
				}
				((WorkItemNodeInstance) nodeInstance).internalSetTimerInstances(timerInstances);
			}
			break;
		case PersisterEnums.SUB_PROCESS_NODE_INSTANCE:
			nodeInstance = new SubProcessNodeInstance();
			((SubProcessNodeInstance) nodeInstance).internalSetProcessInstanceId(stream.readLong());
			nbTimerInstances = stream.readInt();
			if (nbTimerInstances > 0) {
				List<Long> timerInstances = new ArrayList<Long>();
				for (int i = 0; i < nbTimerInstances; i++) {
					timerInstances.add(stream.readLong());
				}
				((SubProcessNodeInstance) nodeInstance).internalSetTimerInstances(timerInstances);
			}
			break;
		case PersisterEnums.MILESTONE_NODE_INSTANCE:
			nodeInstance = new MilestoneNodeInstance();
			nbTimerInstances = stream.readInt();
			if (nbTimerInstances > 0) {
				List<Long> timerInstances = new ArrayList<Long>();
				for (int i = 0; i < nbTimerInstances; i++) {
					timerInstances.add(stream.readLong());
				}
				((MilestoneNodeInstance) nodeInstance).internalSetTimerInstances(timerInstances);
			}
			break;
		case PersisterEnums.TIMER_NODE_INSTANCE:
			nodeInstance = new TimerNodeInstance();
			((TimerNodeInstance) nodeInstance).internalSetTimerId(stream.readLong());
			break;
		case PersisterEnums.EVENT_NODE_INSTANCE:
			nodeInstance = new EventNodeInstance();
			break;
		case PersisterEnums.JOIN_NODE_INSTANCE:
			nodeInstance = new Uml2JoinInstance();
			int number = stream.readInt();
			if (number > 0) {
				Map<Long, Integer> triggers = new HashMap<Long, Integer>();
				for (int i = 0; i < number; i++) {
					long l = stream.readLong();
					int count = stream.readInt();
					triggers.put(l, count);
				}
				((JoinInstance) nodeInstance).internalSetTriggers(triggers);
			}
			break;
		case PersisterEnums.COMPOSITE_NODE_INSTANCE:
			nodeInstance = new CompositeContextNodeInstance();
			nbTimerInstances = stream.readInt();
			if (nbTimerInstances > 0) {
				List<Long> timerInstances = new ArrayList<Long>();
				for (int i = 0; i < nbTimerInstances; i++) {
					timerInstances.add(stream.readLong());
				}
				((CompositeContextNodeInstance) nodeInstance).internalSetTimerInstances(timerInstances);
			}
			break;
		case PersisterEnums.FOR_EACH_NODE_INSTANCE:
			nodeInstance = new ForEachNodeInstance();
			break;
		case PersisterEnums.DYNAMIC_NODE_INSTANCE:
			nodeInstance = new DynamicNodeInstance();
			nbTimerInstances = stream.readInt();
			if (nbTimerInstances > 0) {
				List<Long> timerInstances = new ArrayList<Long>();
				for (int i = 0; i < nbTimerInstances; i++) {
					timerInstances.add(stream.readLong());
				}
				((CompositeContextNodeInstance) nodeInstance).internalSetTimerInstances(timerInstances);
			}
			break;
		case PersisterEnums.STATE_NODE_INSTANCE:
			nodeInstance = new Uml2StateInstance();
			nbTimerInstances = stream.readInt();
			if (nbTimerInstances > 0) {
				List<Long> timerInstances = new ArrayList<Long>();
				for (int i = 0; i < nbTimerInstances; i++) {
					timerInstances.add(stream.readLong());
				}
				((CompositeContextNodeInstance) nodeInstance).internalSetTimerInstances(timerInstances);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown node type: " + nodeType);
		}
		return nodeInstance;
	}
}
