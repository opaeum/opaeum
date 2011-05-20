package net.sf.nakeduml.javageneration;

import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedMultiplicity;
import net.sf.nakeduml.metamodel.core.internal.NakedMultiplicityImpl;
import net.sf.nakeduml.metamodel.core.internal.emulated.AbstractPropertyBridge;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

public class ArtificialProperty extends AbstractPropertyBridge{
	private INakedClassifier baseType;
	private NakedMultiplicityImpl multiplicity;
	private String name;
	private boolean isOrdered;
	private IClassifier type;
	public ArtificialProperty(INakedClassifier owner,INakedClassifier type, String name, IOclLibrary lib, boolean isOrdered){
		super(owner, type);
		this.baseType=type;
		this.multiplicity=new NakedMultiplicityImpl(0, Integer.MAX_VALUE);
		this.isOrdered=isOrdered;
		this.name=name;
		if(isOrdered){
			this.type=lib.lookupCollectionType(CollectionMetaType.SEQUENCE, type);
		}else{
			this.type=lib.lookupCollectionType(CollectionMetaType.SET, type);
		}
	}
	public ArtificialProperty(INakedClassifier owner,INakedClassifier type, String name){
		super(owner, type);
		this.baseType=type;
		this.multiplicity=new NakedMultiplicityImpl(0, 1);
		this.isOrdered=false;
		this.name=name;
		this.type=type;
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
}
