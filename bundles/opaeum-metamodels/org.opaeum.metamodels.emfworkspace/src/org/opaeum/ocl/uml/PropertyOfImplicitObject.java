package org.opaeum.ocl.uml;

import org.eclipse.ocl.uml.Variable;
import org.eclipse.uml2.uml.Property;

public class PropertyOfImplicitObject extends EmulatedVariable{

	private Variable implicitVar;

	public PropertyOfImplicitObject(Variable implicitVar, Property originalElement){
		super(originalElement);
		this.implicitVar=implicitVar;
	}
	public Variable getImplicitVar(){
		return implicitVar;
	}
}
