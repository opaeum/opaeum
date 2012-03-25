package org.nakeduml.runtime.domain.activity;

import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ReplyAction<R> extends Action {

	public ReplyAction() {
		super();
	}

	public ReplyAction(boolean persist, String name) {
		super(persist, name);
	}

	public ReplyAction(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected void execute() {
		super.execute();
	}

	@Override
	protected abstract List<? extends OutputPin<?>> getOutputPins();
	
	public R getReply(){
		return null;
	}

}
