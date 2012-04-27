package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AcceptCallAction extends AcceptEventAction {

	public AcceptCallAction() {
		super();
	}

	public AcceptCallAction(boolean persist, String name) {
		super(persist, name);
	}

	public AcceptCallAction(Vertex vertex) {
		super(vertex);
	}

	protected abstract <R, OUT extends ObjectToken<R>> ReturnInformationOutputPin<R, OUT> getReturnInformationOutputPin();

	public abstract ReplyAction getReplyAction();

	@Override
	protected void transferObjectTokensToAction() {
		super.transferObjectTokensToAction();
		getReturnInformationOutputPin().addOutgoingToken(new SingleObjectToken<Object>(getReturnInformationOutputPin().getName(), "not used except for flow control"));
	}

}
