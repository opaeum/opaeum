package net.sf.nakeduml.validation.namegeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedElement;

/**
 * Regenerates the persistent names of elements irrespective of version or
 * revision. Use with care. Discards previous persistent names
 * 
 * @author abarnard
 * 
 */
@StepDependency(phase = NameGenerationPhase.class, requires = { UmlNameRegenerator.class }, after = { UmlNameRegenerator.class })
public class PersistentNameRegenerator extends AbstractPersistentNameGenerator {
	@VisitBefore(matchSubclasses = true)
	public void regenerateName(INakedElement e) {
		e.getMappingInfo().setPersistentName(generateSqlName(e));
	}
}
