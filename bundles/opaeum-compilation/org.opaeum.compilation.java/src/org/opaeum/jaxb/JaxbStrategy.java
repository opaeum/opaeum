package org.opaeum.jaxb;

import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;

public interface JaxbStrategy  extends ISimpleTypeStrategy{
	public void annotatedMethod(OJAnnotatedOperation oper);
}
