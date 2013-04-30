package org.opaeum.java.metamodel;

import org.opaeum.java.metamodel.generated.OJVisibilityKindGEN;

public class OJVisibilityKind extends OJVisibilityKindGEN implements Comparable<OJVisibilityKind>{

	public OJVisibilityKind(int value, String name) {
		super(value, name);
	}

	@Override
	public int compareTo(OJVisibilityKind o) {
		return this.getValue()-o.getValue();
	}

}