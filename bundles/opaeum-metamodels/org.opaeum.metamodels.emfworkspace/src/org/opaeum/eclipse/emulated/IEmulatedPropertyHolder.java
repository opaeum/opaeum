package org.opaeum.eclipse.emulated;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.opaeum.ocl.uml.OclQueryContext;

public interface IEmulatedPropertyHolder{
	Property getEmulatedAttribute(Element originalElement);
	EList<AbstractEmulatedProperty> getEmulatedAttributes();
	void addEmulatedAttribute(AbstractEmulatedProperty otherEnd);
	void putQuery(Element e,OclQueryContext ctx);
	Collection<OclQueryContext> getQueries();
}
