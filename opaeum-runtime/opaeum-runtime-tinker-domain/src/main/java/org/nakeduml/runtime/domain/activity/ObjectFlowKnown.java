package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Edge;

public abstract class ObjectFlowKnown<O> extends ActivityEdge<ObjectToken<O>> {

	public ObjectFlowKnown(Edge edge) {
		super(edge);
	}
	
	protected Edge getEdge() {
		return this.edge;
	}
	
	protected abstract boolean evaluateGuardConditions(O tokenValue);

	@Override
	protected boolean evaluateGuardConditions(ObjectToken<O> token) {
		return evaluateGuardConditions(token.getObject());	
	}
	
	public ObjectFlowUnknown convertToUnknownObjectFlow() {
		return new ObjectFlowUnknown(ObjectFlowKnown.this.edge) {
			
			@Override
			protected int getWeigth() {
				return ObjectFlowKnown.this.getWeigth();
			}
			
			@SuppressWarnings("unchecked")
			@Override
			protected ActivityNode<ObjectToken<?>> getTarget() {
				return ObjectFlowKnown.this.getTarget();
			}
			
			@SuppressWarnings("unchecked")
			@Override
			protected ActivityNode<ObjectToken<?>> getSource() {
				return ObjectFlowKnown.this.getSource();
			}
			
			@Override
			public String getName() {
				return ObjectFlowKnown.this.getName();
			}
			
			@SuppressWarnings("unchecked")
			@Override
			protected boolean evaluateGuardConditions(ObjectToken<?> token) {
				return ObjectFlowKnown.this.evaluateGuardConditions((ObjectToken<O>) token);
			}
			
			@SuppressWarnings("unchecked")
			@Override
			protected boolean evaluateGuardConditions(Object tokenValue) {
				return ObjectFlowKnown.this.evaluateGuardConditions((O)tokenValue);
			}
		};
	}

	
}
