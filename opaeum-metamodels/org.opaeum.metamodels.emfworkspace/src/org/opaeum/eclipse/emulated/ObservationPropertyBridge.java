package org.opaeum.eclipse.emulated;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Observation;
import org.opaeum.emf.workspace.EmfWorkspace;

public class ObservationPropertyBridge extends AbstractEmulatedProperty{
	public ObservationPropertyBridge(Classifier owner,Observation originalElement, DataType type){
		super(owner, originalElement);
		setType(type);
		setUpper(1);
	}
	@Override
	public String getId(){
		return EmfWorkspace.getId(getOriginalElement()) + "@OBS";
	}
	@Override
	public boolean shouldEmulate(){
		return true;
	}
}
