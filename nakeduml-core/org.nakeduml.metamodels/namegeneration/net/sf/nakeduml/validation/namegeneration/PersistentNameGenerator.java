package net.sf.nakeduml.validation.namegeneration;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.name.NameWrapper;

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
