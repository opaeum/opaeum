package net.sf.nakeduml.metamodel.bpm;

import java.util.Collection;

import org.nakeduml.runtime.domain.TaskDelegation;

import net.sf.nakeduml.metamodel.actions.ITargetElement;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;

public interface INakedResponsibilityDefinition extends INakedElement{
	/**
	 * After creating a task, an assignment will be created for each AbstractUser in this expression 
	 */
	INakedValueSpecification getPotentialOwners();
	/**
	 * When creating a task this expression will populate the lookup for the business administrator
	 * @return
	 */
	INakedValueSpecification getPotentialBusinessAdministrators();
	/**
	 * When creating a task this expression will populate the lookup for the stakeholders
	 * @return
	 */
	INakedValueSpecification getPotentialStakeholders();
	
	Collection<INakedDeadline> getDeadlines();
	TaskDelegation getDelegation();
	INakedClassifier getExpressionContext();
}
