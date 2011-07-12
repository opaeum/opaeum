package org.nakeduml.environment;

import org.nakeduml.runtime.domain.IEnum;

public interface EnumResolver{
	int toNakedUmlId(IEnum e);
	IEnum fromNakedUmlId(int i);
}

