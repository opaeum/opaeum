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
import nl.klasse.octopus.stdlib.IOclLibrary;

public class EmbeddedScreenFlowTaskMessageStructureImpl extends EmulatedCompositionMessageStructure{
	INakedEmbeddedScreenFlowTask action;
	ArtificialProperty endToStateMachine;
	public INakedEmbeddedScreenFlowTask getAction(){
		return action;
	}
	public EmbeddedScreenFlowTaskMessageStructureImpl(INakedEmbeddedScreenFlowTask action,IOclLibrary lib){
		super(action.getActivity(), action, lib);
		this.action = action;
		if(getOpaqueAction().getBehavior().getContext() != null){
			endToStateMachine=new ArtificialProperty(getOpaqueAction().getBehavior(), lib);
		}
	}
	@Override
	public List<INakedProperty> getOwnedAttributes(){
		List<INakedProperty> list = new ArrayList<INakedProperty>();
		list.add(getEndToComposite());
		if(getOpaqueAction().getBehavior().getContext() != null){
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
	public INakedEmbeddedScreenFlowTask getOpaqueAction(){
		return action;
	}
	@Override
	public INakedClassifier getNestingClassifier(){
		return super.getNestingClassifier();
	}
	@Override
	public List<IClassifier> getGeneralizations(){
		if(getOpaqueAction().getBehavior().getContext() == null){
			return Collections.singletonList((IClassifier) getOpaqueAction().getBehavior());
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
	public INakedClassifier getSupertype(){
		if(getOpaqueAction().getBehavior().getContext() == null){
			return getOpaqueAction().getBehavior();
		}else{
			return null;
		}
	}
	@Override
	public IPackage getRoot(){
		return getOpaqueAction().getActivity().getNakedRoot();
	}
}
