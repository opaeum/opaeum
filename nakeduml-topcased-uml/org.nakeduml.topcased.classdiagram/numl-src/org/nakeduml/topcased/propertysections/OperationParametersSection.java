package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.MixedEditDomain;

public class OperationParametersSection extends AbstractParametersSection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getBehavioralFeature_OwnedParameter();
	}
	protected String getLabelText(){
		return null;
	}
}