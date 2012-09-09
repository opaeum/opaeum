package org.opaeum.eclipse.emulated;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeObservation;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class ObservationPropertyBridge extends AbstractEmulatedProperty {
	IPropertyEmulation propertyEmulation;
	public ObservationPropertyBridge(Classifier owner,Observation originalElement, IPropertyEmulation pe){
		super(owner, originalElement);
		setUpper(1);
		this.propertyEmulation=pe;
	}
	@Override
	public Type getType(){
		if(getOriginalElement() instanceof TimeObservation){
			return propertyEmulation.getDateTimeType();
		}else if(EmfTimeUtil.isCumulative((DurationObservation) getOriginalElement())){
			return propertyEmulation.getCumulativeDurationType();
		}else{
			return propertyEmulation.getDurationType();
		}
	}
	@Override
	public String getId(){
		return EmfWorkspace.getId(getOriginalElement()) + "@OBS";
	}
	@Override
	public boolean shouldEmulate(){
		return true;
	}
	@Override
	public int getUpper(){
		return 1;
	}
}
