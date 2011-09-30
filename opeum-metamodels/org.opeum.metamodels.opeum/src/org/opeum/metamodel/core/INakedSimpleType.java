package org.opeum.metamodel.core;

import org.opeum.metamodel.workspace.AbstractStrategyFactory;


/**
 * A INakedSimpleType has a slightly wider meaning than the standard Ocl/Octopus
 * primitives. For that reason, it does not extend the IPrimitive interface in
 * Octopus. A INakedSimpleType is basically defined as any classifier that: <BR>
 * 1. Can be represented as a single HibernateType <BR>
 * 2. Typically maps to one column in the database - maybe more but preferrably
 * not<BR>
 * 3. Can be manipulated with a single input in JSF INakedSimpleDataTypes are
 * configured in a UML tool making extensive use of tagged values. They are very
 * useful for automated data generation and DDL generation.<BR>

 */
public interface INakedSimpleType extends INakedDataType {
	String META_CLASS = "dataType";

	public <T> T getStrategy(Class<T> c);
	public void setStrategyFactory(AbstractStrategyFactory strategies);
	public boolean hasStrategy(Class<?> class1);
}
