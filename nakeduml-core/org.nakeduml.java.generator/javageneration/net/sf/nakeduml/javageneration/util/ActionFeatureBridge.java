package net.sf.nakeduml.javageneration.util;

import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.metamodel.actions.IActionWithTargetElement;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.components.INakedConnectorEnd;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.AbstractPropertyBridge;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;

import org.nakeduml.name.NameConverter;

public class ActionFeatureBridge extends AbstractPropertyBridge{
	private static final long serialVersionUID = 620463438474285488L;
	IClassifier type;
	private IActionWithTargetElement action;
	NakedMultiplicityImpl multiplicity = null;
	private INakedClassifier baseType;
	public NakedMultiplicityImpl getNakedMultiplicity(){
		return multiplicity;
	}
	public void setMultiplicity(NakedMultiplicityImpl multiplicity){
		this.multiplicity = multiplicity;
	}
	public ActionFeatureBridge(IActionWithTargetElement action, NakedUmlLibrary lib){
		super(action.getActivity(), action);
		this.baseType = getType(action);
		if(action.getTargetElement() == null){
			this.multiplicity = new NakedMultiplicityImpl(0, 1);
			setType(getNakedBaseType());
		}else{
			this.multiplicity = (NakedMultiplicityImpl) action.getTargetElement().getNakedMultiplicity();
			IClassifier type = action.getTargetElement().getType();
			if(type instanceof StdlibCollectionType){
				setType(lib.getOclLibrary(). lookupCollectionType(((StdlibCollectionType) type).getMetaType(), getNakedBaseType()));
			}else{
				setType(getNakedBaseType());
			}
		}
		this.action = action;
	}
	private static INakedClassifier getType(IActionWithTargetElement action){
		INakedClassifier baseType=null;
		if(action instanceof INakedCallAction){
			baseType = ((INakedCallAction) action).getMessageStructure();
		}else if(action instanceof INakedEmbeddedTask){
			baseType = ((INakedEmbeddedTask) action).getMessageStructure();
		}
		return baseType;
	}
	public INakedAction getAction(){
		return action;
	}
	public boolean isOrdered(){
		return action.getTargetElement() == null ? false : action.getTargetElement().isOrdered();
	}
	public boolean isUnique(){
		return action.getTargetElement() == null ? false : action.getTargetElement().isUnique();
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
	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd(){
		return Collections.emptySet();
	}
}
