package net.sf.nakeduml.metamodel.bpm.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.stdlib.IOclLibrary;

public class EmbeddedSingleScreenTaskMessageStructureImpl extends EmulatedCompositionMessageStructure {
	INakedEmbeddedSingleScreenTask action;

	public INakedEmbeddedSingleScreenTask getAction(){
		return action;
	}

	private List<INakedProperty> attributes;

	public EmbeddedSingleScreenTaskMessageStructureImpl(INakedEmbeddedSingleScreenTask action,IOclLibrary lib) {
		super(action.getActivity(), action,lib);
		this.action = action;
	}

	@Override
	public List<INakedProperty> getOwnedAttributes() {
		if (attributes == null) {
			attributes = new ArrayList<INakedProperty>();
			for (INakedObjectNode p : action.getPins()) {
				attributes.add(new TypedElementPropertyBridge(this, p,false));
			}
		}
		return attributes;
	}

	public List<IOclContext> getDefinitions() {
		return Collections.emptyList();
	}

	public List<INakedConstraint> getOwnedRules() {
		return Collections.emptyList();
	}


	public boolean isPersistent() {
		return true;
	}

	public INakedEmbeddedSingleScreenTask getOpaqueAction() {
		return action;
	}

	@Override
	public IPackage getRoot() {
		return getOpaqueAction().getActivity().getNakedRoot();
	}



}