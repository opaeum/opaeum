package org.opeum.validation.namegeneration;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.name.NameWrapper;

/**
 * Generates persistent names for elements if they are new and do not have persistent names associated with them yet
 * 
 * @author abarnard
 * 
 */
@StepDependency(phase = NameGenerationPhase.class,requires = {
	UmlNameRegenerator.class
},after = {
	UmlNameRegenerator.class
})
public class PersistentNameGenerator extends AbstractPersistentNameGenerator{
	@VisitBefore(matchSubclasses = true)
	public void updateSqlNameIfNew(INakedElement me){
		if(!me.getMappingInfo().hasPersistentName() || getTaggedValue(me, "persistentName", "persistenceType") != null){
			NameWrapper pname = generateSqlName(me);
			me.getMappingInfo().setPersistentName(pname);
		}
	}
	protected boolean shouldVisitRecursively(INakedElementOwner o){
		return true;
	}
}
