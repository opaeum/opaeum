package org.opaeum.metamodel.core.internal.emulated;

import org.eclipse.uml2.uml.ICompositionParticipant;
import org.eclipse.uml2.uml.INakedComplexStructure;
import org.eclipse.uml2.uml.INakedElementOwner;
import org.eclipse.uml2.uml.INakedRootObject;

public class NakedBusinessCollaboration extends EmulatedClassifier implements ICompositionParticipant,INakedComplexStructure{
	private static final long serialVersionUID = 1L;
	public NakedBusinessCollaboration(INakedRootObject o){
		super(o, o);
		mappingInfo.setIdInModel(getId() + "NakedBusinessCollaboration");
		id = mappingInfo.getIdInModel();
	}
	public boolean hasComposite(){
		return getEndToComposite()!=null;
	};
	public INakedElementOwner getOwnerElement(){
		return owner;
	}
}
