package org.opaeum.ocl.uml;

import org.eclipse.ocl.uml.impl.VariableImpl;
import org.eclipse.uml2.uml.NamedElement;

public class EmulatedVariable extends VariableImpl{
	private NamedElement originalElement;

	public EmulatedVariable(NamedElement originalElement){
		super();
		this.originalElement = originalElement;
	}
	public NamedElement getOriginalElement(){
		return originalElement;
	}
}
