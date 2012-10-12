package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractStringPropertySection;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumTextViewer;
import org.topcased.tabbedproperties.sections.widgets.IText;

public class ElementImportAliasSection extends AbstractStringPropertySection{
	public IText getTextWidget(Composite parent,int style){
		return new OpaeumTextViewer(parent, style);
	}
	@Override
	public String getLabelText(){
		return "Alias:";
	}
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getElementImport_Alias();
	}
}
