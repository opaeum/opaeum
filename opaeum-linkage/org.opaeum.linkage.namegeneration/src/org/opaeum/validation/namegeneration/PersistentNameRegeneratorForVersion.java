package org.opaeum.validation.namegeneration;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.INakedElement;

/**
 * 
 * Regenerats the persistent names for elements if they are new in the current
 * version. Use this to regenerate the database for this version, thus
 * discarding revisional changes
 * 
 * @author abarnard
 * 
 */
@StepDependency(phase = NameGenerationPhase.class, requires = { UmlNameRegenerator.class }, after = { UmlNameRegenerator.class })
public class PersistentNameRegeneratorForVersion extends AbstractPersistentNameGenerator {
	@VisitBefore(matchSubclasses = true)
	public void updateSqlNameIfNewInVersion(INakedElement me) {
		if (me.getMappingInfo().isNewInVersion() || getTaggedValue(me, "persistentName", "persistenceType") != null) {
			me.getMappingInfo().setPersistentName(generateSqlName(me));
		}
	}
}
