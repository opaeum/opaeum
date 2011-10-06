package org.opaeum.validation.namegeneration;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.name.NameWrapper;

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
