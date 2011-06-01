package net.sf.nakeduml.javageneration;

import java.util.Collection;
import java.util.Collections;

import net.sf.nakeduml.metamodel.components.INakedConnectorEnd;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.AbstractPropertyBridge;
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
	public ArtificialProperty(IParameterOwner behavior,IOclLibrary lib){
		super(behavior.getContext(), behavior);
		this.name = behavior.getMappingInfo().getJavaName().getDecapped().getAsIs();
		this.mappingInfo = behavior.getMappingInfo().getCopy();
		this.isOrdered = true;
		this.type = lib.lookupCollectionType(CollectionMetaType.SEQUENCE, type);
		this.isInverse=true;
		if(behavior==behavior.getContext().getClassifierBehavior()){
			this.multiplicity = new NakedMultiplicityImpl(1,1);
		}else{
			this.multiplicity = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);

		}
		this.baseType = (INakedClassifier) (behavior instanceof INakedClassifier ? behavior : new OperationMessageStructureImpl((INakedOperation) behavior));
		this.otherEnd = new ArtificialProperty(baseType, behavior.getContext(), "contextObject");
	}
	public ArtificialProperty(INakedClassifier type,IOclLibrary lib){
		super((INakedClassifier) type.getNameSpace(), type);
		this.isInverse=true;
		this.mappingInfo = type.getMappingInfo().getCopy();
		this.name = type.getMappingInfo().getJavaName().getDecapped().getAsIs();
		this.isOrdered = false;
		this.baseType = type;
		this.multiplicity = new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
		this.type = lib.lookupCollectionType(CollectionMetaType.SET, type);
	}
	private ArtificialProperty(INakedClassifier owner,INakedClassifier type,String name){
		super(owner, type);
		this.multiplicity = new NakedMultiplicityImpl(1, 1);
		this.name = name;
		MappingInfoImpl mi = new MappingInfoImpl();
		this.mappingInfo = mi;
		SingularNameWrapper nameWrapper = new SingularNameWrapper(name, null);
		mi.setSqlNameString(nameWrapper.getUnderscored() + "_id");
		mi.setJavaNameString(name);
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
		return otherEnd;
	}
	public IMappingInfo getMappingInfo(){
		return mappingInfo;
	}
}
