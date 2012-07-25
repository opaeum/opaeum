package org.eclipse.uml2.uml;

import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.model.IClassifier;

import org.opaeum.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;

public class CallBehaviorMessageStructure extends EmulatedCompositionMessageStructure{
	private static final long serialVersionUID = 1L;
	private INakedCallBehaviorAction action;
	public CallBehaviorMessageStructure(INakedCallBehaviorAction b){
		super(b.getActivity(), b.getBehavior());
		this.action = b;
	}
	@Override
	public boolean isPersistent(){
		return action.getBehavior().isPersistent();
	}
	@Override
	public List<? extends INakedConstraint> getOwnedRules(){
		return Collections.emptyList();
	}
	@Override
	public void reinitialize(){
		super.reinitialize();
		super.owner = action.getNearestStructuredElementAsClassifier();
		if(action.getBehavior().getContext() == null){
			addSuperclass(action.getBehavior());
		}else{
		}
	}
	@Override
	public List<IClassifier> getGeneralizations(){
		return Collections.singletonList((IClassifier) getSupertype());
	}
	@Override
	public INakedClassifier getSupertype(){
		return action.getBehavior();
	}
}
