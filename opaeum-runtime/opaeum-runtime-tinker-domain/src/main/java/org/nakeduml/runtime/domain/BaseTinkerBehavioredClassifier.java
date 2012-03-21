package org.nakeduml.runtime.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.nakeduml.runtime.domain.activity.AbstractActivity;
import org.nakeduml.runtime.domain.activity.ActivityNode;
import org.nakeduml.runtime.domain.activity.Event;
import org.nakeduml.runtime.domain.activity.IEvent;
import org.nakeduml.runtime.domain.activity.Token;
import org.nakeduml.tinker.collection.TinkerSequence;
import org.nakeduml.tinker.collection.TinkerSequenceImpl;
import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.domain.IntrospectionUtil;

import com.tinkerpop.blueprints.pgm.Edge;

public abstract class BaseTinkerBehavioredClassifier extends BaseTinkerSoftDelete implements Serializable, TinkerCompositionNode {

	private static final long serialVersionUID = 228929853082097254L;
	private TinkerSequence<IEvent> events;

	public BaseTinkerBehavioredClassifier() {
		super();
	}

	public abstract void receiveSignal(ISignal signal);
	
	public abstract List<AbstractActivity> getAllActivities();
	
	public AbstractActivity getFirstActivityForCallEvent(IEvent event) {
		for (AbstractActivity activity : getAllActivities()) {
			Set<ActivityNode<? extends Token, ? extends Token>> nodesToTrigger = activity.getEnabledNodesWithMatchingTrigger(event);
			if (!nodesToTrigger.isEmpty()) {
				return activity;
			}
		}
		return null;
	}
	
	protected void attachToRoot() {
		Edge edge = GraphDb.getDb().addEdge(null, GraphDb.getDb().getRoot(), this.vertex, "classifierBehavior");
		edge.setProperty("inClass", IntrospectionUtil.getOriginalClass(this.getClass()).getName());
		this.events = new TinkerSequenceImpl<IEvent>(this, "eventPool", "uid", true, false, true);
	}

	public List<IEvent> getEventPool() {
		List<IEvent> result = this.events;
		return result;
	}
	
	public void addToEventPool(IEvent event) {
		if ( event!=null ) {
			this.events.add(event);
		}
	}
	
	public void removeFromEventPool(IEvent event) {
		if ( event!=null ) {
			this.events.remove(event);
		}
	}
	
}
