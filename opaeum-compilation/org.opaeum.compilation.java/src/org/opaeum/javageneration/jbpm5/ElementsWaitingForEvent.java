package org.opaeum.javageneration.jbpm5;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.PersistentNameUtil;

/**
 * A transient object holding the relationship between a node on a behavior that waits for events and signal such event to the BPM engine.
 * In the case of StateMachines, any state with event driven transitions is a WaitForEventNode. In the case of ACtivities any
 * AcceptEventACtion could be a WaitForEventNode.
 * 
 * @author ampie
 * 
 */
public class ElementsWaitingForEvent{
	NamedElement event;
	Map<String,FromNode> fromNodes = new HashMap<String,FromNode>();
	public ElementsWaitingForEvent(NamedElement event){
		this.event = event;
	}
	public ElementsWaitingForEvent(){
	}
	public void addWaitingNode(NamedElement source,NamedElement flow,boolean isRestingNode){
		FromNode fromNode = this.fromNodes.get(source.getName());
		if(fromNode == null){
			fromNode = new FromNode(source, isRestingNode);
			this.fromNodes.put(source.getName(), fromNode);
		}
		// Use sql Name
		fromNode.addTransition(PersistentNameUtil.getPersistentName( flow).getAsIs(), flow);
	}
	public Collection<FromNode> getWaitingNodes(){
		return this.fromNodes.values();
	}
	public NamedElement getEvent(){
		return this.event;
	}
}
