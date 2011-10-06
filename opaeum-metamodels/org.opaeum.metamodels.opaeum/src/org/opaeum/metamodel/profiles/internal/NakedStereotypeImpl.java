package org.opaeum.metamodel.profiles.internal;
import org.opaeum.metamodel.core.internal.NakedClassifierImpl;
import org.opaeum.metamodel.profiles.INakedStereotype;
public class NakedStereotypeImpl extends NakedClassifierImpl implements INakedStereotype {
	private static final long serialVersionUID = 1578420288235712431L;
	@Override
	public String getMetaClass() {
		return "stereotype";
	}
}
