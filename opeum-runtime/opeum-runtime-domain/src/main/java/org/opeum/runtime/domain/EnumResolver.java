package org.opeum.runtime.domain;


public interface EnumResolver{
	int toOpeumId(IEnum e);
	IEnum fromOpeumId(int i);
}

