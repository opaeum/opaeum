package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ReadVariableAction<O> extends VariableAction<O> {

	public ReadVariableAction() {
	}

	public ReadVariableAction(boolean persist, String name) {
		super(persist, name);
	}

	public ReadVariableAction(Vertex vertex) {
		super(vertex);
	}
	
	public abstract OutputPin<O> getResult();
	
	@Override
	protected void execute() {
		super.execute();
		getResult().addIncomingToken(new ObjectToken<O>(getResult().getName(), getVariable()));
	}

}
