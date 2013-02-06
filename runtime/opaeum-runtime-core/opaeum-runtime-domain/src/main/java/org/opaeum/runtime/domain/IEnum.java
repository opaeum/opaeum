package org.opaeum.runtime.domain;

import java.io.Serializable;

public interface IEnum extends Serializable{
	String getUuid();
	long getOpaeumId();
}