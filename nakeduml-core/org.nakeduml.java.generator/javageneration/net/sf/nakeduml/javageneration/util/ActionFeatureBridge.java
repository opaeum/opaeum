package net.sf.nakeduml.javageneration.util;

import org.nakeduml.name.NameConverter;

import net.sf.nakeduml.metamodel.actions.IActionWithTargetElement;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;

public class ActionFeatureBridge extends TypedElementPropertyBridge{
	private static final long serialVersionUID = 620463438474285488L;
	INakedClassifier baseType;
	IClassifier type;
	private IActionWithTargetElement action;
	NakedMultiplicityImpl multiplicity = null;
	public NakedMultiplicityImpl getNakedMultiplicity(){
		return multiplicity;
	}
	public void setMultiplicity(NakedMultiplicityImpl multiplicity){
		this.multiplicity = multiplicity;
	}
	public ActionFeatureBridge(IActionWithTargetElement action,NakedUmlLibrary lib){
		super(action.getActivity(), action.getTargetElement());
		super.originalElement = action;
		if(action instanceof INakedCallAction){
			baseType = ((INakedCallAction) action).getMessageStructure(lib);
		}else if(action instanceof INakedEmbeddedTask){
			baseType = ((INakedEmbeddedTask) action).getMessageStructure(lib);
		}
		if(action.getTargetElement() == null){
			this.multiplicity = new NakedMultiplicityImpl(0, 1);
		}else{
			this.multiplicity = (NakedMultiplicityImpl) action.getTargetElement().getNakedMultiplicity();
		}
		this.action = action;
		if(action.getTargetElement() != null){
			IClassifier type = action.getTargetElement().getType();
			if(type instanceof StdlibCollectionType){
				setType(lib.getOclLibrary(). lookupCollectionType(((StdlibCollectionType) type).getMetaType(), getNakedBaseType()));
			}else{
				setType(getNakedBaseType());
			}
		}else{
			setType(getNakedBaseType());
		}
	}
	public INakedAction getAction(){
		return action;
	}
	public boolean isOrdered(){
		return super.parameter == null ? false : super.parameter.isOrdered();
	}
	public boolean isUnique(){
		return super.parameter == null ? false : super.parameter.isUnique();
	}
	@Override
	public INakedClassifier getNakedBaseType(){
		return baseType;
	}
	public String getName(){
		return "invoked" + NameConverter.capitalize(action.getName()) ;
	}
	public IClassifier getType(){
		return type;
	}
	public void setType(IClassifier type){
		this.type = type;
	}
}
