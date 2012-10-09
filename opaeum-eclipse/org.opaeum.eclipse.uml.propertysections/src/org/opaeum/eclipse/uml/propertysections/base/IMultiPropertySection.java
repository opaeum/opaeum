package org.opaeum.eclipse.uml.propertysections.base;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.topcased.tabbedproperties.sections.AbstractSingleContainedElementSection;

public interface IMultiPropertySection extends ISection{
	EditingDomain getEditingDomain();
	Collection<EObject> getEObjectList();
	EObject getFeatureOwner(EObject selection);
	EObject getEObject();
	void addSubsection(AbstractTabbedPropertySubsection<?,?> ss);
	TabbedPropertySheetWidgetFactory getWidgetFactory();
}