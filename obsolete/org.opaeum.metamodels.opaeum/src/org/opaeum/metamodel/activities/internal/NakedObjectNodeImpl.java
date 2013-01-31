package org.opaeum.metamodel.activities.internal;

import java.util.Collection;

import nl.klasse.octopus.model.IClassifier;

import org.eclipse.uml2.uml.INakedActivityEdge;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedEnumerationLiteral;
import org.eclipse.uml2.uml.INakedExceptionHandler;
import org.eclipse.uml2.uml.INakedExpansionNode;
import org.eclipse.uml2.uml.INakedInstanceSpecification;
import org.eclipse.uml2.uml.INakedMultiplicity;
import org.eclipse.uml2.uml.INakedMultiplicityElement;
import org.eclipse.uml2.uml.INakedObjectFlow;
import org.eclipse.uml2.uml.INakedObjectNode;
import org.eclipse.uml2.uml.INakedValueSpecification;
import org.eclipse.uml2.uml.ObjectNodeType;
import org.opaeum.metamodel.core.internal.NakedMultiplicityElement;

public class NakedObjectNodeImpl extends NakedActivityNodeImpl implements INakedObjectNode{
	private static final long serialVersionUID = 1789017383946876842L;
	private boolean ordered;
	private boolean unique;
	protected INakedMultiplicity multiplicity;
	protected IClassifier type;
	private INakedClassifier baseType;
	private int index;
	private INakedExceptionHandler incomingExceptionHandler;
	private INakedValueSpecification value;
	public INakedObjectNode getFeedingNode(){
		return getObjectNodeSource(getIncoming());
	}
	private boolean isMeasure;
	private boolean isDimension;
	public boolean isMeasure(){
		return isMeasure;
	}
	public boolean isDimension(){
		return isDimension;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature("roleInCube")){
			INakedEnumerationLiteral l = (INakedEnumerationLiteral) stereotype.getFirstValueFor("roleInCube").getValue();
			if(l.getName().equalsIgnoreCase("measure")){
				isMeasure = true;
			}else if(l.getName().equalsIgnoreCase("dimension")){
				isDimension = true;
			}
		}
	}
	@Override
	public boolean canAcceptInputFrom(INakedMultiplicityElement from){
		return from.fitsInTo(this);
	}
	@Override
	public boolean canDeliverOutputTo(INakedMultiplicityElement to){
		if(to instanceof INakedExpansionNode)
			if(((INakedExpansionNode) to).isInputElement()){
				return getNakedMultiplicity().getUpper() > 1;
			}else{
				return true;
			}
		else{
			return fitsInTo(to);
		}
	}
	public INakedExceptionHandler getIncomingExceptionHandler(){
		return incomingExceptionHandler;
	}
	public void setIncomingExceptionHandler(INakedExceptionHandler incomingExceptionHandler){
		this.incomingExceptionHandler = incomingExceptionHandler;
	}
	private INakedObjectNode getObjectNodeSource(Collection<INakedActivityEdge> source){
		for(INakedActivityEdge edge:source){
			return ((INakedObjectFlow) edge).getOriginatingObjectNode();
		}
		return null;
	}
	public INakedObjectNode getFedNode(){
		return getObjectNodeTarget(getOutgoing());
	}
	private INakedObjectNode getObjectNodeTarget(Collection<INakedActivityEdge> outgoing){
		for(INakedActivityEdge edge:outgoing){
			return ((INakedObjectFlow) edge).getFedObjectNode();
		}
		return null;
	}
	public int getIndex(){
		return this.index;
	}
	public void setIndex(int index){
		this.index = index;
	}
	public boolean isOrdered(){
		return this.ordered;
	}
	public void setIsOrdered(boolean isOrdered){
		this.ordered = isOrdered;
	}
	public boolean isUnique(){
		return this.unique;
	}
	public void setIsUnique(boolean isUnique){
		this.unique = isUnique;
	}
	public IClassifier getType(){
		return this.type;
	}
	public void setType(IClassifier type){
		this.type = type;
	}
	public INakedMultiplicity getNakedMultiplicity(){
		return this.multiplicity;
	}
	public void setMultiplicity(INakedMultiplicity multiplicity){
		this.multiplicity = multiplicity;
	}
	public void setBaseType(INakedClassifier type){
		this.baseType = type;
	}
	public INakedClassifier getNakedBaseType(){
		return this.baseType;
	}
	public ObjectNodeType getObjectNodeType(){
		return ObjectNodeType.CENTRAL_BUFFER;
	}
	@Override
	public boolean fitsInTo(INakedMultiplicityElement other){
		return NakedMultiplicityElement.fitsInto(this, other);
	}
	@Override
	public INakedValueSpecification getUpperBound() {
		return this.value;
	}
	public void setUpperBound(INakedValueSpecification value) {
		this.value = value;
	}
}