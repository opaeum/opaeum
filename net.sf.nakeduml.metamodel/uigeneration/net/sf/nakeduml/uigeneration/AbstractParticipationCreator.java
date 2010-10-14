package net.sf.nakeduml.uigeneration;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.userinteractionmetamodel.AbstractUserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

public abstract class AbstractParticipationCreator extends GeneratingUserInteractionTransformationStep{
	public AbstractParticipationCreator(){
		super();
	}



	protected ClassifierUserInteraction findUserinteraction(INakedTypedElement typedElement,NakedStructuralFeatureMap map, INakedInstanceSpecification resultingUserInteraction){
		ClassifierUserInteraction rui = null;
		AbstractUserInteractionFolder baseTypeFolder = findFolderFor(typedElement.getNakedBaseType());
		if(resultingUserInteraction == null){
			if(map.isOne()){
				rui = (ClassifierUserInteraction) baseTypeFolder.findOwnedElement(getUserInteractionFQNameOf(typedElement.getNakedBaseType(),
						UserInteractionKind.EDIT));
			}else{
				rui = (ClassifierUserInteraction) baseTypeFolder.findOwnedElement(getUserInteractionFQNameOf(typedElement.getNakedBaseType(),
						UserInteractionKind.LIST));
			}
		}else{
			rui = (ClassifierUserInteraction) baseTypeFolder.findOwnedElement(getUserInteractionFQNameOf(resultingUserInteraction));
		}
		return rui;
	}
}