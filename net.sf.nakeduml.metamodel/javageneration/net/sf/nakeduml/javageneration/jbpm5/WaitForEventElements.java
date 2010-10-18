package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.metamodel.commonbehaviors.GuardedFlow;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;

/**
 * A transient object holding the relationship between a node on a behavior that
 * waits for events and signal such event to the BPM engine. In the case of
 * StateMachines, any state with event driven transitions is a WaitForEventNode.
 * In the case of ACtivities any AcceptEventACtion could be a WaitForEventNode.
 * 
 * @author ampie
 * 
 */
public class WaitForEventElements {
	INakedElement event;
	Map<String, FromNode> fromNodes = new HashMap<String, FromNode>();
	private boolean completionEvent;

	public WaitForEventElements(INakedElement event) {
		this.event = event;
	}
	public WaitForEventElements() {
		this.completionEvent=true;
	}

	public void addWaitingNode(INakedElement source, GuardedFlow flow ,boolean isRestingNode) {
		FromNode fromNode = this.fromNodes.get(source.getName());
		if (fromNode == null) {
			fromNode = new FromNode(source,isRestingNode);
			this.fromNodes.put(source.getName(), fromNode);
		}// Use sql Name
		fromNode.addTransition(flow.getMappingInfo().getPersistentName().getAsIs(), flow);
	}

	public Collection<FromNode> getWaitingNodes() {
		return this.fromNodes.values();
	}

	public INakedElement getEvent() {
		return this.event;
	}

	public List getArguments() {
		if (this.event instanceof INakedSignal) {
			return ((INakedSignal) this.event).getArgumentParameters();
		} else if (this.event instanceof INakedOperation) {
			return ((INakedOperation) this.event).getArgumentParameters();
		} else {
			// TODO if timeevent return one parameter
			return Collections.EMPTY_LIST;
		}
	}
}
