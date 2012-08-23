package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumQualifiedNameLabelProvider;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

public abstract class OpaeumChooserPropertySection extends AbstractChooserPropertySection implements EObjectNavigationSource{
	public NavigationDecorator decorator = new NavigationDecorator(this);
	@Override
	public EObject getEObjectToGoTo(){
		return (EObject) getFeatureValue();
	}
	@Override
	public CLabel getLabelCombo(){
		return labelCombo;
	}
	public void refresh(){
		if(getEObject().eContainer() != null){//Hack - eclipse calls refresh even if the object was deleted
			super.refresh();
			decorator.refresh();
		}
	}
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new UMLItemProviderAdapterFactory());
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return new OpaeumQualifiedNameLabelProvider(new OpaeumItemProviderAdapterFactory());
	}

}
