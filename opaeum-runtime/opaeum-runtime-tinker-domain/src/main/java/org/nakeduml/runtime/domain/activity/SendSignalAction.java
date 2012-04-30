package org.nakeduml.runtime.domain.activity;

import java.util.Collections;
import java.util.List;

import org.nakeduml.runtime.domain.BaseTinkerBehavioredClassifier;
import org.nakeduml.runtime.domain.IClassifierSignalEvent;
import org.nakeduml.runtime.domain.TinkerClassifierBehaviorExecutorService;
import org.nakeduml.runtime.domain.activity.interf.IOutputPin;
import org.nakeduml.runtime.domain.activity.interf.ISendSignalAction;
import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.ISignal;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class SendSignalAction extends InvocationAction implements ISendSignalAction {

	public SendSignalAction() {
		super();
	}

	public SendSignalAction(boolean persist, String name) {
		super(persist, name);
	}

	public SendSignalAction(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected void execute() {
		System.out.println("executing action " + getClass().getSimpleName());
		TinkerClassifierBehaviorExecutorService.INSTANCE.submit(new IClassifierSignalEvent() {
			@Override
			public Boolean call() throws Exception {
				GraphDb.getDb().startTransaction();
				try {
					resolveTarget().receiveSignal(getSignal());
					GraphDb.getDb().stopTransaction(Conclusion.SUCCESS);
				} catch (Exception e) {
					GraphDb.getDb().stopTransaction(Conclusion.FAILURE);
					throw e;
				}
				return true;
			}

		});
	}

	protected abstract BaseTinkerBehavioredClassifier resolveTarget();

	@Override
	public abstract ISignal getSignal();
	
	@Override
	public List<? extends IOutputPin<?,?>> getOutput() {
		return Collections.emptyList();
	}
	
	protected void transferObjectTokensToAction() {
		//Now take
		
		
//		for (InputPin<?> inputPin : this.getInputPins()) {
//			for (ObjectToken<?> token : inputPin.getInTokens()) {
//				token.removeEdgeFromActivityNode();
//				addToInputPinVariable(inputPin, token.getObject());
//				token.removeEdgeToObject();
//				GraphDb.getDb().removeVertex(token.getVertex());
//			}
//		}
	}	

	
}
