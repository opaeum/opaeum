package net.sf.nakeduml.validation.namegeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedElement;

@StepDependency(phase = NameGenerationPhase.class,requires = {UmlNameRegenerator.class},after = {UmlNameRegenerator.class})
public class JavaNameGenerator extends AbstractJavaNameGenerator{
	@VisitBefore(matchSubclasses = true)
	public void updateJavaNameIfNew(INakedElement nakedElement){
		if(!nakedElement.getMappingInfo().hasJavaName()){
			nakedElement.getMappingInfo().setJavaName(generateJavaName(nakedElement));
			nakedElement.getMappingInfo().setQualifiedJavaName(generateQualifiedJavaName(nakedElement));
		}
	}
}
