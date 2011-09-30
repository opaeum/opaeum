package org.opeum.metamodel.actions;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.opeum.metamodel.commonbehaviors.INakedReception;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedConstraint;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import nl.klasse.octopus.model.IClassifier;

public class CallBehaviorMessageStructure extends EmulatedCompositionMessageStructure{
	private static final long serialVersionUID = -3057414460280596858L;
	private INakedCallBehaviorAction action;
	public CallBehaviorMessageStructure(INakedCallBehaviorAction b){
		super(b.getActivity(), b.getBehavior());
		this.action=b;
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
	public List<INakedProperty> getOwnedAttributes(){
		return Collections.singletonList(getEndToComposite());
	}
	@Override
	public List<IClassifier> getGeneralizations(){
		return Collections.singletonList((IClassifier)getSupertype());
	}
	@Override
	public INakedClassifier getSupertype(){
		return action.getBehavior();
	}
}
