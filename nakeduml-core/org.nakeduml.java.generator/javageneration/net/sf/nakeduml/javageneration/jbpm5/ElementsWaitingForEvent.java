package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedStep;

/**
 * A transient object holding the relationship between a node on a behavior that waits for events and signal such event to the BPM engine.
 * In the case of StateMachines, any state with event driven transitions is a WaitForEventNode. In the case of ACtivities any
 * AcceptEventACtion could be a WaitForEventNode.
 * 
 * @author ampie
 * 
 */
public class ElementsWaitingForEvent{
	INakedEvent event;
	Map<String,FromNode> fromNodes = new HashMap<String,FromNode>();
	public ElementsWaitingForEvent(INakedEvent event){
		this.event = event;
	}
	public ElementsWaitingForEvent(){
	}
	public void addWaitingNode(INakedStep source,GuardedFlow flow,boolean isRestingNode){
		FromNode fromNode = this.fromNodes.get(source.getName());
		if(fromNode == null){
			fromNode = new FromNode(source, isRestingNode);
			this.fromNodes.put(source.getName(), fromNode);
		}
		// Use sql Name
		fromNode.addTransition(flow.getMappingInfo().getPersistentName().getAsIs(), flow);
	}
	public Collection<FromNode> getWaitingNodes(){
		return this.fromNodes.values();
	}
	public INakedEvent getEvent(){
		return this.event;
	}
}
