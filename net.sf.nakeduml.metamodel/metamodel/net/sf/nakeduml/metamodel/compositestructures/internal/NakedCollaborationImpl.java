package net.sf.nakeduml.metamodel.compositestructures.internal;

import net.sf.nakeduml.metamodel.compositestructures.INakedCollaboration;
import net.sf.nakeduml.metamodel.core.internal.NakedClassifierImpl;

public class NakedCollaborationImpl extends NakedClassifierImpl implements INakedCollaboration{

	private static final long serialVersionUID = 4898627188919284719L;


	@Override
	public String getMetaClass() {
		return "collaboration";
	}

}
