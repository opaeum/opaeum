package org.opaeum.runtime.domain;


public interface EnumResolver{
	long toOpaeumId(IEnum e);
	IEnum fromOpaeumId(long i);
}

