package net.sf.nakeduml.metamodel.core.internal;

import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.metamodel.bpm.internal.EmbeddedSingleScreenTaskMessageStructureImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.components.INakedConnectorEnd;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.emulated.AbstractPropertyBridge;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.mapping.internal.MappingInfoImpl;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.nakeduml.name.NameConverter;

public class ArtificialProperty extends AbstractPropertyBridge{
	private static final long serialVersionUID = 1L;
	private INakedClassifier baseType;
	private NakedMultiplicityImpl multiplicity;
	private String name;
	private boolean isOrdered;
	private IClassifier type;
	private INakedProperty otherEnd;
	private boolean isInverse;
	private IOclLibrary lib;
	private MappingInfoImpl mappingInfo;
	public ArtificialProperty(INakedClassifier type,IOclLibrary lib){
		this(type.getNestingClassifier(), type);
		initialiseNestedClasifier(type, lib);
	}
	private void initialiseNestedClasifier(INakedClassifier type,IOclLibrary lib){
		this.lib = lib;
		this.isInverse = true;
		setNameAndMappingInfo(type);
		this.isOrdered = false;
		this.baseType = type;
		this.multiplicity = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
		this.type = lib.lookupCollectionType(CollectionMetaType.SET, type);
	}
	private void setNameAndMappingInfo(INakedClassifier type){
		this.mappingInfo = (MappingInfoImpl) type.getMappingInfo().getCopy();
		this.name = NameConverter.decapitalize(type.getName());
		this.mappingInfo.setJavaNameString(this.name);
		this.mappingInfo.setSqlNameString(NameConverter.toUnderscoreStyle(name));
	}
	public MappingInfoImpl getMappingInfo(){
		return mappingInfo;
	}
	public ArtificialProperty(INakedBehavior behavior,IOclLibrary lib){
		this(behavior.getContext(), behavior);
		initializeOwnedBehavior(behavior, lib);
	}
	private void initializeOwnedBehavior(INakedBehavior behavior,IOclLibrary lib){
		this.lib = lib;
		if(behavior == behavior.getContext().getClassifierBehavior()){
			this.multiplicity = new NakedMultiplicityImpl(1, 1);
			this.isInverse = true;
			this.type = baseType;
			this.name = "classifierBehavior";
			this.mappingInfo = (MappingInfoImpl) behavior.getMappingInfo().getCopy();
			this.mappingInfo.setJavaNameString(name);
			this.mappingInfo.setSqlNameString(NameConverter.toUnderscoreStyle(name));
		}else{
			initSequence(behavior, lib);
		}
	}
	public ArtificialProperty(MessageStructureImpl msg,IOclLibrary lib){
		super(getOwner(msg), msg);
		initializeMessageStructure(msg, lib);
	}
	private void initializeMessageStructure(MessageStructureImpl msg,IOclLibrary lib){
		this.lib = lib;
		initSequence(msg, lib);
	}
	private ArtificialProperty(ArtificialProperty otherEnd,String name,IOclLibrary lib){
		super(otherEnd.getBaseType(), otherEnd.getOwner());
		this.otherEnd = otherEnd;
		this.isInverse = false;
		this.lib = lib;
		this.multiplicity = new NakedMultiplicityImpl(1, 1);
		this.name = name;
		this.mappingInfo = new MappingInfoImpl();
		this.type = otherEnd.getOwner();
		this.baseType = otherEnd.getOwner();
		SingularNameWrapper nameWrapper = new SingularNameWrapper(name, null);
		mappingInfo.setSqlNameString(nameWrapper.getUnderscored() + "_id");
		mappingInfo.setJavaNameString(name);
	}
	private ArtificialProperty(INakedClassifier nestingClassifier,INakedClassifier type2){
		super(nestingClassifier, type2);
		this.baseType = type2;
	}
	private ArtificialProperty(ArtificialProperty otherEnd,IOclLibrary lib){
		super(otherEnd.getBaseType(), otherEnd.getOwner());
		this.otherEnd = otherEnd;
		if(otherEnd.getOwner() instanceof MessageStructureImpl){
			initializeMessageStructure((MessageStructureImpl) otherEnd.getOwner(), lib);
		}else if(otherEnd.getOwner() instanceof INakedBehavior){
			initializeOwnedBehavior((INakedBehavior) otherEnd.getOwner(), lib);
		}else{
			initialiseNestedClasifier(otherEnd.getOwner(), lib);
		}
	}
	public ArtificialProperty(INakedProperty opposite,IOclLibrary oclLibrary){
		super(opposite.getNakedBaseType(), opposite.getOwner());
		this.otherEnd=opposite;
		setNameAndMappingInfo(opposite.getOwner());
		this.multiplicity = new NakedMultiplicityImpl(1, 1);
		this.baseType = opposite.getOwner();
		this.type=this.baseType;
		this.isInverse=false;
		
	}
	private void initSequence(INakedClassifier behavior,IOclLibrary lib){
		this.multiplicity = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
		this.baseType = behavior;
		setNameAndMappingInfo(behavior);
		this.isOrdered = true;
		this.type = lib.lookupCollectionType(CollectionMetaType.SEQUENCE, baseType);
		this.isInverse = true;
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
	public IClassifier getType(){
		return type;
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
			if(baseType instanceof IParameterOwner && ((IParameterOwner) baseType).getContext() == owner){
				otherEnd = new ArtificialProperty(this, "contextObject", lib);
			}else if(baseType instanceof EmbeddedSingleScreenTaskMessageStructureImpl){
				otherEnd = new ArtificialProperty(this, "processObject", lib);
			}else if(baseType instanceof INakedClassifier && baseType.getNestingClassifier() == owner){
				otherEnd = new ArtificialProperty(this, "ownerObject", lib);
			}else{
				otherEnd = new ArtificialProperty(this, lib);
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
			return ap.element.equals(element) && ap.owner.equals(owner) && ap.name.equals(name);
		}else{
			return super.equals(other);
		}
	}
}
