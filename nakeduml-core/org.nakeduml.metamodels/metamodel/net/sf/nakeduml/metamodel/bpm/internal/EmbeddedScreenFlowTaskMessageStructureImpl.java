package net.sf.nakeduml.metamodel.bpm.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedScreenFlowTask;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.ArtificialProperty;
import net.sf.nakeduml.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.oclengine.IOclContext;

public class EmbeddedScreenFlowTaskMessageStructureImpl extends EmulatedCompositionMessageStructure{
	INakedEmbeddedScreenFlowTask action;
	ArtificialProperty endToStateMachine;
	public INakedEmbeddedScreenFlowTask getAction(){
		return action;
	}
	public EmbeddedScreenFlowTaskMessageStructureImpl(INakedEmbeddedScreenFlowTask action){
		super(action.getActivity(), action);
		this.action = action;
		if(action.getBehavior().getContext() == null){
			addSuperclass(action.getBehavior());
		}else{
			endToStateMachine=new ArtificialProperty(action.getBehavior());
		}

	}
	@Override
	public List<INakedProperty> getOwnedAttributes(){
		List<INakedProperty> list = new ArrayList<INakedProperty>();
		list.add(getEndToComposite());
		if(getCallBehaviorAction().getBehavior().getContext() != null){
			list.add(endToStateMachine);
		}
		return list;
	}
	public List<IOclContext> getDefinitions(){
		return Collections.emptyList();
	}
	public List<INakedConstraint> getOwnedRules(){
		return Collections.emptyList();
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
	@Override
	public List<IClassifier> getGeneralizations(){
		if(getCallBehaviorAction().getBehavior().getContext() == null){
			return Collections.singletonList((IClassifier) getCallBehaviorAction().getBehavior());
		}else{
			return Collections.emptyList();
		}
	}
	@Override
	public List<INakedProperty> getEffectiveAttributes(){
		List<INakedProperty> result = super.getEffectiveAttributes();
		result.add(getEndToComposite());
		return result;
	}
	@Override
	public IPackage getRoot(){
		return getCallBehaviorAction().getActivity().getNakedRoot();
	}
}
