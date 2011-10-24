package org.opaeum.validation.namegeneration;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedProperty;
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
		// Some extra work required to ensure that a class and all its required elements hav persistent names for JPA compilation to work
		//Primarily for derived elements
		if(me instanceof INakedProperty){
			INakedProperty p = (INakedProperty) me;
			if(p.getOtherEnd() != null && !p.getOtherEnd().getMappingInfo().hasPersistentName()){
				updateSqlNameIfNew(p.getOtherEnd());
			}
			if(p.getAssociation() != null && !((INakedAssociation) p.getAssociation()).getMappingInfo().hasPersistentName()){
				updateSqlNameIfNew((INakedElement) p.getAssociation());
			}
		}
	}
	protected boolean shouldVisitRecursively(INakedElementOwner o){
		return true;
	}
}
