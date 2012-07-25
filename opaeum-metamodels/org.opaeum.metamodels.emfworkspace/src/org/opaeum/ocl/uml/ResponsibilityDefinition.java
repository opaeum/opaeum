package org.opaeum.ocl.uml;

import java.util.Collection;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.opaeum.runtime.domain.TaskDelegation;

public interface ResponsibilityDefinition{
	/**
	 * After creating a task, an assignment will be created for each AbstractUser in this expression 
	 */
	OpaqueExpression getPotentialOwners();
	/**
	 * When creating a task this expression will populate the lookup for the business administrator
	 * @return
	 */
	OpaqueExpression getPotentialBusinessAdministrators();
	/**
	 * When creating a task this expression will populate the lookup for the stakeholders
	 * @return
	 */
	OpaqueExpression getPotentialStakeholders();
	
	Collection<Deadline> getDeadlines();
	TaskDelegation getDelegation();
	Classifier getExpressionContext();
}
