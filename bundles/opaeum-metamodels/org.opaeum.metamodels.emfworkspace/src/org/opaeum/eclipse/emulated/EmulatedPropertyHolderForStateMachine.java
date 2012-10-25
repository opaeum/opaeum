package org.opaeum.eclipse.emulated;

import org.eclipse.uml2.uml.StateMachine;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class EmulatedPropertyHolderForStateMachine extends EmulatedPropertyHolderForBehavior{
	public EmulatedPropertyHolderForStateMachine(StateMachine owner,IPropertyEmulation e){
		super(owner, e);
	}
}
