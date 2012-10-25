package org.opaeum.javageneration.bpm;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.NamedElement;

/**
 * A transient object holding the relationship between a node on a behavior that waits for events and signal such event to the BPM engine.
 * In the case of StateMachines, any state with event driven transitions is a WaitForEventNode. In the case of ACtivities any
 * AcceptEventACtion could be a WaitForEventNode.
 * 
 * @author ampie
 * 
 */
public class ElementsWaitingForEvent{
	Event event;
	private Set<NamedElement> waitingNodes = new HashSet<NamedElement>();
	public ElementsWaitingForEvent(Event event){
		this.event = event;
	}
	public ElementsWaitingForEvent(){
	}
	public void addWaitingNode(NamedElement source){
		getWaitingNodes().add(source);
	}

	public Event getEvent(){
		return this.event;
	}
	public Set<NamedElement> getWaitingNodes(){
		return waitingNodes;
	}
	public void setWaitingNodes(Set<NamedElement> waitingNodes){
		this.waitingNodes = waitingNodes;
	}
}
