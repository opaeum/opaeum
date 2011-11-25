package org.opaeum.metamodel.activities.internal;

import nl.klasse.octopus.model.IAttribute;

import org.opaeum.metamodel.activities.INakedActivity;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedStructuredActivityNode;
import org.opaeum.metamodel.commonbehaviors.INakedDurationObservation;
import org.opaeum.metamodel.commonbehaviors.INakedTimeObservation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NonInverseArtificialProperty;
import org.opaeum.metamodel.core.internal.emulated.EmulatedCompositionMessageStructure;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;

public class StructuredActivityNodeClassifier extends EmulatedCompositionMessageStructure{
	private static final long serialVersionUID = -4409191496009055952L;
	private INakedStructuredActivityNode node;
	private NonInverseArtificialProperty endToSelf;
	private NonInverseArtificialProperty endToContext;
	public StructuredActivityNodeClassifier(INakedStructuredActivityNode element){
		super(getOwner(element), element);
		this.node = element;
		reinitialize();
	}
	public boolean isPersistent(){
		return node.getActivity().isPersistent();
	}
	@Override
	public void reinitialize(){
		super.reinitialize();
		removeOwnedElement(endToSelf, true);
		endToSelf = new NonInverseArtificialProperty("self", node.getActivity(), true);
		addOwnedElement(endToSelf);
		if(node.getActivity().getContext() != null){
			removeOwnedElement(endToContext, true);
			endToContext = new NonInverseArtificialProperty("contextObject", node.getActivity().getContext(), true);
			addOwnedElement(endToContext);
		}
		for(INakedActivityVariable v:node.getVariables()){
			addOwnedElement(new TypedElementPropertyBridge(this, v));
		}
		for(INakedTimeObservation o:this.node.getTimeObservations()){
			addOwnedElement(new TypedElementPropertyBridge(this, o));
		}
		for(INakedDurationObservation o:this.node.getDurationObservations()){
			addOwnedElement(new TypedElementPropertyBridge(this, o));
		}
	}
	@Override
	public IAttribute findAttribute(String attName){
		IAttribute findAttribute = super.findAttribute(attName);
		if(findAttribute == null){
			if(getOwner() instanceof StructuredActivityNodeClassifier){
				findAttribute = ((INakedClassifier) getOwner()).findAttribute(attName);
			}
		}
		return findAttribute;
	}
	public INakedClassifier getNearestStructuredParent(){
		return getOwner(node);
	}
	private static INakedClassifier getOwner(INakedElement originalElement2){
		INakedClassifier result = null;
		if(originalElement2.getOwnerElement() instanceof INakedActivity){
			result = (INakedClassifier) originalElement2.getOwnerElement();
		}else if(originalElement2.getOwnerElement() instanceof INakedStructuredActivityNode){
			INakedStructuredActivityNode owner = (INakedStructuredActivityNode) originalElement2.getOwnerElement();
			result = ((StructuredActivityNodeClassifier) owner.getMessageStructure()).getNearestStructuredParent();
		}
		return result;
	}
	public INakedProperty getEndToSelf(){
		return endToSelf;
	}
	public INakedProperty getEndToContext(){
		return endToContext;
	}
}
