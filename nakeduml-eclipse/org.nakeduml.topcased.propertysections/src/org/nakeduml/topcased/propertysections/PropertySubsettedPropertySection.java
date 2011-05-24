package org.nakeduml.topcased.propertysections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.internal.sections.TableObjectManager;

public class PropertySubsettedPropertySection extends AbstractPropertyLookupSection{

	@Override
	protected Object getListValues(){
		return ((Property)getEObject()).getSubsettedProperties();
	}

	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getProperty_SubsettedProperty();
	}

	@Override
	protected String getLabelText(){
		return "Subsetted properties";
	}
}
