package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;



public class GeneralizationGeneralSection extends AbstractChooserPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getGeneralization_General();
	}
	public String getLabelText(){
		return "Classifier specialized:";
	}
	protected Object[] getComboFeatureValues(){
		
		List<Object> choices = new ArrayList<Object>();
		choices.add("");
		Collection<EObject> types = OpaeumEclipseContext.getReachableObjectsOfType(getEObject(), ((Generalization)getEObject()).getSpecific().eClass());
		final Iterator<EObject> iterator = types.iterator();
		while(iterator.hasNext()){
			Classifier eObject = (Classifier) iterator.next();
			final EClass specific = ((Generalization)getEObject()).getSpecific().eClass();
			final EClass general = eObject.eClass();
			if(!general.equals(specific)){
				iterator.remove();
			}
		}
		types.remove(((Generalization)getEObject()).getSpecific());

		choices.addAll(UmlMetaTypeRemover.removeAssocations(types));
		return choices.toArray();
	}
	protected Object getFeatureValue(){
		return ((Generalization)getEObject()).getGeneral();
	}
}