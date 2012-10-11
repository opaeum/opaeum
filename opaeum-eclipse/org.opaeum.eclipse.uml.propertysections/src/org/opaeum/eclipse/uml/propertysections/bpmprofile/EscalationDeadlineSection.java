package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.uml.propertysections.base.ChooserPropertyOnStereotypeSection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class EscalationDeadlineSection extends ChooserPropertyOnStereotypeSection{
	@Override
	protected Element getElement(EObject o){
		return (Constraint)o;
	}
	@Override
	public String getLabelText(){
		return "Deadline:";
	}

	@Override
	protected String getAttributeName(){
		return "deadline";
		
	}
	@Override
	protected String getProfileName(){
		return StereotypeNames.OPAEUM_BPM_PROFILE;
	}
	@Override
	protected String getStereotypeName(){
		return StereotypeNames.ESCALATION;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected Object[] getComboFeatureValues(){
		Element o=(Element) getEObject().eContainer();
		List<Object> result=new ArrayList<Object>();
		for(EObject eObject:o.getStereotypeApplications()){
			EStructuralFeature f = eObject.eClass().getEStructuralFeature(TagNames.DEADLINES);
			if(f!=null){
				result.addAll((List<Object>) eObject.eGet(f));
				
			}
		}
		result.add("");
		return result.toArray();
	}
}
