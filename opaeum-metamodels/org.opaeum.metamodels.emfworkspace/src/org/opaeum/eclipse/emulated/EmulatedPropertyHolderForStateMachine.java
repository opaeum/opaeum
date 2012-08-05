package org.opaeum.eclipse.emulated;

import org.eclipse.uml2.uml.StateMachine;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class EmulatedPropertyHolderForStateMachine extends EmulatedPropertyHolderForBehavior{
	@SuppressWarnings("unchecked")
	public EmulatedPropertyHolderForStateMachine(StateMachine owner,IPropertyEmulation e){
		super(owner, e, owner.getOwnedParameters(),EmfTimeUtil.buildObservationPropertiess(owner,e,owner));
	}
}
