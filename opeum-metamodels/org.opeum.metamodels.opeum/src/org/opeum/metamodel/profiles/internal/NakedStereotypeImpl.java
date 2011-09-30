package org.opeum.metamodel.profiles.internal;
import org.opeum.metamodel.core.internal.NakedClassifierImpl;
import org.opeum.metamodel.profiles.INakedStereotype;
public class NakedStereotypeImpl extends NakedClassifierImpl implements INakedStereotype {
	private static final long serialVersionUID = 1578420288235712431L;
	@Override
	public String getMetaClass() {
		return "stereotype";
	}
}
