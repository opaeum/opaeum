package org.opaeum.topcased.propertysections.property;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class SecondEndRoleInCubeSection extends PropertyRoleInCubeSection{
	protected Property getProperty(){
		return ((Association) getEObject()).getMemberEnds().get(1);
	}
}
