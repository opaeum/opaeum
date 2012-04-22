package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OneReadVariableAction<V> extends ReadVariableAction<V> {

	public OneReadVariableAction() {
	}

	public OneReadVariableAction(boolean persist, String name) {
		super(persist, name);
	}

	public OneReadVariableAction(Vertex vertex) {
		super(vertex);
	}
	
	public abstract OneOutputPin<V> getResult();
	
	protected abstract V getVariable();
	
	@Override
	protected void execute() {
		super.execute();
		getResult().addIncomingToken(new SingleObjectToken<V>(getResult().getName(), getVariable()));
	}

}
