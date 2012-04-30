package org.nakeduml.runtime.domain.activity;

import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IInputPin;
import org.nakeduml.runtime.domain.activity.interf.IInvocationAction;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class InvocationAction extends Action implements IInvocationAction {

	public InvocationAction() {
		super();
	}

	public InvocationAction(boolean persist, String name) {
		super(persist, name);
	}

	public InvocationAction(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	public List<? extends IInputPin<?, ?>> getArgument() {
		return getInput();
	}

}
