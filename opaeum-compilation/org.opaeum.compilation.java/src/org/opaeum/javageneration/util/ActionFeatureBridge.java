package org.opaeum.javageneration.util;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.name.NameConverter;

public class ActionFeatureBridge extends AbstractEmulatedProperty{
	private static final long serialVersionUID = 620463438474285488L;
	Classifier type;
	private Action action;
	private Classifier baseType;
	private TypedElement targetElement;

	public ActionFeatureBridge(AcceptCallAction action,OpaeumLibrary lib){
		super(action.getNearestStructuredElementAsClassifier(), action);
		this.multiplicity = new NakedMultiplicityImpl(0, 1);// TODO What if invoked multiple times before reply takes place???
		this.baseType =  action.getOperation().getMessageStructure();
		setType(getType());
		this.action = action;
	}
	public ActionFeatureBridge(IActionWithTargetElement action,OpaeumLibrary lib){
		super(action.getNearestStructuredElementAsClassifier(), action);
		targetElement = action.getTargetElement();
		this.baseType = getType(action);
		if(targetElement == null){
			this.multiplicity = new NakedMultiplicityImpl(0, 1);
			setType((Classifier) getType());
		}else{
			this.multiplicity = (NakedMultiplicityImpl) targetElement.getNakedMultiplicity();
			Classifier type = targetElement.getActualType();
			if(type instanceof CollectionType){
				setType((Classifier) lib.getTypeResolver().resolveCollectionType(((CollectionType) type).getKind(), ((CollectionType) type).getElementType()));
			}else{
				setType((Classifier) getType());
			}
		}
		this.action = action;
	}
	private static Classifier getType(IActionWithTargetElement action){
		Classifier baseType = null;
		if(action instanceof CallAction){
			baseType = ((CallAction) action).getMessageStructure();
		}else if(action instanceof EmbeddedTask){
			baseType = ((EmbeddedTask) action).getMessageStructure();
		}
		return baseType;
	}
	public Action getAction(){
		return action;
	}
	public boolean isOrdered(){
		return targetElement instanceof MultiplicityElement ?  ((MultiplicityElement) targetElement).isOrdered():false;
	}
	public boolean isUnique(){
		return targetElement instanceof MultiplicityElement ?  ((MultiplicityElement) targetElement).isUnique():false;
	}
	@Override
	public Type getType(){
		return baseType;
	}
	public String getName(){
		return "invoked" + NameConverter.capitalize(action.getName());
	}
	public Classifier getActualType(){
		return type;
	}
	public void setType(Classifier type){
		this.type = type;
	}
	@Override
	public Collection<ConnectorEnd> getConnectorEnd(){
		return Collections.emptySet();
	}

}
