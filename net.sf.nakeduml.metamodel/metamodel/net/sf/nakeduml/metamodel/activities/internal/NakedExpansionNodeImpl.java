package net.sf.nakeduml.metamodel.activities.internal;

import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedExpansionRegion;

public class NakedExpansionNodeImpl extends NakedObjectNodeImpl implements INakedExpansionNode {
	@Override
	public INakedExpansionRegion getExpansionRegion() {
		return (INakedExpansionRegion) getOwnerElement();
	}
}
