package org.opaeum.topcased.uml.editor;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.uml.editor.outline.UMLMetamodelFilter;

public class OpaeumMetaModelFilter extends UMLMetamodelFilter{

	@Override
	public EList<EClassifier> getMetamodelElements(){
		BasicEList<EClassifier> result = new BasicEList<EClassifier>(super.getMetamodelElements());
		result.remove(UMLPackage.eINSTANCE.getClass_());
		return result;
	}


}
