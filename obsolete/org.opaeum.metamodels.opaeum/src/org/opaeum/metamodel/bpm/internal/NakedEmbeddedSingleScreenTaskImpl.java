package org.opaeum.metamodel.bpm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.uml2.uml.INakedInstanceSpecification;
import org.eclipse.uml2.uml.INakedMessageStructure;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedPin;
import org.eclipse.uml2.uml.INakedStereotype;
import org.eclipse.uml2.uml.ITargetElement;
import org.opaeum.metamodel.actions.internal.NakedOpaqueActionImpl;
import org.opaeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opaeum.metamodel.bpm.INakedResponsibilityDefinition;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class NakedEmbeddedSingleScreenTaskImpl extends NakedOpaqueActionImpl implements INakedEmbeddedSingleScreenTask{
	private static final long serialVersionUID = -1263880243304275436L;
	private INakedMessageStructure asClass;
	private INakedResponsibilityDefinition taskDefinition;
	private List<INakedOutputPin> outputValues;
	private boolean isSynchronous;
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.getName().equalsIgnoreCase(StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK) && stereotype.getClassifier() instanceof INakedStereotype){
			removeOwnedElement(this.taskDefinition, true);
			this.taskDefinition=new NakedResponsibilityDefinitionImpl(stereotype);
			addOwnedElement(this.taskDefinition);
		}
	}
	public List<INakedOutputPin> getOutputValues(){
		return outputValues;
	}
	public void setOutputValues(List<INakedOutputPin> outputValues){
		replacePins(this.outputValues,outputValues);
		this.outputValues = outputValues;
	}
	public INakedResponsibilityDefinition getTaskDefinition(){
		return taskDefinition;
	}
	@Override
	public Collection<INakedOutputPin> getOutput(){
		return new HashSet<INakedOutputPin>(getOutputValues());
	}
	@Override
	public ITargetElement getTargetElement(){
		return getInPartition();
	}
	@Override
	public List<INakedPin> getPins(){
		List<INakedPin> result = new ArrayList<INakedPin>(getInputValues());
		result.addAll(getOutputValues());
		return result;
	}
	@Override
	public INakedMessageStructure getMessageStructure(){
		return asClass;
	}
	@Override
	public void initMessageStructure(){
		asClass=new EmbeddedSingleScreenTaskMessageStructureImpl(this);
	}
	public boolean isSynchronous(){
		return isSynchronous;
	}
	public void setSynchronous(boolean isSynchronous){
		this.isSynchronous = isSynchronous;
	}
	@Override
	public boolean isLongRunning(){
		return true;
	}
}
