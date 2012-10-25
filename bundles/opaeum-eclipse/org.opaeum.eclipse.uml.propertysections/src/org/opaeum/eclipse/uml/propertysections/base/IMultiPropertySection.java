package org.opaeum.eclipse.uml.propertysections.base;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public interface IMultiPropertySection{
	EditingDomain getEditingDomain();
	Collection<EObject> getEObjectList();
	EObject getFeatureOwner(EObject selection);
	EObject getSelectedObject();
	void addSubsection(AbstractTabbedPropertySubsection<?,?> ss);
	TabbedPropertySheetWidgetFactory getWidgetFactory();
}