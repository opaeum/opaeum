package org.opeum.metamodel.core.internal;

import java.util.Collection;
import java.util.Collections;

import org.opeum.feature.MappingInfo;
import org.opeum.metamodel.bpm.internal.EmbeddedSingleScreenTaskMessageStructureImpl;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.components.INakedConnectorEnd;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedMultiplicity;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.IParameterOwner;
import org.opeum.metamodel.core.internal.emulated.AbstractPropertyBridge;
import org.opeum.metamodel.core.internal.emulated.MessageStructureImpl;
import org.opeum.metamodel.core.internal.emulated.OperationMessageStructureImpl;

import org.opeum.name.NameConverter;

public class ArtificialProperty extends AbstractPropertyBridge{
	private static final long serialVersionUID = 1L;
	private INakedClassifier baseType;
	private NakedMultiplicityImpl multiplicity;
	private String name;
	private boolean isOrdered;
	private INakedProperty otherEnd;
	private boolean isInverse;
	public ArtificialProperty(INakedClassifier type){
		this(type.getNestingClassifier(), type);
		initialiseNestedClasifier(type);
	}
	private void initialiseNestedClasifier(INakedClassifier type){
		this.isInverse = true;
		this.multiplicity = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
		this.name = NameConverter.decapitalize(type.getName());
		this.mappingInfo = (MappingInfo) type.getMappingInfo().getCopy();
		this.mappingInfo.setPersistentName(null);
		this.isOrdered = false;
		this.baseType = type;
		super.isComposite = true;
	}
	@Override
	public void setName(String umlName){
		this.name = umlName;
	}
	public ArtificialProperty(INakedBehavior behavior){
		this(behavior.getContext(), behavior);
		initializeOwnedBehavior(behavior);
	}
	private void initializeOwnedBehavior(INakedBehavior behavior){
		if(behavior == behavior.getContext().getClassifierBehavior()){
			this.multiplicity = new NakedMultiplicityImpl(1, 1);
			this.isInverse = true;
			this.type = baseType;
			this.name = "classifierBehavior";
			this.mappingInfo = (MappingInfo) behavior.getMappingInfo().getCopy();
			this.mappingInfo.setPersistentName(null);
		}else{
			initSequence(behavior);
		}
		super.isComposite = true;
	}
	public ArtificialProperty(MessageStructureImpl msg){
		super(getOwner(msg), msg);
		initializeMessageStructure(msg);
	}
	private void initializeMessageStructure(MessageStructureImpl msg){
		initSequence(msg);
	}
	private ArtificialProperty(ArtificialProperty otherEnd,String name){
		super(otherEnd.getBaseType(), otherEnd.getOwner());
		this.otherEnd = otherEnd;
		this.isInverse = false;
		this.multiplicity = new NakedMultiplicityImpl(1, 1);
		this.name = name;
		this.type = otherEnd.getOwner();
		this.baseType = otherEnd.getOwner();
		this.mappingInfo = (MappingInfo) otherEnd.getOwner().getMappingInfo().getCopy();
		this.mappingInfo.setPersistentName(null);
	}
	private ArtificialProperty(INakedClassifier nestingClassifier,INakedClassifier type2){
		super(nestingClassifier, type2);
		this.baseType = type2;
	}
	private ArtificialProperty(ArtificialProperty otherEnd){
		super(otherEnd.getBaseType(), otherEnd.getOwner());
		this.otherEnd = otherEnd;
		if(otherEnd.getOwner() instanceof MessageStructureImpl){
			initializeMessageStructure((MessageStructureImpl) otherEnd.getOwner());
		}else if(otherEnd.getOwner() instanceof INakedBehavior){
			initializeOwnedBehavior((INakedBehavior) otherEnd.getOwner());
		}else{
			initialiseNestedClasifier(otherEnd.getOwner());
		}
	}
	public ArtificialProperty(INakedProperty opposite){
		super(opposite.getNakedBaseType(), opposite.getOwner());
		this.otherEnd = opposite;
		this.multiplicity = new NakedMultiplicityImpl(1, 1);
		this.baseType = opposite.getOwner();
		this.type = this.baseType;
		this.isInverse = false;
		this.name = baseType.getName();
		this.mappingInfo = (MappingInfo) opposite.getOwner().getMappingInfo().getCopy();
		this.mappingInfo.setPersistentName(null);
	}
	private void initSequence(INakedClassifier behavior){
		this.multiplicity = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
		this.baseType = behavior;
		this.name = NameConverter.decapitalize(behavior.getName());
		this.isOrdered = true;
		this.isInverse = true;
		this.mappingInfo = (MappingInfo) behavior.getMappingInfo().getCopy();
		this.mappingInfo.setPersistentName(null);
	}
	private static INakedClassifier getOwner(MessageStructureImpl task){
		if(task instanceof OperationMessageStructureImpl){
			return ((OperationMessageStructureImpl) task).getOperation().getOwner();
		}else{
			return ((EmbeddedSingleScreenTaskMessageStructureImpl) task).getAction().getActivity();
		}
	}
	@Override
	public boolean isInverse(){
		return isInverse;
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
			if(baseType instanceof OperationMessageStructureImpl && ((OperationMessageStructureImpl) baseType).getOperation().getOwner() == owner){
				otherEnd = new ArtificialProperty(this, "contextObject");
			}else if(baseType instanceof IParameterOwner && ((IParameterOwner) baseType).getContext() == owner){
				otherEnd = new ArtificialProperty(this, "contextObject");
			}else if(baseType instanceof EmbeddedSingleScreenTaskMessageStructureImpl){
				otherEnd = new ArtificialProperty(this, "processObject");
			}else if(baseType instanceof INakedClassifier && baseType.getNestingClassifier() == owner){
				otherEnd = new ArtificialProperty(this, "ownerObject");
			}else{
				otherEnd = new ArtificialProperty(this);
			}
		}
		return otherEnd;
	}
	@Override
	public Collection<? extends INakedElement> getOwnedElements(){
		return Collections.emptySet();
	}
	@Override
	public boolean equals(Object other){
		if(other == this){
			return true;
		}else if(other instanceof ArtificialProperty){
			ArtificialProperty ap = (ArtificialProperty) other;
			return ap.originalElement.equals(originalElement) && ap.owner.equals(owner) && ap.name.equals(name);
		}else{
			return super.equals(other);
		}
	}
}
