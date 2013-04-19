package org.opaeum.javageneration.maps;

import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.java.metamodel.OJPathName;

public interface ExceptionRaisingMap{
	public abstract String exceptionOperName(NamedElement e);
	public abstract String unhandledExceptionOperName();
	public abstract OJPathName messageStructurePath();
}