package org.opaeum.metamodel.bpm.internal;

import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opaeum.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;

public class EmbeddedSingleScreenTaskMessageStructureImpl extends EmulatedCompositionMessageStructure{
	private static final long serialVersionUID = 11223480298L;
	INakedEmbeddedSingleScreenTask action;
	public EmbeddedSingleScreenTaskMessageStructureImpl(INakedEmbeddedSingleScreenTask action){
		super(action.getNearestStructuredElementAsClassifier(), action);
		this.action = action;
		reinitialize();
	}
	public INakedEmbeddedSingleScreenTask getAction(){
		return action;
	}
	@Override
	public void reinitialize(){
		super.reinitialize();
		for(INakedObjectNode p:action.getPins()){
			attributes.add(new TypedElementPropertyBridge(this, p, false));
		}
		super.owner = action.getNearestStructuredElementAsClassifier();
	}
	public INakedEmbeddedSingleScreenTask getOpaqueAction(){
		return action;
	}
}
