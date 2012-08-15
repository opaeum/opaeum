package org.opaeum.metamodel.bpm.internal;

import org.eclipse.uml2.uml.CallBehaviorMessageStructure;
import org.eclipse.uml2.uml.INakedClassifier;
import org.opaeum.metamodel.bpm.INakedEmbeddedScreenFlowTask;

public class EmbeddedScreenFlowTaskMessageStructure extends CallBehaviorMessageStructure{
	private static final long serialVersionUID = -83727119475369046L;
	private INakedEmbeddedScreenFlowTask action;
	public EmbeddedScreenFlowTaskMessageStructure(INakedEmbeddedScreenFlowTask action){
		super(action);
		this.action = action;
		reinitialize();

	}
	public INakedEmbeddedScreenFlowTask getAction(){
		return action;
	}
	public boolean isPersistent(){
		return true;
	}
	public INakedEmbeddedScreenFlowTask getCallBehaviorAction(){
		return action;
	}
	@Override
	public INakedClassifier getNestingClassifier(){
		return super.getNestingClassifier();
	}
}
