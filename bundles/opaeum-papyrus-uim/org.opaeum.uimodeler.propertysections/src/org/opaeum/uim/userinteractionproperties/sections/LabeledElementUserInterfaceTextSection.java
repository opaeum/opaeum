package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.LabelContainer;
import org.opaeum.uim.userinteractionproperties.common.AbstractUserInterfaceTextSection;

public class LabeledElementUserInterfaceTextSection extends AbstractUserInterfaceTextSection{

	@Override
	protected LabelContainer getLabeledElement(EObject selectedObject){
		return ((LabelContainer)selectedObject);
	}
}
