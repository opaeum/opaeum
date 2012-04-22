package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Edge;

public abstract class ObjectFlowUnknown<T extends ObjectToken<?>> extends ActivityEdge<T> {

	public ObjectFlowUnknown(Edge edge) {
		super(edge);
	}

	@Override
	protected abstract boolean evaluateGuardConditions(T token);

//	public abstract <O,Q extends ObjectToken<O>> ObjectFlowKnown<O,Q> convertToKnownObjectFlow();
	
//	public <O,T> ObjectFlowKnown<O,T> convertToKnownObjectFlow() {
//		return new ObjectFlowKnown<T>(ObjectFlowUnknown.this.edge) {
//
//			@SuppressWarnings("unchecked")
//			@Override
//			protected ActivityNode<ObjectToken<T>, ObjectToken<T>> getTarget() {
//				return ObjectFlowUnknown.this.getTarget();
//			}
//
//			@SuppressWarnings("unchecked")
//			@Override
//			protected ActivityNode<ObjectToken<T>, ObjectToken<T>> getSource() {
//				return ObjectFlowUnknown.this.getSource();
//			}
//
//			@Override
//			public String getName() {
//				return ObjectFlowUnknown.this.getName();
//			}
//
//			@Override
//			protected boolean evaluateGuardConditions(ObjectToken<T> token) {
//				return ObjectFlowUnknown.this.evaluateGuardConditions(token);
//			}
//			
//			@Override
//			protected boolean evaluateGuardConditions(T tokenValue) {
//				return true;
//			}
//
//			@Override
//			protected int getWeigth() {
//				return ObjectFlowUnknown.this.getWeigth();
//			}
//		};
//	}

}
