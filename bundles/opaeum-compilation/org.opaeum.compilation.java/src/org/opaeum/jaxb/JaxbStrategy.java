package org.opaeum.jaxb;

import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;

public interface JaxbStrategy  extends ISimpleTypeStrategy{
	public void annotatedField(OJAnnotatedField oper);
}
