package net.sf.nakeduml.javageneration.util;

import net.sf.nakeduml.metamodel.actions.IActionWithTargetElement;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import nl.klasse.octopus.model.IClassifier;

//TODO implement oneToMany emulation with OpaqueActions
public class ActionFeatureBridge extends TypedElementPropertyBridge{
	private static final long serialVersionUID = 620463438474285488L;
	INakedClassifier baseType;
	IClassifier type;
	private IActionWithTargetElement action;
	NakedMultiplicityImpl multiplicity=null;
	public NakedMultiplicityImpl getNakedMultiplicity() {
		return multiplicity;
	}
	public void setMultiplicity(NakedMultiplicityImpl multiplicity) {
		this.multiplicity = multiplicity;
	}
	public ActionFeatureBridge(IActionWithTargetElement action){
		super(action.getActivity(), action.getTargetElement());
		super.element=action;
		if(action instanceof INakedCallAction){
			baseType = ((INakedCallAction) action).getMessageStructure();
		}
		if(action.getTargetElement()==null){
			this.multiplicity=new NakedMultiplicityImpl(0, 1);
		}else{
			this.multiplicity=(NakedMultiplicityImpl) action.getTargetElement().getNakedMultiplicity();
		}
		this.action=action;
	}
	public INakedAction getAction(){
		return action;
	}
	public boolean isOrdered(){
		return super.parameter==null?false:super.parameter.isOrdered();
	}
	public boolean isUnique(){
		return super.parameter==null?false:super.parameter.isUnique();
	}
	@Override
	public INakedClassifier getNakedBaseType(){
		return baseType;
	}
	public String getName(){
		return action.getName();
	}
	public IClassifier getType() {
		return type;
	}
	public void setType(IClassifier type) {
		this.type = type;
	}
}
