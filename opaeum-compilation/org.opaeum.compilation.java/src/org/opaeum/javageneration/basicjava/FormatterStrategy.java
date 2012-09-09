package org.opaeum.javageneration.basicjava;

import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory.ISimpleTypeStrategy;

public interface FormatterStrategy extends ISimpleTypeStrategy{
	public void implementParse(OJAnnotatedOperation parse);
	public void implementFormat(OJAnnotatedOperation format);
}
