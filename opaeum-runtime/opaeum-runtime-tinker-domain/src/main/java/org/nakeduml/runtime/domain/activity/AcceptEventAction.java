package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.jbpm.workflow.core.node.Trigger;
import org.nakeduml.runtime.domain.TinkerNode;
import org.nakeduml.runtime.domain.activity.interf.IAcceptEventAction;
import org.nakeduml.runtime.domain.activity.interf.IEvent;
import org.nakeduml.runtime.domain.activity.interf.IOutputPin;
import org.nakeduml.runtime.domain.activity.interf.ISignalEvent;
import org.nakeduml.runtime.domain.activity.interf.ITrigger;
import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.ISignal;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AcceptEventAction extends Action implements IAcceptEventAction {

//	private List<Trigger> triggers = new ArrayList<Trigger>();
	private boolean triggered = false;
	private IEvent event;

	public AcceptEventAction() {
		super();
	}

	public AcceptEventAction(boolean persist, String name) {
		super(persist, name);
	}

	public AcceptEventAction(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	public Boolean processNextStart() throws NoSuchElementException {
		Boolean result = super.processNextStart();
		if (doAllIncomingFlowsHaveTokens() && hasPreConditionPassed() && hasPostConditionPassed()) {
			setNodeStatus(NodeStatus.ENABLED);
		}
		return result;
	}
	
	@Override
	public List<ControlToken> getInTokens() {
		List<ControlToken> result = new ArrayList<ControlToken>();
		Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + getName());
		for (Edge edge : iter) {
			result.add(new ControlToken(edge.getInVertex()));
		}
		return result;
	}
	
	@Override
	public List<? extends InputPin<?, ?>> getInput() {
		return Arrays.asList();
	}

	public void setTrigger(IEvent signal) {
		this.triggered = true;
		this.event = signal;
	}
	
	@Override
	protected void transferObjectTokensToAction() {
		copyEventToOutputPin(this.event);
		removeEvent(this.event);
	}	

	public abstract void copyEventToOutputPin(IEvent event);

	protected void removeEvent(IEvent event) {
		if (event instanceof ISignalEvent) {
			ISignal signal = ((ISignalEvent)event).getSignal();
			if (signal instanceof TinkerNode) {
				GraphDb.getDb().removeVertex(((TinkerNode) signal).getVertex());
			}
		}
		GraphDb.getDb().removeVertex(((TinkerNode)event).getVertex());
	}	
	
	@Override
	protected boolean isTriggered() {
		return this.triggered;
	}

	@Override
	public abstract List<? extends ITrigger> getTrigger();
//		return triggers;
//	}
	
//	public void addToTriggers(String name, String eventName) {
//		getTrigger().add(new Trigger(name, eventName));
//	}
	
	public boolean containsTriggerForEvent(IEvent event) {
		for (ITrigger trigger : getTrigger()) {
			if (trigger.accepts(event)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<? extends IOutputPin<?,?>> getResult() {
		return getOutput();
	}

}
