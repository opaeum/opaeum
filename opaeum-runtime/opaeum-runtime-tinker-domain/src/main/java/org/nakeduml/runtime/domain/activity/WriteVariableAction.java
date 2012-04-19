package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class WriteVariableAction<V> extends VariableAction<V> {

	public WriteVariableAction() {
		super();
	}

	public WriteVariableAction(boolean persist, String name) {
		super(persist, name);
	}

	public WriteVariableAction(Vertex vertex) {
		super(vertex);
	}
	
	public abstract InputPin<V> getValue();
	protected abstract void writeVariable(V v);
	
	@Override
	protected void execute() {
		super.execute();
		V value = getValue().getInTokens().iterator().next().getObject();
		writeVariable(value);
	}	

}
