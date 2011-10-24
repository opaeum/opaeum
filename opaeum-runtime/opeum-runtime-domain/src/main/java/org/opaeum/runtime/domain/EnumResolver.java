package org.opaeum.runtime.domain;


public interface EnumResolver{
	int toOpaeumId(IEnum e);
	IEnum fromOpaeumId(int i);
}

