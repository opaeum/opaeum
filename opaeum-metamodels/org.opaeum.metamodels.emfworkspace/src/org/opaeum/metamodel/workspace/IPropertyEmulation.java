package org.opaeum.metamodel.workspace;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.opaeum.eclipse.emulated.IEmulatedPropertyHolder;
import org.opaeum.ocl.uml.OpaqueExpressionContext;

public interface IPropertyEmulation{
	DataType getDateTimeType();
	DataType getDurationType();
	IEmulatedPropertyHolder getEmulatedPropertyHolder(Classifier bc);
	Classifier getMessageStructure(Namespace operation);
	Classifier getMessageStructure(OpaqueAction n);
	OpaqueExpressionContext getOclExpressionContext(OpaqueExpression valueSpec);
}
