package org.opaeum.metamodel.workspace;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.opaeum.eclipse.emulated.AbstractEmulatedMessageType;
import org.opaeum.eclipse.emulated.IEmulatedPropertyHolder;

public interface IPropertyEmulation{
	DataType getDateTimeType();
	DataType getDurationType();
	IEmulatedPropertyHolder getEmulatedPropertyHolder(Classifier bc);
	Classifier getMessageStructure(Namespace operation);
	Classifier getMessageStructure(OpaqueAction n);
}
