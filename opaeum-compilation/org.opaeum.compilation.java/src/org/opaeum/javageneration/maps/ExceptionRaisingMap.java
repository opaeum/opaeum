package org.opaeum.javageneration.maps;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.metamodel.core.INakedElement;

public interface ExceptionRaisingMap{
	public abstract String exceptionOperName(INakedElement e);
	public abstract String unhandledExceptionOperName();
	public abstract OJPathName messageStructurePath();
}