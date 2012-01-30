package org.nakeduml.runtime.domain.activity;

import org.nakeduml.runtime.domain.BaseTinkerBehavioredClassifier;
import org.nakeduml.runtime.domain.IClassifierSignalEvent;
import org.nakeduml.runtime.domain.TinkerClassifierBehaviorExecutorService;
import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.ISignal;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class AbstractSendSignalAction extends AbstractAction {

	public AbstractSendSignalAction() {
		super();
	}

	public AbstractSendSignalAction(boolean persist, String name) {
		super(persist, name);
	}

	public AbstractSendSignalAction(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected void execute() {
		System.out.println("x");
		TinkerClassifierBehaviorExecutorService.INSTANCE.submit(new IClassifierSignalEvent() {
			@Override
			public Boolean call() throws Exception {
				GraphDb.getDb().startTransaction();
				try {
					resolveTarget().receiveSignal(constructSignal());
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

	protected abstract ISignal constructSignal();
	
}
