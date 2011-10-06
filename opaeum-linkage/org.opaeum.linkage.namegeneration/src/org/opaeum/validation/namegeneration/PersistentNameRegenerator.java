package org.opaeum.validation.namegeneration;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.name.NameWrapper;

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
	public void regenerateName(INakedElement nakedElement) {
		NameWrapper pname = generateSqlName(nakedElement);
		nakedElement.getMappingInfo().setPersistentName(pname);
		if(nakedElement.getMappingInfo().requiresPersistentRename()){
			getAffectedElements().add(nakedElement);
		}
	}
}
