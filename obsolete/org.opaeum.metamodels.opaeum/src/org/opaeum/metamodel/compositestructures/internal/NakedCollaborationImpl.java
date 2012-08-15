package org.opaeum.metamodel.compositestructures.internal;

import org.eclipse.uml2.uml.INakedCollaboration;
import org.opaeum.metamodel.core.internal.NakedClassifierImpl;

public class NakedCollaborationImpl extends NakedClassifierImpl implements INakedCollaboration{

	private static final long serialVersionUID = 4898627188919284719L;


	@Override
	public String getMetaClass() {
		return "collaboration";
	}

}
