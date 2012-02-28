package org.opaeum.metamodel.core.internal.emulated;

import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedRootObject;

public class NakedBusinessCollaboration extends EmulatedClassifier implements ICompositionParticipant,INakedComplexStructure{
	private static final long serialVersionUID = 1L;
	public NakedBusinessCollaboration(INakedRootObject o){
		super(o, o);
		mappingInfo.setIdInModel(getId() + "NakedBusinessCollaboration");
		id = mappingInfo.getIdInModel();
	}
	public boolean hasComposite(){
		return false;
	};
	public INakedElementOwner getOwnerElement(){
		return owner;
	}
}
