package org.opaeum.simulation.propertysections;

import java.text.NumberFormat;
import java.text.ParseException;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.eclipse.uml.propertysections.base.AbstractStringPropertySection;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;
import org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue;

public class WeightedInstanceValueWeightSection extends AbstractStringPropertySection{
	@Override
	protected EStructuralFeature getFeature(){
		return SimulationPackage.eINSTANCE.getWeightedInstanceValue_Weight();
	}
	@Override
	public String getLabelText(){
		return "Weight";
	}
	protected Object getNewFeatureValue(String newText){
		try{
			return NumberFormat.getInstance().parse(newText).doubleValue();
		}catch(ParseException e){
			return 0d;
		}
	}
	protected String getFeatureAsString(){
		Double weight = getWeightedInstanceValue().getWeight();
		if(weight == null){
			return "";
		}else{
			return NumberFormat.getInstance().format(weight);
		}
	}
	protected Object getOldFeatureValue(){
		return getWeightedInstanceValue().getWeight();
	}
	public WeightedInstanceValue getWeightedInstanceValue(){
		return (WeightedInstanceValue) getSelectedObject();
	}
}
