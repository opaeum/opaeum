package org.opaeum.topcased.activitydiagram.propertysections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Event;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.tabbedproperties.sections.AbstractTextPropertySection;

public class ActivityParameterNodeDirectionSection extends AbstractTextPropertySection{
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getParameter_Direction();
	}
	protected String getLabelText(){
		return "Direction:";
	}
	@Override
	public void refresh(){
		super.refresh();
		setEnabled(false);
	}
	@Override
	protected String getFeatureAsString(){
		Parameter parameter = ((ActivityParameterNode) getEObject()).getParameter();
		if(parameter != null && parameter.getDirection() != null){
			return parameter.getDirection().getName();
		}else{
			return "";
		}
	}
	@Override
	protected Object getNewFeatureValue(String newText){
		return null;
	}
	@Override
	protected Object getOldFeatureValue(){
		return null;
	}
	@Override
	protected void verifyField(Event e){
	}
}
