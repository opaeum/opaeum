package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;


public abstract class CreateObjectAction<O> extends Action {

	public CreateObjectAction() {
		super();
	}

	public CreateObjectAction(boolean persist, String name) {
		super(persist, name);
	}

	public CreateObjectAction(Vertex vertex) {
		super(vertex);
	}
	
	protected abstract O createObject();
	
	public abstract OutputPin<O, SingleObjectToken<O>> getResult();

	@Override
	protected void execute() {
		super.execute();
		O object = createObject();
		getResult().addIncomingToken(new SingleObjectToken<O>(getResult().getName(), object));
	}
	
}
