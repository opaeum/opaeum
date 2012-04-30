package org.nakeduml.runtime.domain.activity;

import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.ICallAction;
import org.nakeduml.runtime.domain.activity.interf.IOutputPin;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class CallAction extends InvocationAction implements ICallAction {

	public CallAction() {
		super();
	}

	public CallAction(boolean persist, String name) {
		super(persist, name);
	}

	public CallAction(Vertex vertex) {
		super(vertex);
	}

	@Override
	public abstract boolean isSynchronous();

	@Override
	public List<? extends IOutputPin<?, ?>> getResult() {
		return getOutput();
	}

}
