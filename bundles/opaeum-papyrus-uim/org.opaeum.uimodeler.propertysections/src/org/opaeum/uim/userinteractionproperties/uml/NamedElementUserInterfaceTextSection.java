package org.opaeum.uim.userinteractionproperties.uml;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.userinteractionproperties.common.AbstractUserInterfaceTextSection;
import org.opaeum.uimodeler.common.IUIElementMapMap;

public class NamedElementUserInterfaceTextSection extends AbstractUserInterfaceTextSection{
	@Override
	public LabeledElement getLabeledElement(EObject e){
		IUIElementMapMap map = (IUIElementMapMap)e.eResource().getResourceSet();
		return  map.getElementFor((Element)e);
	}
}
