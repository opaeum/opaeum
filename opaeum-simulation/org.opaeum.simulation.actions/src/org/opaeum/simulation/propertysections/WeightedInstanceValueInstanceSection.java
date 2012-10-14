package org.opaeum.simulation.propertysections;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.StructuralFeature;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;
import org.opaeum.metamodels.simulation.simulation.ActualInstance;
import org.opaeum.metamodels.simulation.simulation.InstanceSimulation;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;
import org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue;

public class WeightedInstanceValueInstanceSection extends AbstractChooserPropertySection{
	@Override
	protected Object getFeatureValue(){
		// TODO Auto-generated method stub
		return getWeightedInstanceValue().getInstance();
	}
	public WeightedInstanceValue getWeightedInstanceValue(){
		return (WeightedInstanceValue) getEObject();
	}
	@Override
	protected Object[] getComboFeatureValues(){
		Collection<Object> result = new ArrayList<Object>();
		result.add("");
		TreeIterator<EObject> allContents = getWeightedInstanceValue().eResource().getAllContents();
		while(allContents.hasNext()){
			EObject eObject = (EObject) allContents.next();
			if(eObject instanceof InstanceSimulation || eObject instanceof ActualInstance){
				InstanceSpecification is = (InstanceSpecification) eObject;
				if(is.getClassifiers().size() == 1 && getDefiniedFeature()!=null){
					if(is.getClassifiers().get(0).conformsTo(getDefiniedFeature().getType())){
						result.add(eObject);
					}
				}
			}
		}
		return result.toArray();
	}
	public StructuralFeature getDefiniedFeature(){
		return ((Slot) getWeightedInstanceValue().eContainer()).getDefiningFeature();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return SimulationPackage.eINSTANCE.getWeightedInstanceValue_Instance();
	}
	@Override
	public String getLabelText(){
		return "Instance";
	}
}
