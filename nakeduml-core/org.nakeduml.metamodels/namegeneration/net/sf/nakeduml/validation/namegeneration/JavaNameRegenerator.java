package net.sf.nakeduml.validation.namegeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = NameGenerationPhase.class,requires = {
	UmlNameRegenerator.class
},after = {
	UmlNameRegenerator.class
})
public class JavaNameRegenerator extends AbstractJavaNameGenerator{
	@VisitBefore(matchSubclasses = true)
	public void updateJavaName(INakedModelWorkspace nakedElement){
		nakedElement.getMappingInfo().setJavaName(new SingularNameWrapper(nakedElement.getName(),null).getCapped());
		nakedElement.getMappingInfo().setQualifiedJavaName(super.config.getMavenGroupId());

	}
	@VisitBefore(matchSubclasses = true)
	public void updateJavaName(INakedElement nakedElement){
		nakedElement.getMappingInfo().setJavaName(generateJavaName(nakedElement));
		nakedElement.getMappingInfo().setQualifiedJavaName(generateQualifiedJavaName(nakedElement));
		if(nakedElement.getMappingInfo().requiresJavaRename()){
			getAffectedElements().add(nakedElement);
		}
	}
}
