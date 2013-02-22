package org.opaeum.uim.userinteractionproperties.navigation;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.uim.userinteractionproperties.core.AbstractRequiredStatesSection;
import org.opaeum.uim.userinteractionproperties.core.CommonRequiredRolesSection;
import org.opaeum.uim.userinteractionproperties.core.CommonRequiredStatesSection;
import org.opaeum.uimodeler.common.IExplorerMap;

public class NavigableElementRequiredStatesSection extends CommonRequiredStatesSection{
	@Override
	protected EObject createFeatureOwner(EObject currentObject,CompoundCommand cc){
		throw new IllegalStateException();
	}
	@Override
	protected EObject getFeatureOwner(EObject nextObject){
		return ((IExplorerMap)nextObject.eResource().getResourceSet()).getConstraintFor((Element)nextObject);
	}
}
