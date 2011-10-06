package org.opaeum.validation.namegeneration;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.name.SingularNameWrapper;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

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
