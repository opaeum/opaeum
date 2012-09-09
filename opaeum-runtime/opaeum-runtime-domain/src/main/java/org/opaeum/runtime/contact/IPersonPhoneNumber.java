package org.opaeum.runtime.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;

public interface IPersonPhoneNumber extends CompositionNode,IPersistentObject{
	void setPhoneNumber(String phoneNumber);
	String getPhoneNumber();
}
