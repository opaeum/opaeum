package org.opaeum.uim.userinteractionproperties.uml;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.uim.userinteractionproperties.common.CommonRequiredStatesSection;
import org.opaeum.uimodeler.common.IUIElementMapMap;

public class NavigableElementRequiredStatesSection extends CommonRequiredStatesSection{
	@Override
	protected EObject createFeatureOwner(EObject currentObject,CompoundCommand cc){
		throw new IllegalStateException();
	}
	@Override
	protected EObject getFeatureOwner(EObject nextObject){
		return ((IUIElementMapMap)nextObject.eResource().getResourceSet()).getElementFor((Element)nextObject);
	}
}
