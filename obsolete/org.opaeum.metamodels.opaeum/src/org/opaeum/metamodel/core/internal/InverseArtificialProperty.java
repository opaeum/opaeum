package org.opaeum.metamodel.core.internal;

import java.util.Collection;
import java.util.Collections;

import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.internal.types.OclExpression;

import org.eclipse.uml2.uml.CallBehaviorMessageStructure;
import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedBehavioredClassifier;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedConnectorEnd;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedMessageStructure;
import org.eclipse.uml2.uml.INakedMultiplicity;
import org.eclipse.uml2.uml.INakedProperty;
import org.eclipse.uml2.uml.IParameterOwner;
import org.opaeum.metamodel.activities.internal.StructuredActivityNodeClassifier;
import org.opaeum.metamodel.bpm.internal.EmbeddedSingleScreenTaskMessageStructureImpl;
import org.opaeum.metamodel.core.internal.emulated.AbstractEmulatedProperty;
import org.opaeum.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import org.opaeum.name.NameConverter;

public class InverseArtificialProperty extends AbstractEmulatedProperty implements IVariableDeclaration{
	private static final long serialVersionUID = 1L;
	private INakedClassifier baseType;
	private NakedMultiplicityImpl multiplicity;
	private String name;
	private boolean isOrdered;
	private boolean isDerived;
	public InverseArtificialProperty(INakedBehavioredClassifier context,INakedBehavior behavior){
		super(context, behavior);
		initializeOwnedBehavior(behavior);
	}
	public InverseArtificialProperty(INakedClassifier nestingClassifier,INakedClassifier type){
		super(nestingClassifier, type);
		initialiseNestedClasifier(type);
	}
	private void initialiseNestedClasifier(INakedClassifier type){
		this.multiplicity = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
		this.name = NameConverter.decapitalize(type.getName());
		this.mappingInfo.setPersistentName(null);
		this.isOrdered = false;
		this.baseType = type;
		super.isComposite = true;
	}
	@Override
	public void setName(String umlName){
		this.name = umlName;
	}
	private void initializeOwnedBehavior(INakedBehavior behavior){
		if(behavior == behavior.getContext().getClassifierBehavior()){
			this.multiplicity = new NakedMultiplicityImpl(1, 1);
			this.type = baseType=behavior;
			this.name = "classifierBehavior";
			this.mappingInfo.setPersistentName(null);
		}else{
			initSequence(behavior);
		}
		super.isComposite = true;
	}
	public InverseArtificialProperty(INakedClassifier owner,OperationMessageStructureImpl msg){
		super(owner, msg);
		initializeMessageStructure(msg);
	}
	public InverseArtificialProperty(INakedClassifier owner,StructuredActivityNodeClassifier msg){
		super(owner, msg);
		initializeMessageStructure(msg);
	}
	public InverseArtificialProperty(INakedClassifier owner,EmbeddedSingleScreenTaskMessageStructureImpl msg){
		super(owner, msg);
		initializeMessageStructure(msg);
	}
	public InverseArtificialProperty(INakedClassifier owner,CallBehaviorMessageStructure msg){
		super(owner, msg);
		initializeMessageStructure(msg);
	}
	private void initializeMessageStructure(INakedMessageStructure msg){
		initSequence(msg);
		super.isComposite = true;
		
	}
	private void initSequence(INakedClassifier behavior){
		this.multiplicity = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
		this.baseType = behavior;
		this.name = behavior.getName();
		this.isOrdered = true;
		this.mappingInfo.setPersistentName(null);
	}
	@Override
	public boolean isInverse(){
		return true;
	}
	@Override
	public INakedMultiplicity getNakedMultiplicity(){
		return multiplicity;
	}
	@Override
	public INakedClassifier getNakedBaseType(){
		return baseType;
	}
	public String getName(){
		return name;
	}
	@Override
	public boolean isOrdered(){
		return isOrdered;
	}
	@Override
	public boolean isUnique(){
		return !isOrdered;
	}
	@Override
	public Collection<INakedConnectorEnd> getConnectorEnd(){
		return Collections.emptySet();
	}
	public INakedClassifier getBaseType(){
		return baseType;
	}
	public NakedMultiplicityImpl getMultiplicity(){
		return multiplicity;
	}
	public INakedProperty getOtherEnd(){
		if(otherEnd == null){
			if(baseType instanceof StructuredActivityNodeClassifier){
				otherEnd = new NonInverseArtificialProperty(this, "nodeContainer");
			}else if(baseType instanceof OperationMessageStructureImpl && ((OperationMessageStructureImpl) baseType).getOperation().getOwner() == owner){
				otherEnd = new NonInverseArtificialProperty(this, "contextObject");
			}else if(baseType instanceof IParameterOwner && ((IParameterOwner) baseType).getContext() == owner){
				otherEnd = new NonInverseArtificialProperty(this, "contextObject");
			}else if(baseType instanceof EmbeddedSingleScreenTaskMessageStructureImpl){
				otherEnd = new NonInverseArtificialProperty(this, "processObject");
			}else if(baseType instanceof INakedClassifier && baseType.getNestingClassifier() == owner){
				otherEnd = new NonInverseArtificialProperty(this, "ownerObject");
			}
		}
		return otherEnd;
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
		return Collections.emptySet();
	}
	@Override
	public boolean equals(Object other){
		if(other == this){
			return true;
		}else if(other instanceof InverseArtificialProperty){
			InverseArtificialProperty ap = (InverseArtificialProperty) other;
			return ap.originalElement.equals(originalElement) && ap.owner.equals(owner) && ap.name.equals(name);
		}else{
			return super.equals(other);
		}
	}
	@Override
	public OclExpression getInitExpression(){
		return null;
	}
	@Override
	public boolean isIteratorVar(){
		return false;
	}
	public boolean isDerived(){
		return isDerived;
	}
}
