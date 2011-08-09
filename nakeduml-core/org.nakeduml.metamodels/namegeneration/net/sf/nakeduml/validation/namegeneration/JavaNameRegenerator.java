package net.sf.nakeduml.validation.namegeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;

@StepDependency(phase = NameGenerationPhase.class,requires = {
	UmlNameRegenerator.class
},after = {
	UmlNameRegenerator.class
})
public class JavaNameRegenerator extends AbstractJavaNameGenerator{
	@VisitBefore(matchSubclasses = true)
	public void updateJavaName(INakedElement nakedElement){
		nakedElement.getMappingInfo().setJavaName(generateJavaName(nakedElement));
		nakedElement.getMappingInfo().setQualifiedJavaName(generateQualifiedJavaName(nakedElement));
		if(nakedElement instanceof INakedOperation && ((INakedOperation) nakedElement).getMessageStructure() != null){
			updateMessageStructureJavaName(((INakedOperation) nakedElement).getMessageStructure());
		}else if(nakedElement instanceof INakedCallAction && ((INakedCallAction) nakedElement).getMessageStructure() != null){
			updateMessageStructureJavaName(((INakedCallAction) nakedElement).getMessageStructure());
		}else if(nakedElement instanceof INakedEmbeddedTask && ((INakedEmbeddedTask) nakedElement).getMessageStructure() != null){
			updateMessageStructureJavaName(((INakedEmbeddedTask) nakedElement).getMessageStructure());
		}
	}
	private  void updateMessageStructureJavaName(INakedMessageStructure msg){
		if(!(msg instanceof INakedBehavior)){
			this.updateJavaName((INakedElement)msg);
		}
	}
}
