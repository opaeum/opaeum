package net.sf.nakeduml.metamodel.actions;

import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.stdlib.IOclLibrary;

public class CallBehaviorMessageStructure extends EmulatedCompositionMessageStructure{
	private INakedCallBehaviorAction action;
	public CallBehaviorMessageStructure(INakedCallBehaviorAction b, IOclLibrary lib){
		super(b.getActivity(), b.getBehavior(),lib);
		this.action=b;
	}
	@Override
	public boolean isPersistent(){
		return action.getBehavior().isPersistent();
	}
	@Override
	public List<? extends IOclContext> getDefinitions(){
		return Collections.emptyList();
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
