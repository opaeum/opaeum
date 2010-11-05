package net.sf.nakeduml.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.oclengine.IOclContext;

public class OpaqueActionMessageStructureImpl extends MessageStructureImpl {
	INakedOpaqueAction action;

	private List<INakedProperty> attributes;

	public OpaqueActionMessageStructureImpl(INakedOpaqueAction action) {
		super(action.getActivity(), action);
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
		// TODO return true only if usertask
		return true;
	}

	public INakedOpaqueAction getOpaqueAction() {
		return action;
	}

	@Override
	public IPackage getRoot() {
		return getOpaqueAction().getActivity().getRoot();
	}



}
