package net.sf.nakeduml.metamodel.actions;

import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import nl.klasse.octopus.model.IClassifier;

public class CallBehaviorMessageStructure extends EmulatedCompositionMessageStructure{
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
