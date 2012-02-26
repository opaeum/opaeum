package org.opaeum.topcased.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.UMLPackage;

public class PackageImportImportedPackageSection extends OpaeumChooserPropertySection{
	PackageImport getPackageImport(){
		return (PackageImport) getEObject();
	}
	@Override
	protected Object getFeatureValue(){
		return getPackageImport().getImportedPackage();
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> result = new ArrayList<Object>();
		TreeIterator<Notifier> allContents = getPackageImport().eResource().getResourceSet().getAllContents();
		while(allContents.hasNext()){
			Notifier notifier = (Notifier) allContents.next();
			if(notifier instanceof org.eclipse.uml2.uml.Package){
				result.add(notifier);
			}
		}
		return result.toArray();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getPackageImport_ImportedPackage();
	}
	@Override
	protected String getLabelText(){
		return "Imported Package";
	}
}
