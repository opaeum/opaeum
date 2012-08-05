package org.opaeum.eclipse.emulated;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;

public interface IEmulatedPropertyHolder{
	Property getEmulatedAttribute(Element originalElement);
	EList<AbstractEmulatedProperty> getEmulatedAttributes();
	void addEmulatedAttribute(NonInverseArtificialProperty otherEnd);
}
