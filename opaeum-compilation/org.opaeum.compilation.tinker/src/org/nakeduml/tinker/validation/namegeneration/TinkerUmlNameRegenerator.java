package org.nakeduml.tinker.validation.namegeneration;

import org.opaeum.feature.StepDependency;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.name.NameConverter;
import org.opaeum.validation.namegeneration.NameGenerationPhase;
import org.opaeum.validation.namegeneration.UmlNameRegenerator;

@StepDependency(phase = NameGenerationPhase.class, replaces=UmlNameRegenerator.class)
public class TinkerUmlNameRegenerator extends UmlNameRegenerator {

	protected String generateTypedElementName(String in,INakedTypedElement te){
		String name = in;
		if(te instanceof INakedPin){
			INakedPin node = (INakedPin) te;
			if(name == null){
				if(node.getNakedBaseType() == null){
					// Value pins can have null baseTypes
					name = "anonymousPin";
				}else{
					// Generate a unique name
					name = NameConverter.decapitalize(node.getNakedBaseType().getName() + node.getMappingInfo().getOpaeumId());
				}
			}
		}else{
			if(name == null || (te.getNakedBaseType() != null && te.getName().equals(te.getNakedBaseType().getName()))){
				// USe the type's name
				name = NameConverter.decapitalize(te.getNakedBaseType().getName());
			}
		}
		return name;
	}

	
}
