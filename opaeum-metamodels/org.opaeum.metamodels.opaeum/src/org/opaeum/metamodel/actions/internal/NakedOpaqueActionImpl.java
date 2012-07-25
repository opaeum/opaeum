package org.opaeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedOpaqueAction;
import org.opaeum.metamodel.activities.internal.NakedActionImpl;

public abstract class NakedOpaqueActionImpl extends NakedActionImpl implements INakedOpaqueAction{
	private static final long serialVersionUID = -8741446980774863436L;
	private List<INakedInputPin> inputValues;
	@Override
	public Collection<INakedInputPin> getInput(){
		return inputValues;
	}
	public void setInputValues(List<INakedInputPin> inputValues){
		replacePins(this.inputValues, inputValues);
		this.inputValues = inputValues;
	}
	@Override
	public List<INakedInputPin> getInputValues(){
		return inputValues;
	}
}
