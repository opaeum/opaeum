package org.opaeum.metamodel.actions;

import org.opaeum.metamodel.activities.INakedAction;
import org.opaeum.metamodel.core.INakedAssociation;

public interface INakedLinkAction extends INakedAction{
	INakedAssociation getAssociation();
	
}
