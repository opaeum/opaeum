package net.sf.nakeduml.validation.namegeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedElement;

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
