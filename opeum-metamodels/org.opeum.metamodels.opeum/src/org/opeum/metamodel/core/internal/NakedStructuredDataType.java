package org.opeum.metamodel.core.internal;

import org.opeum.metamodel.core.INakedStructuredDataType;

public class NakedStructuredDataType extends NakedClassifierImpl implements INakedStructuredDataType{
	private static final long serialVersionUID = 7015789903579595473L;
	public static final String META_CLASS = "structuredDataType";
	@Override
	public String getMetaClass(){
		return META_CLASS;
	}
	@Override
	public boolean isPersistent(){
		return true;
	}
}