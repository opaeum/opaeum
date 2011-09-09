package org.nakeduml.runtime.domain;


public interface EnumResolver{
	int toNakedUmlId(IEnum e);
	IEnum fromNakedUmlId(int i);
}

