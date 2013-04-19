package org.opaeum.metamodel.profiles.internal;
import org.eclipse.uml2.uml.INakedStereotype;
import org.opaeum.metamodel.core.internal.NakedClassifierImpl;
public class NakedStereotypeImpl extends NakedClassifierImpl implements INakedStereotype {
	private static final long serialVersionUID = 1578420288235712431L;
	@Override
	public String getMetaClass() {
		return "stereotype";
	}
}
