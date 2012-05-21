package org.nakeduml.runtime.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.nakeduml.runtime.domain.activity.AbstractActivity;
import org.nakeduml.runtime.domain.activity.Token;
import org.nakeduml.runtime.domain.activity.interf.IActivityNode;
import org.nakeduml.runtime.domain.activity.interf.IBehavioredClassifier;
import org.nakeduml.runtime.domain.activity.interf.IEvent;
import org.nakeduml.tinker.collection.TinkerSequence;
import org.nakeduml.tinker.collection.TinkerSequenceImpl;
import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.domain.IntrospectionUtil;

import com.tinkerpop.blueprints.pgm.Edge;

public abstract class BaseTinkerBehavioredClassifier extends BaseTinkerSoftDelete implements Serializable, TinkerCompositionNode, IBehavioredClassifier {

	private static final long serialVersionUID = 228929853082097254L;
	protected TinkerSequence<IEvent> events;

	public BaseTinkerBehavioredClassifier() {
		super();
	}

	public abstract void receiveSignal(ISignal signal);
	
	public abstract List<AbstractActivity> getAllActivities();
	
	public AbstractActivity getFirstActivityForEvent(IEvent event) {
		for (AbstractActivity activity : getAllActivities()) {
			Set<IActivityNode<? extends Token, ? extends Token>> nodesToTrigger = activity.getEnabledNodesWithMatchingTrigger(event);
			if (!nodesToTrigger.isEmpty()) {
				return activity;
			}
		}
		return null;
	}
	
	protected void attachToRoot() {
		Edge edge = GraphDb.getDb().addEdge(null, GraphDb.getDb().getRoot(), this.vertex, "classifierBehavior");
		edge.setProperty("inClass", IntrospectionUtil.getOriginalClass(this.getClass()).getName());
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
