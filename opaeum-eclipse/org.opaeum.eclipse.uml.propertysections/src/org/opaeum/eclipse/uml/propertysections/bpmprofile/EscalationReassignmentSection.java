package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import org.opaeum.metamodel.core.internal.TagNames;

public class EscalationReassignmentSection extends AbstractArtificialOpaqueExpressionSection{
	@Override
	protected String getExpressionName(){
		return TagNames.REASSIGNMENT;
	}
}
