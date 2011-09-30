package org.opeum.metamodel.core.internal;

import org.opeum.metamodel.core.INakedValueType;

/**
 * Martin Fowler's "valueType" concept. It is a simple data type, i.e. it is not structured and can therefor not structurally be broken down
 * into smaller bits (attributes). It is represented as one column in the database and as one field on a form. Could have any operations and
 * derived properties, but its actual data is represented as one single unit
 */
public class NakedValueTypeImpl extends NakedSimpleDataTypeImpl implements INakedValueType{
	private static final long serialVersionUID = -7145152831022832129L;
	public String getMetaClass(){
		return INakedValueType.META_CLASS;
	}
}
