package net.sf.nakeduml.metamodel.core.internal.emulated;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedProperty;

public abstract class EmulatedCompositionMessageStructure extends MessageStructureImpl implements ICompositionParticipant{
	protected List<INakedProperty> attributes;


	@Override
	public List<? extends INakedConstraint> getOwnedRules(){
		return Collections.emptyList();
	}
	private INakedProperty endToComposite;
	public EmulatedCompositionMessageStructure(INakedClassifier owner,INakedElement element){
		super(owner, element);
	}
	@Override
	public boolean hasComposite(){
		return true;
	}
	@Override
	public INakedProperty getEndToComposite(){
		return this.endToComposite;
	}
	@Override
	public void setEndToComposite(INakedProperty artificialProperty){
		this.endToComposite = artificialProperty;
	}
	public void reinitialize(){
		this.attributes=null;
	}
	@Override
	public Collection<? extends INakedElement> getOwnedElements(){
		HashSet<INakedElement> hashSet = new HashSet<INakedElement>( super.getOwnedElements());
		hashSet.addAll(getOwnedAttributes());
		hashSet.addAll(getNakedGeneralizations());
		hashSet.addAll(getInterfaceRealizations());
		return hashSet;
	}
	
}