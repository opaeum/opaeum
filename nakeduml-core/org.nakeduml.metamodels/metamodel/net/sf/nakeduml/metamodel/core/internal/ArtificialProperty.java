package net.sf.nakeduml.metamodel.core.internal;

import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.metamodel.bpm.internal.EmbeddedSingleScreenTaskMessageStructureImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.components.INakedConnectorEnd;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.emulated.AbstractPropertyBridge;
import net.sf.nakeduml.metamodel.core.internal.emulated.MessageStructureImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import net.sf.nakeduml.metamodel.mapping.internal.MappingInfoImpl;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

public class ArtificialProperty extends AbstractPropertyBridge{
	private INakedClassifier baseType;
	private NakedMultiplicityImpl multiplicity;
	private String name;
	private boolean isOrdered;
	private IClassifier type;
	private ArtificialProperty otherEnd;
	private IMappingInfo mappingInfo;
	private boolean isInverse;
	private IOclLibrary lib;
	public ArtificialProperty(INakedClassifier type,IOclLibrary lib){
		this(type.getNestingClassifier(), type);
		this.isInverse = true;
		this.mappingInfo = type.getMappingInfo().getCopy();
		this.name = type.getMappingInfo().getJavaName().getDecapped().getAsIs();
		this.isOrdered = false;
		this.baseType = type;
		this.multiplicity = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
		this.type = lib.lookupCollectionType(CollectionMetaType.SET, type);
	}
	public ArtificialProperty(INakedBehavior behavior,IOclLibrary lib){
		this(behavior.getContext(), behavior);
		if(behavior == behavior.getContext().getClassifierBehavior()){
			this.multiplicity = new NakedMultiplicityImpl(1, 1);
			this.isInverse = true;
			type=baseType;
		}else{
			this.multiplicity = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
			initSequence(behavior, lib);
		}
	}
	public ArtificialProperty(MessageStructureImpl msg,IOclLibrary lib){
		super(getOwner(msg), msg);
		this.multiplicity = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
		initSequence(msg, lib);
	}
	/**
	 * Constructor for the endToComposite
	 * @param owner
	 * @param type
	 * @param name
	 * @param lib
	 */
	public ArtificialProperty(INakedClassifier owner,INakedClassifier type,String name,IOclLibrary lib){
		super(owner, type);
		this.isInverse = false;
		this.lib = lib;
		this.multiplicity = new NakedMultiplicityImpl(1, 1);
		this.name = name;
		MappingInfoImpl mi = new MappingInfoImpl();
		this.mappingInfo = mi;
		SingularNameWrapper nameWrapper = new SingularNameWrapper(name, null);
		mi.setSqlNameString(nameWrapper.getUnderscored() + "_id");
		mi.setJavaNameString(name);
	}
	private ArtificialProperty(INakedClassifier nestingClassifier,INakedClassifier type2){
		super(nestingClassifier,type2);
		this.baseType = type2;
		this.mappingInfo = type2.getMappingInfo().getCopy();
	}
	private void initSequence(INakedClassifier behavior,IOclLibrary lib){
		this.name = behavior.getMappingInfo().getJavaName().getDecapped().getAsIs();
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
	public ArtificialProperty getOtherEnd(){
		if(otherEnd == null){
			if(baseType instanceof IParameterOwner){
				otherEnd = new ArtificialProperty(baseType, owner, "contextObject", lib);
			}else if(baseType instanceof INakedClassifier && baseType.getNestingClassifier() == owner){
				otherEnd = new ArtificialProperty(baseType, owner, "owningObject", lib);
			}else if(baseType instanceof EmbeddedSingleScreenTaskMessageStructureImpl){
				otherEnd = new ArtificialProperty(baseType, owner, "processObject", lib);
			}else if(owner instanceof MessageStructureImpl){
				otherEnd = new ArtificialProperty((MessageStructureImpl)baseType, lib);
			}else if(owner instanceof INakedBehavior){
				otherEnd = new ArtificialProperty((INakedBehavior)baseType, lib);
			}else{
				otherEnd = new ArtificialProperty(baseType, lib);
			}
		}
		return otherEnd;
	}
	public IMappingInfo getMappingInfo(){
		return mappingInfo;
	}
}
