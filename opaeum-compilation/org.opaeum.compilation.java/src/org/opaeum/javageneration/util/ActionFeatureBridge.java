package org.opaeum.javageneration.util;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;

import org.opaeum.metamodel.actions.IActionWithTargetElement;
import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.bpm.INakedEmbeddedTask;
import org.opaeum.metamodel.components.INakedConnectorEnd;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.core.internal.NakedMultiplicityImpl;
import org.opaeum.metamodel.core.internal.emulated.AbstractPropertyBridge;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opeum.name.NameConverter;

public class ActionFeatureBridge extends AbstractPropertyBridge{
	private static final long serialVersionUID = 620463438474285488L;
	IClassifier type;
	private INakedAction action;
	NakedMultiplicityImpl multiplicity = null;
	private INakedClassifier baseType;
	private INakedTypedElement targetElement;
	
	public NakedMultiplicityImpl getNakedMultiplicity(){
		return multiplicity;
	}
	public void setMultiplicity(NakedMultiplicityImpl multiplicity){
		this.multiplicity = multiplicity;
	}
	public ActionFeatureBridge(INakedAcceptCallAction action, OpaeumLibrary lib){
		super(action.getActivity(), action);
		this.multiplicity = new NakedMultiplicityImpl(0, 1);//TODO What if invoked multiple times before reply takes place???
		this.baseType = action.getOperation().getMessageStructure();
		setType(getNakedBaseType());
		this.action = action;
	}
	public ActionFeatureBridge(IActionWithTargetElement action, OpaeumLibrary lib){
		super(action.getActivity(), action);
		targetElement = action.getTargetElement();
		this.baseType = getType(action);
		if(targetElement == null){
			this.multiplicity = new NakedMultiplicityImpl(0, 1);
			setType(getNakedBaseType());
		}else{
			this.multiplicity = (NakedMultiplicityImpl) targetElement.getNakedMultiplicity();
			IClassifier type = targetElement.getType();
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
		return targetElement == null ? false : targetElement.isOrdered();
	}
	public boolean isUnique(){
		return targetElement == null ? false : targetElement.isUnique();
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
