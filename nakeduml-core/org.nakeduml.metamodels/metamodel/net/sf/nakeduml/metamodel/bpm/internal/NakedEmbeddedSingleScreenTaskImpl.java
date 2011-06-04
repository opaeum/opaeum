package net.sf.nakeduml.metamodel.bpm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.sf.nakeduml.metamodel.actions.ITargetElement;
import net.sf.nakeduml.metamodel.actions.internal.NakedOpaqueActionImpl;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import nl.klasse.octopus.stdlib.IOclLibrary;

public class NakedEmbeddedSingleScreenTaskImpl extends NakedOpaqueActionImpl implements INakedEmbeddedSingleScreenTask{
	private INakedMessageStructure asClass;
	private INakedResponsibilityDefinition taskDefinition;
	private List<INakedOutputPin> outputValues;
	private boolean isSynchronous;
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.getName().equalsIgnoreCase(StereotypeNames.EMBEDDED_SINGLE_SCREEN_TASK)){
			this.taskDefinition=new NakedResponsibilityDefinitionImpl(stereotype);
			addOwnedElement(this.taskDefinition);
		}
	}
	public List<INakedOutputPin> getOutputValues(){
		return outputValues;
	}
	public void setOutputValues(List<INakedOutputPin> outputValues){
		this.outputValues = outputValues;
	}
	public INakedResponsibilityDefinition getTaskDefinition(){
		return taskDefinition;
	}
	public void setTaskDefinition(INakedResponsibilityDefinition taskDefinition){
		this.taskDefinition = taskDefinition;
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
	public INakedMessageStructure getMessageStructure(IOclLibrary lib){
		if(asClass==null){
			asClass=new EmbeddedSingleScreenTaskMessageStructureImpl(this,lib);
		}
		return asClass;
	}
	public boolean isSynchronous(){
		return isSynchronous;
	}
	public void setSynchronous(boolean isSynchronous){
		this.isSynchronous = isSynchronous;
	}
}
