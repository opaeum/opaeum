package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Edge;

public abstract class OneObjectFlowKnown<O> extends ObjectFlowKnown<O, SingleObjectToken<O>> {

	public OneObjectFlowKnown(Edge edge) {
		super(edge);
	}
	
	protected Edge getEdge() {
		return this.edge;
	}
	
	protected abstract boolean evaluateGuardConditions(O tokenValue);
	
	@Override
	protected boolean evaluateGuardConditions(SingleObjectToken<O> token) {
		return evaluateGuardConditions(token.getObject());	
	}
	
	public OneObjectFlowUnknown convertToUnknownObjectFlow() {
		return new OneObjectFlowUnknown(OneObjectFlowKnown.this.edge) {

			@SuppressWarnings("unchecked")
			@Override
			protected boolean evaluateGuardConditions(Object tokenValue) {
				return OneObjectFlowKnown.this.evaluateGuardConditions((O) tokenValue);
			}

			@Override
			public <IN extends Token, OUT extends Token> ActivityNode<IN, OUT> getTarget() {
				return OneObjectFlowKnown.this.getTarget();
			}

			@Override
			public <IN extends Token, OUT extends Token> ActivityNode<IN, OUT> getSource() {
				return OneObjectFlowKnown.this.getSource();
			}

			@Override
			public String getName() {
				return OneObjectFlowKnown.this.getName();
			}

			@Override
			protected int getWeigth() {
				return OneObjectFlowKnown.this.getWeigth();
			}

		};
	}
	
}
