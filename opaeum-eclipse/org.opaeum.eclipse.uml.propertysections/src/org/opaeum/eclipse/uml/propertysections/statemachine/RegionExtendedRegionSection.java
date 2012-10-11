package org.opaeum.eclipse.uml.propertysections.statemachine;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfStateMachineUtil;
import org.opaeum.eclipse.uml.propertysections.base.OpaeumChooserPropertySection;

public class RegionExtendedRegionSection extends OpaeumChooserPropertySection{
	@Override
	protected Object getFeatureValue(){
		return getRegion().getExtendedRegion();
	}
	public Region getRegion(){
		return (Region) getEObject();
	}
	@Override
	protected Object[] getComboFeatureValues(){
		List<Object> result = new ArrayList<Object>();
		if(getRegion().getState() != null && getRegion().getState().getRedefinedState() != null){
			result.addAll(getRegion().getState().getRedefinedState().getRegions());
		}else{
			StateMachine sm = EmfStateMachineUtil.getStateMachine(getRegion());
			for(Classifier psm:sm.getGenerals()){
				if(psm instanceof StateMachine){
					result.addAll(((StateMachine) psm).getRegions());
				}
			}
		}
		result.add("");
		return result.toArray();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getRegion_ExtendedRegion();
	}
	@Override
	public String getLabelText(){
		return "Extended Region";
	}
}
