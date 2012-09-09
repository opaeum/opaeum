package org.opaeum.eclipse.uml.propertysections.statemachine;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Vertex;
import org.opaeum.eclipse.uml.propertysections.base.OpaeumChooserPropertySection;

public class StateRedefinedStateSection extends OpaeumChooserPropertySection{
	@Override
	protected Object getFeatureValue(){
		return getState().getRedefinedState();
	}
	public State getState(){
		return (State) getEObject();
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> result = new ArrayList<Object>();
		if(getState().getContainer().getExtendedRegion() != null){
			EList<Vertex> subvertices = getState().getContainer().getExtendedRegion().getSubvertices();
			for(Vertex vertex:subvertices){
				if(vertex instanceof State){
					result.add(vertex);
				}
			}
		}
		result.add("");
		return result.toArray();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getState_RedefinedState();
	}
	@Override
	protected String getLabelText(){
		return "Redefined State";
	}
}
