package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.custom.CLabel;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

public abstract class OpaeumChooserPropertySection extends AbstractChooserPropertySection implements EObjectNavigationSource{
	public NavigationDecorator decorator=new NavigationDecorator(this);
	@Override
	public EObject getEObjectToGoTo(){
		return (EObject) getFeatureValue();
	}
	@Override
	public CLabel getLabelCombo(){
		return labelCombo;
	}
	public void refresh(){
		super.refresh();
		decorator.refresh();
	}
}
