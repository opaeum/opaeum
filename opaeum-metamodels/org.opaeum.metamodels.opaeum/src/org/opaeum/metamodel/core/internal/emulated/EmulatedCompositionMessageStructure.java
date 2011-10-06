package org.opaeum.metamodel.core.internal.emulated;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.opaeum.feature.MappingInfo;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedConstraint;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedProperty;

public abstract class EmulatedCompositionMessageStructure extends MessageStructureImpl implements ICompositionParticipant{
	private static final long serialVersionUID = -3198245957575601442L;
	protected List<INakedProperty> attributes;
	private MappingInfo mappingInfo;
	private String implementationCode;

	@Override
	public List<? extends INakedConstraint> getOwnedRules(){
		return Collections.emptyList();
	}
	private INakedProperty endToComposite;
	private String id;
	public EmulatedCompositionMessageStructure(INakedClassifier owner,INakedElement element){
		super(owner, element);
		this.mappingInfo=element.getMappingInfo().getCopy();
		this.id=element.getId() + getClass().getSimpleName();
		this.mappingInfo.setIdInModel(id);
	}
	@Override
	public String getId(){
		return id;
	}
	@Override
	public MappingInfo getMappingInfo(){
		return mappingInfo;
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
		if(endToComposite!=null){
			hashSet.add(endToComposite);
		}
		return hashSet;
	}
	@Override
	public void removeObsoleteArtificialProperties(){
	}
	public String getImplementationCode(){
		return implementationCode;
	}
	public void setImplementationCode(String implementationCode){
		this.implementationCode = implementationCode;
	}
	
}